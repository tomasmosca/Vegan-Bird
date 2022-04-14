package juego;

import java.awt.Color;
import entorno.Entorno;

public class Bala {
	
	public double x = 0;
	public double y = 0;
	public double width = 5;
	public double height = 5;
	private double diametro = 10;
	public boolean toDelete = false;
	
	public Bala(double x, double y,double width,double height,boolean toDelete){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.toDelete = toDelete;
		
	}
	
	public void dibujarBala(Entorno entorno) {
		entorno.dibujarCirculo(x, y, diametro, Color.GREEN);
	}
	
	public static boolean intersecSegm(double a1,double b1,double a2,double b2) {
		return ((a1 <= b2 && a1>= a2) || (a2 <= b1 && a2>=a1));
	}
	
	public boolean setocan(Tubo otro) {
		
		double a1 = this.x - this.width/2;
		double b1 = this.x + this.width/2;
		double a2 = otro.getX() - otro.getWidth()/2;
		double b2 = otro.getX() + otro.getWidth()/2;
		
		double c1 = this.y-this.height/2;
		double d1 = this.y+this.height/2;
		double c2 = otro.getY()-otro.getHeight()/2;
		double d2 = otro.getY()+otro.getHeight()/2;
		
		return (intersecSegm(a1,b1,a2,b2) && intersecSegm(c1,d1,c2,d2));
	}
	
    public boolean setocan2(Tubo otro) {
		
		double a1 = this.x - this.width/2;
		double b1 = this.x + this.width/2;
		double a2 = otro.getX2() + 50 - otro.getWidth()/2;
		double b2 = otro.getX2() + 50 + otro.getWidth()/2;
		
		double c1 = this.y-this.height/2;
		double d1 = this.y+this.height/2;
		double c2 = otro.getY2()-otro.getHeight()/2;
		double d2 = otro.getY2()+otro.getHeight()/2;
		
		return (intersecSegm(a1,b1,a2,b2) && intersecSegm(c1,d1,c2,d2));
	}
    
    public boolean setocan3(Tubo otro) {
		
		double a1 = this.x - this.width/2;
		double b1 = this.x + this.width/2;
		double a2 = otro.getX3() + 50 - otro.getWidth()/2;
		double b2 = otro.getX3() + 50 + otro.getWidth()/2;
		
		double c1 = this.y-this.height/2;
		double d1 = this.y+this.height/2;
		double c2 = otro.getY3()-otro.getHeight()/2;
		double d2 = otro.getY3()+otro.getHeight()/2;
		
		return (intersecSegm(a1,b1,a2,b2) && intersecSegm(c1,d1,c2,d2));
	}
    
   public boolean setocan4(Tubo otro) {
		
		double a1 = this.x - this.width/2;
		double b1 = this.x + this.width/2;
		double a2 = otro.getX() - otro.getWidth()/2;
		double b2 = otro.getX() + otro.getWidth()/2;
		
		double c1 = this.y-this.height/2;
		double d1 = this.y+this.height/2;
		double c2 = 600+otro.getY()-30-otro.getHeight()/2;
		double d2 = 600+otro.getY()-30+otro.getHeight()/2;
		
		return (intersecSegm(a1,b1,a2,b2) && intersecSegm(c1,d1,c2,d2));
   }
   
   public boolean setocan5(Tubo otro) {
		
		double a1 = this.x - this.width/2;
		double b1 = this.x + this.width/2;
		double a2 = otro.getX2() + 50 - otro.getWidth()/2;
		double b2 = otro.getX2() + 50 + otro.getWidth()/2;
		
		double c1 = this.y-this.height/2;
		double d1 = this.y+this.height/2;
		double c2 = 600+otro.getY2()-30-otro.getHeight()/2;
		double d2 = 600+otro.getY2()-30+otro.getHeight()/2;
		
		return (intersecSegm(a1,b1,a2,b2) && intersecSegm(c1,d1,c2,d2));
	}
   
   public boolean setocan6(Tubo otro) {
		
		double a1 = this.x - this.width/2;
		double b1 = this.x + this.width/2;
		double a2 = otro.getX3() + 50 - otro.getWidth()/2;
		double b2 = otro.getX3() + 50 + otro.getWidth()/2;
		
		double c1 = this.y-this.height/2;
		double d1 = this.y+this.height/2;
		double c2 = 600+otro.getY3()-30-otro.getHeight()/2;
		double d2 = 600+otro.getY3()-30+otro.getHeight()/2;
		
		return (intersecSegm(a1,b1,a2,b2) && intersecSegm(c1,d1,c2,d2));
	}
   
   public boolean setocan7(Vegetales otro) {
		
	    double a1 = this.x - this.width/2;
		double b1 = this.x + this.width/2;
		double a2 = otro.x - otro.width/2;
		double b2 = otro.x + otro.width/2;
		
		double c1 = this.y-this.height/2;
		double d1 = this.y+this.height/2;
		double c2 = otro.y-otro.height/2;
		double d2 = otro.y+otro.height/2;
		
		return (intersecSegm(a1,b1,a2,b2) && intersecSegm(c1,d1,c2,d2));
	}
   
   public boolean setocan8(Hamburguesas otro) {
		
	    double a1 = this.x - this.width/2;
		double b1 = this.x + this.width/2;
		double a2 = otro.x - otro.width/2;
		double b2 = otro.x + otro.width/2;
		
		double c1 = this.y-this.height/2;
		double d1 = this.y+this.height/2;
		double c2 = otro.y-otro.height/2;
		double d2 = otro.y+otro.height/2;
		
		return (intersecSegm(a1,b1,a2,b2) && intersecSegm(c1,d1,c2,d2));
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getDiametro() {
		return diametro;
	}
	
	public void setDiametro(double diametro) {
		this.diametro = diametro;
	}
	
    public void move() {
        this.x +=2;
    }
    
    public void borrar() {
    	this.toDelete = true;
    }
    
}
