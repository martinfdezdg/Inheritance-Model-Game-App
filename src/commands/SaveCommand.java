package commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import PlantsVsZombies.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParserException;
import printers.MyStringUtils;


public class SaveCommand extends Command{
	private final static String commandName = "save";
	private final static String commandInfo = "[Sa]ve <filename>";
	private final static String helpInfo = "Save the state of the game to a file.";
	private String fileName;

	
	public SaveCommand() {
		super(commandName, commandInfo, helpInfo);
	}

	public boolean execute(Game game) throws CommandExecuteException {
		if (!MyStringUtils.isValidFilename(fileName))
			throw new CommandExecuteException("Invalid filename: the filename contains invalid characters.");
			
		try (BufferedWriter outStream = new BufferedWriter(new FileWriter(fileName + ".dat"))){
			outStream.write("Plants Vs Zombies v3.0");
			outStream.newLine();
			outStream.newLine();
			game.store(outStream);
		}
		catch (IOException e){
			throw new CommandExecuteException("Error de E/S : " + e);
		}
		System.out.println("Game successfully saved in file " + fileName + ".dat. Use the load commmad to reload it.");
		return false;
	}

	public Command parse(String[] commandWords) throws CommandParserException {
		if (commandWords[0].equals(commandName) || commandWords[0].equals("sa")) {
			if (commandWords.length != 2) 
				throw new CommandParserException("Incorrect number of arguments for save command: " + commandInfo + ".");
			fileName = commandWords[1];
			return this;
		}
		else return null;
	}
}
