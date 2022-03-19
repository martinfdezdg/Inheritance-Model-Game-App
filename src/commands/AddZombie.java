package commands;

import PlantsVsZombies.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParserException;
import factories.PlantFactory;
import factories.ZombieFactory;
import objects.Plant;
import objects.Zombie;

public class AddZombie extends Command{
	private final static String commandName = "addzombie";
	private final static String commandInfo = "[AZ]ddzombie <zombie> <x> <y>";
	private final static String helpInfo = "Adds a zombie in position x, y.";
	private String zombieName;
	private int x,y;
	
	public AddZombie() {
		super(commandName, commandInfo, helpInfo);
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		Zombie zombie = ZombieFactory.getZombie(zombieName);
		if (zombie == null) {
			throw new CommandExecuteException ("Unknown zombie name: " + zombieName);
		}
		else {
			if(game.addZombieToGame(zombie, x, y)) {
				UpdateCommand update = new UpdateCommand();
				update.execute(game);
				return true;
			}
			else return false;
		}
	}

	public Command parse(String[] commandWords) throws CommandParserException {
		try {
			if (commandWords[0].equals(commandName) || commandWords[0].equals("az")) {
				if (commandWords.length != 4) 
					throw new CommandParserException("Incorrect number of arguments for add command: [A]dd <zombie> <x> <y>");
				zombieName = commandWords[1];
				x = Integer.parseInt(commandWords[2]);
				y = Integer.parseInt(commandWords[3]);
				return this;
			}
			else return null;
		}
		catch (NumberFormatException e) {
			throw new CommandParserException("Invalid argument for add command, number expected: [A]dd <zaombie> <x> <y>");
		}	
	}
}
