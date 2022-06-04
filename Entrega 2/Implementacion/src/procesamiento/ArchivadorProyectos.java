package procesamiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import modelo.Actividad;
import modelo.GestorActividades;
import modelo.PaqueteDeTrabajo;
import modelo.Participante;
import modelo.ProxyRegistro;
import modelo.Proyecto;
import modelo.Tarea;
import modelo.WBS;


public class ArchivadorProyectos
{
	// ATRIBUTOS
	private HashMap<String, Proyecto> catalogoProyectos = new HashMap<String, Proyecto>();
	private HashMap<String, ArrayList<String>> usuariosProyectos = new HashMap<String, ArrayList<String>>();
	
	
	// CONSTRUCTOR
	public ArchivadorProyectos()
	{
		try
		{
			cargarProyectos();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("ERROR: el archivo indicado no se encontro.");
		}
		catch (IOException e)
		{
			System.err.println("ERROR: hubo un problema leyendo el archivo.");
			System.err.println(e.getMessage());
		}
	}
	
	
	// CARGA DEL ARCHIVO	
	private void cargarProyectos() throws FileNotFoundException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("./data/proyectos.txt", StandardCharsets.UTF_8));
		String linea = br.readLine();
		WBS wbsActual = null;
		Tarea tareaActual = null;
		
		while (linea != null)
		{
			String[] partes = linea.split(",");
			
			if (partes[0].equals("PROY"))
			{
				Proyecto proyectoActual = cargarUnProyecto(partes);
				wbsActual = proyectoActual.getWBS();
			}
			
			else if (partes[0].equals("PQT"))
			{
				PaqueteDeTrabajo paquete = cargarUnPaquete(partes);
				wbsActual.agregarPaquete(paquete);
			}
			
			else if (partes[0].equals("TAR"))
			{
				tareaActual = cargarUnaTarea(partes);
				wbsActual.agregarTarea(tareaActual);
			}
			
			else if (partes[0].equals("ACT"))
			{
				cargarUnaActividad(partes, tareaActual);
			}
			
			linea = br.readLine();
		}
		br.close();
		generarAuxiliar();
	}

	
	private Proyecto cargarUnProyecto(String[] partes)
	{
		String titulo = partes[1];
		String descripcion = partes[2];
		ArrayList<String> tipos = new ArrayList<String>(Arrays.asList(partes[3].split(";")));
		String fechaInicio = partes[4];
		String fechaFin = partes[5];
		String[] datosCreador = partes[6].split(";");
		Participante creador = new Participante(datosCreador[0], datosCreador[1]);
		registrarParticipante(datosCreador,titulo);
		
		Proyecto proyectoActual = new GestorActividades(titulo, descripcion, fechaInicio,
														fechaFin, tipos, creador);
		
		if (partes.length > 7)
		{
			for (int i=7; i<partes.length; i++)
			{
				String[] datosParticipante = partes[i].split(";");
				Participante participante = new Participante(datosParticipante[0], datosParticipante[1]);
				registrarParticipante(datosParticipante, titulo);
				proyectoActual.agregarParticipante(participante);
			}
		}
		catalogoProyectos.put(titulo, proyectoActual);
		
		return proyectoActual;
	}
	
	
	private PaqueteDeTrabajo cargarUnPaquete(String[] partes)
	{
		String titulo = partes[1];
		String descripcion = partes[2];
		int indexPadre = Integer.parseInt(partes[3]);
		
		PaqueteDeTrabajo paquete = new PaqueteDeTrabajo(titulo, descripcion, indexPadre);
	
		return paquete;
	}
	
	
	private Tarea cargarUnaTarea(String[] partes)
	{
		String nombreTarea = partes[1];
		String descripcion = partes[2];
		String tipoTarea = partes[3];
		String fechaEstimadaFin = partes[4];
		int tiempoEstimado = Integer.parseInt(partes[5]);
		int indexPadre = Integer.parseInt(partes[6]);
		ArrayList<Participante> responsables = new ArrayList<Participante>();
		
		for (int i=7; i<partes.length; i++)
		{
			String[] datosParticipante = partes[i].split(";");
			Participante participante = new Participante(datosParticipante[0], datosParticipante[1]);
			responsables.add(participante);
		}
		
		Tarea tarea = new Tarea(nombreTarea, descripcion, tipoTarea, fechaEstimadaFin,
								tiempoEstimado, indexPadre, responsables);
	
		return tarea;
	}
	
	
	private void cargarUnaActividad(String[] partes, Tarea tarea)
	{
		try
		{
			String tipoActividad = partes[1];
			String titulo = partes[2];
			String descripcion = partes[3];
			String fecha = partes[4];
			String horaInicio = partes[5];
			String horaFin = partes[6];
			String[] datosAutor = partes[7].split(";");
			Participante autor = new Participante(datosAutor[0], datosAutor[1]);
			String tituloProyecto = partes[8];
			boolean cierraTarea = Boolean.parseBoolean(partes[9]);
			Proyecto elProyecto = catalogoProyectos.get(tituloProyecto); //El proyecto tiene que existir
			
			Actividad proxy1 = new ProxyRegistro(tituloProyecto, tipoActividad, titulo, descripcion,
					 							 fecha, horaInicio, horaFin, autor, cierraTarea);
			Actividad proxy2 = new ProxyRegistro(tituloProyecto, tipoActividad, titulo, descripcion,
					 							 fecha, horaInicio, horaFin, autor, cierraTarea);
			
			elProyecto.registrarActividad(proxy1, proxy2, tarea);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
	private void registrarParticipante(String[] datosParticipante, String tituloProyecto)
	{
		String login = datosParticipante[0];
		String nombre = datosParticipante[1];
		
		if (this.usuariosProyectos.containsKey(login))
		{
			usuariosProyectos.get(login).add(tituloProyecto);
		}
		else
		{
			ArrayList<String> newList = new ArrayList<String>();
			newList.add(nombre);
			newList.add(tituloProyecto);
			usuariosProyectos.put(login, newList);
		}
	}
	
	
	private void generarAuxiliar()
	{
		try
		{
		BufferedWriter fw = new BufferedWriter(new FileWriter("data/auxiliar.txt"));
		Iterator<String> i = usuariosProyectos.keySet().iterator();
		
		while(i.hasNext())
		{
			String login = i.next();
			ArrayList<String> datos = usuariosProyectos.get(login);
			String nombre = datos.get(0);
			String linea = (login + ";" +  nombre);
			for (int j= 1; j<datos.size(); j++)
			{
				linea = linea + ";" + datos.get(j);
			}
			
			fw.write(linea);
			fw.newLine();
		}
		fw.close();
		
		
		}
		catch (IOException e)
		{
			System.err.println("ERROR: hubo un problema generando archivo auxiliar.");
		}
				
	}
	
	
	// METODOS DE PROCESAMIENTO
	public Proyecto getProyecto(String nombreProyecto)
	{	
		/*
		 * Retorna un Proyecto a partir de su nombre
		 */
		
		return catalogoProyectos.get(nombreProyecto);
	}
	
	
	public void guardarInfoProyecto(Proyecto proyecto)
	{
		/*
		 * Guarda o actualiza la informacion de un proyecto
		 */
		
		String nombreProyecto = proyecto.getNombre();
		catalogoProyectos.put(nombreProyecto, proyecto);
		guardarArchivo();
	}
	
	
	private void guardarArchivo() 
	{
		/*
		 * Guarda la informacion del catalogo al archivo
		 * 
		 * NOTA: Solo se ejecuta si el usuario elige la opcion "Cerrar Aplicacion"
		 */
		
		try
		{
		BufferedWriter fw = new BufferedWriter(new FileWriter("data/proyectos.txt"));
		Iterator<String> i = catalogoProyectos.keySet().iterator();
		
		while(i.hasNext())
		{
			// LINEA DEL PROYECTO
			String tituloProyecto = i.next();
			Proyecto elProyecto = catalogoProyectos.get(tituloProyecto);
			String lineaP = guardarUnProyecto(elProyecto);
			fw.write(lineaP);
			fw.newLine();
			
			//LINEAS DE LOS PAQUETES
			WBS wbs = elProyecto.getWBS();
			ArrayList<PaqueteDeTrabajo> listaPaquetes = wbs.getListaPaquetes();
			ArrayList<Tarea> lasTareas = new ArrayList<Tarea>();
			
			for (PaqueteDeTrabajo paquete : listaPaquetes)
			{
				ArrayList<Tarea> tareasPaquete = paquete.getTareas();
				lasTareas.addAll(tareasPaquete);
				
				if (!paquete.getIndexPadre().equals(-1))
				{
					String lineaPq = guardarUnPaquete(paquete);
					fw.write(lineaPq);
					fw.newLine();
				}
			}
			
			//LINEAS DE LAS TAREAS
			for (Tarea tarea : lasTareas)
			{
				String lineaT = guardarUnaTarea(tarea);
				fw.write(lineaT);
				fw.newLine();
				
				//LINEAS DE LAS ACTIVIDADES
				for (Actividad actividad : tarea.getActividades())
				{
					String lineaA = guardarUnaActividad(actividad, tituloProyecto);
					fw.write(lineaA);
					fw.newLine();
				}
			}
			
			
			/*HashMap<String, ArrayList<Actividad>> actividades = elProyecto.getActividades();
			Iterator<String> k = actividades.keySet().iterator();
			
			while(k.hasNext())
			{
				String tituloActividad = k.next();
				ArrayList<Actividad> homonimas = actividades.get(tituloActividad);
				
				for (int index=0; index<homonimas.size(); index++)
				{
					Actividad actividad = homonimas.get(index);
					
				}
			}*/
		}
		fw.close();
		
		}
		catch (IOException e)
		{
			System.err.println("ERROR: hubo un problema generando archivo auxiliar.");
		}
	}

	
	private String guardarUnProyecto(Proyecto elProyecto)
	{
		String tituloProyecto = elProyecto.getNombre();
		String descripcion = elProyecto.getDescripcion();
		String lineaP = "PROY," + tituloProyecto + "," + descripcion; 
		
		ArrayList<String> tiposActividades = elProyecto.getTiposActividades();
		String tiposActividadesStr = tiposActividades.get(0);
		
		for (int index=1; index<tiposActividades.size(); index++)
		{
			tiposActividadesStr += ";" + tiposActividades.get(index);
		}
		
		lineaP += "," + tiposActividadesStr;
		
		String fechaInicio = elProyecto.getFechaInicio();
		String fechaFin = elProyecto.getFechaFin();
		
		lineaP += "," + fechaInicio + "," + fechaFin;
		
		HashMap<String, Participante> participantes = elProyecto.getParticipantes();
		Iterator<String> j = participantes.keySet().iterator();
		
		while(j.hasNext())
		{
			String login = j.next();
			Participante elParticipante = participantes.get(login);
			String nombre = elParticipante.getNombre();
			lineaP += "," + login + ";" + nombre;
		}
		
		return lineaP;
	}
	
	
	private String guardarUnPaquete(PaqueteDeTrabajo paquete)
	{
		String lineaPq = "PQT,";
		lineaPq += paquete.getTitulo() + ",";
		lineaPq += paquete.getDescripcion() + ",";
		lineaPq += paquete.getIndexPadre().toString();
		return lineaPq;
	}
	
	
	private String guardarUnaTarea(Tarea tarea)
	{
		String lineaT = "TAR,";
		lineaT += tarea.getNombreTarea() + ",";
		lineaT += tarea.getDescripcion() + ",";
		lineaT += tarea.getTipoTarea() + ",";
		lineaT += tarea.getFechaEstimadaFin() + ",";
		lineaT += tarea.getTiempoEstimado().toString() + ",";
		lineaT += tarea.getIndexPadre().toString();
		
		for (Participante participante : tarea.getResponsables())
		{
			String login = participante.getLogin();
			String nombre = participante.getNombre();
			lineaT += "," + login + ";" + nombre;
		}
		
		return lineaT;
	}
	
	
	private String guardarUnaActividad(Actividad actividad, String tituloProyecto)
	{
		String tituloActividad = actividad.getTitulo();
		String tipoActividad = actividad.getTipoActividad();
		String descripcionActividad = actividad.getDescripcion();
		String fechaActividad = actividad.getFecha();
		String horaInicio = actividad.getHoraInicio();
		String horaFin = actividad.getHoraFin();
		Participante autor = actividad.getAutor();
		String loginAutor = autor.getLogin();
		String nombreAutor = autor.getNombre();
		Boolean cierraTarea = actividad.cierraTarea();
		String cierra = cierraTarea.toString();
		
		String lineaA = "ACT" + "," + tipoActividad + "," + tituloActividad;
		lineaA += "," + descripcionActividad + "," + fechaActividad;
		lineaA += "," + horaInicio + "," + horaFin + ",";
		lineaA += loginAutor + ";" + nombreAutor + "," + tituloProyecto + "," + cierra;
		
		return lineaA;
	}

}
