package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class DialogModificarRegistro extends JDialog
				implements ActionListener, KeyListener
{
	
	private MenuProyecto padre;
	private String titulo;
	private int index;
	
	private JPanel settingsReg;
	private JTextField cuadroFecha;
	private JTextField cuadroHoraInicio;
	private JTextField cuadroHoraFin;
	private JButton botonAceptar;
	private JLabel textLabel;
	
	
	public DialogModificarRegistro(MenuProyecto padre, String titulo,
			int index, String fecha, String horaInicio, String horaFin)
	{
		this.padre = padre;
		this.titulo = titulo;
		this.index = index;
		
		settingsReg = new JPanel();
		settingsReg.setLayout(null);
		
		int y = 25;
		final int spacing = 35;
		
		
		//Fecha
		JLabel mensajeFecha = new JLabel("Fecha:");
		mensajeFecha.setBounds(20, y, 150, 30);
		mensajeFecha.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsReg.add(mensajeFecha);
		
		cuadroFecha = new JTextField(fecha);
		cuadroFecha.addKeyListener(this);
		cuadroFecha.setBounds(220, y+4, 150, 23);
		settingsReg.add(cuadroFecha);
		y += spacing;
		
		//Hora Inicio
		JLabel mensajeHoraI = new JLabel("Hora de inicio:");
		mensajeHoraI.setBounds(20, y, 150, 30);
		mensajeHoraI.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsReg.add(mensajeHoraI);
		
		cuadroHoraInicio = new JTextField(horaInicio);
		cuadroHoraInicio.addKeyListener(this);
		cuadroHoraInicio.setBounds(220, y+4, 150, 23);
		settingsReg.add(cuadroHoraInicio);
		y += spacing;
		
		//Hora Fin
		JLabel mensajeHoraF = new JLabel("Hora de finalizacion:");
		mensajeHoraF.setBounds(20, y, 150, 30);
		mensajeHoraF.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsReg.add(mensajeHoraF);
		
		cuadroHoraFin = new JTextField(horaFin);
		cuadroHoraFin.addKeyListener(this);
		cuadroHoraFin.setBounds(220, y+4, 150, 23);
		settingsReg.add(cuadroHoraFin);
		y += 50;
		
		//Boton de aceptar
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(147, y, 100, 25);
		botonAceptar.addActionListener(this);
		add(botonAceptar);
		y += 40;
		
		//Mensaje de advertencia
		textLabel = new JLabel("");
		textLabel.setBounds(20, y, 600, 23);
		textLabel.setForeground(Color.RED);
		this.add(textLabel);
		y += 70;
		
		//Settings del JDialog
		add(settingsReg);
		setTitle("Informacion del registro");
		setModal(true);
		setSize(400, y);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	
	//METODOS DEL LISTENER
	private void continuar()
	{
		String fecha = cuadroFecha.getText();
		String horaInicio = cuadroHoraInicio.getText();
		String horaFin = cuadroHoraFin.getText();
		
		if (fecha.equals("") || horaInicio.equals("") ||
				horaFin.equals(""))
		{
			String texto = "Por favor complete todos los campos";
			textLabel.setText(texto);
		}
		
		else
		{
			padre.actualizarRegistro(titulo, index, fecha,
								      horaInicio, horaFin);
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
