import java.util.ArrayList;
public class BattleshipModel {
	public int turn = 1;
	public int[][] enemy;
	public int[][] home;
	public ArrayList<Integer> enemyMoves = new ArrayList<Integer>();
	public int[][] allTiles;

	

	public BattleshipModel() {
		allTiles = getAllTiles();
		home = new int[8][8];
		enemy = new int[8][8];
		home = initShips();
		enemy = initShips();
		for (int a=0; a<64; a++) {
			enemyMoves.add(a);
		}

	}
	public void processRound(int x, int y, int[][] boardH, int[][] boardE) {
		enemyTurn(boardH, boardE);
		processHits(x, y, boardH, boardE);
	}

	public void processHits(int x, int y, int[][] boardH, int[][] boardE) {

		int column = x;
		int row = y;
		// if the guess was a miss
		if (boardE[column][row] == 0) {
			boardE[column][row] = -2;
		}

		// if the guess hit a ship
		if (boardE[column][row] == 1) {
			boardE[column][row] = -1;
		}

		// if already guessed
		if (boardE[column][row] <= -1) {
		}
	}

	public void enemyTurn(int[][] boardH, int[][] boardE) {
		double random = Math.random();
		int index;
		index = (int) Math.floor(random * enemyMoves.size());
		int move = enemyMoves.get(index);
		int x = allTiles[move][0];
		int y = allTiles[move][1];
		processHits(x, y, boardE, boardH);
		enemyMoves.remove(index);
		System.out.println("Enemy move: " + y + " " + x);
		turn++;
	}

	public int hasWon(int[][] boardH, int[][] boardE) {
		boolean hShip = false;
		boolean eShip = false;
		int result = 0;
		for (int[] each : boardH) {
			for (int item : each) {
				if (item == 1) {
					hShip = true;
				}
			}
		}
		for (int[] each : boardE) {
			for (int item : each) {
				if (item == 1) {
					eShip = true;
				}
			}
		}
		if (!hShip) {
			result = -1;
		}
		if (!eShip) {
			result = 1;
		}
		return result;
	}

	public static int[][] getAllTiles() {
		int[][] result = new int[64][2];
		int temp = 0;
		for (int a = 0; a <= 7; a++) {
			for (int b = 0; b <= 7; b++) {
				result[temp][0] = a;
				result[temp][1] = b;
				temp++;

			}
		}
		return result;
	}

	public static int[][] initShips() {

		// used to designate whether the ship should go vertical or horizontal
		boolean orientation = true;

		int[][] board = new int[8][8];
		ArrayList<Integer> count = new ArrayList<Integer>();
		int[][] possible = getAllTiles();
		int temp = 0;
		int shipLength = 6;
		int shipsPlaced = 0;
		int x;
		int y;

        while (shipsPlaced < 5) {
			double random = Math.random();            
			orientation = !orientation;
			if (count.size() == 0) {
				for (int a=0; a<64; a++) {
					count.add(count.size());
				}
			}
			temp = (int) Math.floor(random * count.size());
			x = possible[temp][0];
			y = possible[temp][1];			
			boolean emptySquare = true;

            //vertical
            if (orientation) {            
				if (y + shipLength  <= 7) {
					for (int i = y; i < y + shipLength; i++) {
						//occupied
						if (board[x][i] == 1) {
							emptySquare = false;
						}
					}
				
					//empty
					if (emptySquare) {
						for (int i = y; i < y + shipLength; i++) {
							board[x][i] = 1;                                                                            
						}
						shipsPlaced++;
						shipLength--;
					}
				}     
            }            
        
            //horizontal
            if (!orientation) {
                
                if (x + shipLength <= 7) {
					for (int i = x; i < x + shipLength; i++) {   
						//occupied
						if (board[i][y] == 1) {
							emptySquare = false;
						}    
					}
					
					//empty
					if (emptySquare) {
						for (int i = x; i < x + shipLength; i++) {
						
							board[i][y] = 1;                         
						}
						shipsPlaced++;
						shipLength--;
					}
				}
			}
			count.remove(temp);
		}
		return board;
	}
}
