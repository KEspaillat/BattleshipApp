import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleshipController implements ActionListener {
	BattleshipView view;
	BattleshipModel model;
	private int x;
	private int y;
	
	
	
	
	public BattleshipController(BattleshipModel m,BattleshipView v) {
		model = m;
		view = v;
		
	}

	public void CreateField(int[][] home, int[][] enemy) {
		drawSeas(home, enemy);
		view.submitButton.addActionListener(this);

		view.battleship.add(view.grid);
		view.battleship.add(view.board);
		view.battleship.add(view.commands);
		view.battleship.pack();
	}

	public void shoot(int x, int y, int[][] boardH, int[][] boardE) {
		model.processRound(x, y, boardH, boardE);
		CreateField(boardH, boardE);
	}

	//Game Loop Sequence
	@Override
	public void actionPerformed(ActionEvent e) {
		x = view.rowY.getSelectedIndex();
		y = view.columnX.getSelectedIndex();
		model.processRound(x, y, model.home, model.enemy);
		System.out.println("Coords: " + x + " " + y + "\n");
		view.grid.removeAll();
		view.board.removeAll();
		//Checks for a winner
		int winner = model.hasWon(model.home, model.enemy);
		System.out.println("Winner " + winner);
		gameFinished(winner);
		drawSeas(model.home, model.enemy);
	}

	public void drawSeas(int[][] home, int[][] enemy) {
		Color playerColor = Color.black;
		Color guessColor = Color.blue;
		view.rounds.setText(" Round #: " + model.turn);
		
		for (x = 0; x <= 7; x++) {
			for (y = 0; y <= 7; y++) {
				if (home[x][y] == 1) {
					playerColor = view.ship;
				} else if (home[x][y] == - 1) {
					playerColor = view.sunk;
				} else if (home[x][y] == -2) {
					playerColor = view.miss;
				} else {
					playerColor = view.sea;
				}
				view.panelPlayer[x][y] = new JPanel();
				view.panelPlayer[x][y].setBackground(playerColor);
				view.panelPlayer[x][y].setBorder(BorderFactory.createLineBorder(view.background, 2));
				view.panelPlayer[x][y].setLayout(new GridLayout());
				view.panelPlayer[x][y].setPreferredSize(new Dimension(46,46));
				view.grid.add(view.panelPlayer[x][y]);
			}
		}
		for (x = 0; x <= 7; x++) {
			for (y = 0; y <= 7; y++) {	
				if (enemy[x][y] == -1) {
					guessColor = view.sunk;
				} else if (enemy[x][y] == -2) {
					guessColor = view.miss;
				} else {
					guessColor = view.sea;
				}
				view.panelGuess[x][y] = new JPanel();
				view.panelGuess[x][y].setBorder(BorderFactory.createLineBorder(view.background, 2));
				view.panelGuess[x][y].setBackground(guessColor);
				view.panelGuess[x][y].setLayout(new GridLayout());
				view.panelGuess[x][y].setPreferredSize(new Dimension(46,46));
				view.board.add(view.panelGuess[x][y]);
			}
		}
	}
	public void gameFinished(int winner) {
		JLabel won = new JLabel();
		JButton playAgain = new JButton( "Play Again");
		playAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				view.grid.removeAll();
				view.board.removeAll();
				BattleshipModel model = new BattleshipModel();
				view = new BattleshipView();
				BattleshipController controller = new BattleshipController(model, view);
				controller.CreateField(model.home, model.enemy);  
			}
		});
		if (winner > 0) {
			view.commands.removeAll();
			won.setText(" You're the Winner!");
			view.commands.add(won);
			view.commands.add(playAgain);
			view.battleship.add(view.commands);
			view.battleship.pack();
		}
		if (winner < 0) {
			view.commands.removeAll();
			won.setText(" You have been Defeated");
			view.commands.add(won);
			view.commands.add(playAgain);
			view.battleship.add(view.commands);
			view.battleship.pack();
		} else {
			return;
		}
	}
}
