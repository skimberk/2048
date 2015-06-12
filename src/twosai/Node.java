package twosai;

import java.util.Iterator;

public interface Node extends Iterable<Node> {
	abstract public int getScore();
	abstract public boolean isTerminal();
	abstract public Iterator<Node> iterator();
}
