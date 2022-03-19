package factories;

import objects.Zombie;
import objects.Deportista;

import java.util.Random;

import objects.Caracubo;
import objects.Comun;

public class ZombieFactory {
	private static Zombie[] availableZombies = {
			new Comun(),
			new Caracubo(),
			new Deportista(),
	};
	
	//ACTIONS
	public static Zombie getZombie(String zombieName){
		if (zombieName.equals("comun") || zombieName.equals("z"))
			return new Comun(); 
		else if (zombieName.equals("caracubo") || zombieName.equals("w"))
			return new Caracubo();
		else if (zombieName.equals("deportista") || zombieName.equals("x"))
			return new Deportista();
		else return null;
	}
	
	public static Zombie spawnZombie(Random rand) {
		int randomNumber = rand.nextInt(availableZombies.length);
		String zombieName = availableZombies[randomNumber].getLetter().toLowerCase();
		return getZombie(zombieName);
	}
	
	public static String listOfAvailableZombies() {
		StringBuilder infoList = new StringBuilder();
		for (int i = 0; i < availableZombies.length; ++i) {
			infoList.append(availableZombies[i].helpText() + "\n");
		}
		return infoList.toString();
	}
}
