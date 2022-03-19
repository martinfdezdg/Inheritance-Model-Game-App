package commands;

import PlantsVsZombies.Game;

public class ResetCommand extends NoParamsCommand {
	private final static String commandID = "r";
	private final static String commandName = "reset";
	private final static String commandInfo = "[R]eset";
	private final static String helpInfo = "Starts a new game.";
	
	public ResetCommand() {
		super(commandID, commandName, commandInfo, helpInfo);
	}
	
	public boolean execute(Game game) {
		game.reset();
		return true;
	}
}
