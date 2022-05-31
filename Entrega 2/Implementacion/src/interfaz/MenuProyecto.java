package interfaz;

import java.awt.GridLayout;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

import modelo.Actividad;
import modelo.CoordinadorProyecto;
import modelo.Participante;
import procesamiento.ArchivoUsuarios;


@SuppressWarnings("serial")
public class MenuProyecto extends Menu
{
	private VentanaAplicacion ventana;	
	private PanelProyecto1 p1;
	private PanelProyecto2 p2;	
	
	
	public MenuProyecto(VentanaAplicacion padre)
	{
		super(3, "PROYECTO");
		this.ventana = padre;
		ventana.enableBotonContinuar(false);
		setLayout(new GridLayout(2, 1));
		
		CoordinadorProyecto coordinadorProyecto = padre.getCoordinadorProyecto();
		String nombre = coordinadorProyecto.getNombreProyecto();
		String descripcion = coordinadorProyecto.getDescripcionProyecto();
		String fechaInicio = coordinadorProyecto.getFechaInicio();
		String fechaFin = coordinadorProyecto.getFechaFin();
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> logins = new ArrayList<String>(participantesProyecto.keySet());
		
		p1 = new PanelProyecto1(this, nombre, descripcion,
				                fechaInicio, fechaFin, logins);
		add(p1);
		
		p2 = new PanelProyecto2(this);
		add(p2);
	}

	
	//PRIMER PANEL
	public String getNombreParticipante(String login)
	{
		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante participante = archivoUsuarios.getParticipante(login);
		
		return participante.getNombre();
	}
	
	
	//AGREGAR PARTICIPANTE
	public void newParticipantSettings()
	{
		new DialogAgregarParticipante(this);
	}
	
	
	public boolean loginRegistrado(String login)
	{
		boolean ans = true;
		
		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante usuarioEnUso = archivoUsuarios.getParticipante(login);
		
		if (usuarioEnUso == null)
		{
			ans = false;				
		}
		
		return ans;
	}
	
	
	public void agregarParticipante(String login)
	{
		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante usuarioEnUso = archivoUsuarios.getParticipante(login);
		String nombre = usuarioEnUso.getNombre();
		
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.agregarParticipante(new Participante(login, nombre));
		ventana.refresh();
		
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> nombres = new ArrayList<String>(participantesProyecto.keySet());
		
		p1.setListaParticipantes(nombres);
	}
	
	
	public void agregarParticipante(String login, String nombre)
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.agregarParticipante(new Participante(login, nombre));
		ventana.refresh(); //REVISAR
		
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> nombres = new ArrayList<String>(participantesProyecto.keySet());
		
		p1.setListaParticipantes(nombres);
	}
	
	
	//AGREGAR ACTIVIDAD
	public void newActivitySettings()
	{
		DialogRegistrarActividad settingsAct = new DialogRegistrarActividad(this);
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		
		//Menu desplegable de tipos
		ArrayList<String> tipos = coordinadorProyecto.getTiposActividades();
		
		for (String tipo : tipos)
		{
			settingsAct.addTipoDesplegable(tipo);
		}
		
		//Menu desplegable de participantes
		String loginAutor = ventana.getUsuarioEnUso().getLogin();
		
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> logins = new ArrayList<String>(participantesProyecto.keySet());
		settingsAct.addParticipanteDesplegable(loginAutor);
		
		for (String login : logins)
		{
			if (!login.equals(loginAutor))
			{
				settingsAct.addParticipanteDesplegable(login);
			}
		}
		
		//Mostrar cuadro de dialogo
		settingsAct.setVisible(true);
	}

	
	public void agregarActividad(String tipoActividad, String titulo, String descripcion,
								 String horaInicio, String loginAutor)
	{
		String fecha = ventana.getFecha();
		String horaActual = ventana.getHora();

		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante autor = archivoUsuarios.getParticipante(loginAutor);
		
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.registrarActividad(tipoActividad, titulo, descripcion,
											   fecha, horaInicio, horaActual, autor);
	}


	//MODIFICAR REGISTRO
	public void elegirTituloRegistro()
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> actividades = coordinadorProyecto.getActividades();
		ArrayList<String> titulos = new ArrayList<String>(actividades.keySet());
		
		DialogElegirRegistro selectReg = new DialogElegirRegistro(this); 
		
		for (String titulo : titulos)
		{
			selectReg.addTituloDesplegable(titulo);
		}
		
		selectReg.setVisible(true);
	}
	
	
	public void addRegistros(DialogElegirRegistro selectReg, String titulo)
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> actividades = coordinadorProyecto.getActividades();
		ArrayList<Actividad> registros = actividades.get(titulo);
	
		for (Actividad registro : registros)
		{
			String fechaAct = registro.getFecha();
			String horaInicio = registro.getHoraInicio();
			String horaFin = registro.getHoraFin();
			selectReg.addFechaDesplegable(fechaAct, horaInicio, horaFin);
		}
	}
	
	
	public void modificarRegistro(String titulo, int index)
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> actividades = coordinadorProyecto.getActividades();
		Actividad registro = actividades.get(titulo).get(index);
		
		String fecha = registro.getFecha();
		String horaInicio = registro.getHoraInicio();
		String horaFin = registro.getHoraFin();
		
		new DialogModificarRegistro(this, titulo, index, fecha, horaInicio, horaFin);
	}
	
	
	public void actualizarRegistro(String titulo, int index,
			String fecha, String horaInicio, String horaFin)
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.actualizarActividad(titulo, index, fecha,
												horaInicio, horaFin);
	}
	
	
	
	//GENERAR REPORTE
	public void elegirParticipante()
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> logins = new ArrayList<String>(participantesProyecto.keySet());
		
		DialogElegirParticipante selectPart = new DialogElegirParticipante(this);
		
		for (String login : logins)
		{
			selectPart.addParticipanteDesplegable(login);
		}
		
		selectPart.setVisible(true);
	}
	
	
	public void generarReporte(String login)
	{
		ArchivoUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		
		Participante participante = archivoUsuarios.getParticipante(login);
		ArrayList<Actividad> actividadesMiembro = coordinadorProyecto.actividadesMiembro(login);
		int tiempoTotal = coordinadorProyecto.tiempoTotal(actividadesMiembro);
		HashMap<String, Double> promedios = coordinadorProyecto.tiempoPorActividad(actividadesMiembro);
		
		
		new DialogReporte(participante, actividadesMiembro, tiempoTotal, promedios);		
	}

	
	// GENERAR CALENDARIO
	public void generarCalendario() throws ParseException
	{
		CoordinadorProyecto coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> Actividades = coordinadorProyecto.getActividades();
		String fechaInicio = coordinadorProyecto.getFechaInicio();
		Collection<ArrayList<Actividad>> values = Actividades.values();
		HashMap<String, Integer> fechas = new HashMap<String, Integer>();
		for (ArrayList<Actividad> list : values)
		{
			for (int i = 0; i <list.size(); i++)
			{
				Actividad Act = list.get(i);
				String fecha = Act.getFecha();
				if (fechas.containsKey(fecha))
				{
					fechas.put(fecha,  Integer.valueOf(fechas.get(fecha).intValue()+1));
				}
				else
				{
					fechas.put(fecha, Integer.valueOf(1));
				}
			}
		}
		new DialogCalendario(fechas, fechaInicio);
	}


}
