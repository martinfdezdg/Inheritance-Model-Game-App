package printers;

import PlantsVsZombies.Game;

public class ReleasePrinter extends BoardPrinter implements GamePrinter {
	public static final String NAME = "Release";
	
	@Override
	public void encodeGame(Game game) {
		dimx = Game.DIMX;
		dimy = Game.DIMY;
		board = new String[dimx][dimy];
		for(int i = 0; i < Game.DIMX; i++) {
			for(int j = 0; j < Game.DIMY; j++) {
				board[i][j] = game.toStringRelease(i,j);
			}
		}
	}
		
	//PRINT
	public String printGame(Game game) {
		encodeGame(game);
		return game.getInfoRelease() + boardToString(7);
	}
}
