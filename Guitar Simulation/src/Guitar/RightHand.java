package Guitar;

public class RightHand implements GuitarPlayerRightHand {

	Guitar guitar;
	public RightHand(Guitar guitar){
		this.guitar=guitar;
	}

	@Override
	public void pluck(int... wires) {
		for(int i : wires){
			guitar.pluck(i);
		}
	}// end of method

	@Override
	public void strum() {
		for(int i=6;i>0;i--){
			guitar.pluck(i);
			pause(80);
		}
	}// end of method

	@Override
	public void strum(int wireNum) {

		for(int i=wireNum;i>0;i--){
			guitar.pluck(i);
			pause(80);
		}
	}// end of method

	@Override
	public void pause(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			// ignore for now
		}

	}// end of method

}// end of class

