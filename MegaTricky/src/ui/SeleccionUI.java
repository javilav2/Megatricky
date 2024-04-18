/**
 *  Panel de seleccion de icono y nombre
 *  para cada jugador del juego
 *  
 * @author Juan Stiven Avila
 *
 */

package ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import data.Jugador;

public class SeleccionUI extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Jugador jugador1,jugador2;
	private JButton btnAceptar;
	
	public SeleccionUI() {//Constructor del panel seleccion con la pantalla para seleccionar un icono y un nombre para cada jugador
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setSize(200, 600);
		this.setBorder(new EmptyBorder(10,10,10,10));
		
		agregarComponentes();
	}

	private void agregarComponentes() {
		
		JTextField jug1Nombre=new JTextField("Jugador 1");//Crea un campo de texto para el nombre del jugador 1
		jug1Nombre.setHorizontalAlignment(JTextField.CENTER);
		jug1Nombre.setSize(100, 30);
		this.add(jug1Nombre);		
		
		JList<ImageIcon> jug1Icono= seleccionIcono();//Añade una lista de iconos para seleccionar
		jug1Icono.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		jug1Icono.setSize(100, 90);
		this.add(jug1Icono);
		
		JLabel centro=new JLabel("Escoge un nombre y un icono para cada jugador");//Muestra una indicacion al usuario
		centro.setAlignmentX(Component.CENTER_ALIGNMENT);
		centro.setSize(200,200);
		this.add(centro);
		
		JTextField jug2Nombre=new JTextField("Jugador 2");//Crea un campo de texto para el nombre del jugador 2
		jug2Nombre.setHorizontalAlignment(JTextField.CENTER);
		jug2Nombre.setSize(100, 30);
		this.add(jug2Nombre);	
		
		JList<ImageIcon> jug2Icono= seleccionIcono();//Añade una lista de iconos para seleccionar
		jug2Icono.setSize(100, 90);
		jug2Icono.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		this.add(jug2Icono);
		
		btnAceptar = new JButton("Aceptar");//Crea un boton para enviar los iconos y nombres para verificarlos
		btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAceptar.setSize(70,30);
		this.add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//El boton almacena los nombres y los iconos de cada jugador, 
				//verifica que no sean iguales y los manda al panel juego
				String nombre1= jug1Nombre.getText();
				ImageIcon icono1= jug1Icono.getSelectedValue();
				String nombre2= jug2Nombre.getText();
				ImageIcon icono2= jug2Icono.getSelectedValue();
				
				if(jug1Icono.isSelectionEmpty()||jug2Icono.isSelectionEmpty()) {
					//Envia un mensaje al usuario si no ha seleccionado algun icono
					JOptionPane.showMessageDialog(null, "Selecciona un icono para cada jugador");
				} else if(nombre1.equals(nombre2)||icono1.getImage().equals(icono2.getImage())) {
					//Envia un mensaje si los iconos o los nombres dados por el usuario son iguales
					JOptionPane.showMessageDialog(null, "Selecciona Iconos y Nombres diferentes para cada jugador");
				} else {
					jugador1=new Jugador(1,nombre1,icono1);//Crea dos jugadores, cada uno con el icono
					jugador2=new Jugador(2,nombre2,icono2);//y el nombre asignado por el usuario
				}
			}
		});
		
	}
	
	private JList<ImageIcon> seleccionIcono() {//Crea una lista de iconos con las imagenes png que se encuentran en la carpeta imagenes/iconos
		File[] imagenes = new File("imagenes/iconos").listFiles();//Pone en un array todas las imagenes de la carpeta imagenes
		DefaultListModel<ImageIcon> iconos= new DefaultListModel<ImageIcon>();
		
		for(File file:imagenes) {
			ImageIcon icono = new ImageIcon("imagenes/iconos/"+file.getName());
			iconos.addElement(icono);
		}
		JList<ImageIcon> listaIconos = new JList<ImageIcon>(iconos);
		listaIconos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return listaIconos;
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}
}
