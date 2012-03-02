package com.wimbli.serverevents;

import java.util.logging.Level;

import org.bukkit.conversations.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;


public class ServerEventsCommand implements CommandExecutor
{
	private ServerEvents plugin;
	private static ConversationFactory conversationFactory;
	private static Conversation conversation;

	public ServerEventsCommand(ServerEvents plugin)
	{
		this.plugin = plugin;
		this.conversationFactory = new ConversationFactory(plugin)
			.withFirstPrompt(new RegisterStartPrompt())
			.withEscapeSequence("/cancel")
			.withTimeout(300);
	}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split)
	{
		// reload command?
		if (split.length == 1 && split[0].equals("reload"))
		{
			if (!sender.hasPermission("serverevents.reload"))
			{
				sender.sendMessage("You do not have permission to reload the data for ServerEvents.");
				return true;
			}

			plugin.getLogger().log(Level.INFO, "Reloading ServerEvents data from server_events.yml at the command of \""+sender.getName()+"\".");
			if (sender instanceof Player)
				sender.sendMessage("Reloading ServerEvents data from server_events.yml.");

			ServerEvents.reloadData();
			return true;
		}

		// register command?
		if (split.length == 1 && split[0].equals("register"))
		{
			if (!sender.hasPermission("serverevents.register"))
			{
				sender.sendMessage("You do not have permission to register a Twitter account with ServerEvents.");
				return true;
			}

			if (sender instanceof Conversable)
			{
				conversation = conversationFactory.buildConversation((Conversable)sender);
				conversation.begin();
			}
			else
				sender.sendMessage("You are unable to receive conversations. Maybe your CraftBukkit is out of date?");

//			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new RegisterCommand(sender));

			return true;
		}

		// no recognized subcommand, then
		sender.sendMessage("Commands: \"serverevents reload\", \"serverevents register\".");
		return true;
	}


	private class RegisterStartPrompt extends MessagePrompt
	{
		public String getPromptText(ConversationContext context)
		{
			Register.register(context.getForWhom() instanceof Player);
			return "All information will also be output to your server log. You can cancel this process by entering \"/cancel\".";
		}

		@Override
		protected Prompt getNextPrompt(ConversationContext context)
		{
			return new RegisterPinPrompt();
		}
	}
	private class RegisterPinPrompt extends ValidatingPrompt
	{
		public String getPromptText(ConversationContext context)
		{
			return "Enter the PIN below after you have granted access. [PIN]:";
		}

		@Override
		protected boolean isInputValid(ConversationContext context, String input)
		{
			if (!pinLooksLegit(input)) return false;

			return Register.tryPin(input);
		}

		private boolean pinLooksLegit(String pin)
		{
			return (pin == null || pin.length() == 0 || pin.length() == 7);
		}

		@Override
		protected String getFailedValidationText(ConversationContext context, String invalidInput)
		{
			if (!pinLooksLegit(invalidInput))
				return "A valid PIN should be 7 digits, or otherwise left empty.";
			else
				return null;
		}

		@Override
		protected Prompt acceptValidatedInput(ConversationContext context, String string)
		{
			return Prompt.END_OF_CONVERSATION;
		}
	}

	public static void msg(String text)
	{
		if (conversation != null)
			conversation.getForWhom().sendRawMessage(text);
	}

	public static void endConversation()
	{
		if (conversation != null)
			conversation.abandon();
	}
/*
	// this is needed so the Register class doesn't have any direct reference to a CommandSender, otherwise executing the ServerEvents jar directly will fail
	public static class RegisterCommand implements Runnable
	{
		private CommandSender sender;

		public RegisterCommand(CommandSender sender)
		{
			this.sender = sender;
		}

		public void run()
		{
			Register.register(this);
		}

		public void msg(String text)
		{
			sender.sendMessage(text);
		}
	}*/
}