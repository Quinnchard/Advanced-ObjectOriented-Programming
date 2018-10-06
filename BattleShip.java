// Programmed by Jay J. James JR
// 10/5/2018
import java.util.Scanner;
public class BattleShip{

	private String gameType;
	private Player player1;
	private Player player2;
	private long startTime;
	private long endTime;
	private static Scanner scan = new Scanner(System.in);

	public BattleShip(){ 
		// Upon the instantiation of a new instance of
		// a BattleShip object the user will be prompted
		// to select a game strategy. Either Multiplayer
		// or VS computer. Strategy will be implented 
		// inside of constructor.

		// Depending on the strategy entered player2
		// will be instantiated as either a human
		// or a computer
		displayBattleShipMatrix();
		System.out.println("Enter: " + "\n" +
			"1 - Multiplayer" + "\n" + "2 - VS Computer");
		int choice = scan.nextInt();
		if(choice == 1){
			this.gameType = "Multiplayer";
			this.player1 = new Human();
			this.player2 = new Human();
			this.startTime = 0;
			this.endTime = 0;
		}
		else{
			this.gameType = "VS Computer";
			this.player1 = new Human();
			this.player2 = new Computer();
			this.startTime = 0;
			this.endTime = 0;
		}
	}

	public void startGame(){
		// This method will simulate a battle ship game using nested while loops
		// The outer loop will be used to reset all the variables for a new game
		// except the number of times the player has won as described in the lab
		// assignment file. The inner loop will be used to simulate the actual game
		// until either a player has won, or a player has ran out of torpedos
		

		// The p1Score and p2Score variables will be incremented after every win
		int p1Score = 0;
		int p2Score = 0;

		// The boolean 'gamePlaying' will be passed into the outer loop and will be terminated when
		// the inner loop 'playing' evaluates to true

		boolean gamePlaying = true;
		boolean playing = true;
		while(gamePlaying){
			startTime = System.currentTimeMillis();
			int round = 1;
			System.out.println("Welcome to the Battleship game!");
			System.out.println("This implementation only supports numerical x y values");

			// The mehtod will then check the strategy passed into the constructor and will
			// initialize player two to either a 'Human' or 'Computer' instance
			if(gameType.equals("Multiplayer")){
				player2 = new Human();
			}
			else{
				player2 = new Computer();
			}
			// We refresh the board at the start of each game so that players can choose
			// their ship locations all over again.
			player1.refreshBoard();
			player2.refreshBoard(); 	
			System.out.println("P1: Place six ships on board with xy coordinates");
			//System.out.println();
			player1.placeShipsHuman();
			//System.out.println();

			// Here we use the instance of keyword to check which class our polymorphic
			// player class was instantiated as. Depending on what player 2 was instantiated
			// as we use either a human strategy or a computer strategy to launch torpedos
			if(player2 instanceof Computer){
				System.out.println("Computer is placing it's coordinates");
				player2.placeShipsComputer();
			}
			else{
				if(player2 instanceof Human)
				System.out.println("P2: Place six ships on board with xy coordinates");
				player2.placeShipsHuman();
		}
	    
		while(playing){
			//  Each round, we will print the scores of both players, the round number, and the
			//  number of remaining ships each player has

			
			System.out.println("Round: " + round + "\n" + 
				"P1:Ships- " + player1.numShips + "\n" + "P2:Ships- " + player2.numShips);

			// Below the user will be prompted to select their x y coordinates
			// If they are within bounds, the player or computer will launch a torpedo
			// at that location in their opponents matrix
			player1.displayMatrix();
			System.out.println("P1:Enter an x coordinate");
			int x = scan.nextInt();
			System.out.println("P1:Enter a y coordinate");
			int y = scan.nextInt();
			if(x < 0 || x > 8 || y < 0 || y > 8){
				System.out.println("Cannot insert out of bounds");
			}
			else{
				// Each launch will return a value of true or false
				// if a false value returns, either the player or computer
				// ran out of ships or torpedos

				// A response will be printed depeding on whether 
				// or not we have a win or draw
				if(!player1.launchHuman(new Torpedo(x,y),player2)){
					if(isWin()||isDraw()){
						playing = false;
						break;
					}
				}
			}
				
				if(player2 instanceof Computer){
					System.out.println("Computer launched torpedo");
					if(!player2.launchComputer(player1)){
						if(isWin()||isDraw()){
						playing = false;
						break;
						}
					}
				}
				else{
					player2.displayMatrix();
					System.out.println("P2:Enter an x coordinate");
					int x2 = scan.nextInt();
					System.out.println("P2:Enter a y coordinate");
					int y2 = scan.nextInt();
					if(x2 < 0 || x2 > 8 || y2 < 0 || y2 > 8){
						System.out.println("Cannot insert out of bounds");
					}
					else{
						if(!player2.launchHuman(new Torpedo(x2,y2),player1))
							{
								if(isWin()|| isDraw()){
									playing = false;
									break;
								}
							};
						if(!player1.launchHuman(new Torpedo(x,y),player2))
						{
							if(isWin()||isDraw()){
							playing = false;
							break;
							}
						}

					

				}
				
					
			
				round++;

				}
				
			
		}
		// At the end of each game, the user will be prompted to start a new game or exit
		if(!playing){

			if(player1.numShips == 0){
					p2Score++;
			}

			if(player2.numShips == 0){
					p1Score++;
			}
			System.out.println("Would you like to start a new game? Y/N");
			String response = scan.next();
			if(response.equalsIgnoreCase("N")){
				System.out.println("Game Over");
				System.out.println("P1 Score: " + p1Score);
				System.out.println("P2 Score: " + p2Score);
				gamePlaying = false;
			}
		}
		playing = true;
		endTime = System.currentTimeMillis();

		}
		// Finally we print the total game time
		System.out.println("Game time " + (float) (((endTime -startTime)/1000)/60)+ " minute(s)");


	}

	public boolean isWin(){

		// The isWin method will take two scores in as
		// parameters and increments a score depending
		// on which player lost all their ships
		boolean isWin = false;
		
		if(player1.numShips == 0 || player2.numShips == 0){
			isWin = true;

			if(player1.numShips == 0 && isWin == true){
				System.out.println("Player 2 is the winner!");
				
			}
			else if(player2.numShips == 0 && isWin == true){
				System.out.println("player 1 is the winner!");
			
			}
			else
			{

			}
		}
		
		return isWin;

	}

	public boolean isDraw(){
		// isDraw will check whether or not a player ran out of torpedos
		boolean isDraw = false;
		if(player1.numTorpedos == 0 || player2.numTorpedos == 0){
			isDraw =
			 true;
		}
		
		else{
			isDraw = false;
		}
		
		return isDraw;

	}
	public static void displayBattleShipMatrix(){
		// Fancy display of battleship board
		String[] title = {"A","T","T","L","E","S","H","I","P"};
		System.out.print("B" + " ");
		for(int i = 0; i < 9; i++){
			if(i == 0){

				System.out.print("[ ]");
			}
			else{
				System.out.print("["+(i -1)+"]" + " ");

			}
		}
		System.out.println();

		for(int i = 0; i < 9; i++){
			System.out.print(title[i] + " ");
			if(i == 9){
				System.out.print("[ ]" + " ");
			}	
			else{
				System.out.print("["+(Math.abs(i-(9-1))+"]" + " "));

			}


			
			for(int j = 0; j <9 ; j++){
				System.out.print("[" + "]" + " ");
			}
			System.out.println();
		}
	}



}