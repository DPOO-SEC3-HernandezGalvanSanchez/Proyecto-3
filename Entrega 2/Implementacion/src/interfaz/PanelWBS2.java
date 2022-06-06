package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

import modelo.PaqueteDeTrabajo;
import modelo.ReporteAvance;
import modelo.ReporteCalidadPlaneacion;
import modelo.ReporteDesempenoEquipo;
import modelo.ReporteResumenProyecto;
import modelo.WBS;


@SuppressWarnings("serial")
public class PanelWBS2 extends JPanel implements ActionListener
{
	private MenuWBS padre;
	private WBS wbs;
	
	private JButton botonAceptar;
	private JRadioButton botonOpc1;
	private JRadioButton botonOpc2;
	private JRadioButton botonOpc3;
	private JRadioButton botonOpc4;
	
	private JLabel textLabel;
	
	
	public PanelWBS2(MenuWBS padre, WBS wbs)
	{
		this.padre = padre;
		this.wbs = wbs;
		
		setLayout(null);
		setBorder(new EtchedBorder());
		
		JLabel titulo = new JLabel("Seleccione el reporte que desea consultar:");
		titulo.setBounds(40, 10, 600, 30);
		titulo.setFont(new Font("Bold", Font.BOLD, 13));
		add(titulo);
		
		//OPCIONES
		botonOpc1 = new JRadioButton("Avance de un Paquete");
		botonOpc1.setFont(new Font("Bold", Font.PLAIN, 13));
		botonOpc1.setSelected(true);
		botonOpc1.setBounds(50, 40, 200, 30);
		add(botonOpc1);
		
		botonOpc2 = new JRadioButton("Calidad de Planeacion");
		botonOpc2.setFont(new Font("Bold", Font.PLAIN, 13));
		botonOpc2.setBounds(50, 70, 200, 30);
		add(botonOpc2);
		
		botonOpc3 = new JRadioButton("Desempeno del Equipo");
		botonOpc3.setFont(new Font("Bold", Font.PLAIN, 13));
		botonOpc3.setBounds(50, 100, 200, 30);
		add(botonOpc3);
		
		botonOpc4 = new JRadioButton("Resumen del Proyecto");
		botonOpc4.setFont(new Font("Bold", Font.PLAIN, 13));
		botonOpc4.setBounds(50, 130, 200, 30);
		add(botonOpc4);
		
		ButtonGroup opciones = new ButtonGroup();
		opciones.add(botonOpc1);
		opciones.add(botonOpc2);
		opciones.add(botonOpc3);
		opciones.add(botonOpc4);
		
		//BOTON ACEPTAR
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(220, 170, 100, 25);
		botonAceptar.addActionListener(this);
		add(botonAceptar);
		
		//LABEL INFERIOR
		textLabel = new JLabel("El avance del proyecto completo se puede consultar en 'Resumen del Proyecto'");
		textLabel.setBounds(40, 210, 600, 20);
		textLabel.setForeground(new Color(105, 105, 105));;
		this.add(textLabel);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonAceptar)
		{
			if (botonOpc1.isSelected())
			{
				String titulo = JOptionPane.showInputDialog(this, "Ingrese el titulo del paquete");
				
				for (int i=0; i<wbs.getNumPaquetes(); i++)
				{
					PaqueteDeTrabajo paquete = wbs.getPaquete(i);
					
					if (paquete.getTitulo().equals(titulo))
					{
						ReporteAvance avance = wbs.calcularAvancePaquete(i);
						//new DialogReporteAvance(avance);
					}
				}
			}
			
			else if (botonOpc2.isSelected())
			{
				ReporteCalidadPlaneacion calidad = wbs.calcularCalidadPlaneacion();
				//new DialogReporteCalidadPlaneacion(calidad);
			}
			
			else if (botonOpc3.isSelected())
			{
				ReporteDesempenoEquipo equipo = wbs.calcularDesempenoEquipo();
				//new DialogReporteDesempeno(equipo);
			}
			
			else if (botonOpc4.isSelected())
			{
				ReporteResumenProyecto resumen = wbs.calcularResumenProyecto();
			}
		}
	}
	
	
}
