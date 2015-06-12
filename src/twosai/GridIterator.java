package twosai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class GridIterator implements Iterator<Node> {
	
	private Stack<Grid> children = new Stack<Grid>();
	
	public GridIterator(Grid grid) {
		
		Grid left = grid.createChild();
		left.moveLeft();
		
		if(!left.equals(grid)) {
			ArrayList<Integer> indices = left.getZeroIndices();
			
			if(indices.size() == 0) {
				children.push(left);
			}
			else {
				for(int index : indices) {
					Grid copy = left.clone();
					copy.setNumberAtIndex(index, 2);
					children.push(copy);
				}
			}
		}
		
		Grid right = grid.createChild();
		right.moveRight();
		
		if(!right.equals(grid)) {
			ArrayList<Integer> indices = right.getZeroIndices();
			
			if(indices.size() == 0) {
				children.push(right);
			}
			else {
				for(int index : indices) {
					Grid copy = right.clone();
					copy.setNumberAtIndex(index, 2);
					children.push(copy);
				}
			}
		}
		
		Grid up = grid.createChild();
		up.moveUp();
		
		if(!up.equals(grid)) {
			ArrayList<Integer> indices = up.getZeroIndices();
			
			if(indices.size() == 0) {
				children.push(up);
			}
			else {
				for(int index : indices) {
					Grid copy = up.clone();
					copy.setNumberAtIndex(index, 2);
					children.push(copy);
				}
			}
		}
		
		Grid down = grid.createChild();
		down.moveDown();
		

		if(!down.equals(grid)) {
			ArrayList<Integer> indices = down.getZeroIndices();
			
			if(indices.size() == 0) {
				children.push(down);
			}
			else {
				for(int index : indices) {
					Grid copy = down.clone();
					copy.setNumberAtIndex(index, 2);
					children.push(copy);
				}
			}
		}
	}

	public boolean hasNext() {
		return !children.isEmpty();
	}

	public Grid next() {
		if(children.isEmpty()) {
			return null;
		}
		else {
			return children.pop();
		}
	}

}
