package commands;

import PlantsVsZombies.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParserException;
import printers.DebugPrinter;
import printers.ReleasePrinter;

public class PrintModeCommand extends Command{
	private final static String commandName = "printmode";
	private final static String commandInfo = "[P]rintMode <mode>";
	private final static String helpInfo = "change print mode [Release|Debug].";
	private String printModeName;
	
	public PrintModeCommand() {
		super(commandName, commandInfo, helpInfo);
	}

	public boolean execute(Game game) throws CommandExecuteException {
		if (printModeName.equals("release")) {
			game.setPrintMode(new ReleasePrinter());
			return true;
		}
		else if (printModeName.equals("debug")) {
			game.setPrintMode(new DebugPrinter());
			return true;
		}
		else {
			throw new CommandExecuteException ("Unknown print mode: " + printModeName);
		}
	}

	public Command parse(String[] commandWords) throws CommandParserException {
		if (commandWords[0].equals(commandName)) {
			if (commandWords.length != 2) 
				throw new CommandParserException("Incorrect number of arguments for printmode command: [P]rintMode release|debug.");
			printModeName = commandWords[1];
			return this;
		}
		else return null;
	}
}
