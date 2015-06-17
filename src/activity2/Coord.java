package activity2;

public class Coord {
	private int row;
	private int column;
	
	public Coord(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String toString() {
		return "(" + row + "," + column + ")";
	}
}
