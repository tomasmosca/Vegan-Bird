package juego;

import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;

public class Hamburguesas {
	
	private Image ham;
	Random r = new Random();
	Random r2 = new Random();
	int lowx = 550;
	int highx = 750;
	int lowy = 100;
	int highy = 500;
	public double x;
	public double y;
	public double angulo;
	public double escala;
	public double width = 45;
	public double height = 32;
	
	public Hamburguesas(int x,int y, double angulo, double escala) {
		
		this.x = x;
		this.y = y;
		this.angulo = angulo;
		this.escala = escala;
		
		ham = Herramientas.cargarImagen("burger.png");
		
	}
	
	public void dibujarHam(Entorno entorno) {
		entorno.dibujarImagen(ham, x, y, angulo, escala);
	}
	
	public void move() {
		this.x = this.x-2;
	}
	
	public void randomY() {
		this.y = r.nextInt(highy-lowy) + lowy;
	}
	
	public void randomX() {
		this.x = r2.nextInt(highx-lowx) + lowx;
	}

	public Image getHam() {
		return ham;
	}

	public void setHam(Image ham) {
		this.ham = ham;
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

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public double getEscala() {
		return escala;
	}

	public void setEscala(double escala) {
		this.escala = escala;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public static boolean intersecSegm(double a1,double b1,double a2,double b2) {
		return ((a1 <= b2 && a1>= a2) || (a2 <= b1 && a2>=a1));
	}
		
	public boolean setocan(Bird otro) {
			
		double a1 = this.x - this.width/2;
		double b1 = this.x + this.width/2;
		double a2 = otro.getX() - otro.width/2;
		double b2 = otro.getX() + otro.width/2;
			
		double c1 = this.y-this.height/2;
		double d1 = this.y+this.height/2;
		double c2 = otro.getY()-otro.height/2;
		double d2 = otro.getY()+otro.height/2;
		
		return (intersecSegm(a1,b1,a2,b2) && intersecSegm(c1,d1,c2,d2));
	}
		
	public boolean setocan2(Vegetales otro) {
			
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
	
	public boolean setocan3(Hamburguesas otro) {
		
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

}
