/**
 * Permite usar dos funciones utiles
 * para el juego
 * 
 * @author Juan Stiven Avila 
 *
 */
package businessLogic;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;

import data.Jugador;
import data.Tablero;

public class Juego {

	public static int verificarVictoria(Tablero tablero) {
		/* Recibe un Tablero, obtiene los datos de sus casillas, y duvuelve 1 si gano jug1 
		 * o 2 si gano jug2, si no ha ganado nadie devuelve 0
		 * 
		 * Tablero
		 *  1|2|3
		 * -------
		 *  4|5|6
		 * -------
		 *  7|8|9
		 *
		 *0=Casilla Libre
		 *1=Casilla Jugador 1
		 *2=Casilla Jugador 2
		 *
		 */
		
		//Crea dos ArrayList para almacenar las posiciones de las fichas de los jugadores
		ArrayList<Integer> jug1 = new ArrayList<Integer>(); 
		ArrayList<Integer> jug2 = new ArrayList<Integer>();
		
		//Crea una Lista con las combinaciones de victoria
		ArrayList<ArrayList<Integer>> mezclas = new ArrayList<ArrayList<Integer>>();
		mezclas.add(new ArrayList<Integer>(Arrays.asList(1,2,3)));
		mezclas.add(new ArrayList<Integer>(Arrays.asList(4,5,6)));
		mezclas.add(new ArrayList<Integer>(Arrays.asList(7,8,9)));
		mezclas.add(new ArrayList<Integer>(Arrays.asList(1,4,7)));
		mezclas.add(new ArrayList<Integer>(Arrays.asList(2,5,8)));
		mezclas.add(new ArrayList<Integer>(Arrays.asList(3,6,9)));
		mezclas.add(new ArrayList<Integer>(Arrays.asList(1,5,9)));
		mezclas.add(new ArrayList<Integer>(Arrays.asList(3,5,7)));
		
		//Recorre el tablero para guardar la posicion de las fichas de los jugadores 
		for(int i=1;i<10;i++) {
			int ficha= tablero.getTablero()[i];
			if (ficha==1)
				jug1.add(i);
			if (ficha==2)
				jug2.add(i);
		}
		
		//Verifica si ha ganado algun jugador en el tablero
		for(ArrayList<Integer> mezcla:mezclas) {
			if(jug1.containsAll(mezcla)) return (1);//devuelve 1 si ha ganado el jugador 1
			if(jug2.containsAll(mezcla)) return (2);//devuelve 2 si ha ganado el jugador 2
		}
		return 0;//devuelve 0 si nadie ha ganado en el tablero
	}

	public static void marcarCasilla(Jugador jugador,JButton boton) {
		//Recibe un Jugador y un boton, obtiene su numJug y marca la casilla
		if (jugador.getNumJug()==1)
			boton.setIcon(jugador.getIcono());
		if (jugador.getNumJug()==2)
			boton.setIcon(jugador.getIcono());
		boton.setEnabled(false);
	}
}
