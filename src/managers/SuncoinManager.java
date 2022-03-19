package managers;

public class SuncoinManager {
	private static final int CHARGE = 10;
	private int suncoins;
	
	//CONSTRUCTOR
	public SuncoinManager() {
		suncoins = 50;
	}
	
	//OPERATIONS
	public void recharge() {
		suncoins += CHARGE;
	}
	
	public void buy(int price) {
		suncoins -= price;
	}
	
	//CHECKERS
	public boolean isEnough(int price) {
		return (suncoins >= price);
	}
	
	//GETS
	public int getSuncoins() {
		return suncoins;
	}
	
	//SETS
	public void setSuncoins(int suncoins) {
		this.suncoins = suncoins;
	}
}