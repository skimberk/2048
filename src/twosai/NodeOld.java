package twosai;

import activity2.Direction;
import java.util.ArrayList;

public class NodeOld {
	private int[] numbers;
	private Direction direction;
	private int depth;
	public double probability;
	
	public static int iterations = 0;
	
	private ArrayList<NodeOld> children = new ArrayList<NodeOld>();
	
	public NodeOld(int[] numbers, Direction direction, int depth, double probability) {
		this.numbers = numbers;
		this.direction = direction;
		this.depth = depth;
		this.probability = probability;
		
		iterations++;
		
		if(depth != 0) {
			int[] left = TwosLogic.clone(numbers);
			int[] right = TwosLogic.clone(numbers);
			int[] up = TwosLogic.clone(numbers);
			int[] down = TwosLogic.clone(numbers);
			
			addAllPossibilities(left, Direction.LEFT);
			addAllPossibilities(right, Direction.RIGHT);
			addAllPossibilities(up, Direction.UP);
			addAllPossibilities(down, Direction.DOWN);
			
			int halfSize = children.size() / 2;
			
			for(NodeOld node : children) {
				node.probability /= halfSize;
			}
		}
	}
	
	public void addAllPossibilities(int[] numbers, Direction direction) {
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] == 0) {
				int[] two = TwosLogic.clone(numbers);
				two[i] = 2;
				
				children.add(new NodeOld(two, direction, depth - 1, 0.9));
				
				int[] four = TwosLogic.clone(numbers);
				four[i] = 4;
				
				children.add(new NodeOld(four, direction, depth - 1, 0.1));
			}
		}
	}
	
	public double getScore() {
		double score = 0;

		if(depth == 0) {
			int highest = 0;
			int highestIndex = -1;
			for(int number : numbers) {
				if(number == 0) {
					score += 1000;
				}
				
				if(number > highest) {
					highest = number;
				}
			}
			
			score += Math.pow(highest, 20);
		}
		else {			
			for(NodeOld node : children) {
				score += node.getScore() * node.probability;
			}
		}
		
		return score;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public Direction getBestDirection() {
		double bestScore = -1;
		Direction bestDirection = null;
		
		for(NodeOld node : children) {
			double score = node.getScore();
			
			if(score > bestScore) {
				bestScore = score;
				bestDirection = node.getDirection();
			}
		}
		
		return bestDirection;
	}
	
	public static void main(String[] args) {
		int[] grid = {
				0, 2, 0, 0,
				0, 0, 0, 0,
				0, 0, 2, 0,
				0, 0, 0, 0
		};
		
		for(int i = 0; i < 10; i++) {
			System.out.println(TwosLogic.toString(grid));
			System.out.println();
			
			NodeOld top = new NodeOld(grid, null, 3, 1.0);
			Direction direction = top.getBestDirection();
			
			grid = TwosLogic.clone(grid);
			
			System.out.println(direction);
			
			if(direction == Direction.LEFT) {
				TwosLogic.mergeLeftRows(grid);
			}
			else if(direction == Direction.RIGHT) {
				TwosLogic.mergeRightRows(grid);
			}
			else if(direction == Direction.UP) {
				TwosLogic.mergeUpRows(grid);
			}
			else if(direction == Direction.DOWN) {
				TwosLogic.mergeDownRows(grid);
			}
			
			TwosLogic.addRandom(grid);
		}
		
//		Node start = new Node(new int[] {
//				2, 2, 2, 128,
//				2, 2, 2, 2,
//				2, 2, 2, 2,
//				2, 2, 2, 2
//				}, null, 0, 3, 1.0);
//		
//		System.out.println(iterations);

//		System.out.println(start.getBestDirection());
	}
}
