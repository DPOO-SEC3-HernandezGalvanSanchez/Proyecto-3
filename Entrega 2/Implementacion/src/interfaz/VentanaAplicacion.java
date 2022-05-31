package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.CoordinadorProyecto;
import modelo.Participante;
import procesamiento.ArchivoUsuarios;


@SuppressWarnings("serial")
public class VentanaAplicacion extends JFrame implements ActionListener
{
	private CoordinadorProyecto coordinadorProyecto = new CoordinadorProyecto();
	private ArchivoUsuarios archivoUsuarios = new ArchivoUsuarios();
	
	private String loginEnUso;
	private Participante usuarioEnUso;
	private String FECHA;
	private String HORA_ACTUAL;
	
	private Menu menuActual;
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JButton botonContinuar;
	private JButton botonAtras;
	
	
	
	public VentanaAplicacion()
	{
		setLayout(new BorderLayout());
		initPanelInferior();
		add(panelInferior, BorderLayout.SOUTH);
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy"); 
		FECHA = formatter1.format(Calendar.getInstance().getTime());
		
		SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm"); 
		HORA_ACTUAL = formatter2.format(Calendar.getInstance().getTime());
		
		menuActual = new MenuLogin(this);
		updateMenu();
		
		setSize(550, 600);
		setTitle("Proyecto 2 - DPOO");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void refresh()
	{
		String nombreProyecto = coordinadorProyecto.getNombreProyecto();
		
		coordinadorProyecto = new CoordinadorProyecto();
    	archivoUsuarios = new ArchivoUsuarios();
    	
    	coordinadorProyecto.seleccionarProyecto(nombreProyecto);
	}
	
	
	//AUXILIARES
	private void initPanelInferior()
	{
		panelInferior = new JPanel();
		panelInferior.setBackground(Color.white);
		
		panelInferior.setLayout(new BorderLayout());
		
		botonContinuar = new JButton("Continuar");
		botonContinuar.addActionListener(this);
		panelInferior.add(botonContinuar, BorderLayout.EAST);
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(this);
		botonAtras.setPreferredSize(new Dimension(80, 27));
		botonAtras.setVisible(false);
		panelInferior.add(botonAtras, BorderLayout.WEST);		
	}
	
	
	private void updateMenu()
	{
		if (panelSuperior!=null)
		{
			panelSuperior.setVisible(false);
		}
		
		String header = menuActual.getHeader();
		JLabel titulo = new JLabel(header);
		titulo.setFont(new Font("Bold", Font.BOLD, 15));
		
		panelSuperior = new JPanel();
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		panelSuperior.add(titulo);
		
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(menuActual, BorderLayout.CENTER);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonContinuar)
        {
            Integer menuID = menuActual.getID();
            
            if (menuID.equals(1))
            {
            	botonAtras.setVisible(true);
            	menuActual.setVisible(false);
            	menuActual = new MenuEleccionProyecto(this);
            	updateMenu();
            }
            
            else if (menuID.equals(2))
            {
            	botonContinuar.setVisible(false);
            	menuActual.setVisible(false);
            	menuActual = new MenuProyecto(this);
            	updateMenu();
            }            
        }
		
		else if (e.getSource()==botonAtras)
        {
            Integer menuID = menuActual.getID();
            
            if (menuID.equals(2))
            {
            	botonAtras.setVisible(false);
            	menuActual.setVisible(false);
            	menuActual = new MenuLogin(this);
            	updateMenu();
            }
            
            if (menuID.equals(3))
            {
            	refresh();
            	botonContinuar.setVisible(true);
            	menuActual.setVisible(false);
            	menuActual = new MenuEleccionProyecto(this);
            	updateMenu();
            }
        }
    }
	
	
	//GETTERS y SETTERS
	public String getFecha()
	{
		return FECHA;
	}
	
	public String getHora()
	{
		return HORA_ACTUAL;
	}
	
	public String getLoginEnUso()
	{
		return loginEnUso;
	}
	
	public Participante getUsuarioEnUso()
	{
		return usuarioEnUso;
	}
	
	public ArchivoUsuarios getArchivoUsuarios()
	{
		return archivoUsuarios;
	}
	
	public CoordinadorProyecto getCoordinadorProyecto()
	{
		return coordinadorProyecto;
	}
	
	public void setLoginEnUso(String login)
	{
		loginEnUso = login;
	}
	
	public void setUsuarioEnUso(Participante usuario)
	{
		usuarioEnUso = usuario;
	}
	
	public void enableBotonContinuar(boolean bool)
	{
		botonContinuar.setEnabled(bool);
	}
	
	
	
	//EJECUCION
	public static void main(String[] args)
	{
		new VentanaAplicacion();
	}
	
}
