package activity2;

public class Number {
	private int value;
	private int originalPosition;
	private int finalPosition;
	
	public Number(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getOriginalPosition() {
		return originalPosition;
	}
	
	public void setOriginalPosition(int originalPosition) {
		this.originalPosition = originalPosition;
	}
	
	public int getFinalPosition() {
		return finalPosition;
	}
	
	public void setFinalPosition(int finalPosition) {
		this.finalPosition = finalPosition;
	}
	
	public String toString() {
		return "" + value;
	}
	
	public boolean equals(Number other) {
		return other != null && value == other.getValue();
	}
}
