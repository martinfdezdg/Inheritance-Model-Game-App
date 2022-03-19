package objects;

public class Petacereza extends Plant {
	public final static String LETTER = "C";
	public final static String NAME = "Peta[C]ereza";
	public final static int LIFE = 2;
	public final static int FRECUENCY = 2;
	public final static int COST = 50;
	public final static int HARM = 10;
	
	//CONSTRUCTOR
	public Petacereza(){
		super(LETTER, NAME, LIFE, FRECUENCY, COST, HARM);
	}
	
	//ACTIONS
	public void update(){
		if (initialCycle%FRECUENCY == game.getCycleCount()%FRECUENCY && initialCycle != game.getCycleCount()) {
			game.petacerezaAttack(x,y,HARM);
			isAlive = false;
			life = 0;
		}
	}
}