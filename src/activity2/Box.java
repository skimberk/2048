package activity2;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Box extends JLabel {
	private static final long serialVersionUID = 1L;

	private int width = 100;
	private int height = 100;
	
	private int destinationX = 0;
	private int destinationY = 0;
	
	private int startX = 0;
	private int startY = 0;
	
	private Ease ease = null;
	
	private final static Color[] colors = {
		new Color(0xccc0b4),
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

	public Box() {
		setOpaque(true);
		setBounds(startX, startY, width, height);
		setHorizontalAlignment(CENTER);
	}
	
	public void setValue(int value) {
		int fontSize = 55;
		
		if(value > 999) {
			fontSize = 35;
		}
		else if(value > 99) {
			fontSize = 45;
		}
		
		Color fontColor = new Color(0x776E65);
		
		if(value >= 8) {
			fontColor = new Color(0xf9f6f2);
		}
		
		int colorIndex = (int) (Math.log(value) / Math.log(2));
		
		if(colorIndex < 0 || colorIndex >= colors.length) {
			colorIndex = 0;
		}
		
		Color backgroundColor = colors[colorIndex];
		
		setBackground(backgroundColor);
		setForeground(fontColor);
		setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
		setText("" + value);
	}
	
	public void tick() {
		if(ease != null) {
			double progress = ease.getProgress();

			int x = (int) Ease.fromTo(startX, destinationX, progress);
			int y = (int) Ease.fromTo(startY, destinationY, progress);
			
			setBounds(x, y, width, height);
			
			validate();
			repaint();
			
			if(ease.isDone()) {
				ease = null;
			}
		}
	}
	
	public void goTo(int x, int y, Ease ease) {
		startX = destinationX;
		startY = destinationY;
		
		destinationX = x;
		destinationY = y;
		
		this.ease = ease;
	}
	
	public void setPosition(int x, int y) {
		startX = x;
		startY = y;
		
		destinationX = x;
		destinationY = y;
		
		setBounds(x, y, width, height);
		
		validate();
		repaint();
	}
}
