package activity2;

import java.util.ArrayList;
import java.util.Arrays;

public class GameState {
	private int[] numbers;
	private int[] lastNumbers;
	private boolean isLost;
	private int score;
	private Direction lastShift;
	private Coord[] lastShiftCoords;
	
	private ArrayList<StateChangeListener> listeners = new ArrayList<StateChangeListener>();
	
	public GameState() {
		newGame();
	}
	
	public void addStateListener(StateChangeListener listener) {
		listeners.add(listener);
		stateChanged();
	}
	
	private void stateChanged() {
		for(StateChangeListener listener : listeners) {
			listener.stateChanged();
		}
	}
	
	public void shiftDirection(Direction direction) {
		if(!isLost) {
			
			if(!TwosLogic.anyMovesPossible(numbers)) {
				isLost = true;
				
				stateChanged();
				return;
			}
			
			int[] shifted = TwosLogic.clone(numbers);
			int[] movement = new int[numbers.length];
			Arrays.fill(movement, -1);
			
			if(direction == Direction.LEFT) {
				score += TwosLogic.mergeLeftRows(shifted, movement);
			}
			else if(direction == Direction.RIGHT) {
				score += TwosLogic.mergeRightRows(shifted, movement);
			}
			else if(direction == Direction.UP) {
				score += TwosLogic.mergeUpRows(shifted, movement);
			}
			else if(direction == Direction.DOWN) {
				score += TwosLogic.mergeDownRows(shifted, movement);
			}
			
			if(shifted != null && !TwosLogic.equals(numbers, shifted)) {
				lastShift = direction;
				lastShiftCoords = TwosLogic.movementCoords(movement, direction);
				lastNumbers = numbers;
				numbers = shifted;
				TwosLogic.addRandom(numbers);
				
				stateChanged();
			}
		}
	}
	
	public int[] getNumbers() {
		return numbers;
	}
	
	public int[] getLastNumbers() {
		return lastNumbers;
	}
	
	public Direction getLastShift() {
		return lastShift;
	}
	
	public Coord[] getLastShiftCoords() {
		return lastShiftCoords;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean isLost() {
		return isLost;
	}
	
	public void newGame() {
		isLost = false;
		score = 0;
		lastShift = null;
		
		numbers = new int[4 * 4];
		lastNumbers = new int[numbers.length];
		
		TwosLogic.addRandom(numbers);
		TwosLogic.addRandom(numbers);
		
		stateChanged();
	}
}
