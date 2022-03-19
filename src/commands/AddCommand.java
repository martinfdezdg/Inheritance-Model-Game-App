package commands;

import PlantsVsZombies.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParserException;
import factories.PlantFactory;
import objects.Plant;

public class AddCommand extends Command{
	private final static String commandName = "add";
	private final static String commandInfo = "[A]dd <plant> <x> <y>:";
	private final static String helpInfo = "Adds a plant in position x, y.";
	private String plantName;
	private int x,y;
	
	public AddCommand() {
		super(commandName, commandInfo, helpInfo);
	}

	public boolean execute(Game game) throws CommandExecuteException {
		Plant plant = PlantFactory.getPlant(plantName);
		if (plant == null) {
			throw new CommandExecuteException ("Unknown plant name: " + plantName);
		}
		else {
			if (game.addPlantToGame(plant, x, y)) {
				UpdateCommand update = new UpdateCommand();
				update.execute(game);
				return true;
			}
			return false;
		}
	}

	public Command parse(String[] commandWords) throws CommandParserException {
		try {
			if (commandWords[0].equals(commandName) || commandWords[0].equals("a")) {
				if (commandWords.length != 4) 
					throw new CommandParserException("Incorrect number of arguments for add command: [A]dd <plant> <x> <y>");
				plantName = commandWords[1];
				x = Integer.parseInt(commandWords[2]);
				y = Integer.parseInt(commandWords[3]);
				return this;
			}
			else return null;
		}
		catch (NumberFormatException e) {
			throw new CommandParserException("Invalid argument for add command, number expected: [A]dd <plant> <x> <y>");
		}	
	}
}
