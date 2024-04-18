/**
 *  Menu principal del juego con las opcines de 
 *  jugar, mostrar las reglas y salir
 *  
 * @author Juan Stiven Avila
 *
 */

package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.JuegoUI;

public class MenuUI extends JFrame{
	
	private static final long serialVersionUID = 8787992256970685491L;
	private JPanel mainPanel, menuPanel, reglasPanel;
	private JLabel[] reglas;
	private SeleccionUI panelSeleccion;
	private JuegoUI juegoPanel;
	private JDialog menuPausa;

	public MenuUI() {
		//Establece el titulo de la ventana
		this.setTitle("MegaTricky");
		this.setIconImage(new ImageIcon("imagenes/principal/icono.png").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainPanel =new JPanel();
		this.mainPanel.setBorder(new EmptyBorder(5,5,5,5));
		this.setSize(700,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(mainPanel);
		iniciarComponentes();
		this.setVisible(true);
	}
	
	private void iniciarComponentes() {
		setupMenuUI();
		setupSeleccion();
		setupReglas();
	}

	private void setupMenuUI() {
		//Crea un Panel para el menu con botones para iniciar el juego, mostrar las reglas y para salir
		this.menuPanel=new JPanel();
		this.mainPanel.add(this.menuPanel);
		menuPanel.setLayout(new GridLayout(5,1,10,10));
		menuPanel.setMinimumSize(new Dimension(450,500));
		
		JPanel title=new JPanel();
		JLabel megaTricky = new JLabel("MegaTricky");//
		megaTricky.setFont(new Font("Chiller",Font.PLAIN,100));
		title.add(megaTricky);
		this.menuPanel.add(title);
		
		JPanel iniciar=new JPanel();
		JButton btnIniciar=new JButton("Iniciar Juego");//Boton para iniciar el juego
		btnIniciar.setPreferredSize(new Dimension(120,40));
		iniciar.add(btnIniciar);
		this.menuPanel.add(iniciar);
		
		JPanel reglas=new JPanel();
		JButton btnReglas=new JButton("Reglas");//Boton para mostrar las reglas del juego
		btnReglas.setPreferredSize(new Dimension(100,30));
		reglas.add(btnReglas);
		this.menuPanel.add(reglas);
		
		JPanel salir=new JPanel();
		JButton btnSalir = new JButton("Salir");//Boton para salir del juego
		btnSalir.setPreferredSize(new Dimension(70,30));
		salir.add(btnSalir);
		this.menuPanel.add(salir);
		
		panelSeleccion=new SeleccionUI();
		
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(menuPanel);
				showSeleccion();
			}
		});
		
		btnReglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(menuPanel);
				showReglas();
			}
		});
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void setupSeleccion() {
		panelSeleccion=new SeleccionUI();
		panelSeleccion.getBtnAceptar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(panelSeleccion.getJugador1()!=null) {
						remove(panelSeleccion);
						showJuego();
					}
			}
		});
	}
	
	private void setupReglas() {
		//Crea un panel con las imagenes correspondientes a las reglas del juego
		reglasPanel=new JPanel();
		reglasPanel.setLayout(new BorderLayout());
		
		JButton botonAnt =new JButton("<");//Boton para ver la imagen anterior de las reglas
		reglasPanel.add(botonAnt,BorderLayout.WEST);
		
		JButton botonSig =new JButton(">");//Boton para ver la siguiente imagen de las reglas
		reglasPanel.add(botonSig,BorderLayout.EAST);
		
		JButton botonVolver =new JButton("Volver");//Boton para vlver al menu principal
		reglasPanel.add(botonVolver,BorderLayout.SOUTH);
		
		reglas=new JLabel[6];
		for (int i=1;i<6;i++) {
			//Crea el array de JLabel con las imagenes de las reglas
			reglas[i]=new JLabel(new ImageIcon("imagenes/reglas/regla"+i+".png"));
		}
		reglasPanel.add(reglas[1],BorderLayout.CENTER);
		botonAnt.setEnabled(false);
		
		botonAnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i=1;i<6;i++) {
					if(java.util.Arrays.asList(reglasPanel.getComponents())
							.contains(reglas[i])){
						if(i!=5)
							botonSig.setEnabled(true);
						if(i==2)
							botonAnt.setEnabled(false);
						reglasPanel.remove(reglas[i]);
						reglasPanel.add(reglas[i-1],BorderLayout.CENTER);
						
						reglasPanel.updateUI();
						break;
					}		
				}
			}
		});
		
		botonSig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i=5;i>0;i--) {
					if(java.util.Arrays.asList(reglasPanel.getComponents())
							.contains(reglas[i])){
						if(i!=0)
							botonAnt.setEnabled(true);
						if(i==4)
							botonSig.setEnabled(false);
						reglasPanel.remove(reglas[i]);
						reglasPanel.add(reglas[i+1],BorderLayout.CENTER);
						reglasPanel.updateUI();
						break;
					}		
				}
			}
		});
		
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(reglasPanel);
				showMenu();
			}
		});
	}
	
	public void showMenuPausa() {
		//Crea una ventana para el menu de pausa que se puede acceder durante el juego
		menuPausa=new JDialog(this);
		menuPausa.setSize(200, 200);
		menuPausa.setLayout(new FlowLayout());
		menuPausa.setLocationRelativeTo(null);
		
		menuPausa.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JButton btnReanudar= new JButton("Reanudar juego");
		menuPausa.add(btnReanudar);
		
		JButton btnReiniciar= new JButton("Reiniciar juego");
		menuPausa.add(btnReiniciar);
		
		JButton btnVolverMenu= new JButton("Volver al menu principal");
		menuPausa.add(btnVolverMenu);
		
		JButton btnSalir= new JButton("Salir");
		menuPausa.add(btnSalir);
		
		btnReanudar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(true);
				menuPausa.dispose();
			}
		});
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(juegoPanel);
				showJuego();
				mainPanel.setVisible(true);
				menuPausa.dispose();
			}
		});
		btnVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(juegoPanel);
				showMenu();
				mainPanel.setVisible(true);
				menuPausa.dispose();
			}
			
		});
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.menuPausa.setVisible(true);
	}
	
	public void showReglas() {
		this.add(reglasPanel);
		this.setSize(450,450);
	}
	
	public void showMenu() {
		iniciarComponentes();
		this.setSize(700,600);
	}
	
	public void showJuego() {
		//Muestra el panel que contiene el juego con los jugadores obtenidos anteriormente en la pantalla de seleccion 
		juegoPanel = new JuegoUI(panelSeleccion.getJugador1(),panelSeleccion.getJugador2());
		
		this.add(juegoPanel);
		this.setSize(800,500);
		
		juegoPanel.getBtnPausa().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showMenuPausa();
				mainPanel.setVisible(false);
				remove(juegoPanel.getBtnPausa());
			}
		});
	}

	public void showSeleccion() {
		this.add(panelSeleccion);
		this.setSize(300,600);
	}
}
