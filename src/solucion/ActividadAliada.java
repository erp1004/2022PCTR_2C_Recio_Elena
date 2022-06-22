package solucion;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActividadAliada implements Runnable {
	private int  T;
	private int M = 20;
	private IJuego juego;
	
	public ActividadAliada(int T, IJuego juego) {
		this.T= T;
		this.juego=juego;
	}
	
	public void run() {
		for(int i = 0; i<M; i++) {
			try {
				juego.eliminarEnemigo(T);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5)*1000);
			
			}catch(InterruptedException e){
				Logger.getGlobal().log(Level.INFO, "Actividad aliada interrumpida");	
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
		
	}
}