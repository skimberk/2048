package activity1;

import java.awt.*;

import javax.swing.*;

public class GameView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameState state;
	private ActionHandler actionHandler;
	
	private JPanel top;
	private JPanel bottom;
	private JPanel grid;
	
	private JLabel bottomLabel;
	private JLabel scoreLabel;
	private JLabel titleLabel;
	
	private JButton newGameButton;
	
	private final static Color[] colors = {
			new Color(0xEEE4DA),
			new Color(0xede0c8),
			new Color(0xf2b179),
			new Color(0xf59563),
			new Color(0xf67c5f),
			new Color(0xf65e3b),
			new Color(0xedcf72),
			new Color(0xedcc61),
			new Color(0xedc850),
			new Color(0xedc53f),
			new Color(0xedc22e),
	};

	public GameView() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setTitle("Sebastian's Twos Game");
		setSize(544, 672);
		setResizable(false);
		
		top = new JPanel();
		bottom = new JPanel();
		grid = new JPanel();
		
		top.setPreferredSize(new Dimension(64, 64));
		top.setLayout(new BorderLayout());

		bottom.setPreferredSize(new Dimension(64, 64));
		bottom.setLayout(new BorderLayout());
		
		grid.setLayout(new GridLayout(4, 4, 16, 16));
		grid.setPreferredSize(new Dimension(512, 512));
		grid.setBackground(new Color(0xbbada0));
		grid.setBorder(BorderFactory.createLineBorder(new Color(0xbbada0), 16));
		
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
		
		state = new GameState(this);
		actionHandler = new ActionHandler(state);
		
		addKeyListener(actionHandler);
		newGameButton.addActionListener(actionHandler);
		newGameButton.addKeyListener(actionHandler);
		
		setVisible(true);
	}
	
	public void drawTiles(int[] values) {
		grid.removeAll();
		
		for(int value : values) {
			grid.add(createNumberLabel(value));
		}
		
		grid.revalidate();
	}
	
	public void setBottomLabel(String value) {
		bottomLabel.setText(value);
	}
	
	private static JLabel createNumberLabel(int number) {
		JLabel label = new JLabel("" + number, SwingConstants.CENTER);
		
		int fontSize = 55;
		
		if(number > 999) {
			fontSize = 35;
		}
		else if(number > 99) {
			fontSize = 45;
		}
		
		Color fontColor = new Color(0x776E65);
		
		if(number >= 8) {
			fontColor = new Color(0xf9f6f2);
		}
		
		int colorIndex = (int) (Math.log(number) / Math.log(2)) - 1;
		
		if(colorIndex < 0 || colorIndex >= colors.length) {
			colorIndex = 0;
		}
		
		Color backgroundColor = colors[colorIndex];
		
		label.setOpaque(true);
		label.setBackground(backgroundColor);
		label.setForeground(fontColor);
		label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
		
		return label;
	}
}
