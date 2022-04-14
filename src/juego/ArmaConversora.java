package juego;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ArmaConversora {
	
	private Image arma;
	private int x = 300;
	private int y = 200;
	public int width = 260;
	public int height = 190;
	public double vel;
	public boolean bala = false;
	public final double posY = 0;
	
	public ArmaConversora(){
		
		loadImage();
		
	}
	
	public double updateBala() {
		return vel+=1;
	}
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon("C:/Users/Tomas/Desktop/FlappyBird/raygun.png");
		arma = ii.getImage();
	}
	
	public Image getArma() {
		return arma;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double getPosy() {
		return posY;
	}

}
