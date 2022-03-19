package commands;

import PlantsVsZombies.Game;

public class ExitCommand extends NoParamsCommand {
	private final static String commandID = "e";
	private final static String commandName = "exit";
	private final static String commandInfo = "[E]xit";
	private final static String helpInfo = "Terminate the program.";
	
	public ExitCommand() {
		super(commandID, commandName, commandInfo, helpInfo);
	}
	
	public boolean execute(Game game) {
		System.out.println("*** Game Over : User exit ***");
		game.setIsFinished();
		return false;
	}
}
