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
public class DialogElegirRegistro extends JDialog
				implements ActionListener, KeyListener
{
	private MenuProyecto padre;
	private String tituloAct;
	
	private JPanel selectRegistro;
	private JLabel mensajeR;
	private JComboBox<String> desplegableT;
	private JComboBox<String> desplegableR;
	private JButton botonContinuar;
	private JButton botonAceptar;
	
	
	public DialogElegirRegistro(MenuProyecto padre)
	{
		this.padre = padre;
		
		selectRegistro = new JPanel();
		selectRegistro.setLayout(null);
		
		int y = 25;
		
		//Seleccion del titulo
		JLabel mensajeT = new JLabel("Seleccione el titulo de la actividad:");
		mensajeT.setBounds(20, y, 260, 30);
		mensajeT.setFont(new Font("Bold", Font.PLAIN, 13));
		selectRegistro.add(mensajeT);
		
		desplegableT = new JComboBox<String>();
		desplegableT.setBounds(290, y + 4, 190, 23);
		desplegableT.addKeyListener(this);
		selectRegistro.add(desplegableT);
		y += 40;
		
		//Seleccion del registro segun su fecha
		mensajeR = new JLabel("Seleccione el registro a modificar:");
		mensajeR.setBounds(20, y, 260, 30);
		mensajeR.setFont(new Font("Bold", Font.PLAIN, 13));
		mensajeR.setVisible(false);
		selectRegistro.add(mensajeR);
		
		desplegableR = new JComboBox<String>();
		desplegableR.setBounds(290, y + 4, 190, 23);
		desplegableR.addKeyListener(this);
		desplegableR.setVisible(false);
		selectRegistro.add(desplegableR);
		y += 70;
		
		//Boton de continuar
		botonContinuar = new JButton("Continuar");
		botonContinuar.setBounds(212, y, 100, 25);
		botonContinuar.addActionListener(this);
		add(botonContinuar);
		
		//Boton de aceptar
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(212, y, 100, 25);
		botonAceptar.addActionListener(this);
		botonAceptar.setVisible(false);
		add(botonAceptar);
		
		//Settings del JDialog
		add(selectRegistro);
		setTitle("Seleccionar registro");
		setModal(true);
		setSize(530, 220);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	
	public void addTituloDesplegable(String titulo)
	{
		desplegableT.addItem(titulo);
	}
	
	
	public void addFechaDesplegable(String fecha, 
			String horaInicio, String horaFin)
	{
		String t = fecha + "    " + horaInicio + "-" + horaFin;
		desplegableR.addItem(t);
	}
	
	
	//METODOS DEL LISTENER
	private void continuar()
	{
		tituloAct = desplegableT.getSelectedItem().toString();
		padre.addRegistros(this, tituloAct);
		
		desplegableT.setEnabled(false);
		desplegableR.setVisible(true);
		mensajeR.setVisible(true);
		botonContinuar.setVisible(false);
		botonAceptar.setVisible(true);
	}
	
	
	private void aceptar()
	{
		this.dispose();
		int index = desplegableR.getSelectedIndex();
		padre.modificarRegistro(tituloAct, index);	
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonContinuar)
		{
			continuar();
		}
		
		else if (e.getSource()==botonAceptar)
		{
			aceptar();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{	
		if (e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if (e.getComponent()==desplegableT)
			{
				continuar();
			}
			
			else if (e.getComponent()==desplegableR)
			{
				aceptar();
			}
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
