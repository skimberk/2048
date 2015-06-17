package twosai;

import activity2.Direction;
import activity2.TwosLogic;
import activity2.GameView;
import activity2.GameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Grid implements Node {
	
	private int[] numbers;
	private int score;
	private Grid parent;
	private Direction direction;
	
	public Grid(int[] numbers, int score, Grid parent, Direction direction) {
		this.numbers = numbers;
		this.score = score;
		this.parent = parent;
		this.direction = direction;
	}

	public int getScore() {
		int weightedScore = score + (int)(Math.log(score) * getNumZeros());
		return weightedScore;
	}
	
	private int getNumZeros() {
		int zeros = 0;
		
		for(int number : numbers) {
			if(number == 0) {
				zeros++;
			}
		}
		
		return zeros;
	}
	
	private int getMonotonicScore() {
		int sum = 0;
		
		int lastNumber = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			sum += lastNumber - numbers[i];
		}
		
		return sum;
	}
	
	private int getClusteringScore() {
		double sum = 0;
		
		int length = numbers.length;
		int row = (int) Math.sqrt(length);
		
		for(int i = 0; i < length; i++) {
			double thisSum = 0;
			int number = numbers[i];
			int j;
			
			// To the left
			j = i - 1;
			if(j >= 0 && i / row == j / row) {
				thisSum += Math.abs(number - numbers[j]);
			}
			
			// To the right
			j = i + 1;
			if(j < length && i / row == j / row) {
				thisSum += Math.abs(number - numbers[j]);
			}
			
			// Up
			j = i - row;
			if(j >= 0) {
				thisSum += Math.abs(number - numbers[j]);
			}
			
			// Down
			j = i + row;
			if(j < length) {
				thisSum += Math.abs(number - numbers[j]);
			}
			
			sum += thisSum / 4;
		}

		return (int) sum;
	}

	public boolean isTerminal() {
		return !TwosLogic.anyMovesPossible(numbers);
	}

	public Iterator<Node> iterator() {
		return new GridIterator(this);
	}
	
	public void setNumberAtIndex(int index, int number) {
		numbers[index] = number;
	}
	
	public Grid getParent() {
		return parent;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public ArrayList<Integer> getZeroIndices() {
		ArrayList<Integer> zeroIndices = new ArrayList<Integer>();
		
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] == 0) {
				zeroIndices.add(i);
			}
		}
		
		return zeroIndices;
	}
	
	public Grid clone() {
		return new Grid(Arrays.copyOf(numbers, numbers.length), score, parent, direction);
	}
	
	public Grid createChild() {
		return new Grid(Arrays.copyOf(numbers, numbers.length), score, this, direction);
	}
	
	public int[] getNumbers() {
		return numbers;
	}
	
	public boolean equals(Grid grid) {
		return TwosLogic.equals(this.numbers, grid.getNumbers());
	}
	
	public void moveLeft() {
		score += TwosLogic.mergeLeftRows(numbers, null);
		direction = Direction.LEFT;
	}

	public void moveRight() {
		score += TwosLogic.mergeRightRows(numbers, null);
		direction = Direction.RIGHT;
	}
	
	public void moveUp() {
		score += TwosLogic.mergeUpRows(numbers, null);
		direction = Direction.UP;
	}
	
	public void moveDown() {
		score += TwosLogic.mergeDownRows(numbers, null);
		direction = Direction.DOWN;
	}
	
	public static void main(String[] args) {
//		Grid grid = new Grid(new int[] {2, 2, 0, 0}, 0, null, null);
//		
//		for(Node child : grid) {
//			System.out.println("Child: " + child);
//			System.out.println("Direction: " + ((Grid) child).getDirection());
//			System.out.println("Parent: " + ((Grid) child).getParent());
//			System.out.println();
//		}
		
		GameView view = new GameView();
		GameState state = view.getGameState();
		
		while(!state.isLost()) {
			Node best = AlphaBeta.alphaBeta(new Grid(state.getNumbers(), 0, null, null), 3, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
			
			Node current = best;
			
			while(((Grid) current).getParent().getParent() != null) {
				current = ((Grid) current).getParent();
			}
			
			
			state.shiftDirection(((Grid) current).getDirection());
			System.out.println(((Grid) current).getDirection());
		}
	}

}
