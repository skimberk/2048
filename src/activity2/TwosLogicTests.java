package activity2;

//import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import testHelp.verify;

public class TwosLogicTests {

	@Test
	public void mergeLeftFunctionsCorrectly() {
		int[] row;
		
		row = new int[] {0, 0, 0, 2};
		TwosLogic.mergeLeft(row, null, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {2, 0, 0, 0})).isTrue();
		
		row = new int[] {0, 0, 2, 2};
		TwosLogic.mergeLeft(row, null, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {4, 0, 0, 0})).isTrue();
		
		row = new int[] {0, 2, 2, 2};
		TwosLogic.mergeLeft(row, null, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {4, 2, 0, 0})).isTrue();
		
		row = new int[] {2, 2, 2, 2};
		TwosLogic.mergeLeft(row, null, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {4, 4, 0, 0})).isTrue();
		
		row = new int[] {8, 0, 0, 8};
		TwosLogic.mergeLeft(row, null, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {16, 0, 0, 0})).isTrue();
		
		row = new int[] {8, 2, 0, 8};
		TwosLogic.mergeLeft(row, null, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {8, 2, 8, 0})).isTrue();
		
		row = new int[] {8, 4, 2, 0};
		TwosLogic.mergeLeft(row, null, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {8, 4, 2, 0})).isTrue();
		
		row = new int[] {4, 2, 0, 2};
		TwosLogic.mergeLeft(row, null, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {4, 4, 0, 0})).isTrue();
		
		row = new int[] {4, 2, 2, 4};
		TwosLogic.mergeLeft(row, null, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {4, 4, 4, 0})).isTrue();
	}
	
	@Test
	public void mergeLeftRecordsMovementCorrectly() {
		int[] row = new int[] {2, 2, 0, 2};
		int[] movement = new int[] {-1, -1, -1, -1};

		TwosLogic.mergeLeft(row, movement, 0, row.length);
		verify.that(Arrays.equals(movement, new int[] {-1, 0, -1, 1})).isTrue();
	}
	
	@Test
	public void reverseEntireArrayWorksCorrectly() {
		int[] array = new int[] {1, 2, 3, 4};
		TwosLogic.reverse(array, 0, array.length);
		verify.that(Arrays.equals(array, new int[] {4, 3, 2, 1})).isTrue();
	}
	
	@Test
	public void reverseEmptyArrayWorksCorrectly() {
		int[] array = new int[] {};
		TwosLogic.reverse(array, 0, array.length);
		verify.that(Arrays.equals(array, new int[] {})).isTrue();
	}
	
	@Test
	public void reverseArrayOfOneWorksCorrectly() {
		int[] array = new int[] {1};
		TwosLogic.reverse(array, 0, array.length);
		verify.that(Arrays.equals(array, new int[] {1})).isTrue();
	}
	
	@Test
	public void reversePartOfArrayWorksCorrectly() {
		int[] array = new int[] {1, 2, 3, 4};
		TwosLogic.reverse(array, 1, 3);
		verify.that(Arrays.equals(array, new int[] {1, 3, 2, 4})).isTrue();
	}
	
	@Test
	public void anyMovesPossibleReturnsFalseWhenNoMovesPossible() {
		int[] numbers = new int[] {2, 4, 4, 2};
		verify.that(TwosLogic.anyMovesPossible(numbers)).isFalse();
	}
	
	@Test
	public void anyMovesPossibleReturnsTrueWhenMovesArePossible() {
		int[] numbers = new int[] {2, 2, 4, 8};
		verify.that(TwosLogic.anyMovesPossible(numbers)).isTrue();
	}
	
	@Test
	public void transposeWorksCorrectly() {
		int[] array = new int[] {1, 2, 3, 4};
		TwosLogic.transpose(array);
		verify.that(Arrays.equals(array, new int[] {1, 3, 2, 4})).isTrue();
	}
	
	@Test
	public void toStringWorksCorrectly() {
		int[] array = new int[] {1, 2, 3, 4};
		verify.that(TwosLogic.toString(array)).isEqualTo("1 2 \n3 4 \n");
	}
	
	@Test
	public void addRandomAddsTwoOrFour() {
		int[] array = new int[] {0, 0, 0, 0};
		TwosLogic.addRandom(array);
		
		int numZeros = 0;
		int added = 0;
		
		for(int number : array) {
			if(number == 0) {
				numZeros++;
			}
			else {
				added = number;
			}
		}
		
		verify.that(numZeros == 3 && added == 2 || added == 4).isTrue();
	}

}
