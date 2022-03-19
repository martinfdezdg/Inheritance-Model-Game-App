package printers;

import PlantsVsZombies.Game;

public class DebugPrinter extends BoardPrinter implements GamePrinter {
	public static final String NAME = "Debug";
	
	@Override
	public void encodeGame(Game game) {
		dimx = 1; 
		dimy = 0;
		String str;
		
		int k = 0;
		board = new String[dimx][dimy];
		for(int i = 0; i < Game.DIMX; i++) {
			for(int j = 0; j < Game.DIMY; j++) {
				str = game.toStringDebug(i,j);
				if (!str.equals(" ")){
					dimy++;
					board = resizeBoard(board,dimx,dimy);
					board[0][k] = str;
					k++;
				}
			}
		}
	}
	
	private String[][] resizeBoard(String[][] board, int dimx, int dimy) {
		String[][] newboard = new String[dimx][dimy];
		for (int i = 0; i < dimy - 1; ++i) {
			newboard[0][i] = board[0][i];
		}
		return newboard;
	}
	
	//PRINT
	public String printGame(Game game) {
		encodeGame(game);
		return game.getInfoDebug() + boardToString(20);
	}
}
