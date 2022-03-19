package factories;

import objects.Plant;
import objects.Sunflower;
import objects.Nuez;
import objects.Peashooter;
import objects.Petacereza;

public class PlantFactory {
	
	private static Plant[] availablePlants = {
			new Sunflower(),
			new Peashooter(),
			new Petacereza(),
			new Nuez(),
	};
	
	//ACTIONS
	public static Plant getPlant(String plantName){
		if (plantName.equals(Sunflower.NAME) || plantName.equals("s"))
			return new Sunflower();
		else if (plantName.equals(Peashooter.NAME) || plantName.equals("p"))
			return new Peashooter();
		else if (plantName.equals(Petacereza.NAME) || plantName.equals("c"))
			return new Petacereza();
		else if (plantName.equals(Nuez.NAME) || plantName.equals("n"))
			return new Nuez();
		else return null;
	}
	
	public static String listOfAvailablePlants() {
		StringBuilder infoList = new StringBuilder();
		for (int i = 0; i < availablePlants.length; ++i) {
			infoList.append(availablePlants[i].helpText() + "\n");
		}
		return infoList.toString();
	}
	
}
