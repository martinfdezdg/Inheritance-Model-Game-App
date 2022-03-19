package commands;

import PlantsVsZombies.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParserException;

public abstract class Command {
	private String helpInfo;
	private String commandInfo;
	protected String commandName; 
	
	public Command(String commandName, String commandInfo, String helpInfo){
		this.commandName = commandName;
		this.commandInfo = commandInfo;
		this.helpInfo = helpInfo; 
	}

	public abstract boolean execute(Game game) throws CommandExecuteException;
	public abstract Command parse(String[] commandWords) throws CommandParserException;
	public String helpText() {
		return commandInfo + ": " + helpInfo;
	}
}
