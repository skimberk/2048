package activity2;

public class GameState {
	private Number[][] numbers;
	private boolean isLost;
	
	private GameView view;
	
	public GameState(GameView view) {
		this.view = view;
		
		newGame();
	}
	
	public void shiftDirection(Direction direction) {
		if(!isLost) {
			view.setBottomLabel("Shifted tiles " + direction.toString().toLowerCase());
			
			Number[][] left = TwosLogic.left(numbers);
			Number[][] right = TwosLogic.right(numbers);
			Number[][] up = TwosLogic.up(numbers);
			Number[][] down = TwosLogic.down(numbers);
			
			if(TwosLogic.equal(numbers, left) && TwosLogic.equal(numbers, right)
					&& TwosLogic.equal(numbers, up) && TwosLogic.equal(numbers, down)) {
				view.setBottomLabel("Game is lost!");
				isLost = true;
				return;
			}
			
			Number[][] shifted = null;
			
			if(direction == Direction.LEFT) {
				shifted = left;
			}
			else if(direction == Direction.RIGHT) {
				shifted = right;
			}
			else if(direction == Direction.UP) {
				shifted = up;
			}
			else if(direction == Direction.DOWN) {
				shifted = down;
			}
			
			if(shifted != null && !TwosLogic.equal(numbers, shifted)) {
				numbers = TwosLogic.addRandom(shifted);
				
				int[] values = TwosLogic.boardToInts(numbers);
				view.drawTiles(values);
			}
		}
	}
	
	public int[] getNumbers() {
		return TwosLogic.boardToInts(numbers);
	}
	
	public boolean isLost() {
		return isLost;
	}
	
	public void newGame() {
		view.setBottomLabel("New game started");
		isLost = false;
		
		numbers = new Number[4][4];
		
		numbers[1][2] = new Number(2);
		numbers[3][0] = new Number(2);
		
		int[] values = TwosLogic.boardToInts(numbers);
		view.drawTiles(values);
	}
}
