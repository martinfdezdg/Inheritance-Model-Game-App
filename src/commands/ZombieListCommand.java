package commands;

import PlantsVsZombies.Game;
import factories.ZombieFactory;

public class ZombieListCommand extends NoParamsCommand {
	private final static String commandID = "zl";
	private final static String commandName = "zombielist";
	private final static String commandInfo = "[Z]ombielist";
	private final static String helpInfo = "Prints the list of zombies.";
	
	//CONSTRUCTOR
	public ZombieListCommand() {
		super(commandID, commandName, commandInfo, helpInfo);
	}
	
	//ACTIONS
	public boolean execute(Game game) {
		System.out.println(ZombieFactory.listOfAvailableZombies());	
		return false;
	}
}
