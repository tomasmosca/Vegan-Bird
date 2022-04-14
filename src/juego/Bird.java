package juego;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Bird{
	
	private Image image;
	private final int X = 100;
	private final int Y = 250;
	private int x, y;
	public int width = 45;
    public int height = 32;
	public boolean dead;
	public int jumpdalay = 0;
	public double gravedad = 0.2;
	public double yVel = 0;
	private double rotation = 0.0;
	
	public Bird() {
		
		loadImage();
		this.x = X;
		this.y = Y;
		
	}
	
	public void rotation() {
		
		rotation = (90 * (yVel + 20) / 20) - 90;
        rotation = rotation * Math.PI / 180;

        if (rotation > Math.PI / 2)
            rotation = Math.PI / 2;
	}
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon("C:/Users/Tomas/Desktop/FlappyBird/bird.png");
		image = ii.getImage();
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = (int) y;
	}
	
	public double getRotation() {
		return rotation;
	}

}
