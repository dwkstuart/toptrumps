
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Class that creates the GUI which is used to begin a game or choose to view
 * previous statistics
 *
 */

public class startGui extends JFrame implements ActionListener {
	/**
	 * instance variables
	 */
	private Integer[] players = new Integer[] { 1, 2, 3, 4 };
	private JComboBox numberPlayers;
	private JButton newGame, viewStats;


	/**creates start gui
	 *
	 */
	public startGui() {

		setTitle("Top Trumps");
		setSize(350, 200);
		setLocation(400, 200);
		setLayout(new GridLayout(3, 1));

		JLabel choosePlayers = new JLabel("Choose number of opponents");
		newGame = new JButton("Start New Game");
		viewStats = new JButton("View Statistics");
		numberPlayers = new JComboBox<Integer>(players);

		//create and populate panels with GUI objects
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();

		pan1.add(choosePlayers);
		pan1.add(numberPlayers);
		pan2.add(newGame);
		pan3.add(viewStats);

		//add panels to JFrame
		add(pan1);
		add(pan2);
		add(pan3);

		//action listeners for the two buttons
		newGame.addActionListener((ActionListener) this);
		viewStats.addActionListener(this);

		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		//player selects number of opponents in drop down
		//new game is created with that number of computer players
		if (e.getSource() == newGame) {
			int totalPlayers = (Integer) numberPlayers.getSelectedItem();
			GameGUI run = new GameGUI(totalPlayers);
			//run.ResetGUI();
			//run.UpdatePlayer(0);
			System.out.println(totalPlayers);
		}

		else {
			DbCon stats = new DbCon();
			System.out.println("view Stats");
			stats.DbConnect();
			stats.getAvgDraws();
		}
		
		//make GUI disappear after initial choice has been made
		this.setVisible(false);

	}

}
