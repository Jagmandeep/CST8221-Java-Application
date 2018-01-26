package Guitar;


public class Wire {
	private Board board = Board.getInstance();
	private int fretNum=0;
	private int tension;
	
	public Wire( int tensions){

		tension = tensions;
		
	}
	
public void vibrate(){
	
	board.soundNote(tension+fretNum);
}
public void pluck(){
	vibrate();
	
}
public void changeTension(int deltaTension){
	tension+=deltaTension;
	
}
public void fret(int fretNum){ // value for fret to be in inputed, if zero wire doesn't touch fret
	this.fretNum = fretNum;
}
}
