package activity2;

import java.util.ArrayList;
import java.util.Arrays;

public class TwosLogic {
	public static int mergeLeft(int[] numbers, int[] movement, int from, int to) {
		int i = from;
		int score = 0;
		
		for(int j = from + 1; j < to; j++) {
			int n = numbers[i];
			int m = numbers[j];
			
//			for(int k = 0; k < numbers.length; k++) {
//				if(k == i) {
//					System.out.print(">");
//				}
//				if(k == j) {
//					System.out.print("^");
//				}
//				
//				System.out.print(numbers[k] + " ");
//			}
//			System.out.println();
			
			if(m != 0 && n != m && n != 0) {
				i++;
				n = numbers[i];
				
				if(i == j) {
					continue;
				}
			}
			
			if(m != 0 && m == n) {
				score += 2 * m;
				numbers[i] = 2 * m;
				numbers[j] = 0;
				
				if(movement != null) {
					movement[j] = i - from;
				}
				
				i++;
			}
			else if(m != 0 && n == 0) {
				numbers[i] = m;
				numbers[j] = 0;
				
				if(movement != null) {
					movement[j] = i - from;
				}
			}
		}
		
		return score;
	}
	
	public static void reverse(int[] numbers, int from, int to) {
		int end = (to - from) / 2;
		
		for(int i = 0; i < end; i++) {
			int temp = numbers[from + i];
			numbers[from + i] = numbers[to - i - 1];
			numbers[to - i - 1] = temp;
		}
	}
	
	public static int mergeLeftRows(int[] numbers, int[] movement) {
		int size = (int) Math.sqrt(numbers.length);
		int score = 0;
		
		for(int i = 0; i < size; i++) {
			score += mergeLeft(numbers, movement, i * size, i * size + size);
		}
		
		return score;
	}
	
	public static int mergeRightRows(int[] numbers, int[] movement) {
		reverseRows(numbers);
		if(movement != null) {
			reverseRows(movement);
		}
		
		int score = mergeLeftRows(numbers, movement);
		
		reverseRows(numbers);
		if(movement != null) {
			reverseRows(movement);
		}
		
		return score;
	}
	
	public static int mergeUpRows(int[] numbers, int[] movement) {
		transpose(numbers);
		if(movement != null) {
			transpose(movement);
		}

		int score = mergeLeftRows(numbers, movement);

		transpose(numbers);
		if(movement != null) {
			transpose(movement);
		}
		
		return score;
	}
	
	public static int mergeDownRows(int[] numbers, int[] movement) {
		transpose(numbers);
		if(movement != null) {
			transpose(movement);
		}

		int score = mergeRightRows(numbers, movement);

		transpose(numbers);
		if(movement != null) {
			transpose(movement);
		}
		
		return score;
	}
	
	public static boolean anyMovesPossible(int[] numbers) {
		int[] shifted = clone(numbers);
		mergeLeftRows(shifted, null);
		if(!equals(numbers, shifted)) return true;
		
		copyFromTo(numbers, shifted);
		mergeRightRows(shifted, null);
		if(!equals(numbers, shifted)) return true;

		copyFromTo(numbers, shifted);
		mergeUpRows(shifted, null);
		if(!equals(numbers, shifted)) return true;
		
		copyFromTo(numbers, shifted);
		mergeDownRows(shifted, null);
		if(!equals(numbers, shifted)) return true;
		
		return false;
	}
	
	public static void reverseRows(int[] numbers) {
		int size = (int) Math.sqrt(numbers.length);
		
		for(int i = 0; i < size; i++) {
			reverse(numbers, i * size, i * size + size);
		}
	}
	
	public static void transpose(int[] numbers) {
		int size = (int) Math.sqrt(numbers.length);
		
		for(int i = 0; i < size - 1; i++) {
			for(int j = i + 1; j < size; j++) {
				int m = i + j * size;
				int n = j + i * size;
				
				int temp = numbers[m];
				numbers[m] = numbers[n];
				numbers[n] = temp;
			}
		}
	}
	
	public static void addRandom(int[] numbers) {
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] == 0) {
				indices.add(i);
			}
		}
		
		if(!indices.isEmpty()) {
			int index = indices.get((int) (Math.random() * indices.size()));
			
			numbers[index] = Math.random() < 0.9 ? 2 : 4;
		}
	}
	
	public static int[] clone(int[] numbers) {
		return Arrays.copyOf(numbers, numbers.length);
	}
	
	public static boolean equals(int[] first, int[] second) {
		return Arrays.equals(first, second);
	}
	
	public static void copyFromTo(int[] from, int[] to) {
		for(int i = 0; i < from.length; i++) {
			to[i] = from[i];
		}
	}

	public static String toString(int[] numbers) {
		int size = (int) Math.sqrt(numbers.length);
		String string = "";
		
		for(int i = 0; i < numbers.length; i++) {
			string += numbers[i] + " ";
			
			if(i % size == size - 1) {
				string += "\n";
			}
		}
		
		return string;
	}
	
	public static Coord[] movementCoords(int[] movement, Direction direction) {
		int size = (int) Math.sqrt(movement.length);
		Coord[] coords = new Coord[movement.length];
		
		for(int i = 0; i < movement.length; i++) {
			int row = i / size;
			int column = i % size;
			int value = movement[i];
			
			if(value == -1) {
				coords[i] = new Coord(row, column);
			}
			else if(direction == Direction.LEFT) {
				coords[i] = new Coord(row, value);
			}
			else if(direction == Direction.RIGHT) {
				coords[i] = new Coord(row, size - value - 1);
			}
			else if(direction == Direction.UP) {
				coords[i] = new Coord(value, column);
			}
			else if(direction == Direction.DOWN) {
				coords[i] = new Coord(size - value - 1, column);
			}
		}
		
		return coords;
	}
}
