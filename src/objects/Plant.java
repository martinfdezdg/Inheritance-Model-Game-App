package objects;

public abstract class Plant extends GameObject {
	private int cost;
	private int harm;
	
	//CONSTRUCTOR
	public Plant(String letter, String name, int life, int frecuency, int cost, int harm) {
		super(letter, name, life, frecuency);
		this.cost = cost;
		this.harm = harm;
	}
	
	//HELP
	public String helpText() {
		return name + ": Cost: " + cost + " Harm: " + harm;
	}
	
	//GETS
	public int getCost() {
		return cost;
	}
}