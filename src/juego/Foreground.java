package juego;

public class Foreground {
	
	public double ground_offset;
	
	public Foreground() {
	}
	
	public void cicloFg() {
		ground_offset += 2;
		ground_offset = ground_offset%300;
	}

}
