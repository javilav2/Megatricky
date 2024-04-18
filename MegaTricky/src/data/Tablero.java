/**
 *  
 * @author Juan Stiven Avila
 *
 */

package data;

public class Tablero {
	/*
	 * 0|0|0
	 *-------
	 * 0|0|0
	 *-------
	 * 0|0|0
	 *
	 *0=Casilla Libre
	 *1=Casilla Jugador 1
	 *2=Casilla Jugador 2
	 *
	 */

	private int[] tablero;//Las casillas se almacenan en un array list de enteros

	public Tablero() {
		this.tablero=new int[10];
		for(int i=1;i<10;i++) {
			this.tablero[i]=0;
		}
	}
	
	public void marcarCasilla(int casilla, Jugador jugador) {
		this.tablero[casilla]=jugador.getNumJug();
	}

	public int getValue(int i) {
		return tablero[i];
	}

	public int[] getTablero() {
		return tablero;
	}
	
}
