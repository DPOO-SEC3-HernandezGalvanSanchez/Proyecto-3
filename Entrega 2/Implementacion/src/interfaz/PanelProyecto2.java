package interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class PanelProyecto2 extends JPanel implements ActionListener
{
	private MenuProyecto padre;
	
	private JButton botonAceptar;
	private JRadioButton botonOpc1;
	private JRadioButton botonOpc2;
	private JRadioButton botonOpc3;
	private JRadioButton botonOpc4;
	private JRadioButton botonOpc5;
	
	
	public PanelProyecto2(MenuProyecto padre)
	{
		this.padre = padre;
		
		setLayout(null);
		setBorder(new EtchedBorder());
		
		JLabel titulo = new JLabel("Seleccione la accion que desea realizar:");
		titulo.setBounds(40, 10, 600, 30);
		titulo.setFont(new Font("Bold", Font.BOLD, 13));
		add(titulo);
		
		//OPCIONES
		botonOpc1 = new JRadioButton("Agregar un participante");
		botonOpc1.setFont(new Font("Bold", Font.PLAIN, 13));
		botonOpc1.setSelected(true);
		botonOpc1.setBounds(50, 40, 200, 30);
		add(botonOpc1);
		
		botonOpc3 = new JRadioButton("Modificar un registro");
		botonOpc3.setFont(new Font("Bold", Font.PLAIN, 13));
		botonOpc3.setBounds(50, 70, 200, 30);
		add(botonOpc3);
		
		botonOpc4 = new JRadioButton("Generar un reporte");
		botonOpc4.setFont(new Font("Bold", Font.PLAIN, 13));
		botonOpc4.setBounds(50, 100, 200, 30);
		add(botonOpc4);
		
		botonOpc5 = new JRadioButton("Ver calendario");
		botonOpc5.setFont(new Font("Bold", Font.PLAIN, 13));
		botonOpc5.setBounds(50, 130, 200, 30);
		add(botonOpc5);
		
		ButtonGroup opciones = new ButtonGroup();
		opciones.add(botonOpc1);
		opciones.add(botonOpc2);
		opciones.add(botonOpc3);
		opciones.add(botonOpc4);
		opciones.add(botonOpc5);
		
		//BOTON ACEPTAR
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(220, 180, 100, 25);
		botonAceptar.addActionListener(this);
		add(botonAceptar);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonAceptar)
		{
			if (botonOpc1.isSelected())
			{
				padre.newParticipantSettings();
			}
			
			/*else if (botonOpc2.isSelected())
			{
				padre.newActivitySettings();
			}*/
			
			else if (botonOpc3.isSelected())
			{
				padre.elegirTituloRegistro();
			}
			
			else if (botonOpc4.isSelected())
			{
				padre.elegirParticipante();
			}
			
			else if (botonOpc5.isSelected())
			{
				System.out.println("Opcion 5");
				try {
					padre.generarCalendario();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		}
	}
	
}
