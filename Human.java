// Programmed by Jay J. James JR
// 10/5/2018
// This class is a subclass of the player class and inherits necessary superclass methods to play game
public class Human extends Player {

	public Human(){
		super();
	}

	@Override
	public void placeShipsHuman(){
		super.placeShipsHuman();
	}

	@Override
	public boolean launchHuman(Torpedo torpedo, Player opponent){
		return super.launchHuman(torpedo,opponent);

	}
	@Override
	public void displayMatrix(){
		super.displayMatrix();
	}
	@Override
	public int[][] getBoard(){
		return super.getBoard();
	}
	





}