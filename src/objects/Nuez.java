package objects;

public class Nuez extends Plant{
	public final static String LETTER = "N";
	public final static String NAME = "[N]uez";
	public final static int LIFE = 10;
	public final static int FRECUENCY = 1;
	public final static int COST = 50;
	public final static int HARM = 0;
	
	//CONSTRUCTOR
	public Nuez(){
		super(LETTER, NAME, LIFE, FRECUENCY, COST, HARM);
	}
	
	//UPDATE
	public void update() {}
}
