package objects;

import java.io.BufferedWriter;
import java.io.IOException;

import PlantsVsZombies.Game;

public abstract class GameObject {
	protected Game game;
	protected int initialCycle;
	protected String letter;
	protected String name;
	protected int x, y;
	protected int life;
	protected int frecuency;
	protected boolean isAlive;


	//CONSTRUCTOR
	public GameObject(String letter, String name, int life, int frecuency) {
		this.letter = letter;
		this.name = name;
		this.life = life;
		this.frecuency = frecuency;
		this.isAlive = true;
	}
	
	//ACTIONS
	public void add(int x, int y, Game game) {
		this.game = game;
		this.initialCycle = game.getCycleCount();
		this.x = x;
		this.y = y;
	}
	
	public void hurt(int damage) {
		life -= damage;
		isAlive = life > 0;
	}
	
	public abstract void update();
	
	
	//GETS
	public boolean getIsAlive() {
		return isAlive;
	}
	public String getName() {
		return name;
	}
	
	//SETS 
	public void setInitialCycle(int initialCycle) {
		this.initialCycle = initialCycle;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	//PRINT
	public String toStringRelease() {
		String stringLife = Integer.toString(life);
		return letter + "["+ stringLife + "]";
	}
	
	public String toStringDebug() {
		String stringLife = Integer.toString(life);
		return letter + "[" + "l:" + stringLife + ",x:" + x + ",y:" + y + ",t:"+ (frecuency - game.getCycleCount()%frecuency) +"]";
	}

	public boolean getIsEmpty(int x, int y) {
		return this.x != x || this.y != y;
	}
	
	//FILE
	public void store(BufferedWriter outStream) throws IOException {
		outStream.write(letter+":"+Integer.toString(life)+":"+Integer.toString(x)+":"+Integer.toString(y)+":"+Integer.toString(frecuency - game.getCycleCount()%frecuency)+", ");
	}
}