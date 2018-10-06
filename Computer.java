// Programmed by Jay J. James JR
// 10/5/2018
// This class is a subclass of the player class and inerits necessary superclass methods to play game
public class Computer extends Player{

	public Computer(){
		super();
	}
	
	public boolean launch(Player opponent){
		return super.launchComputer(opponent);
	}
	public void placeShipsComputer(){
		super.placeShipsComputer();
	}

}