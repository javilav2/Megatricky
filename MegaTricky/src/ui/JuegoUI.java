/**
 *  Panel del juego, muestra un tablero 3x3
 *  cada uno con su respectivo tablero 3x3 de 
 *  la clase TableroUI, ademas muestra que jugador
 *  tiene el turno actual y tiene un boton de pausa
 *  
 * @author Juan Stiven Avila
 *
 */

package ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import businessLogic.Juego;
import data.Jugador;
import data.Tablero;

public class JuegoUI extends JPanel{

	private static final long serialVersionUID = 1L;

	private JPanel tableroPanel;
	GridBagConstraints ubicacion = new GridBagConstraints();
	private Jugador jugador1, jugador2, jugadorActual;
	private JButton jug1Btn, jug2Btn, btnPausa;
	private TableroUI[] tablero = new TableroUI[10];
	private Tablero[] tableroData=new Tablero[10];
	private Tablero tableroGrande = new Tablero();
	
	public JuegoUI(Jugador jugador1,Jugador jugador2) {//Jpanel del juego principal
		this.setSize(600,500);
		this.setLayout(new GridBagLayout());
		
		this.jugador1=jugador1;
		this.jugador2=jugador2;
		setupJugadores();
		setupJuegoPanel();
	}
	
	private void setupJugadores() {
		//Crea JButton con el nombre y el icono de cada jugador para saber quien tiene el turno
		
		jug1Btn =new JButton(jugador1.getNombre(),jugador1.getIcono());
		jug1Btn.setSize(170, 100);
		ubicacion.gridx=0;
		ubicacion.gridy=0;
		ubicacion.gridheight=1;
		ubicacion.gridwidth=1;
		this.add(jug1Btn,ubicacion);
		
		jug2Btn =new JButton(jugador2.getNombre(),jugador2.getIcono());
		jug2Btn.setSize(170, 100);
		ubicacion.gridx=2;
		ubicacion.gridy=0;
		ubicacion.gridheight=1;
		ubicacion.gridwidth=1;
		jug2Btn.setEnabled(false);
		this.add(jug2Btn,ubicacion);
		
		btnPausa=new JButton("Pausa");
		ubicacion.gridx=1;
		ubicacion.gridy=0;
		ubicacion.gridheight=1;
		ubicacion.gridwidth=1;
		this.add(btnPausa,ubicacion);
		
	}

	private void setupJuegoPanel() {
		//Crea un panel con gridlayout para un tablero de 3x3
		jugadorActual=jugador1;
		tableroPanel=new JPanel();
		tableroPanel.setLayout(new GridLayout(3, 3));
		tableroPanel.setSize(300, 300);
		
		for(int posTab=1;posTab<10;posTab++) {
			//Ciclo para crear un array de tableros de datos y otro de los tableros graficos
			tableroData[posTab]=new Tablero();
			tablero[posTab]=new TableroUI();
			this.tableroPanel.add(tablero[posTab]);
			
			for(int posBtn=1;posBtn<10;posBtn++) {
				//Ciclo para añadir los listeners de los botones 
				final int tabNum=posTab;
				final int btnNum=posBtn;
				
				//Añade la acción de marcar para cada casilla
				tablero[posTab].getButton(posBtn).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(jugadorActual.equals(jugador1)) {
							tableroData[tabNum].marcarCasilla(btnNum, jugador1);
							Juego.marcarCasilla(jugador1, tablero[tabNum].getButton(btnNum));
							cambiarTablero(btnNum);
							verificarVictoria(tabNum);
							cambiarJugador();
						}else {
							tableroData[tabNum].marcarCasilla(btnNum, jugador2);
							Juego.marcarCasilla(jugador2, tablero[tabNum].getButton(btnNum));
							cambiarTablero(btnNum);
							verificarVictoria(tabNum);
							cambiarJugador();
						}
					}
				});;
			}
		}
		ubicacion.gridx=1;
		ubicacion.gridy=1;
		ubicacion.gridheight=1;
		ubicacion.gridwidth=1;
		ubicacion.anchor = GridBagConstraints.CENTER;
		this.add(tableroPanel,ubicacion);
	}
	
	private void verificarVictoria(int tabNum) {
		//verifica si el tablero ya lo gano algun jugador, y marca su respectiva casilla en el tablero grande
		if(Juego.verificarVictoria(tableroData[tabNum])!=0) {
				tableroGrande.marcarCasilla(tabNum,jugadorActual);
				tablero[tabNum].marcarTablero(jugadorActual);
			}
		if(Juego.verificarVictoria(tableroGrande)!=0) {//verifica alguien ha ganado en el tablero grande
			finJuego();
		}
	}
	
	private void cambiarJugador() {
		//Cambia el estado del jugador actual al jugador contrario
		if (jugadorActual.equals(jugador1)) {
			jug1Btn.setEnabled(false);
			jug2Btn.setEnabled(true);
			jugadorActual=jugador2;
		} else {
			jug1Btn.setEnabled(true);
			jug2Btn.setEnabled(false);
			jugadorActual=jugador1;
		}
	}
	
	private void cambiarTablero(int posicion) {
		//Cambia el tablero activo 
		if(Juego.verificarVictoria(tableroData[posicion])!=0) {
			//Si ya gano alguien en el tablero a jugar, desbloquea todos los tableros disponibles
			for(int i=1;i<10;i++) {
				if(Juego.verificarVictoria(tableroData[i])!=0)
					tablero[i].setDisable();
				else
					tablero[i].setEnable(tableroData[i]);
			}
		} else {
			for(int i=1;i<10;i++) {
				//Si en el tablero a jugar no ha ganado nadie solo desbloquea ese tablero
				if(i==posicion) {
					tablero[i].setEnable(tableroData[i]);
				}else {
					tablero[i].setDisable();
				}
			}
		}
	}
	
	private void finJuego() {
		for(int i=1;i<10;i++) {
			//Deshabilita todos los botones para que no se pueda jugar mas
				tablero[i].setDisable();
		}
		ubicacion.gridx=1;
		ubicacion.gridy=2;
		ubicacion.gridheight=1;
		ubicacion.gridwidth=1;
		ubicacion.anchor = GridBagConstraints.WEST;
		
		//Muestra en la pantalla un mensaje anunciando el ganador con su respectivo icono
		JLabel ganador = new JLabel("HA GANADO");
		ganador.setFont(new Font("Chiller", Font.BOLD, 40));
		this.add(ganador, ubicacion);
		
		ubicacion.anchor = GridBagConstraints.EAST;
		JLabel img1 = new JLabel(new ImageIcon("imagenes/iconos"+jugadorActual.getIcono().toString()));
		this.add(img1, ubicacion);
		
		this.updateUI();
		
	}
	
	public JButton getBtnPausa() {
		return btnPausa;
	}
}
