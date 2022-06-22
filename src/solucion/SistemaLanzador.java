package solucion;



public class SistemaLanzador {
	
	private static final int M=22;

	public static void main (String[] args) {
		final Juego juego = new Juego(1, M);

		System.out.println("Simulacion videojuego");
					
		
		ActividadEnemiga enemigos1 = new ActividadEnemiga(1, juego);
		ActividadAliada aliados1 = new ActividadAliada(1, juego);
		new Thread (enemigos1).start();
		new Thread (aliados1).start();
		
		ActividadEnemiga enemigos2 = new ActividadEnemiga(2, juego);
		ActividadAliada aliados2 = new ActividadAliada(2, juego);
		new Thread (enemigos2).start();
		new Thread (aliados2).start();
		
		ActividadEnemiga enemigos3 = new ActividadEnemiga(3, juego);
		ActividadAliada aliados3 = new ActividadAliada(3, juego);
		new Thread (enemigos3).start();
		new Thread (aliados3).start();
		
		ActividadEnemiga enemigos4 = new ActividadEnemiga(4, juego);
		ActividadAliada aliados4 = new ActividadAliada(4, juego);
		new Thread (enemigos4).start();
		new Thread (aliados4).start();	
		
		
	}
}

