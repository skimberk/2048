package activity2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TwosLogic {
	public static Number[][] swapRowsColumns(Number[][] numbers) {
		Number[][] swapped = new Number[numbers[0].length][numbers.length];
		
		for(int row = 0; row < numbers.length; row++) {
			for(int column = 0; column < numbers[0].length; column++) {
				swapped[column][row] = numbers[row][column];
			}
		}
		
		return swapped;
	}
	
	private static Number[] removeNullEntries(Number[] numbers) {
		ArrayList<Number> noNulls = new ArrayList<Number>();
		
		for(Number number : numbers) {
			if(number != null) {
				noNulls.add(number);
			}
		}
		
		return noNulls.toArray(new Number[noNulls.size()]);
	}
	
	public static Number[] reverse(Number[] numbers) {
		List<Number> list = Arrays.asList(Arrays.copyOf(numbers, numbers.length));
		
		Collections.reverse(list);
		
		int length = numbers.length;
		
		for(Number number : list) {
			if(number == null) continue;
			
			number.setOriginalPosition(length - number.getOriginalPosition() - 1);
			number.setFinalPosition(length - number.getFinalPosition() - 1);
		}
		
		return list.toArray(new Number[length]);
	}
	
	public static Number[] mergeLeft(Number[] original) {
		for(int i = 0; i < original.length; i++) {
			if(original[i] == null) continue;

			original[i].setOriginalPosition(i);
		}
		
		Number[] noNulls = removeNullEntries(original);
		Number[] finalNumbers = new Number[original.length];
		
		int positionInFinal = 0;
		
		for(int i = 0; i < noNulls.length; i++) {
			if(i != noNulls.length - 1 && noNulls[i].equals(noNulls[i + 1])) {
				finalNumbers[positionInFinal] = new Number(noNulls[i].getValue() + noNulls[i + 1].getValue());
				noNulls[i].setFinalPosition(positionInFinal);
				noNulls[i + 1].setFinalPosition(positionInFinal);
				i++;
			}
			else {
				finalNumbers[positionInFinal] = new Number(noNulls[i].getValue());
				noNulls[i].setFinalPosition(positionInFinal);
			}
			
			positionInFinal++;
		}
		
		return finalNumbers;
	}
	
	public static Number[][] reverseAll(Number[][] numbers) {
		Number[][] reversed = new Number[numbers.length][];
		
		for(int row = 0; row < numbers.length; row++) {
			reversed[row] = reverse(numbers[row]);
		}
		
		return reversed;
	}
	
	public static Number[][] mergeLeftAll(Number[][] numbers) {
		Number[][] merged = new Number[numbers.length][];
		
		for(int row = 0; row < numbers.length; row++) {
			merged[row] = mergeLeft(numbers[row]);
		}
		
		return merged;
	}
	
	public static String boardToString(Number[][] numbers) {
		String string = "";

		for(int row = 0; row < numbers.length; row++) {
			for(int column = 0; column < numbers[0].length; column++) {
				string += (numbers[row][column] == null ? "." : numbers[row][column]) + " ";
			}
			
			string += "\n";
		}
		
		return string;
	}
	
	public static int[] boardToInts(Number[][] numbers) {
		int[] out = new int[numbers.length * numbers[0].length];
		
		int i = 0;
		
		for(int row = 0; row < numbers.length; row++) {
			for(int column = 0; column < numbers[0].length; column++) {
				Number number = numbers[row][column];
				
				if(number == null) {
					out[i] = 0;
				}
				else {
					out[i] = number.getValue();
				}
				
				i++;
			}
		}
		
		return out;
	}
	
	public static Number[][] addRandom(Number[][] numbers) {
		int numNulls = 0;
		
		for(int row = 0; row < numbers.length; row++) {
			for(int column = 0; column < numbers[0].length; column++) {
				if(numbers[row][column] == null) {
					numNulls++;
				}
			}
		}
		
		int i = 0;
		
		for(int row = 0; row < numbers.length; row++) {
			for(int column = 0; column < numbers[0].length; column++) {
				if(numbers[row][column] == null) {
					if(((int) (Math.random() * numNulls)) == 0 || i == numNulls - 1) {
						numbers[row][column] = new Number(2);
						return numbers;
					}
					
					i++;
				}
			}
		}
		
		return numbers;
	}
	
	public static boolean equal(Number[][] first, Number[][] second) {
		for(int row = 0; row < first.length; row++) {
			for(int column = 0; column < first[0].length; column++) {
				Number firstNumber = first[row][column];
				Number secondNumber = second[row][column];
				if(firstNumber != null && !firstNumber.equals(secondNumber)
						|| firstNumber == null && secondNumber != null
						|| firstNumber != null && secondNumber == null) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static Number[][] left(Number[][] numbers) {
		return mergeLeftAll(numbers);
	}
	
	public static Number[][] right(Number[][] numbers) {
		return reverseAll(mergeLeftAll(reverseAll(numbers)));
	}
	
	public static Number[][] up(Number[][] numbers) {
		return swapRowsColumns(mergeLeftAll(swapRowsColumns(numbers)));
	}
	
	public static Number[][] down(Number[][] numbers) {
		return swapRowsColumns(reverseAll(mergeLeftAll(reverseAll(swapRowsColumns(numbers)))));
	}
	
//	public static void main(String[] args) {
////		Number[] numbers = new Number[] {
////				new Number(2),
////				new Number(4),
////				new Number(4),
////				new Number(16)
////		};
////		
////		System.out.println(Arrays.toString(reverse(mergeLeft(reverse(numbers)))));
////		
////		Number[] reversed = reverse(numbers);
////		
////		for(Number number : reversed) {
////			if(number == null) continue;
////			
////			System.out.println(number.getOriginalPosition() + " --> " + number.getFinalPosition());
////		}
//		
//		Number[][] board = new Number[4][4];
//		
//		board[3][3] = new Number(2);
//		board[3][0] = new Number(2);
//		
//		System.out.println("Initial");
//		System.out.println(boardToString(board));
//		
//		System.out.println("Left");
//		System.out.println(boardToString(mergeLeftAll(board)));
//		
//		System.out.println("Right");
//		System.out.println(boardToString(reverseAll(mergeLeftAll(reverseAll(board)))));
//		
//		System.out.println("Up");
//		System.out.println(boardToString(swapRowsColumns(mergeLeftAll(swapRowsColumns(board)))));
//		
//		System.out.println("Down");
//		System.out.println(boardToString(swapRowsColumns(reverseAll(mergeLeftAll(reverseAll(swapRowsColumns(board)))))));
//	}
}
