/**
 * @author Juan Stiven Avila
 *
 */

package data;

import javax.swing.ImageIcon;

public class Jugador {

	private int numJug;
	private String nombre;
	private ImageIcon icono;
	
	public Jugador(int numJug, String nombre, ImageIcon icono) {
		super();
		this.numJug = numJug;
		this.nombre = nombre;
		this.icono = icono;
	}

	public ImageIcon getIcono() {
		return icono;
	}

	public int getNumJug() {
		return numJug;
	}

	public String getNombre() {
		return nombre;
	}	
}
