package PlantsVsZombies;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import exceptions.CommandExecuteException;
import exceptions.FileContentsException;
import list.GameObjectList;
import managers.SuncoinManager;
import managers.ZombieManager;
import objects.Plant;
import objects.Zombie;
import printers.GamePrinter;

public class Game {
	public static final int DIMX = 4;
	public static final int DIMY = 8;
	
	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";
	
	private GamePrinter gamePrinter;
	
	private Level level;
	private long seed;
	private Random rand;
	
	private int cycleCount;
	private boolean gameOver;
	
	private SuncoinManager suncoins;
	private ZombieManager zombies;
	
	private GameObjectList plantslist;
	private GameObjectList zombieslist;
	
	//CONSTRUCTOR
	public Game(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		rand = new Random(seed);
		
		cycleCount = 0;
		gameOver = false;
		
		suncoins = new SuncoinManager();
		zombies = new ZombieManager(level.getNumZombies());
		
		plantslist = new GameObjectList();
		zombieslist = new GameObjectList();
	}
	
	//UPDATE
	public boolean update() {
		zombies.spawn(this);
		plantslist.update();
		if (playerWins()) {
			gameOver = true;
			return false;
		}
		else { 
			zombieslist.update();
			if (zombiesWin()) {
				gameOver = true;
				return false;
			}
		}
		plantslist.remove();
		zombieslist.remove();
		cycleCount++;
		return true;
	}
	
	//ACTIONS
	public boolean addPlantToGame(Plant plant, int x, int y) throws CommandExecuteException { 
		if (suncoins.isEnough(plant.getCost())) {
			if (validPosition(x, y) && x < DIMY - 1) {
				if (emptySpace(x,y)) {
					plantslist.addGameObject(plant, x, y, this);
					suncoins.buy(plant.getCost());
					return true;
				}
				else throw new CommandExecuteException("Failed to add " + plant.getName() + ": (" + x +", " + y +") is already occupied."); 
			}
			else throw new CommandExecuteException("Failed to add " + plant.getName() + ": (" + x +", " + y +") is an invalid position.");
		}
		else throw new CommandExecuteException("Failed to add " + plant.getName() + ": not enough suncoins to buy it.");
	}
	
	public boolean addZombieToGame(Zombie zombie, int x, int y) throws CommandExecuteException {
		if (zombies.put()) {
			if (validPosition(x, y)) {
				if (emptySpace(x,y)) {
					zombieslist.addGameObject(zombie, x, y, this);
					return true;
				}
				else throw new CommandExecuteException("Failed to add " + zombie.getName() + ": (" + x +", " + y +") is already occupied."); 
			}
			else throw new CommandExecuteException("Failed to add " + zombie.getName() + ": (" + x +", " + y +") is an invalid position.");
		}
		else throw new CommandExecuteException("Failed to add " + zombie.getName() + ": not enough remaining zombies.");
	}
	
	public void peashooterAttack(int x, int y, int harm) {
		boolean found = false;
		for (int i = y + 1; i < DIMY && !found; ++i)
			found = zombieslist.hurtGameObject(x, i, harm);
	}
	
	public void petacerezaAttack(int x, int y, int harm) {
		for (int i = x - 1; i <= x + 1; ++i)
			for (int j = y - 1; j <= y + 1; ++j)
				zombieslist.hurtGameObject(i, j, harm);
	}
	
	public boolean zombieAttack(int x, int y, int harm) {
		return plantslist.hurtGameObject(x, y - 1 , harm);
	}
	
	//CHECKERS
	public boolean validPosition(int x, int y) {
		return y >= 0 && x >= 0 && y < Game.DIMY && x < Game.DIMX;
	}
	
	public boolean emptySpace(int x, int y) {
		return plantslist.toStringRelease(x, y).equals(" ") && zombieslist.toStringRelease(x, y).equals(" ");
	}
	
	public boolean playerWins() {
		boolean playerWins = zombies.getRemainingZombies() == 0 && zombieslist.getCounter() == 0;
		if (playerWins) {
			System.out.println("*** Game Over : Player wins! ***");
			return true;
		}
		else return false;
	}
	
	public boolean zombiesWin() {
		if (zombieslist.zombiesWin()) {
			System.out.println("*** Game Over : Zombies win... ***");
			return true;
		}
		else return false;
	}
	
	//SETS
	public void setIsFinished() {
		gameOver = true;
	}
	
	public void setPrintMode(GamePrinter gamePrinter) {
		this.gamePrinter = gamePrinter;
	}
	
	//GETS		
	public int getCycleCount() {
		return cycleCount;
	}
	
	public int getSuncoins() {
		return suncoins.getSuncoins();
	}
	
	public int getRemainingZombies() {
		return zombies.getRemainingZombies();
	}

	public boolean getIsFinished() {
		return gameOver;
	}
	
	public Level getLevel() {
		return level;
	}

	public Random getRand() {
		return rand;
	}
	
	public long getSeed() {
		return seed;
	}
	
	//PRINT	
	public String printGame() {
		return gamePrinter.printGame(this);
	}
	
	public String toStringRelease(int x, int y) {
		String objectText = plantslist.toStringRelease(x, y);
		if (objectText.equals(" ")) objectText = zombieslist.toStringRelease(x, y);
		return objectText;
	}
	
	public String toStringDebug(int x, int y) {
		String objectText = plantslist.toStringDebug(x, y);
		if (objectText.equals(" ")) objectText = zombieslist.toStringDebug(x, y);
		return objectText;
	}
	
	public String getInfoRelease() {
		StringBuilder str =  new StringBuilder();
		str.append("Number of cycles: ");
		str.append(cycleCount + "\n");
		str.append("Suncoins: ");
		str.append(suncoins.getSuncoins() + "\n");
		str.append("Remaining zombies: ");
		str.append(zombies.getRemainingZombies() + "\n");
		return str.toString();
	}
	
	public String getInfoDebug() {
		StringBuilder str = new StringBuilder();
		str.append("Number of cycles: ");
		str.append(cycleCount + "\n");
		str.append("Suncoins: ");
		str.append(suncoins.getSuncoins() + "\n");
		str.append("Remaining zombies: ");
		str.append(zombies.getRemainingZombies() + "\n");
		str.append("Level: ");
		str.append(level + "\n");
		str.append("Seed: ");
		str.append(seed + "\n");
		return str.toString();
	}
	
	//FILE
	public void store(BufferedWriter outStream) throws IOException {
		outStream.write("cycle: ");
		outStream.write(Integer.toString(cycleCount));
		outStream.newLine();
		outStream.write("sunCoins: ");
		outStream.write(Integer.toString(suncoins.getSuncoins()));
		outStream.newLine();
		outStream.write("level: ");
		outStream.write(level.toString());
		outStream.newLine();
		outStream.write("remZombies: ");
		outStream.write(Integer.toString(zombies.getRemainingZombies()));
		outStream.newLine();
		outStream.write("plantslist: ");
		plantslist.store(outStream);
		outStream.newLine();
		outStream.write("zombieslist: ");
		zombieslist.store(outStream);
	}
	
	public void load(BufferedReader inSream) throws FileContentsException, IOException {
		try {
			String[] cycle = loadLine(inSream, "cycle", false);
			if (Integer.parseInt(cycle[0]) < 0) {
				throw new FileContentsException("invalid cycle.");
			}
			this.cycleCount = Integer.parseInt(cycle[0]);
		
			String[] sunCoins = loadLine(inSream, "sunCoins", false);
			if (Integer.parseInt(sunCoins[0]) < 0) {
				throw new FileContentsException("invalid number of suncoins.");
			}
			this.suncoins.setSuncoins(Integer.parseInt(sunCoins[0]));
		
			String[] level = loadLine(inSream, "level", false);
			this.level = Level.parse(level[0]);
			if (this.level == null) {
				throw new FileContentsException("invalid level.");
			}
			
			String[] remZombies = loadLine(inSream, "remZombies", false);
			if (Integer.parseInt(remZombies[0]) < 0 || Integer.parseInt(remZombies[0]) > this.level.getNumZombies()) {
				throw new FileContentsException("invalid number of remaining zombies.");
			}
			this.zombies.setRemainingZombies(Integer.parseInt(remZombies[0]));
		
			String[] plantStrings = loadLine(inSream, "plantslist", true);
			if (plantStrings.length > DIMX*(DIMY-1)) {
				throw new FileContentsException("invalid plantslist length."); 
			}
			plantslist = new GameObjectList(plantStrings, this, DIMX*DIMY);
			
			String[] zombieStrings = loadLine(inSream, "zombieslist", true);
			if (zombieStrings.length > DIMX*DIMY) {
				throw new FileContentsException("invalid zombieslist length."); 
			}
			zombieslist = new GameObjectList(zombieStrings, this, DIMX*DIMY);
		}
		catch (NumberFormatException e) {
			throw new FileContentsException("invalid file contents.");
		}	
	}
	
	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList) 
			throws IOException, FileContentsException { 
		String line = inStream.readLine().trim();
		if (!line.startsWith(prefix + ":") ) 
			throw new FileContentsException(wrongPrefixMsg + prefix); 
		String contentString = line.substring(prefix.length()+1).trim(); 
		String[] words; 
		if (!contentString.equals("")) { 
			if (!isList ) { 
				words = contentString.split("\\s+");
				if (words.length != 1)
					throw new FileContentsException(lineTooLongMsg + prefix);
			} 
			else words = contentString.split(",\\s*"); 
		}
		else {
			if (!isList)
				throw new FileContentsException(lineTooShortMsg + prefix);
			words = new String[0];
		}
		return words;	
	}
	
	//AUX
	public void rechargeSuncoins() {
		suncoins.recharge();
	}
	
	public void copy(Game aux) {
		this.cycleCount = aux.cycleCount;
		this.suncoins.setSuncoins(aux.getSuncoins());
		this.zombies.setRemainingZombies(aux.getRemainingZombies());
		this.level = aux.level;
		this.plantslist = aux.plantslist;
		this.zombieslist = aux.zombieslist;
	}
	
	public void reset() {
		rand = new Random(seed);
		
		cycleCount = 0;
		gameOver = false;
		
		suncoins = new SuncoinManager();
		zombies = new ZombieManager(level.getNumZombies());
		
		plantslist = new GameObjectList();
		zombieslist = new GameObjectList();
	}
}