package Guitar;


public class Peg {
	Wire wire;               // peg has wire

	public Peg(Wire wire){  // constructor of Peg has wire
		this.wire = wire;
	}       // end of constructor

	public void turn(int deltaTension){         // turn method changes Wire.changeTension value by amount turned
		wire.changeTension(deltaTension);
	}// end of turn method
}// end of Peg class
