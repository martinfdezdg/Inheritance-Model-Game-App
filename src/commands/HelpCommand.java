package commands;

import PlantsVsZombies.Game;

public class HelpCommand extends NoParamsCommand {
	private final static String commandID = "h";
	private final static String commandName = "help";
	private final static String commandInfo = "[H]elp";
	private final static String helpInfo = "Prints this help message.";
	
	public HelpCommand() {
		super(commandID, commandName, commandInfo, helpInfo);
	}
	
	public boolean execute(Game game) {
		System.out.println(CommandParser.commandHelp());
		return false;
	}
}
