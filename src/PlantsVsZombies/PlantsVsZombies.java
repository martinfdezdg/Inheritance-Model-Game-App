package PlantsVsZombies;
import java.util.Random;

import printers.DebugPrinter;
import printers.ReleasePrinter;

public class PlantsVsZombies {
	
	public static void main(String[] args) {
		if (args.length != 2 ) System.out.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed].");
		else {
			Level level = Level.parse(args[0]);
			long seed = 0;
			if (level==null) System.out.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE.");
			else {
				try {
					seed = (args.length>1)?Long.parseLong(args[1]):new Random().nextInt(1000);
					Game game = new Game(level,seed);
					Controller controller = new Controller(game);
					game.setPrintMode(new ReleasePrinter());
					controller.run();
				} 
				catch (NumberFormatException e) {
					System.out.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: the seed must be a number.");
				}
			}
		}
	}
}