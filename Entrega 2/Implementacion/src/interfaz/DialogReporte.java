package interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;

import modelo.Actividad;
import modelo.Participante;


@SuppressWarnings("serial")
public class DialogReporte extends JDialog
{

	public DialogReporte(Participante participante, ArrayList<Actividad> actividades,
								  int tiempoTotal, HashMap<String, Double> promedios)
	{
		setLayout(new BorderLayout());
		
		addPanelSuperior(participante);
		addPanelCentral(actividades);
		addPanelInferior(tiempoTotal, promedios);
		
		setTitle("Reporte del participante");
		setModal(true);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	
	private void addPanelSuperior(Participante participante)
	{
		JPanel pSuperior = new JPanel();
		pSuperior.setLayout(new GridLayout(3, 1, 1, 2));
		pSuperior.setBorder(new EtchedBorder());
		
		JLabel lNombre = new JLabel("Nombre: " + participante.getNombre());		
		lNombre.setFont(new Font("Bold", Font.PLAIN, 13));
		pSuperior.add(lNombre);
		
		JLabel lCorreo = new JLabel("Correo: " + participante.getCorreo());
		lCorreo.setFont(new Font("Bold", Font.PLAIN, 13));
		pSuperior.add(lCorreo);
		
		this.add(pSuperior, BorderLayout.NORTH);
	}

	
	private void addPanelCentral(ArrayList<Actividad> actividades)
	{
		JPanel pCentro = new JPanel();
		pCentro.setLayout(null);
		pCentro.setBorder(new EtchedBorder());
		
		JLabel mensaje = new JLabel("Detalle de las actividades realizadas:");
		mensaje.setFont(new Font("Bold", Font.PLAIN, 13));
		mensaje.setBounds(0, 0, 250, 30);
		pCentro.add(mensaje);
		
		
		JTextArea info = new JTextArea();
		info.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		info.setEditable(false);
		
		for (Actividad act : actividades)
		{
			info.append("Titulo: " + act.getTitulo() + "\n");
			info.append("Tipo: " + act.getTipoActividad()  + "\n");
			info.append("Descripcion: " + act.getDescripcion()  + "\n");
			info.append("Fecha: " + act.getFecha() + "\n");
			info.append("Hora de inicio: " + act.getHoraInicio() + "\n");
			info.append("Hora de finalizacion: " + act.getHoraFin() + "\n");
			info.append("Duracion: " + act.getTiempo() + " minutos\n");
			info.append("-------------------------------------------");
			info.append("-------------------------------------------\n");
		}
		
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(info);
		scroll.setBounds(50, 40, 380, 160);
		pCentro.add(scroll);
		
		this.add(pCentro, BorderLayout.CENTER);
	}
	
	
	private void addPanelInferior(int tiempoTotal,
			 	HashMap<String, Double> promedios)
	{
		JPanel pInferior = new JPanel();
		pInferior.setLayout(new GridLayout(10, 1, 1, 2));
		pInferior.setBorder(new EtchedBorder());
		
		JLabel lMensajeTotal = new JLabel("Tiempo total invertido:");
		pInferior.add(lMensajeTotal);
		
		JLabel lTotal = new JLabel(tiempoTotal + " minutos");
		lTotal.setFont(new Font("Bold", Font.PLAIN, 12));
		pInferior.add(lTotal);
		
		pInferior.add(new JLabel(""));
		
		JLabel lMensajePromedio = new JLabel("Tiempo promedio por tipo de actividad:");
		pInferior.add(lMensajePromedio);
		
		
		for(String tipo: promedios.keySet())
		{
			double promedio = promedios.get(tipo);
			JLabel linea = new JLabel("- " + tipo + ": " + promedio + " minutos");
			linea.setFont(new Font("Bold", Font.PLAIN, 12));
			pInferior.add(linea);
		}
		
		
		this.add(pInferior, BorderLayout.SOUTH);
	}

}
