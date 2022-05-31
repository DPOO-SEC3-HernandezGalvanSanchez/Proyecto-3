package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class DialogRegistrarActividad extends JDialog implements ActionListener, KeyListener
{
	private MenuProyecto padre;
		
	private JPanel settingsAct;
	private JComboBox<String> desplegableT;
	private JComboBox<String> desplegableA;
	private JTextField cuadroTitulo;
	private JTextField cuadroDescripcion;
	private JTextField cuadroHoraI;
	private JButton botonAceptar;
	private JLabel textLabel;
	
	
	public DialogRegistrarActividad(MenuProyecto padre)
	{
		this.padre = padre;
		
		settingsAct = new JPanel();
		settingsAct.setLayout(null);
		
		int y = 25;
		final int x = 220;
		final int spacing = 40;
		
		//Tipo
		JLabel mensajeTipo = new JLabel("Seleccione el tipo:");
		mensajeTipo.setBounds(20, y, 150, 30);
		mensajeTipo.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsAct.add(mensajeTipo);
		
		desplegableT = new JComboBox<String>();
		desplegableT.addKeyListener(this);
		desplegableT.setBounds(x, y+4, 170, 23);
		settingsAct.add(desplegableT);
		y += spacing;
		
		//Autor
		JLabel mensajeAutor = new JLabel("Seleccione el autor:");
		mensajeAutor.setBounds(20, y, 150, 30);
		mensajeAutor.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsAct.add(mensajeAutor);
		
		desplegableA = new JComboBox<String>();
		desplegableA.addKeyListener(this);
		desplegableA.setBounds(x, y+4, 170, 23);
		settingsAct.add(desplegableA);
		y += spacing;
		
		//Titulo
		JLabel mensajeTitulo = new JLabel("Ingrese el titulo:");
		mensajeTitulo.setBounds(20, y, 200, 30);
		mensajeTitulo.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsAct.add(mensajeTitulo);
		
		cuadroTitulo = new JTextField();
		cuadroTitulo.addKeyListener(this);
		cuadroTitulo.setBounds(x, y+4, 170, 23);
		settingsAct.add(cuadroTitulo);
		y += spacing;
		
		//Descripcion
		JLabel mensajeDescripcion = new JLabel("Ingrese una corta descripcion:");
		mensajeDescripcion.setBounds(20, y, 200, 30);
		mensajeDescripcion.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsAct.add(mensajeDescripcion);
		
		cuadroDescripcion = new JTextField();
		cuadroDescripcion.addKeyListener(this);
		cuadroDescripcion.setBounds(x, y+4, 170, 23);
		settingsAct.add(cuadroDescripcion);
		y += spacing;
		
		//Hora Inicio
		JLabel mensajeHoraI = new JLabel("Ingrese la hora de inicio:");
		mensajeHoraI.setBounds(20, y, 200, 30);
		mensajeHoraI.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsAct.add(mensajeHoraI);
		
		cuadroHoraI = new HintTextField("Ej: 14:00");
		cuadroHoraI.addKeyListener(this);
		cuadroHoraI.setBounds(x, y+4, 80, 23);
		settingsAct.add(cuadroHoraI);
		
		//Boton de aceptar
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(167, y+50, 100, 25);
		botonAceptar.addActionListener(this);
		add(botonAceptar);
		
		//Mensaje de advertencia
		textLabel = new JLabel("");
		textLabel.setBounds(20, y+90, 600, 23);
		textLabel.setForeground(Color.RED);
		this.add(textLabel);
		
		//Settings del JDialog
		add(settingsAct);
		setTitle("Datos de la actividad");
		setModal(true);
		setSize(440, y+160);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	
	public void addTipoDesplegable(String tipo)
	{
		desplegableT.addItem(tipo);
	}
	
	public void addParticipanteDesplegable(String login)
	{
		desplegableA.addItem(login);
	}
	
	
	//METODOS DE LISTENER
	private void continuar()
	{
		String tipo = desplegableT.getSelectedItem().toString();
		String loginAutor = desplegableA.getSelectedItem().toString();
		String titulo = cuadroTitulo.getText();
		String descripcion = cuadroDescripcion.getText();
		String horaI = cuadroHoraI.getText();
		
		if (titulo.equals("") || descripcion.equals("")
				|| horaI.equals(""))
		{
			String texto = "Por favor complete todos los campos";
			textLabel.setText(texto);
		}
		
		else
		{
			//padre.agregarActividad(tipo, titulo, descripcion,
			//					   horaI, loginAutor);
			this.dispose();
		}
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonAceptar)
		{
			continuar();
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e)
	{	
		if (e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			continuar();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{	
	}

	@Override
	public void keyReleased(KeyEvent e)
	{	
	}
	
	
}
