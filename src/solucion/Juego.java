package solucion;

import java.util.Enumeration;
import java.util.Hashtable;


public class Juego implements IJuego{
	private int N  = 0;
	private int contadorEnemigosTotales;
	private Hashtable<Integer, Integer> contadoresEnemigosTipo;
	private Hashtable<Integer, Integer> contadoresEliminadosTipo;
	private int MAXENEMIGOS;
	private int MINENEMIGOS;
	
	public Juego (int minenemigos, int maxenemigos) {
		this.MAXENEMIGOS = maxenemigos;
		this.MINENEMIGOS = minenemigos;
		contadorEnemigosTotales = 0;
		contadoresEnemigosTipo = new Hashtable<Integer,Integer>();
		contadoresEliminadosTipo = new Hashtable<Integer,Integer>();
		
		for (int i = 1; i < 5; i++) {
			contadoresEnemigosTipo.put(i, 0);
			contadoresEliminadosTipo.put(i, 0);
		}
			
	}
	

	
	@Override
	public synchronized void generarEnemigo(int T) {
		comprobarAntesDeGenerar(T);
		contadorEnemigosTotales++;
		contadoresEnemigosTipo.put(T, contadoresEnemigosTipo.get(T)+1);
		imprimirInfo(T, "Generado ");
		checkInvariante();
		notifyAll();
	}
	
	public synchronized void eliminarEnemigo(int T)  {
		
		comprobarAntesDeEliminar(T);
		if(contadorEnemigosTotales>=0) {
		contadorEnemigosTotales--;
		contadoresEnemigosTipo.put(T, contadoresEnemigosTipo.get(T)-1);
		contadoresEliminadosTipo.put(T, contadoresEliminadosTipo.get(T)+1);
		imprimirInfo(T, "Eliminado ");
		checkInvariante();
		notifyAll();
		}
	}
	
	
	private void imprimirInfo(int T, String Enemigos) {
		System.out.println(Enemigos + "enemigo de tipo " + T);
		System.out.println("--> Enemigos totales: " + contadorEnemigosTotales);
		
		for(int e: contadoresEnemigosTipo.keySet()) {
			
			System.out.println("---> Enemigos de tipo " + e + ":" + contadoresEnemigosTipo.get(e) + " -----  [Eliminados: " + contadoresEliminadosTipo.get(e) + "]");
		
			}
		
		System.out.println("   "); 
		
	}
	
	public int sumarContadores() {
		int sumarContadores=0;
			Enumeration<Integer> Enemigos=contadoresEnemigosTipo.elements();
			while (Enemigos.hasMoreElements()) {
				sumarContadores += Enemigos.nextElement();
			}
			
		return sumarContadores;
	}
	
	protected void checkInvariante() {
		assert sumarContadores() == contadorEnemigosTotales : "ERROR: La suma de los contadores de tipos de enemigos debe ser igual al contador de enemigos totales ";
		assert ((contadorEnemigosTotales <= MAXENEMIGOS) && (contadorEnemigosTotales>=0)) : "El numero de enemigos debe de ser menor que M y mayor que 0";
	}

	protected synchronized void comprobarAntesDeGenerar(int T)  {
		if(contadorEnemigosTotales==MAXENEMIGOS) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected synchronized void comprobarAntesDeEliminar(int T)  {
		if(contadorEnemigosTotales==MINENEMIGOS) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
