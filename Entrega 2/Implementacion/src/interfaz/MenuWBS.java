package interfaz;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.Actividad;
import modelo.CoordinadorProyecto;
import modelo.Participante;
import modelo.Tarea;
import modelo.WBS;
import procesamiento.ArchivoUsuarios;

@SuppressWarnings("serial")
public class MenuWBS extends Menu
{
	private VentanaAplicacion ventana;
	private PanelWBS1 p1;
	private PanelWBS2 p2;
	
	
	public MenuWBS(VentanaAplicacion padre)
	{
		super(4, "Work Breakdown Structure");
		this.ventana = padre;
		ventana.enableBotonContinuar(false);
		setLayout(new GridLayout(2, 1));
		
		WBS wbs = ventana.getCoordinadorProyecto().getWBS();
		
		p1 = new PanelWBS1(this, wbs);
		this.add(p1);
		
		p2 = new PanelWBS2(this, wbs);
		this.add(p2);
	}
	
	public Actividad agregarActividad(String tipoActividad, String titulo, String descripcion,
			 String horaInicio, String loginAutor, Tarea tarea, boolean cierraTarea) throws Exception
	{
		String fecha = ventana.getFecha();
		String horaActual = ventana.getHora();
		
		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante autor = archivoUsuarios.getParticipante(loginAutor);
		
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		Actividad act = coordinadorProyecto.registrarActividad(tipoActividad, titulo, descripcion,
								         fecha, horaInicio, horaActual, autor, tarea, cierraTarea);
		
		return act;
	}
	
	public void guardarArchivo()
	{
		ventana.getCoordinadorProyecto().guardarArchivo();
	}
	
	public Participante getParticipante(String login)
	{
		ArchivoUsuarios a = ventana.getArchivoUsuarios();
		return a.getParticipante(login);
	}
	
	public void newTaskSettings(DialogNuevaTarea settingsTask)
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		
		//Menu desplegable de tipos
		ArrayList<String> tipos = coordinadorProyecto.getTiposActividades();
		
		for (String tipo : tipos)
		{
			settingsTask.addTipoDesplegable(tipo);
		}
		
		//Menu desplegable de participantes
		String loginAutor = ventana.getUsuarioEnUso().getLogin();
		
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> logins = new ArrayList<String>(participantesProyecto.keySet());
		settingsTask.addParticipanteCB(loginAutor);
		
		for (String login : logins)
		{
			if (!login.equals(loginAutor))
			{
				settingsTask.addParticipanteCB(login);
			}
		}
		
		settingsTask.addParticipantesScroll();
		
		//Mostrar cuadro de dialogo
		settingsTask.setVisible(true);
	}
}
