package objects;

public class Caracubo extends Zombie {
	public final static String LETTER = "W";
	public final static String NAME = "[Z]ombie caracubo";
	public final static int LIFE = 8;
	public final static int FRECUENCY = 4;
	public final static int HARM = 1;
	
	//CONSTRUCTORA
	public Caracubo() {
		super(LETTER, NAME, LIFE, FRECUENCY, HARM);
	}
	
	public void update(){
		if (initialCycle%FRECUENCY == game.getCycleCount()%FRECUENCY && initialCycle != game.getCycleCount()) {
			if (!game.zombieAttack(x,y,HARM) && game.validPosition(x,y-1) && game.emptySpace(x,y-1)) {
				y--;
			}
		}
	}
}
