package objects;

public class Deportista extends Zombie {
	public final static String LETTER = "X";
	public final static String NAME = "[Z]ombie deportista";
	public final static int LIFE = 2;
	public final static int FRECUENCY = 1;
	public final static int HARM = 1;
	
	//CONSTRUCTORA
	public Deportista() {
		super(LETTER, NAME, LIFE, FRECUENCY, HARM);
	}
	
	//ACTIONS
	public void update(){
		if (initialCycle != game.getCycleCount()) {
			if (!game.zombieAttack(x,y,HARM) && game.validPosition(x,y-1) && game.emptySpace(x,y-1)) {
				y--;
			}
		}
	}
}
