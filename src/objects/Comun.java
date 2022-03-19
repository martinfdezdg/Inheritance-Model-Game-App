package objects;

public class Comun extends Zombie {
	public final static String LETTER = "Z";
	public final static String NAME = "[Z]ombie comun";
	public final static int LIFE = 5;
	public final static int FRECUENCY = 2;
	public final static int HARM = 1;
	
	//CONSTRUCTORA
	public Comun() {
		super(LETTER, NAME, LIFE, FRECUENCY, HARM);
	}
	
	//ACTIONS
	public void update(){
		if (initialCycle%FRECUENCY == game.getCycleCount()%FRECUENCY && initialCycle != game.getCycleCount()) {
			if (!game.zombieAttack(x,y,HARM) && game.validPosition(x,y-1) && game.emptySpace(x,y-1)) {
				y--;			
			}
		}
	}
}
