package commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import PlantsVsZombies.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParserException;
import exceptions.FileContentsException;
import printers.MyStringUtils;


public class LoadCommand extends Command{
	private final static String commandName = "load";
	private final static String commandInfo = "[Lo]ad <filename>";
	private final static String helpInfo = "Load the state of the game from a file.";
	private String fileName;

	
	public LoadCommand() {
		super(commandName, commandInfo, helpInfo);
	}

	public boolean execute(Game game) throws CommandExecuteException {
		Game aux = new Game(game.getLevel(),game.getSeed());
		
		if (!MyStringUtils.isValidFilename(fileName))
			throw new CommandExecuteException("Invalid filename: the filename contains invalid characters.");
			
		if (!MyStringUtils.fileExists(fileName) || !MyStringUtils.isReadable(fileName)) 
			throw new CommandExecuteException("File not found.");
			
		try (BufferedReader inSream = new BufferedReader(new FileReader(fileName))) {
			inSream.readLine();
			inSream.readLine();
			aux.load(inSream);
		
		} catch (IOException e) {
			throw new CommandExecuteException("Error de E/S : " + e);
			
		} catch (FileContentsException e) { 
			throw new CommandExecuteException("Load failed: " + e);
		}
		
		game.copy(aux);
		System.out.println("Game successfully loaded from file " + fileName + ".");
		return true;
	}

	public Command parse(String[] commandWords) throws CommandParserException {
		if (commandWords[0].equals(commandName) || commandWords[0].equals("lo")) {
			if (commandWords.length != 2) 
				throw new CommandParserException("Incorrect number of arguments for load command: " + commandInfo + ".");
			fileName = commandWords[1];
			return this;
		}
		else return null;
	}
}
