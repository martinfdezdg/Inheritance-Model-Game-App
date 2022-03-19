package commands;

import exceptions.CommandParserException;

public abstract class NoParamsCommand extends Command{
	private String commandID;

	public NoParamsCommand(String commandID, String commandName, String commandInfo, String helpInfo) {
		super(commandName, commandInfo, helpInfo);
		this.commandID = commandID;
	}
	
	public Command parse(String[] commandWords) throws CommandParserException {
		if (commandWords[0].equals(commandName) ||commandWords[0].equals(commandID)) {
			if (commandWords.length != 1) 
				throw new CommandParserException("Incorrect number of arguments for no params command.");
			return this;
		}
		else return null;
	}
}
