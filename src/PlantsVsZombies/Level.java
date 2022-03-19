package PlantsVsZombies;

public enum Level {
	INSANE(10,3), HARD(5,2), EASY(3,1);
	
	private int numZombies;
	private int frecuency;
	
	//CONSTRUCTORA
	private Level(int numZombies, int frecuency) {
		this.numZombies = numZombies;
		this.frecuency = frecuency;
	}
	
	public static Level parse(String inputString) {
		for (Level level : Level. values())
			if (level . name().equalsIgnoreCase(inputString)) return level;
		return null;
	}
	
	public static String all (String separator) {
		StringBuilder sb = new StringBuilder();
		for (Level level : Level. values())
			sb. append(level.name() + separator);
		String allLevels = sb.toString();
		return allLevels.substring(0,allLevels.length()-separator.length());
	}
	
	//GETS
	public int getNumZombies() {
		return numZombies;
	}
	
	public int getFrecuency() {
		return frecuency;
	}
}