/**
 * 
 */
package net.com.FloodIt;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author sbecht
 *
 */
public class FloodTest {
	
	public static void main (String[] args) {
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.blue);
		colors.add(Color.green);
		colors.add(Color.red);
		colors.add(Color.yellow);
		/*Grid testGrid = new Grid(2, colors);
		System.out.println("New game");
		testGrid.grid[0][1].setColor(Color.yellow);
		testGrid.printGrid();
		testGrid.floodTiles(Color.yellow);
		testGrid.floodTiles(Color.green);*/

		Game testGame = new Game(10, colors, 10, 0);
		System.out.println("New game");
		/*testGame.onClickFlood(Color.blue);
		testGame.onClickFlood(Color.blue);
		testGame.onClickFlood(Color.blue);
		testGame.onClickFlood(Color.blue);
		*/

		//testGame.getGrid().printGrid();
		AI player = new AI();
		/*int counter = 0;
		while (testGame.getGrid().getFlood().size() < 16) {
			Color move = player.takeTurn();
			System.out.println(move);
			testGame.onClickFlood(move);
			counter++;
			
		}
		System.out.println(counter);*/		
		
		Color move = player.takeTurn(testGame);
		System.out.println("Winning color" + move);
		
		
		
	}

}
