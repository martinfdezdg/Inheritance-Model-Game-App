package objects;

public class Sunflower extends Plant {
	public final static String LETTER = "S";
	public final static String NAME = "[S]unflower";
	public final static int LIFE = 1;
	public final static int FRECUENCY = 2;
	public final static int COST = 20;
	public final static int HARM = 0;
	
	//CONSTRUCTOR
	public Sunflower(){
		super(LETTER, NAME, LIFE, FRECUENCY, COST, HARM);
	}
	
	//ACTIONS
	public void update(){
		if (initialCycle%FRECUENCY == game.getCycleCount()%FRECUENCY && initialCycle != game.getCycleCount())
			game.rechargeSuncoins();
	}
}