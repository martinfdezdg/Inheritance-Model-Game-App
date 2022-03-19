package commands;

import PlantsVsZombies.Game;
import exceptions.CommandExecuteException;

public class UpdateCommand extends NoParamsCommand {
	private final static String commandID = "";
	private final static String commandName = "";
	private final static String commandInfo = "[none]";
	private final static String helpInfo = " Skips cycle.";
	
	//CONSTRUCTOR
	public UpdateCommand() {
		super(commandID, commandName, commandInfo, helpInfo);
	}
	
	//ACTIONS
	public boolean execute(Game game) {
		return game.update();
	}
}
