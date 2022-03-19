package managers;

import PlantsVsZombies.Game;
import exceptions.CommandExecuteException;
import factories.ZombieFactory;

public class ZombieManager {
	private int remainingZombies;
	
	//CONSTRUCTOR
	public ZombieManager(int remainingZombies) {
		this.remainingZombies = remainingZombies;
	}

	//OPERATIONS
	public void spawn(Game game) {
		int x = game.getRand().nextInt(Game.DIMX);
		if (game.validPosition(x, Game.DIMY-1) && game.emptySpace(x, Game.DIMY-1))
			if (isZombieAdded(game.getRand().nextInt(10),game.getLevel().getFrecuency()))
				try {
					game.addZombieToGame(ZombieFactory.spawnZombie(game.getRand()), x, Game.DIMY-1);
				} catch (CommandExecuteException e) {}
	}
	
	public boolean put() {
		if (remainingZombies > 0) {
			remainingZombies--;
			return true;
		}
		else return false;
	}
	
	//CHECKERS
	public boolean isZombieAdded(int randomNumber, int frecuency) {
		return randomNumber < frecuency && remainingZombies > 0;
	}
	
	//GETS
	public int getRemainingZombies() {
		return remainingZombies;
	}
	
	//SETS
	public void setRemainingZombies(int remainingZombies) {
		this.remainingZombies = remainingZombies;
	}
}