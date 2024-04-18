/**
 * Crea un tablero 3x3
 * de botones para insertar en el 
 * tablero principal del juego
 * 
 * @author Juan Stiven Avila
 *
 */

package ui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import data.Jugador;
import data.Tablero;

public class TableroUI extends JLayeredPane{

	private static final long serialVersionUID = 2947679074510640476L;
	
	private JButton[] casillas;
	
	public TableroUI() {//Constructor de un JPanel con un tablero 3x3
		this.setLayout(new GridLayout(3, 3, 0, 0));
		this.setBorder(BorderFactory.createLineBorder(new Color(164,13,13)));
		this.setSize(600, 600);
		crearCasillas();
	}

	private void crearCasillas() {
		//Crea un TreeMap con 9 casillas y luego las añade al tablero
		casillas= new JButton[10];
		
		ImageIcon fondoCasilla =new ImageIcon("imagenes/principal/cuadrado.png");
		for(int i=1;i<10;i++) {
			JButton casilla=new JButton(fondoCasilla);
			casilla.setBorder(BorderFactory.createEmptyBorder());
			this.casillas[i]=casilla;
			this.add(casillas[i]);
		}
	}

	public void setEnable(Tablero tablero) {
		//Activa los botones de un tablero excepto los ya marcados
		for(int i=1;i<10;i++) {
			if(tablero.getValue(i)==0) {
				casillas[i].setEnabled(true);
			}	
		}
	}
	
	public void setDisable() {
		//Desactiva los botones de un tablero
		for(int i=1;i<10;i++) {
			casillas[i].setEnabled(false);
		}
	}
	
	public JButton getButton(int i) {
		return this.casillas[i];
	}

	public void marcarTablero(Jugador jugador) {
		//Marca el todo el tablero con el icono del jugador
		this.setLayout(null);
		JLabel fondo = new JLabel(new ImageIcon("imagenes/iconos"+jugador.getIcono().toString()));
		fondo.setBounds(0,0,90,90);
		this.add(fondo,new Integer(1));
		
	}
}
