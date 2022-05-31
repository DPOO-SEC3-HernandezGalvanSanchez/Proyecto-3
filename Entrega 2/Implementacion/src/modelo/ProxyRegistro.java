package modelo;

public class ProxyRegistro implements Actividad
{
	private Registro elRegistro;
	
	public ProxyRegistro(String tipoActividad, String titulo, String descripcion,
						 String fecha, String horaInicio, String horaFin,
						 Participante autor, String nombreTarea, boolean cierraTarea)
	{
		String idActividad = generarID(titulo, fecha, horaInicio, horaFin, autor);
		AlmacenActividades almacen = AlmacenActividades.getInstance();
		Registro elRegistro = almacen.getRegistro(idActividad);
		
		if (elRegistro == null)
		{
			elRegistro = new Registro(tipoActividad, titulo, descripcion, fecha,
									  horaInicio, horaFin, autor, nombreTarea,
									  cierraTarea);
			
			almacen.guardarRegistro(idActividad, elRegistro);
		}
		
		this.elRegistro = elRegistro;
	}
	
	public String getTipoActividad()
	{
		return elRegistro.getTipoActividad();
	}
	
	public String getTitulo()
	{
		return elRegistro.getTitulo();
	}
	
	public String getDescripcion()
	{
		return elRegistro.getDescripcion();
	}
	
	public String getFecha()
	{
		return elRegistro.getFecha();
	}
	
	public String getHoraInicio()
	{
		return elRegistro.getHoraInicio();
	}
	
	public String getHoraFin()
	{
		return elRegistro.getHoraFin();
	}
	
	public Participante getAutor()
	{
		return elRegistro.getAutor();
	}
	
	public int getTiempo()
	{
		return elRegistro.getTiempo();
	}
	
	public void setFecha(String nuevaFecha)
	{
		elRegistro.setFecha(nuevaFecha);
	}
	
	public void setHoraInicio(String nuevaHoraInicio)
	{
		elRegistro.setHoraInicio(nuevaHoraInicio);
	}
	
	public void setHoraFin(String nuevaHoraFin)
	{
		elRegistro.setHoraFin(nuevaHoraFin);
	}
	
	private String generarID(String titulo, String fecha, String horaInicio,
							 String horaFin, Participante autor)
	{
		String idActividad = titulo + ";" + fecha + ";" + horaInicio + ";" + horaFin;
		idActividad += ";" + autor.getLogin();
		
		return idActividad;
	}
}
