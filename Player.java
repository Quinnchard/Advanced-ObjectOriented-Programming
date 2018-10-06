// Programmed by Jay J. James JR
// 10/5/2018

import java.util.Scanner;
import java.util.Random;

public class Player {
	protected static Scanner scan = new Scanner(System.in);
	protected int[][] Board;
	protected int numTorpedos;
	protected int numShips;

	public Player(){
		// Each instance of player will have their own
		// board, number of torpedos,
		// and number of ships

		this.Board = new int[9][9];
		this.numTorpedos = 45;
		this.numShips = 6;
	}

	public void placeShipsHuman(){
		// Each ship will be represented as an integer value
		// It's size will be equal to it's integer value as well;
		int ships = this.numShips;
		while(ships > 0){
			// For a total of 6 times the user will be prompted to select
			// an xy coordinate pair to place on the board
			System.out.println("Enter x coordinate");
			int x = scan.nextInt();
			System.out.println("Enter y coordinate");
			int y = scan.nextInt();

			// If the coordinate is outside of array bounds, we will not accept it
			if(x < 0 || x > 8 || y < 0 || y > 8){
				System.out.println("Cannot enter coordinate out of bounds");
			}
			// If the ship already exists on the board we will not accept it
			else if(this.Board[x][y] != 0){
					System.out.println("A ship already exists at this location");
			}
			// Else the ship of size and value will be placed on the board
			else{
					this.Board[x][y] = ships;
					
					ships--;
			}

				
		}
		

	}

	public void placeShipsComputer(){
		// This method will be inherited by the computer class
		// to randomly place ships on the board
		int ships = this.numShips;
		while(ships > 0){
			// We generate random points to place on our grid
			// The random.nextInt function will never go out of
			// bounds with the array length passed into it so
			// we dont have to check bound errors
			int x = new Random().nextInt(9);
			int y = new Random().nextInt(9);
			// If a ship exists already, we wont place it at this location
			if(this.Board[x][y] != 0){
				
			}
			// If it does not, we place a new ship
			else{
					this.Board[x][y] = ships;
					
					ships--;
			}
			
		}
	}

	public void displayMatrix(){
		// This method simply iterates the matrix
		// and prints it's values
		for(int i = 0; i < Board.length; i++){
			for(int j = 0; j < Board.length; j++){
				System.out.print("[" + Board[i][j] + "]" + " ");
			}
			System.out.println();
		}
	}

	// Returns the matrix
	public int[][] getBoard(){
		return this.Board;
	}

	public void refreshBoard(){
		// This method will refresh the board after every game
		// so that players can place new values on it
		this.Board = new int[9][9];
	}

	public boolean launchHuman(Torpedo torpedo, Player opponent){


		// This method will return a boolean value signaling
		// whether or not a player can still launch a torpedo

		// This method takes a torpedo representing a xy coordinate
		// and will use it to attack a ship on the board of another
		// player

		// If we return false, that means either a player has
		// lost all of their ships or a player has ran out of
		// torpedos

		boolean userCanLaunch = true;
		Scanner scan = new Scanner(System.in);
		// If a player runs out of torpedos we return false
		if(numTorpedos == 0)
		{
			System.out.println("Player is out of torpedos!!!");
			userCanLaunch = false;
			return userCanLaunch;
		}
		// If a player correctly strikes a coordinate on their opponents board we will enter a bonus round to hit the opponent again

		else if(opponent.getBoard()[torpedo.getX()][torpedo.getY()] == 1|| opponent.getBoard()[torpedo.getX()][torpedo.getX()] == 2 || opponent.getBoard()[torpedo.getX()][torpedo.getY()] == 3|| 
			opponent.getBoard()[torpedo.getX()][torpedo.getY()] == 4 || opponent.getBoard()[torpedo.getX()][torpedo.getY()] == 5 || opponent.getBoard()[torpedo.getX()][torpedo.getY()] == 6)
		{
			System.out.println("Hit!!!!");
			// We set the value to -1 to denote a sunken ship
			opponent.getBoard()[torpedo.getX()][torpedo.getY()] = -1;
			opponent.numShips--;
			numTorpedos--;

			// This loop will represent a bonus round; prompting the user to enter a xy coordinate pair until either a miss occurs,
			// all ships have been hit, or a player loses all their torpedos
			boolean stillShooting = true;
			while(stillShooting){
				if(numTorpedos == 0){
					stillShooting = false;
					userCanLaunch = false;
					break;
				}
				if(opponent.numShips == 0){
					System.out.println("Opponent is out of ships");
					userCanLaunch =false;
					stillShooting = false;
					break;
				}
				
					System.out.println("Enter another x coordinate");
					int x2 = scan.nextInt();
					System.out.println("Enter another y coordinate");
					int y2 = scan.nextInt();
					if(opponent.getBoard()[x2][y2] == 1|| opponent.getBoard()[x2][y2] == 2 || opponent.getBoard()[x2][y2] == 3|| opponent.getBoard()[x2][y2] == 4 
					|| opponent.getBoard()[x2][y2] == 5 || opponent.getBoard()[x2][y2] == 6){
					System.out.println("Hit!!!!!");
					opponent.getBoard()[torpedo.getX()][torpedo.getY()] = -1;
					opponent.numShips--;
					numTorpedos--;
					}
					else{
						System.out.println("Miss");
						numTorpedos--;
						stillShooting = false;
						break;
					}


				
			}
			
		}
		return userCanLaunch;
	}

	public boolean launchComputer(Player opponent){

		// This method will return a boolean value signaling
		// whether or not a player can still launch a torpedo

		// This method takes a torpedo representing a xy coordinate
		// and will use it to attack a ship on the board of another
		// player

		// If we return false, that means either a player has
		// lost all of their ships or a player has ran out of
		// torpedos

		boolean userCanLaunch = true;
		int x = new Random().nextInt(9);
		int y = new Random().nextInt(9);
		Torpedo torpedo = new Torpedo(x,y);

		// If a player correctly strikes a coordinate on their opponents board we will enter a bonus round to hit the opponent again
		if(numTorpedos == 0)
		{
			System.out.println("Player is out of torpedos!!!");
			userCanLaunch = false;
			return userCanLaunch;
		}
		else if(opponent.getBoard()[torpedo.getX()][torpedo.getY()] == 1|| opponent.getBoard()[torpedo.getX()][torpedo.getX()] == 2 || opponent.getBoard()[torpedo.getX()][torpedo.getY()] == 3|| 
			opponent.getBoard()[torpedo.getX()][torpedo.getY()] == 4 || opponent.getBoard()[torpedo.getX()][torpedo.getY()] == 5 || opponent.getBoard()[torpedo.getX()][torpedo.getY()] == 6)
		{
			System.out.println("Hit!!!!");
			// We set the value to -1 to denote a sunken ship

			opponent.getBoard()[torpedo.getX()][torpedo.getY()] = -1;
			opponent.numShips--;


			numTorpedos--;
			boolean stillShooting = true;
			// This loop will represent a bonus round; prompting the user to enter a xy coordinate pair until either a miss occurs,
			// all ships have been hit, or a player loses all their torpedos
			while(stillShooting){
				if(numTorpedos == 0){
					userCanLaunch = false;
					stillShooting = false;
				}
				if(opponent.numShips == 0){
					System.out.println("Opponent is out of ships");
					userCanLaunch  = false;
					stillShooting = false;

				}
				int x2 =  new Random().nextInt(9);
				int y2 = new Random().nextInt(9);
				if(opponent.getBoard()[x2][y2] == 1|| opponent.getBoard()[x2][y2] == 2 || opponent.getBoard()[x2][y2] == 3|| opponent.getBoard()[x2][y2] == 4 
				|| opponent.getBoard()[x2][y2] == 5 || opponent.getBoard()[x2][y2] == 6){
					System.out.println("Hit!!!!!");
					opponent.getBoard()[torpedo.getX()][torpedo.getY()] = -1;
					opponent.numShips--;
					numTorpedos--;
				}
				else{
					System.out.println("Miss");
					numTorpedos--;
					stillShooting = false;
				}
			}
			
		}
		else{
			System.out.println("Miss");
			numTorpedos--;
		}
		return userCanLaunch;

	}





	

}