package org.bukkit.croemmich.serverevents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DataSourceDatabase extends DataSource {
	
	protected static final Logger log 		   = Logger.getLogger("Minecraft");
	String name = "ServerEvents";
	private static String table = "server_events";
	private static String username = "minecraft";
	private static String password = "";
	private static String database = "jdbc:mysql://localhost:3306/minecraft";
	private static String driver = "com.mysql.jdbc.Driver";
	
    private static String sqlMakeTable 		   = "CREATE TABLE IF NOT EXISTS `"+table+"` ("+
    												"`id` int(11) unsigned NOT NULL AUTO_INCREMENT," + 
    												"`msg` text NOT NULL," +
    												"`time` datetime NOT NULL,"+
    												"PRIMARY KEY (`id`))";
    private static String sqlCheckTableExist   = "SHOW TABLES LIKE '"+table+"'";
    private static String sqlInsert			   = "INSERT INTO `"+table+"` (`msg`, `time`) VALUES (?, NOW())";
	

	public DataSourceDatabase(String username, String password, String database, String table, String driver) {
		DataSourceDatabase.username = username;
		DataSourceDatabase.password = password;
		DataSourceDatabase.database = database;
		DataSourceDatabase.table = table;
		DataSourceDatabase.driver = driver;
		createTable();
	}

	@Override
	public void displayMessage(String msg) {
		execute(sqlInsert,msg);
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try{
			try {
				Class.forName(driver);
			} catch (Exception e) {}
			conn = DriverManager.getConnection(database + "?autoReconnect=true&user=" + username + "&password=" + password);
		} catch (Exception e) {
			log.severe(name + ": " + e.getMessage());
		}
		checkConnection(conn);
		return conn;
	}
	
	private boolean checkConnection (Connection conn) throws SQLException {
		if (conn == null) {
				log.severe("Could not connect to the database. Check your credentials in " + ServerEvents.configFile);
			throw new SQLException();
		}
		if (!conn.isValid(5)) {
        	log.severe("Could not connect to the database.");
        	throw new SQLException();
        }
		return true;
	}
	
	private boolean execute(String sql, String message) {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	conn = getConnection();
        	ps = conn.prepareStatement(sql);
        	if (message != null && !message.equalsIgnoreCase("")) {
        		ps.setString(1, message);
        	}
        	
        	if (ps.execute()) {
        		return true;
        	}
        } catch (SQLException ex) {
        	log.severe(name + ": " + ex.getMessage());
        	String msg = name + ": could not execute the sql \"" + sql + "\"";
        	if (message != null ) {
        		msg += "    ?=" +message;
        	}
        	log.severe(msg);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
            	log.severe(name + ": " + ex.getMessage());
            }
        }
        return false;
	}
	
	private boolean createTable() {
		Connection conn = null;
        Statement s = null;
        try {
        	conn = getConnection();
        	s = conn.createStatement();
        	s.executeUpdate(sqlMakeTable);
        	ResultSet rs = s.executeQuery(sqlCheckTableExist);
        	if (rs.first()) {
        		return true;
        	}
        } catch (SQLException ex) {
        	log.severe(name + ": " + ex.getMessage());
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
            	log.severe(name + ": " + ex.getMessage());
            }
        }
        return false;
	}
}