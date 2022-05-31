package interfaz;

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


@SuppressWarnings("serial")
public class DialogElegirParticipante extends JDialog
					implements ActionListener, KeyListener
{
	private MenuProyecto padre;
	
	private JPanel selectParticipante;
	private JComboBox<String> desplegable;
	private JButton botonAceptar;
	
	
	public DialogElegirParticipante(MenuProyecto padre)
	{
		this.padre = padre;
		
		selectParticipante = new JPanel();
		selectParticipante.setLayout(null);
		
		//Desplegable
		JLabel mensaje = new JLabel("Seleccione el participante:");
		mensaje.setBounds(20, 25, 180, 30);
		mensaje.setFont(new Font("Bold", Font.PLAIN, 13));
		selectParticipante.add(mensaje);
		
		desplegable = new JComboBox<String>();
		desplegable.addKeyListener(this);
		desplegable.setBounds(220, 29, 170, 23);
		selectParticipante.add(desplegable);
		
		//Boton de aceptar
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(167, 75, 100, 25);
		botonAceptar.addActionListener(this);
		add(botonAceptar);
		
		//Settings del JDialog
		add(selectParticipante);
		setTitle("Seleccionar participante");
		setModal(true);
		setSize(440, 160);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	
	public void addParticipanteDesplegable(String login)
	{
		desplegable.addItem(login);
	}
	
	
	//METODOS DEL LISTENER
	private void continuar()
	{
		this.dispose();
		String login = desplegable.getSelectedItem().toString();
		padre.generarReporte(login);
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
