package objects;

public abstract class Zombie extends GameObject {
	private int harm;
	
	//CONSTRUCTOR
	public Zombie(String letter, String name, int life, int frecuency, int harm) {
		super(letter, name, life, frecuency);
		this.harm = harm;
	}
	
	//HELP
	public String helpText() {
		return name + ": Speed: " + frecuency + " Harm: " + harm + " Life: " + life;
	}
	
	//GETS
	public String getLetter() {
		return letter;
	}
}