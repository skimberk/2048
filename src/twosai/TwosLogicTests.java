package twosai;

//import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import testHelp.verify;

public class TwosLogicTests {

	@Test
	public void mergeLeftFunctionsCorrectly() {
		int[] row;
		
		row = new int[] {0, 0, 0, 2};
		TwosLogic.mergeLeft(row, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {2, 0, 0, 0})).isTrue();
		
		row = new int[] {0, 0, 2, 2};
		TwosLogic.mergeLeft(row, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {4, 0, 0, 0})).isTrue();
		
		row = new int[] {0, 2, 2, 2};
		TwosLogic.mergeLeft(row, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {4, 2, 0, 0})).isTrue();
		
		row = new int[] {2, 2, 2, 2};
		TwosLogic.mergeLeft(row, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {4, 4, 0, 0})).isTrue();
		
		row = new int[] {8, 0, 0, 8};
		TwosLogic.mergeLeft(row, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {16, 0, 0, 0})).isTrue();
		
		row = new int[] {8, 2, 0, 8};
		TwosLogic.mergeLeft(row, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {8, 2, 8, 0})).isTrue();
		
		row = new int[] {8, 4, 2, 0};
		TwosLogic.mergeLeft(row, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {8, 4, 2, 0})).isTrue();
		
		row = new int[] {4, 2, 0, 2};
		TwosLogic.mergeLeft(row, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {4, 4, 0, 0})).isTrue();
		
		row = new int[] {4, 2, 2, 4};
		TwosLogic.mergeLeft(row, 0, row.length);
		verify.that(Arrays.equals(row, new int[] {4, 4, 4, 0})).isTrue();
	}

}
