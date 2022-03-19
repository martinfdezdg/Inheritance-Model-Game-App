package list;

import java.io.BufferedWriter;
import java.io.IOException;

import PlantsVsZombies.Game;
import exceptions.CommandExecuteException;
import exceptions.FileContentsException;
import factories.PlantFactory;
import factories.ZombieFactory;
import managers.SuncoinManager;
import objects.GameObject;
import objects.Plant;
import objects.Sunflower;
import objects.Zombie;

public class GameObjectList {
	private int counter;
	GameObject list[];
	
	//CONSTRUCTOR
	public GameObjectList() {
		counter = 0;
		list = new GameObject[1];
	}
	
	public GameObjectList(String[] objectsStrings, Game game, int i) throws FileContentsException {
		counter = 0;
		list = new GameObject[i];
		try {
			for (String s : objectsStrings) {
				String[] atributos = s.toLowerCase().split(":s*");
				int life = Integer.parseInt(atributos[1]);
				int x = Integer.parseInt(atributos[2]);
				int y = Integer.parseInt(atributos[3]);
				int cycles = Integer.parseInt(atributos[4]);
				
				GameObject object =  PlantFactory.getPlant(atributos[0]);
				if (object == null)	{
					object = ZombieFactory.getZombie(atributos[0]);
					if (object == null)
						throw new FileContentsException("invalid object.");
				}
				addGameObject(object, x, y, game);
				object.setInitialCycle(game.getCycleCount() - cycles);
				object.setLife(life);
			}
		}
		catch(NumberFormatException e) { 
			throw new FileContentsException("invalid file format.");
		}	
	}

	//ACTIONS
	public void addGameObject(GameObject object, int x, int y, Game game) {
		if (list.length == counter) resizeList();
		object.add(x, y, game);
		list[counter] = object;
		++counter;
	}
	
	public boolean hurtGameObject(int x, int y, int damage) {
		GameObject object = getGameObject(x, y);
		if (object != null) {
			object.hurt(damage);
			return true;
		}
		else return false;
	}
	
	public void remove() {
		GameObject newlist[] = new GameObject[list.length];
		int i = 0, j = 0;
		while(i < counter) {
			if (list[i].getIsAlive()) {
				newlist[j] = list[i];
				++j;
			}
			++i;
		}
		list = newlist;
		counter = j;
	}
	
	private GameObject getGameObject(int x, int y) {
		GameObject object = null;
		for (int i = 0; i < counter && object == null; ++i) {
			if (!list[i].getIsEmpty(x, y)) object = list[i];
		}
		return object;
	}
	
	public boolean zombiesWin() {
		boolean found = false;
		for (int i = 0; i < Game.DIMX && !found; ++i) 
			found = (getGameObject(i,0) != null);
		return (found);
	}
	
	public void update() {
		for (int i = 0; i < counter; ++i) {
			list[i].update();
		}
	}
	
	//LISTS
	private void resizeList() {
		GameObject newlist[] = new GameObject[list.length*2];
		for (int i = 0; i < list.length; ++i) {
			if (list[i].getIsAlive()) newlist[i] = list[i];
		}
		list = newlist;
	}
	
	//GETS
	public int getCounter() {
		return counter;
	}
	
	//PRINT
	public String toStringRelease(int x, int y) {
		String objectText = " ";
		GameObject object = this.getGameObject(x,y);
		if (object != null) objectText = object.toStringRelease();
		return objectText;
	}
	
	public String toStringDebug(int x, int y) {
		String objectText = " ";
		GameObject object = this.getGameObject(x,y);
		if (object != null) objectText = object.toStringDebug();
		return objectText;
	}
	
	//FILE
	public void store(BufferedWriter outStream) throws IOException {
		for (int i = 0; i < counter; ++i) {
			list[i].store(outStream);
		}
	}
}