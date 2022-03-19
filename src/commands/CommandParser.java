package commands;

import exceptions.CommandParserException;

public class CommandParser {
	private static Command[] availableCommands = {
			new AddCommand(),
			new AddZombie(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new ZombieListCommand(),
			new PrintModeCommand(),
			new LoadCommand(),
			new SaveCommand(),
			new UpdateCommand(),
			};
	
	public static Command parseCommand(String[] commandWords) throws CommandParserException {
		Command command = null;
		for(int i = 0; i < availableCommands.length && command == null; ++i) {
			command = availableCommands[i].parse(commandWords);
		}
		if (command == null) {
			throw new CommandParserException("Unknown command. Use ’help’ to see the available commands.");
		}
		return command;
	}
	
	public static String commandHelp() {
		StringBuilder helpText = new StringBuilder();
		for(int i = 0; i < availableCommands.length; ++i) {
			helpText.append(availableCommands[i].helpText() + "\n");
		}
		return helpText.toString();
	}
}
