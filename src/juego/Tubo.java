package juego;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Tubo {
	
	private Image imageSur;
	private Image imageNorte;
	private final int X = 820;
	private final int Y = 20;
	public static int width = 66;
    public static int height = 400;
	public static int x, y, x2, y2, x3, y3;
	public boolean tubo2 = false;
	public boolean tubo3 = false;
	
	public Tubo() {
		
		loadImageSur();
		loadImageNorte();
		
		setX(X);
		setY(Y);
		
		setX2(X);
		setY2(Y);
		
		setX3(X);
		setY3(Y);

		y = +(int)(Math.random() * 170) - 150 / 2;
		y2 = +(int)(Math.random() * 170) - 150 / 2;
		y3 = +(int)(Math.random() * 170) - 150 / 2;
	}
	
    public static void setX(int x) {
		Tubo.x = x;
	}

	public static void setY(int y) {
		Tubo.y = y;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		Tubo.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		Tubo.y2 = y2;
	}

	public int getX3() {
		return x3;
	}

	public int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Tubo.width = width;
	}

	public int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Tubo.height = height;
	}

	public void setX3(int x3) {
		Tubo.x3 = x3;
	}

	public int getY3() {
		return y3;
	}

	public void setY3(int y3) {
		Tubo.y3 = y3;
	}

	public void ciclo() {
		
		x-=2;
		if (x == 550) {
			tubo2 = true;
		}
		if (x < -80) {
			x = X;
			y = +(int)(Math.random() * 170) - 150 / 2;
		}
	}
    
    public void ciclo2() {
    	
        x2-=2;
    	if (x2 == 550) {
			tubo3 = true;
		}
		if (x2 < -80) {
			x2 = X;
			y2 = +(int)(Math.random() * 170) - 150 / 2;
		}
    }
    
    public void ciclo3() {
    	
        x3-=2;
    	if (x3 < -80) {
			x3 = X;
			y3 = +(int)(Math.random() * 170) - 150 / 2;
		}
    }
    
    public static boolean collides(int _x, int _y, int _width, int _height) {  

        int margin = 18;
        if ((_x + _width > x && _x + margin < x + width)) {
            if (( _y < y + height/2 + 16)) { // si colisiona con south
                return true;
            } else if ((_y + _height > (600+y-30) - height/2 + 16)) { // si colisiona con north
                return true;
            }
        }
        return false;
    }
    
    public static boolean collides2(int _x, int _y, int _width, int _height) {
    	
    	int margin = 18;
    	if ((_x + _width > x2 + 50 && _x + margin < x2 + 50 + width)) {
    		if(( _y < y2 + height/2 + 16)) {
    			return true;
    		}else if ((_y + _height > (600+y2-30) - height/2 + 16)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static boolean collides3(int _x, int _y, int _width, int _height) {
    	
    	int margin = 18;
    	if ((_x + _width > x3 + 50 && _x + margin < x3 + 50 + width)) {
    		if(( _y < y3 + height/2 + 16)) {
    			return true;
    		}else if ((_y + _height > (600+y3-30) - height/2 + 16)) {
    			return true;
    		}
    	}
    	return false;
    }
    
	private void loadImageSur() {
		ImageIcon ii = new ImageIcon("C:/Users/Tomas/Desktop/FlappyBird/pipe-south.png");
		imageSur = ii.getImage();
	}
	
	private void loadImageNorte() {
		ImageIcon ii = new ImageIcon("C:/Users/Tomas/Desktop/FlappyBird/pipe-north.png");
		imageNorte = ii.getImage();
	}
	
	public Image getImageSur() {
		return imageSur;
	}
	
    public Image getImageNorte() {
		return imageNorte;
	}
	
    public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
