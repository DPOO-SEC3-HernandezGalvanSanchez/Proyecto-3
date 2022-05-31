package procesamiento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.Participante;


public class ArchivoUsuarios
{
	// ATRIBUTOS
	private HashMap<String, Participante> infoUsuarios = new HashMap<String, Participante>();
	private HashMap<String, ArrayList<String>> proyectosUsuarios = new HashMap<String, ArrayList<String>>();
	
	
	// CONSTRUCTOR
	public ArchivoUsuarios() 
	{
		try {
			cargarUsuarios();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// METODOS
	public Participante newParticipante(String login, String nombre)
	{
		Participante pn = new Participante(login, nombre);
		infoUsuarios.put(login, pn);
		
		return pn;
	}
	
	public Participante getParticipante(String loginUsuario)
	{				
		return infoUsuarios.get(loginUsuario);
	}
	
	
	public ArrayList<String> getProyectosUsuario(String loginUsuario)
	{	
		/*
		 * Extraer los proyectos de determinado usuario
		 * 
		 * Nota: no se retorna directamente 'proyectos' para mantener el encapsulamiento
		 */
		
		ArrayList<String> proyectosDelUsuario = null;
		ArrayList<String> proyectos = proyectosUsuarios.get(loginUsuario);
		
		if (proyectos != null)
			proyectosDelUsuario = new ArrayList<String>(proyectos);
		
		return proyectosDelUsuario;
	}
	
	
	public void guardarProyecto(String loginUsuario, String nombreProyecto)
	{
		/*
		 * Guardar un nuevo proyecto llamado 'nombreProyecto' para el
		 * usuario 'loginUsuario'
		 */
		
		ArrayList<String> proyectosDelUsuario = getProyectosUsuario(loginUsuario);
		
		if (proyectosDelUsuario != null)
		{
			proyectosDelUsuario.add(nombreProyecto);
			proyectosUsuarios.put(loginUsuario, proyectosDelUsuario);
		}
	
		else
		{
			ArrayList<String> proyectosDelNuevoUsuario = new ArrayList<String>();
			proyectosDelNuevoUsuario.add(nombreProyecto);
			proyectosUsuarios.put(loginUsuario, proyectosDelNuevoUsuario);
		}	
	}
	
	
	private void cargarUsuarios() throws FileNotFoundException, IOException
	{
		/*
		 * Cargar los usuarios del sistema y sus proyectos
		 */
		
		BufferedReader br = new BufferedReader(new FileReader("./data/auxiliar.txt", StandardCharsets.UTF_8));
		String linea = br.readLine();
		while (linea != null)
		{
			String[] datos = linea.split(";");
			String login = datos[0];
			String nombre = datos[1];
			
			Participante pn = new Participante(login,nombre);
			ArrayList<String> proyectos = new ArrayList<String>();
			int tamano = datos.length;
			
			infoUsuarios.put(login, pn);
			
			for (int i = 2; i<tamano;i++)
			{
				proyectos.add(datos[i]);
			}
			proyectosUsuarios.put(login, proyectos);
			linea = br.readLine();
		}
		br.close();
	}
	
}
