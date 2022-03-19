package objects;

public class Peashooter extends Plant{
	public final static String LETTER = "P";
	public final static String NAME = "[P]eashooter";
	public final static int LIFE = 3;
	public final static int FRECUENCY = 1;
	public final static int COST = 50;
	public final static int HARM = 1;

	//CONSTRUCTOR
	public Peashooter(){
		super(LETTER, NAME, LIFE, FRECUENCY, COST, HARM);
	}
	
	//ACTIONS
	public void update() {
		if (initialCycle != game.getCycleCount()) {
			game.peashooterAttack(x,y,HARM);
		}
	}
}