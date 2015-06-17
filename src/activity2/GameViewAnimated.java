package activity2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameViewAnimated extends JFrame implements StateChangeListener, AnimationFinishListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameState state;
	private ActionHandler actionHandler;
	
	private JPanel top;
	private JPanel bottom;
	private Grid grid;
	
	private JLabel bottomLabel;
	private JLabel scoreLabel;
	private JLabel titleLabel;
	
	private JButton newGameButton;

	public GameViewAnimated() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setTitle("Sebastian's Twos Game");
		setSize(500, 648);
		setResizable(false);
		
		top = new JPanel();
		bottom = new JPanel();
		grid = new Grid(4, 4, this);
		
		top.setPreferredSize(new Dimension(64, 64));
		top.setLayout(new BorderLayout());

		bottom.setPreferredSize(new Dimension(64, 64));
		bottom.setLayout(new BorderLayout());
		
		grid.setBackground(new Color(0xbbada0));
		
		newGameButton = new JButton("New game");
		newGameButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
		top.add(newGameButton, BorderLayout.WEST);
		
		titleLabel = new JLabel("Twos", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 36));
		top.add(titleLabel, BorderLayout.CENTER);
		
		scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
		scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 16));
		scoreLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
		top.add(scoreLabel, BorderLayout.EAST);
		
		bottomLabel = new JLabel("", SwingConstants.CENTER);
		bottomLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
		bottom.add(bottomLabel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(top);
		add(grid);
		add(bottom);
		
		state = new GameState();
		actionHandler = new ActionHandler(state);
		state.addStateListener(this);
		
		addKeyListener(actionHandler);
		newGameButton.addActionListener(actionHandler);
		newGameButton.addKeyListener(actionHandler);
		
		Timer timer = new Timer(20, this);
		timer.start();
		
		setVisible(true);
	}
	
	private void setBottomLabel(String value) {
		bottomLabel.setText(value);
	}
	
	private void setScore(int score) {
		scoreLabel.setText("Score: " + score);
	}
	
	public GameState getGameState() {
		return state;
	}

	public void stateChanged() {
		if(state.isLost()) {
			setBottomLabel("Game is lost");
		}
		else if(state.getLastShift() == null) {
			setBottomLabel("New game started");
		}
		else {
			setBottomLabel("Shifted tiles " + state.getLastShift().toString().toLowerCase());
		}

		setScore(state.getScore());
		
		if(state.getLastShiftCoords() == null) {
			grid.setNumbers(state.getNumbers());
		}
		else {
			grid.setNumbers(state.getLastNumbers());
			grid.moveTo(state.getLastShiftCoords());
		}
	}

	public void animationFinished() {
		grid.setNumbers(state.getNumbers());
	}

	public void actionPerformed(ActionEvent e) {
		grid.tick();
	}
}
