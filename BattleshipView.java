package BattleshipApp;
import java.awt.*;  
import javax.swing.*;

public class BattleshipView {

	JPanel[][] panelPlayer;
	JPanel[][] panelGuess;
	JPanel grid = new JPanel();
	JPanel board = new JPanel();
	JFrame battleship = new JFrame();
	JButton submitButton;
	JPanel commands = new JPanel();
	String[] list = {"1", "2", "3", "4", "5", "6", "7", "8"};
	JComboBox<String> columnX;
	JComboBox<String> rowY;
	JLabel rounds;
	Color miss = Color.decode("#0074D9");
	Color ship = Color.decode("#AAAAAA");
	Color sea = Color.decode("#7FDBFF");
	Color sunk = Color.decode("#FF4136");
	Color background = Color.decode("#DDDDDD");
	
	public BattleshipView() {
		panelPlayer = new JPanel[8][8];
		panelGuess = new JPanel[8][8];
		
		rounds = new JLabel();
		submitButton = new JButton("Shoot");
		columnX = new JComboBox(list);
		rowY = new JComboBox(list);
		commands.setPreferredSize(new Dimension(200,100));
		commands.setLayout(new GridLayout(2,2));
		commands.add(columnX);
		commands.add(rowY);
		commands.add(submitButton);
		commands.add(rounds);
		

		
		battleship.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		battleship.setVisible(true);
		battleship.setTitle("Battleship");
		battleship.setLayout(new FlowLayout());
		grid.setLayout(new GridLayout(8, 8));
		grid.setPreferredSize(new Dimension(425, 425));
		board.setLayout(new GridLayout(8, 8));		 
		board.setPreferredSize(new Dimension(425, 425));
	}

}
