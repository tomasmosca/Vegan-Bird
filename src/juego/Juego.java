package juego;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego                   // falta fix de transformaciones tardes (se podrian borrar balas cuando termina juego)
{                                                           // añadir algun sound mas??
	// El objeto Entorno que controla el tiempo y otros                                   
	private Entorno entorno;                                                 // juego completo (V1.0): 2197 code lines
	
	private Bird bird;
	private Tubo tuboSur;
	private Tubo tuboNorte;
	private Foreground Fg;
	private ArmaConversora raygun;
	private Vegetales vegetales;
	private Vegetales vegetales2;
	private Vegetales vegetales3;
	private Vegetales vegetales4;
	private Vegetales vegtemporal1;
	private Vegetales vegtemporal2;
	private Vegetales vegtemporal3;
	private Hamburguesas ham1;
	private Hamburguesas ham2;
	private Hamburguesas ham3;
	
	Random r1x = new Random();
	Random r1y = new Random();
	Random r2x = new Random();
	Random r2y = new Random();
	Random r3x = new Random();
	Random r3y = new Random();
	Random r4x = new Random();
	Random r4y = new Random();
	
	int lowx = 350;
	int highx = 750;
	int lowy = 50;
	int highy = 450;
	
	private Bala bala;
	private double yPos;
	private boolean paused;
	private ArrayList<Bala> balas;
	private int pauseDelay;
	private int restartDelay;
	private double posY;
	private boolean started;
	private boolean gameover;
	public int score;
	private Image bg;
	private Image fg;
	private Image carrot;
	private boolean groundCollision = false;
	
	public Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Vegan Bird - V1.0 - BETA", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		
		loadbg();
		loadfg();
		loadC();
		
		bird = new Bird();
		yPos = bird.getY();
		Fg = new Foreground();
		raygun = new ArmaConversora();
		balas = new ArrayList<Bala>();
		tuboSur = new Tubo();
		tuboNorte = new Tubo();
		
		vegetales = new Vegetales(r1x.nextInt(highx-lowx) + lowx,r1y.nextInt(highy-lowy) + lowy,0,0.2);
		vegetales2 = new Vegetales(r2x.nextInt(highx-lowx) + lowx,r2y.nextInt(highy-lowy) + lowy,0,0.2);
		vegetales3 = new Vegetales(r3x.nextInt(highx-lowx) + lowx,r3y.nextInt(highy-lowy) + lowy,0,0.2);
		vegetales4 = new Vegetales(r4x.nextInt(highx-lowx) + lowx,r4y.nextInt(highy-lowy) + lowy,0,0.2);
		vegtemporal1 = new Vegetales(-50,200,0.0,0.2);
		vegtemporal2 = new Vegetales(-70,200,0.0,0.2);
		vegtemporal3 = new Vegetales(-100,200,0.0,0.2);
		ham1 = new Hamburguesas(400,200,0,0.2);
		ham2 = new Hamburguesas(700,400,0,0.2);
		ham3 = new Hamburguesas(600,500,0,0.2);
		
		Herramientas.cargarSonido("sfx_hit.wav");
		Herramientas.cargarSonido("sfx_wing.wav");
		Herramientas.cargarSonido("sfx_point.wav");
		Herramientas.cargarSonido("eating1.wav");
		Herramientas.cargarSonido("eating2.wav");
		Herramientas.cargarSonido("eating3.wav");
		
		pressed();
		restart();
		this.entorno.iniciar(); //inicia juego
	}
	
	public void restart() {
		
		paused = false;
        started = false;
        gameover = false;
        groundCollision = false;
        
        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        
        bird = new Bird();
        yPos = bird.getY();
        tuboSur = new Tubo();
		tuboNorte = new Tubo();
		
		balas = new ArrayList<Bala>();
		vegetales = new Vegetales(r1x.nextInt(highx-lowx) + lowx,r1y.nextInt(highy-lowy) + lowy,0,0.2);
        vegetales2 = new Vegetales(r2x.nextInt(highx-lowx) + lowx,r2y.nextInt(highy-lowy) + lowy,0,0.2);
		vegetales3 = new Vegetales(r3x.nextInt(highx-lowx) + lowx,r3y.nextInt(highy-lowy) + lowy,0,0.2);
		vegetales4 = new Vegetales(r4x.nextInt(highx-lowx) + lowx,r4y.nextInt(highy-lowy) + lowy,0,0.2);
		vegtemporal1 = new Vegetales(-50,200,0.0,0.2);
		vegtemporal2 = new Vegetales(-70,200,0.0,0.2);
		vegtemporal3 = new Vegetales(-100,200,0.0,0.2);
		ham1 = new Hamburguesas(400,200,0,0.2);
		ham2 = new Hamburguesas(700,400,0,0.2);
		ham3 = new Hamburguesas(600,500,0,0.2);
		
		Herramientas.cargarSonido("sfx_hit.wav");
		Herramientas.cargarSonido("sfx_wing.wav");
		Herramientas.cargarSonido("sfx_point.wav");
		Herramientas.cargarSonido("eating1.wav");
		Herramientas.cargarSonido("eating2.wav");
		Herramientas.cargarSonido("eating3.wav");
	}
	
	private String score() {
		return String.valueOf(score);
	}
	
	private void watchForStart() {
		if (!started && entorno.sePresiono(entorno.TECLA_ESPACIO)) {
            started = true;
        }
	}
	
	private void watchForPause() {
        if (pauseDelay > 0)
            pauseDelay--;
        
        if (entorno.sePresiono('p') && pauseDelay <= 0 && !bird.dead) {
            paused = !paused;
            pauseDelay = 10;
        }
    }
	
	private void watchForReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (entorno.sePresiono('r') && restartDelay <= 0 && bird.dead) {
            restart();
            restartDelay = 10;
            return;
        }
    }
	
	private void loadbg() {
		ImageIcon ii = new ImageIcon("C:/Users/Tomas/Desktop/FlappyBird/background.png");
		bg = ii.getImage();
	}
	
	private void loadfg() {
		ImageIcon ii = new ImageIcon("C:/Users/Tomas/Desktop/FlappyBird/foreground.png");
		fg = ii.getImage();
	}
	
	private void loadC() {
		ImageIcon ii = new ImageIcon("C:/Users/Tomas/Desktop/FlappyBird/carrot.png");
		carrot = ii.getImage();
	}
	
	public Image getCarrot() {
		return carrot;
	}
	
	public Image getFg() {
		return fg;
	}
	
	public Image getBg() {
		return bg;
	}
	
	private void SpawnerTubos() {
		entorno.dibujarImagen(tuboSur.getImageSur(), tuboSur.getX(), tuboSur.getY(), 0);
		entorno.dibujarImagen(tuboNorte.getImageNorte(), tuboSur.getX(), 600+tuboSur.getY()-30, 0);		
		entorno.dibujarImagen(tuboSur.getImageSur(), tuboSur.getX2() + 50, tuboSur.getY2(), 0);
		entorno.dibujarImagen(tuboNorte.getImageNorte(), tuboSur.getX2() + 50, 600+tuboSur.getY2()-30, 0);
		entorno.dibujarImagen(tuboSur.getImageSur(), tuboSur.getX3() + 50, tuboSur.getY3(), 0);
		entorno.dibujarImagen(tuboNorte.getImageNorte(), tuboSur.getX3() + 50, 600+tuboSur.getY3()-30, 0);
	}
	
	public void updateDrawRay() {
		entorno.dibujarImagen(raygun.getArma(), bird.getX()+8, yPos-27, 0, 0.2);	
	}
	
	public void dibujarBala() {
		
		for (Bala bala : new ArrayList<>(balas)) {
			
			if (!bala.setocan(tuboSur) || !bala.setocan2(tuboSur) || !bala.setocan3(tuboSur) || !bala.setocan4(tuboSur) || !bala.setocan5(tuboSur) || !bala.setocan6(tuboSur) || !bala.setocan7(vegetales) || !bala.setocan7(vegetales2) || !bala.setocan7(vegetales3) || !bala.setocan7(vegetales4) || !bala.setocan8(ham1) || !bala.setocan8(ham2) || !bala.setocan8(ham3)) {
				bala.dibujarBala(entorno);
				bala.move();
			}
			if (bala.setocan(tuboSur) || bala.setocan2(tuboSur) || bala.setocan3(tuboSur) || bala.setocan4(tuboSur) || bala.setocan5(tuboSur) || bala.setocan6(tuboSur) || bala.setocan7(vegetales) || bala.setocan7(vegetales2) || bala.setocan7(vegetales3) || bala.setocan7(vegetales4)) {
				balas.remove(bala);
			}
			if (bala.setocan8(ham1)) {
				balas.remove(bala);
				vegtemporal1.setX(ham1.getX());
				vegtemporal1.setY(ham1.getY());
				ham1.randomY();
				ham1.randomX();
				score+=3;
				Herramientas.play("sfx_point.wav");
			}
			if (bala.setocan8(ham2)) {
				balas.remove(bala);
				vegtemporal2.setX(ham2.getX());
				vegtemporal2.setY(ham2.getY());
				ham2.randomY();
				ham2.randomX();
				score+=3;
				Herramientas.play("sfx_point.wav");
			}
			if (bala.setocan8(ham3)) {
				balas.remove(bala);
				vegtemporal3.setX(ham3.getX());
				vegtemporal3.setY(ham3.getY());
				ham3.randomY();
				ham3.randomX();
				score+=3;
				Herramientas.play("sfx_point.wav");
			}
		}
	}
		
	public void pressed() {
		if (entorno.sePresiono(entorno.TECLA_ENTER) && !bird.dead && started && !paused) {
			posY = yPos - 33;
			bala = new Bala(bird.getX()+30, posY,5,5,false);
			balas.add(bala);
		}
	}
	
	public void drawVeg() {
		
		//                                         -------------------------- BROCOLI --------------------------------
		
		if (!Tubo.collides((int)vegetales.getX(), (int)vegetales.getY(), (int)vegetales.width, (int)vegetales.height)&&!Tubo.collides2((int)vegetales.getX(), (int)vegetales.getY(), (int)vegetales.width, (int)vegetales.height)&&!Tubo.collides3((int)vegetales.getX(), (int)vegetales.getY(), (int)vegetales.width, (int)vegetales.height)&&!vegetales.setocan2(vegetales2) && !vegetales.setocan2(vegetales3) && !vegetales.setocan2(vegetales4) && !vegetales.setocan3(ham1) && !vegetales.setocan3(ham2) && !vegetales.setocan3(ham3)) {
			vegetales.dibujarB(entorno);
		}
		if (!bird.dead && !paused) {
			vegetales.move();
		}
		if (vegetales.getX() < -70) {
			vegetales.randomY();
			vegetales.randomX();
		}
		if (Tubo.collides((int)vegetales.getX(), (int)vegetales.getY(), (int)vegetales.width, (int)vegetales.height)||Tubo.collides2((int)vegetales.getX(), (int)vegetales.getY(), (int)vegetales.width, (int)vegetales.height)||Tubo.collides3((int)vegetales.getX(), (int)vegetales.getY(), (int)vegetales.width, (int)vegetales.height)) {
			vegetales.randomY();
			vegetales.randomX();
		}
        if (vegetales.setocan(bird)) {
        	score++;
            vegetales.randomY();
			vegetales.randomX();
			Herramientas.play("eating4.wav");
		}
        if (vegetales.setocan2(vegetales2) || vegetales.setocan2(vegetales3) || vegetales.setocan2(vegetales4)) {
            vegetales.randomY();
			vegetales.randomX();
        }
        if (vegetales.setocan3(ham1) || vegetales.setocan3(ham2) || vegetales.setocan3(ham3)) {
        	vegetales.randomY();
			vegetales.randomX();
        }
        
        //                             ------------------------------ CARROT -------------------------------------
        
        if (!Tubo.collides((int)vegetales2.getX(), (int)vegetales2.getY(), (int)vegetales2.width, (int)vegetales2.height)&&!Tubo.collides2((int)vegetales2.getX(), (int)vegetales2.getY(), (int)vegetales2.width, (int)vegetales2.height)&&!Tubo.collides3((int)vegetales2.getX(), (int)vegetales2.getY(), (int)vegetales2.width, (int)vegetales2.height)&&!vegetales2.setocan2(vegetales) && !vegetales2.setocan2(vegetales3) && !vegetales2.setocan2(vegetales4) && !vegetales2.setocan3(ham1) && !vegetales2.setocan3(ham2) && !vegetales2.setocan3(ham3)) {
			vegetales2.dibujarCa(entorno);
		}
        if (!bird.dead  && !paused) {
			vegetales2.move();
		}
        if (vegetales2.getX() < -50) {
        	vegetales2.randomY();
        	vegetales2.randomX();
		}
        if (Tubo.collides((int)vegetales2.getX(), (int)vegetales2.getY(), (int)vegetales2.width, (int)vegetales2.height)||Tubo.collides2((int)vegetales2.getX(), (int)vegetales2.getY(), (int)vegetales2.width, (int)vegetales2.height)||Tubo.collides3((int)vegetales2.getX(), (int)vegetales2.getY(), (int)vegetales2.width, (int)vegetales2.height)) {
        	vegetales2.randomY();
			vegetales2.randomX();
		}
        if (vegetales2.setocan(bird)) {
        	score++;
			vegetales2.randomY();
			vegetales2.randomX();
			Herramientas.play("eating5.wav");
		}
        if (vegetales2.setocan2(vegetales) || vegetales2.setocan2(vegetales3) || vegetales2.setocan2(vegetales4)) {
    	   vegetales2.randomY();
           vegetales2.randomX();
        }
        if (vegetales2.setocan3(ham1) || vegetales2.setocan3(ham2) || vegetales2.setocan3(ham3)) {
    	   vegetales2.randomY();
    	   vegetales2.randomX();
    	}
       
       //                                       ------------------------------ CORN ---------------------------------
       
        if (!Tubo.collides((int)vegetales3.getX(), (int)vegetales3.getY(), (int)vegetales3.width, (int)vegetales3.height)&&!Tubo.collides2((int)vegetales3.getX(), (int)vegetales3.getY(), (int)vegetales3.width, (int)vegetales3.height)&&!Tubo.collides3((int)vegetales3.getX(), (int)vegetales3.getY(), (int)vegetales3.width, (int)vegetales3.height)&&!vegetales3.setocan2(vegetales) && !vegetales3.setocan2(vegetales2) && !vegetales3.setocan2(vegetales4) && !vegetales3.setocan3(ham1) && !vegetales3.setocan3(ham2) && !vegetales3.setocan3(ham3)) {
        	vegetales3.dibujarCo(entorno);
		}
		if (!bird.dead  && !paused) {
			vegetales3.move();
		}
		if (vegetales3.getX() < -90) {
			vegetales3.randomY();
			vegetales3.randomX();
		}
		if (Tubo.collides((int)vegetales3.getX(), (int)vegetales3.getY(), (int)vegetales3.width, (int)vegetales3.height)||Tubo.collides2((int)vegetales3.getX(), (int)vegetales3.getY(), (int)vegetales3.width, (int)vegetales3.height)||Tubo.collides3((int)vegetales3.getX(), (int)vegetales3.getY(), (int)vegetales3.width, (int)vegetales3.height)) {
			vegetales3.randomY();
			vegetales3.randomX();
		}
		if (vegetales3.setocan(bird)) {
    	   score++;
           vegetales3.randomY();
           vegetales3.randomX();
           Herramientas.play("eating6.wav");
		}
		if (vegetales3.setocan2(vegetales) || vegetales3.setocan2(vegetales2) || vegetales3.setocan2(vegetales4)) {
    	   vegetales3.randomY();
           vegetales3.randomX();
        }
		if (vegetales3.setocan3(ham1) || vegetales3.setocan3(ham2) || vegetales3.setocan3(ham3)) {
    	   vegetales3.randomY();
    	   vegetales3.randomX();
    	}
       
       //                               -----------------------------  LETUCE -----------------------------------
       
		if (!Tubo.collides((int)vegetales4.getX(), (int)vegetales4.getY(), (int)vegetales4.width, (int)vegetales4.height)&&!Tubo.collides2((int)vegetales4.getX(), (int)vegetales4.getY(), (int)vegetales4.width, (int)vegetales4.height)&&!Tubo.collides3((int)vegetales4.getX(), (int)vegetales4.getY(), (int)vegetales4.width, (int)vegetales4.height)&&!vegetales4.setocan2(vegetales) && !vegetales4.setocan2(vegetales2) && !vegetales4.setocan2(vegetales3)) {
    	   vegetales4.dibujarL(entorno);
        }
		if (!bird.dead  && !paused) {
			vegetales4.move();
		}
		if (vegetales4.getX() < -110) {
    	   vegetales4.randomY();
    	   vegetales4.randomX();
    	}
       if (Tubo.collides((int)vegetales4.getX(), (int)vegetales4.getY(), (int)vegetales4.width, (int)vegetales4.height)||Tubo.collides2((int)vegetales4.getX(), (int)vegetales4.getY(), (int)vegetales4.width, (int)vegetales4.height)||Tubo.collides3((int)vegetales4.getX(), (int)vegetales4.getY(), (int)vegetales4.width, (int)vegetales4.height)) {
			vegetales4.randomY();
			vegetales4.randomX();
		}
       if (vegetales4.setocan(bird)) {
    	   score++;
           vegetales4.randomY();
           vegetales4.randomX();
           Herramientas.play("eating7.wav");
		}
       if (vegetales4.setocan2(vegetales) || vegetales4.setocan2(vegetales2) || vegetales4.setocan2(vegetales3)) {
    	   vegetales4.randomY();
           vegetales4.randomX();
       }
       if (vegetales4.setocan3(ham1) || vegetales4.setocan3(ham2) || vegetales4.setocan3(ham3)) {
    	   vegetales4.randomY();
    	   vegetales4.randomX();
       }
       
       //                                 ----------------------- TEMPORALES -------------------------
       
       if (!vegtemporal1.setocan(bird)) {
			vegtemporal1.dibujarB(entorno);
		}
       if (!bird.dead  && !paused) {
    	   vegtemporal1.move();
		}
		if (vegtemporal1.setocan(bird)) {
			score+=3;
			vegtemporal1.setX(-50);
			Herramientas.play("eating8.wav");
		}
		if (!vegtemporal2.setocan(bird)) {
			vegtemporal2.dibujarCa(entorno);
		}
        if (!bird.dead  && !paused) {
    	   vegtemporal2.move();
		}
		if (vegtemporal2.setocan(bird)) {
			score+=3;
			vegtemporal2.setX(-50);
			Herramientas.play("eating5.wav");
		}
		if (!vegtemporal3.setocan(bird)) {
			vegtemporal3.dibujarCo(entorno);
		}
        if (!bird.dead  && !paused) {
    	   vegtemporal3.move();
		}
		if (vegtemporal3.setocan(bird)) {
			score+=3;
			vegtemporal3.setX(-50);
			Herramientas.play("eating7.wav");
		}
	}
	
	public void drawHams() {
		
		//                                 -------------------------- BURGER 1 ---------------------------
		
		if (!Tubo.collides((int)ham1.getX(), (int)ham1.getY(), (int)ham1.width, (int)ham1.height)&&!Tubo.collides2((int)ham1.getX(), (int)ham1.getY(), (int)ham1.width, (int)ham1.height)&&!Tubo.collides3((int)ham1.getX(), (int)ham1.getY(), (int)ham1.width, (int)ham1.height)&&!ham1.setocan2(vegetales) && !ham1.setocan2(vegetales2) && !ham1.setocan2(vegetales3) && !ham1.setocan2(vegetales4) && !ham1.setocan3(ham2) && !ham1.setocan3(ham3)) {
			ham1.dibujarHam(entorno);
		}
		if (!bird.dead  && !paused) {
			ham1.move();
		}
		if (ham1.getX() < -10) {
			ham1.randomY();
			ham1.randomX();
		}
		if (Tubo.collides((int)ham1.getX(), (int)ham1.getY(), (int)ham1.width, (int)ham1.height)||Tubo.collides2((int)ham1.getX(), (int)ham1.getY(), (int)ham1.width, (int)ham1.height)||Tubo.collides3((int)ham1.getX(), (int)ham1.getY(), (int)ham1.width, (int)ham1.height)) {
			ham1.randomY();
			ham1.randomX();
		}
		if (ham1.setocan(bird)) {
			score-=5;
			ham1.randomY();
	        ham1.randomX();
	        Herramientas.play("eating1.wav");
		}
		if (ham1.setocan2(vegetales) || ham1.setocan2(vegetales2) || ham1.setocan2(vegetales3) || ham1.setocan2(vegetales4)) {
			ham1.randomY();
			ham1.randomX();
	    }
		if (ham1.setocan3(ham2) || ham1.setocan3(ham3)) {
			ham1.randomY();
			ham1.randomX();
		}
		 
		 //                             -------------------------- BURGER 2 ---------------------------
		
		if (!Tubo.collides((int)ham2.getX(), (int)ham2.getY(), (int)ham2.width, (int)ham2.height)&&!Tubo.collides2((int)ham2.getX(), (int)ham2.getY(), (int)ham2.width, (int)ham2.height)&&!Tubo.collides3((int)ham2.getX(), (int)ham2.getY(), (int)ham2.width, (int)ham2.height)&&!ham2.setocan2(vegetales) && !ham2.setocan2(vegetales2) && !ham2.setocan2(vegetales3) && !ham2.setocan2(vegetales4) && !ham2.setocan3(ham1) && !ham2.setocan3(ham3)) {
			ham2.dibujarHam(entorno);
		}
		if (!bird.dead  && !paused) {
			ham2.move();
		}
		if (ham2.getX() < -10) {
			ham2.randomY();
			ham2.randomX();
		}
		if (Tubo.collides((int)ham2.getX(), (int)ham2.getY(), (int)ham2.width, (int)ham2.height)||Tubo.collides2((int)ham2.getX(), (int)ham2.getY(), (int)ham2.width, (int)ham2.height)||Tubo.collides3((int)ham2.getX(), (int)ham2.getY(), (int)ham2.width, (int)ham2.height)) {
			ham2.randomY();
			ham2.randomX();
		}
		if (ham2.setocan(bird)) {
			score-=5;
			ham2.randomY();
	        ham2.randomX();
	        Herramientas.play("eating2.wav");
		}
		if (ham2.setocan2(vegetales) || ham2.setocan2(vegetales2) || ham2.setocan2(vegetales3) || ham2.setocan2(vegetales4)) {
			ham2.randomY();
			ham2.randomX();  
	    }
		if (ham2.setocan3(ham1) || ham2.setocan3(ham3)) {
			ham2.randomY();
			ham2.randomX();
		}
		 
		 //                                 -------------------------- BURGER 3 ---------------------------
		
		if (!Tubo.collides((int)ham3.getX(), (int)ham3.getY(), (int)ham3.width, (int)ham3.height)&&!Tubo.collides2((int)ham3.getX(), (int)ham3.getY(), (int)ham3.width, (int)ham3.height)&&!Tubo.collides3((int)ham3.getX(), (int)ham3.getY(), (int)ham3.width, (int)ham3.height)&&!ham3.setocan2(vegetales) && !ham3.setocan2(vegetales2) && !ham3.setocan2(vegetales3) && !ham3.setocan2(vegetales4) && !ham3.setocan3(ham1) && !ham3.setocan3(ham2)) {
			ham3.dibujarHam(entorno);
		}
		if (!bird.dead  && !paused) {
			ham3.move();
		}
		if (ham3.getX() < -10) {
			ham3.randomY();
			ham3.randomX();
		}
		if (Tubo.collides((int)ham3.getX(), (int)ham3.getY(), (int)ham3.width, (int)ham3.height)||Tubo.collides2((int)ham3.getX(), (int)ham3.getY(), (int)ham3.width, (int)ham3.height)||Tubo.collides3((int)ham3.getX(), (int)ham3.getY(), (int)ham3.width, (int)ham3.height)) {
			ham3.randomY();
			ham3.randomX();
		}
		if (ham3.setocan(bird)) {
			score-=5;
			ham3.randomY();
	        ham3.randomX();
	        Herramientas.play("eating3.wav");
		}
		if (ham3.setocan2(vegetales) || ham3.setocan2(vegetales2) || ham3.setocan2(vegetales3) || ham3.setocan2(vegetales4)) {
			ham3.randomY();
			ham3.randomX();
	    }
		if (ham3.setocan3(ham1) || ham3.setocan3(ham2)) {
			ham3.randomY();
			ham3.randomX();
		}
	}
	
    public void Birdupdate() {  // podria estar en la clase bird
		
    	bird.yVel += bird.gravedad;
    	
    	if (bird.jumpdalay > 0) {
    		bird.jumpdalay--;
    	}
    	if (!bird.dead && entorno.sePresiono(entorno.TECLA_ESPACIO) && bird.jumpdalay <= 0) {
    		bird.yVel = -5;
    		bird.jumpdalay = 10;
    	}
    	if (!bird.dead && entorno.sePresiono(entorno.TECLA_ESPACIO)) {
    		yPos-=1;
    		Herramientas.play("sfx_wing.wav");
    	}
    	
    	yPos += (int)bird.yVel;
    	bird.setY(yPos);
    	
    	if (bird.dead == true && groundCollision == false) {
    		bird.rotation();
    	}
	}
    
    private void checkForCollisions() {
        
    	if (Tubo.collides(bird.getX(), (int)yPos, bird.width, bird.height)) {
    		Herramientas.play("sfx_hit.wav");
            bird.dead = true;
            yPos += 0.5;           
            bird.yVel +=2;
            bird.gravedad = 0.2;
        }
    	if (Tubo.collides2(bird.getX(), (int)yPos, bird.width, bird.height)) {
    		Herramientas.play("sfx_hit.wav");
        	bird.dead = true;
        	yPos += 0.5;
            bird.yVel +=2;
            bird.gravedad = 0.2;
        }
    	if (Tubo.collides3(bird.getX(), (int)yPos, bird.width, bird.height)) {
    		Herramientas.play("sfx_hit.wav");
        	bird.dead = true;
        	yPos += 0.5;
            bird.yVel +=2;
            bird.gravedad = 0.2;
        }
    }
    
    private void groundCollision() {
    	
    	if (yPos + bird.height > 650 - 80) {
            bird.dead = true;
            groundCollision = true;
            yPos = 650 - 80 - bird.height;
            bird.yVel = 0;
            bird.gravedad = 0;
        }
    }
    
    private void topCollision() {
    	if (yPos + bird.height < -35 + 80) {
    		yPos = -97 + 80 + bird.height;
    	}
    }
    
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 */
	public void tick()
	{                 
		// Procesamiento de un instante de tiempo
		
	    entorno.dibujarImagen(getBg(), 400, 300, 0, 1.65);
	    SpawnerTubos();
		entorno.dibujarImagen(getFg(), -13 -Fg.ground_offset, 380, 0);
		entorno.dibujarImagen(getFg(), 480- Fg.ground_offset, 380, 0);    // 480?
		entorno.dibujarImagen(getFg(), 443.2*2 -Fg.ground_offset, 380, 0);
		pressed();
		updateDrawRay();
		dibujarBala();
		
		if (started ) {
			
			drawVeg();
			drawHams();
		}
		
		entorno.dibujarImagen(bird.getImage(), bird.getX(), yPos, bird.getRotation());
        watchForStart();
        
		if (!started) {
			entorno.cambiarFont("Arial", 40, Color.WHITE);
			entorno.escribirTexto("Vegan Bird", 320, 70);
			entorno.cambiarFont("Arial", 35, Color.WHITE);
			entorno.escribirTexto("Presiona 'espacio' para volar!", 200, 300);
			entorno.cambiarFont("Arial", 18, Color.WHITE);
			entorno.escribirTexto("Presiona 'P' para pausar el juego", 285, 150);
			return;
		}
		if (started) {
			entorno.cambiarFont("Arial", 20, Color.WHITE);
			entorno.escribirTexto("Puntuación:", 30, 50);
			entorno.cambiarFont("Arial", 20, Color.WHITE);
			entorno.escribirTexto(score(), 140, 50);
		}
		
		watchForPause();
        watchForReset();
        
        if (paused) {
        	entorno.cambiarFont("Arial", 40, Color.WHITE);
        	entorno.escribirTexto("Pausado", 320, 300);
            return;
        }
		Birdupdate();
		
		if (!Tubo.collides(bird.getX(), (int)yPos, bird.width, bird.height) && !bird.dead) {
			tuboSur.ciclo();
			if (tuboSur.tubo2) {
				tuboSur.ciclo2();
			}
			if (tuboSur.tubo3) {
				tuboSur.ciclo3();
			}
		}
		if (!bird.dead) {
			checkForCollisions();
			Fg.cicloFg();
		}
		if (gameover) {
            return;
		}
		if (bird.dead) {
			entorno.cambiarFont("Arial", 40, Color.WHITE);
    	    entorno.escribirTexto("Game Over", 300, 300);
    	    entorno.cambiarFont("Arial", 20, Color.WHITE);
    	    entorno.escribirTexto("Presiona 'R' para volver a intentar" , 250, 200);
		}
		groundCollision();
		topCollision();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
