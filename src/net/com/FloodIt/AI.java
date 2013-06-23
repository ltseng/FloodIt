/**
 * 
 */
package net.com.FloodIt;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author sbecht
 *
 */
public class AI {

	//bounded breadth first search
	//think multi-player
	//assumes human makes move that will get them the most tiles that turn
	//somehow connects to appview to update colors

	private Stack<Node> stack = new Stack<Node>();
	private Color currentColor;
	private Color winningColor;
	private int currentCount;
	private int winningCount;
	private int maxDepth;
	private ArrayList<Color> gameColors;

	/**
	 * 
	 */
	public AI() {
		maxDepth = 2;
		//Is the game linked all the way through, i.e. through other players' moves?
	}

	/**
	 * @param game
	 */
	public Color takeTurn(Game game) {
		this.gameColors = game.getGrid().getColors();
		@SuppressWarnings("unchecked")
		Node rootNode = new Node(gameDeepCopy(game), (ArrayList<Color>) this.gameColors.clone());
		stack.push(rootNode);
		winningColor = rootNode.nodeGame.getGrid().getFloodColor();
		winningCount = rootNode.nodeGame.getGrid().getFlood().size();
		rootNode.colors.remove(game.getPlayerList().get(0).getCurrentColor());
		return buildSearchStack(0);
	}

	/**
	 * @return
	 */
	private Game gameDeepCopy(Game g) {
		return new Game(g.getGrid().getSize(), g.getGrid().getColors(), g.maxMoves, g.gamemode);
	}

	/**
	 * @param currentDepth
	 */
	private Color buildSearchStack(int currentDepth) {
		
		if (stack.isEmpty()) {
			return winningColor;
		}
		Node currentNode = stack.peek();
		setCurrentColor(currentDepth, currentNode);
			
		if (currentDepth == maxDepth) {
			currentCount = currentNode.nodeGame.getGrid().getFlood2().size();
			stack.pop();
			currentDepth--;
			System.out.println(currentCount);
			System.out.println(currentColor);
			if (currentCount > winningCount) {
				winningCount = currentCount;
				winningColor = currentColor;
			}
		}
		else if (currentNode.colors.isEmpty()) {
			stack.pop();
			currentDepth--;
		}
		
		else {
			@SuppressWarnings("unchecked")
			Node newNode = new Node(gameDeepCopy(currentNode.nodeGame), (ArrayList<Color>) this.gameColors.clone()); 
			Color firstColor = currentNode.colors.get(0);
			int tempCount = newNode.nodeGame.getGrid().getFlood2().size();
			newNode.nodeGame.floodSimulator(firstColor);
			if (newNode.nodeGame.getGrid().getFlood2().size() != tempCount) {
				stack.push(newNode);
				currentDepth++;
			}
			currentNode.colors.remove(firstColor);
			
			
		}
		return buildSearchStack(currentDepth);

		//interface with appview
	}

	/**
	 * @param currentDepth
	 * @param currentNode
	 */
	private void setCurrentColor(int currentDepth, Node currentNode) {
		if (currentDepth == 1) {
			currentColor = currentNode.nodeGame.getGrid().getFloodColor();
		}
	}
}

class Node {
	public Game nodeGame;
	public ArrayList<Color> colors;

	/**
	 * @param game
	 */
	public Node(Game game, ArrayList<Color> gameColors) {
		nodeGame = game;
		colors = gameColors;
	}

}


