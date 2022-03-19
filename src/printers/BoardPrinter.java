package printers;

import PlantsVsZombies.Game;

public abstract class  BoardPrinter {
	protected int dimx;
	protected int dimy;
	String[][] board;
	final String space = " ";
	
	public abstract void encodeGame(Game game);
	
	public String boardToString(int cellSize) {
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimy * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int i=0; i < dimx; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j < dimy; j++) {
					str.append(MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}
}
