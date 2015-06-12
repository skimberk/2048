package activity1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionHandler implements KeyListener, ActionListener {
	
	private GameState state;
	
	public ActionHandler(GameState state) {
		this.state = state;
	}

	public void keyTyped(KeyEvent e) {
		// Do nothing
	}

	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		Direction direction = null;
		
		if(keyCode == 37) {
			direction = Direction.LEFT;
		}
		else if(keyCode == 38) {
			direction = Direction.UP;
		}
		else if(keyCode == 39) {
			direction = Direction.RIGHT;
		}
		else if(keyCode == 40) {
			direction = Direction.DOWN;
		}
		
		if(direction != null) {
			state.shiftDirection(direction);
		}
	}

	public void keyReleased(KeyEvent e) {
		// Do nothing
	}

	public void actionPerformed(ActionEvent e) {
		state.newGame();
	}

}
