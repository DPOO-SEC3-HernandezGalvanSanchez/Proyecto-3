package interfaz;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class PanelProyecto1 extends JPanel
{
	private MenuProyecto padre;
	private JScrollPane scrollPane = new JScrollPane();
	
	public PanelProyecto1(MenuProyecto padre, String nombre, String descripcion,
		    String fechaInicio, String fechaFin, ArrayList<String> participantes)
	{
		this.padre = padre;
		setLayout(null);
		setBorder(new EtchedBorder());
		
		int y = 40;
		final int spacing = 25;
		
		JLabel titulo = new JLabel("Informacion del proyecto");
		titulo.setBounds(40, 10, 600, 30);
		titulo.setFont(new Font("Bold", Font.BOLD, 13));
		add(titulo);
		
		JLabel mensajeNombre = new JLabel("Nombre: " + nombre);
		mensajeNombre.setBounds(40, y, 400, 30);
		mensajeNombre.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeNombre);
		y+=spacing;
		
		JLabel mensajeDescripcion = new JLabel("Descripcion: " + descripcion);
		mensajeDescripcion.setBounds(40, y, 400, 30);
		mensajeDescripcion.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeDescripcion);
		y+=spacing;
		
		JLabel mensajeFechaI = new JLabel("Fecha de inicio: " + fechaInicio);
		mensajeFechaI.setBounds(40, y, 400, 30);
		mensajeFechaI.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeFechaI);
		y+=spacing;
		
		JLabel mensajeFechaF = new JLabel("Fecha estimada de finalizacion: " + fechaFin);
		mensajeFechaF.setBounds(40, y, 400, 30);
		mensajeFechaF.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeFechaF);
		y+=spacing;
		
		JLabel mensajeParticipantes = new JLabel("Participantes: ");
		mensajeParticipantes.setBounds(40, y, 400, 30);
		mensajeParticipantes.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeParticipantes);
		
		setListaParticipantes(participantes);
	    scrollPane.setBounds(140, y+5, 300, 80);
	    add(scrollPane);
	}
	
	
	public void setListaParticipantes(ArrayList<String> logins)
	{
		String[] titulosTabla = {"Login", "Nombre"};
		String[][] infoTabla = new String[logins.size()][2];
		
		for (int i=0; i<logins.size(); i++)
		{
			String login = logins.get(i);
			infoTabla[i][0] = login;
			infoTabla[i][1] = padre.getNombreParticipante(login);
		}
		
		JTable tablaParticipantes = new JTable(infoTabla, titulosTabla);
	    scrollPane.setViewportView(tablaParticipantes);
	}
	
}
