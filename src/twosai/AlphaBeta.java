package twosai;

public class AlphaBeta {
	public static Node alphaBeta(Node node, int depth, int alpha, int beta, boolean maxing) {
		if(depth == 0 || node.isTerminal()) {
			return node;
		}
		
		Node value = null;
		
		if(maxing) {
			for(Node child : node) {
				Node best = alphaBeta(child, depth - 1, alpha, beta, false);
				
				if(value == null || best.getScore() > value.getScore()) {
					value = best;
					alpha = Math.max(alpha, best.getScore());
					
					if(beta <= alpha) {
						break;
					}
				}
			}
		}
		else {
			for(Node child : node) {
				Node worst = alphaBeta(child, depth - 1, alpha, beta, true);
				
				if(value == null || worst.getScore() < value.getScore()) {
					value = worst;
					alpha = Math.max(alpha, worst.getScore());
					
					if(beta <= alpha) {
						break;
					}
				}
			}
		}
		
		return value;
	}
}
