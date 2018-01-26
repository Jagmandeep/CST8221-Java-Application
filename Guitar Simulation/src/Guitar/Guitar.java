package Guitar;


public class Guitar<wireNum> {

	 /*
	    * Insert these constant definitions in your Guitar.java file.
	    * These constants represent the starting tensions
	    * of the six wires (we'll use midi notes as our
	    * unit of tension rather than pounds or Newtons).
	    * You will pass one of these constants to the Wire
	    * constructor as you create each of the six Wires.
	    * For example, to create the first Wire, you would
	    * use new Wire(MIDI_E4)
	    */
	   // sixth Wire (E6), sounds an E2 note, which is midi 40
	   public final static int MIDI_E2 = 40;
	   // fifth Wire (A), sounds an A2 note, which is midi 45
	   public final static int MIDI_A2 = 45;
	   // fourth Wire (D), sounds a D3 note, which is midi 50
	   public final static int MIDI_D3 = 50;
	   // third Wire (G), sounds a G3 note, which is midi 55
	   public final static int MIDI_G3 = 55;
	   // second Wire (B), sounds a B3 note, which is midi 59
	   public final static int MIDI_B3 = 59;
	   // first Wire (E1), sounds an E4 note, which is midi 64
	   public final static int MIDI_E4 = 64;
	   

		Wire[] wire = new Wire[7];
		Peg[] peg = new Peg[7];
		
	public Guitar(){
		  
		   

			
		   wire[1]=new Wire(MIDI_E4);
		   wire[2]=new Wire(MIDI_B3);
		   wire[3]=new Wire(MIDI_G3);
		   wire[4]=new Wire(MIDI_D3);
		   wire[5]=new Wire(MIDI_A2);
		   wire[6]=new Wire(MIDI_E2);
		   
			   peg[1]= new Peg(wire[1]);
			   peg[2]= new Peg(wire[2]);
			   peg[3]= new Peg(wire[3]);
			   peg[4]= new Peg(wire[4]);
			   peg[5]= new Peg(wire[5]);
			   peg[6]= new Peg(wire[6]);
		   

	}
	public void pluck(int wireNum){
		
		wire[wireNum].pluck();
		
	}
	public void turn(int pegNum,int deltaTension){
		peg[pegNum].turn( deltaTension);
	}

	public void fret(int wireNum,int  fretNum){
		wire[wireNum].fret(fretNum);
	}
	public void hammerOn(int wireNum, int fretNum){
		fret(wireNum,fretNum);
		pluck(wireNum);
	}

}

