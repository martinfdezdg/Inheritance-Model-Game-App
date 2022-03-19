package commands;

import PlantsVsZombies.Game;
import factories.PlantFactory;

public class ListCommand extends NoParamsCommand {
	private final static String commandID = "l";
	private final static String commandName = "list";
	private final static String commandInfo = "[L]ist";
	private final static String helpInfo = "Prints the list of available plants.";
	
	public ListCommand() {
		super(commandID, commandName, commandInfo, helpInfo);
	}
	
	public boolean execute(Game game) {
		System.out.println(PlantFactory.listOfAvailablePlants());
		return false;
	}
}
