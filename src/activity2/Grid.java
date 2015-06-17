package activity2;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Grid extends JPanel {

	private static final long serialVersionUID = 1L;

	private int rows;
	private int columns;
	
	private AnimationFinishListener listener;
	
	private Box[][] grid;
	
	private Ease ease;
	
	public Grid(int rows, int columns, AnimationFinishListener listener) {
		this.rows = rows;
		this.columns = columns;
		
		this.listener = listener;
		
		grid = new Box[rows][columns];
		
		setLayout(null);
		setPreferredSize(new Dimension(120 * columns + 20, 120 * rows + 20));
	}
	
	public void setNumbers(int[] numbers) {
		removeAll();
		
		for(int i = 0; i < numbers.length; i++) {
			int number = numbers[i];
			int row = i / columns;
			int column = i % columns;
			
			if(number != 0) {
				Box box = new Box();
				box.setValue(number);
				box.setPosition(120 * column + 20, 120 * row + 20);
				
				add(box);
				grid[row][column] = box;
			}
			else {
				grid[row][column] = null;
			}
		}
		
		validate();
		repaint();
	}
	
	public void moveTo(Coord[] coordinates) {
		ease = new Ease(150);

		for(int i = 0; i < coordinates.length; i++) {
			int row = i / columns;
			int column = i % columns;
			Coord coord = coordinates[i];
			Box box = grid[row][column];

			if(box != null && coord != null) {
				box.goTo(120 * coord.getColumn() + 20, 120 * coord.getRow() + 20, ease);
			}
		}
	}
	
	public void tick() {
		if(ease != null) {
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					Box box = grid[row][column];

					if(box != null) {
						box.tick();
					}
				}
			}
			
			if(ease.isDone()) {
				ease = null;
				listener.animationFinished();
			}
		}
	}
}
