package activity1;

public class GameState {
	private int[] values = new int[4 * 4];
	
	private GameView view;
	
	public GameState(GameView view) {
		this.view = view;
		
		for(int i = 0; i < values.length; i++) {
			values[i] = i;
		}
		
		newGame();
	}
	
	public void shiftDirection(Direction direction) {
		view.setBottomLabel("Shifted tiles " + direction.toString().toLowerCase());
	}
	
	public void newGame() {
		view.setBottomLabel("New game started");
		
		shuffle(values);
		view.drawTiles(values);
	}
	
	private static void shuffle(int[] array) {
		for(int i = array.length - 1; i >= 0; i--) {
			int randomIndex = (int) (Math.random() * i);
			
			int temp = array[randomIndex];
			array[randomIndex] = array[i];
			array[i] = temp;
		}
	}
}
