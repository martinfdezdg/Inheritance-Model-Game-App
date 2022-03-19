package PlantsVsZombies;
import java.util.Scanner;

import commands.Command;
import commands.CommandParser;
import exceptions.CommandExecuteException;
import exceptions.CommandParserException;
import printers.GamePrinter;

public class Controller {
	private static Scanner in = new Scanner(System.in);
	Game game;
	private final String welcome = "Welcome to PlantsVsZombies v3.0\n";
	private final String prompt = "Command > ";
	private final String unknownCommandMsg = "Unknown command";
	
	//CONSTRUCTOR
	public Controller(Game game) {
		this.game = game;
	}
	
	//RUN
	public void run() {
		System.out.println(welcome);
		System.out.println(game.printGame());
		
		while (!game.getIsFinished()) {
			System.out.print(prompt);
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
			try {
				Command command = CommandParser.parseCommand(words);
				if (command != null) {
					if (command.execute(game)) 
						System.out.println(game.printGame());
				}
				else System.err.println(unknownCommandMsg);
			}
			catch (CommandParserException | CommandExecuteException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			}	
		}
	}
}