package modelo;


public class Registro implements Actividad
{
	private String tipoActividad;
	private String titulo;
	private String descripcion;
	private String fecha;
	private String horaInicio;
	private String horaFin;
	private Participante autor;
	private boolean cierraTarea;
	private int tiempo;
	
	
	//CONSTRUCTOR
	public Registro(String tipoActividad, String titulo, String descripcion,
					String fecha, String horaInicio, String horaFin,
					Participante autor, boolean cierraTarea)
	{
		this.tipoActividad = tipoActividad;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.autor = autor;
		this.cierraTarea = cierraTarea;
		this.tiempo = calcularTiempo(horaInicio,horaFin);
	}
	
	
	//GETTERS
	public String getTipoActividad()
	{
		return tipoActividad;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public String getFecha()
	{
		return fecha;
	}
	
	public String getHoraInicio()
	{
		return horaInicio;
	}

	public String getHoraFin()
	{
		return horaFin;
	}

	public Participante getAutor()
	{
		return autor;
	}
	
	public int getTiempo()
	{
		return tiempo;
	}
	
	public boolean cierraTarea()
	{
		return cierraTarea;
	}	
	
	
	//SETTERS
	public void setFecha(String nuevaFecha)
	{
		this.fecha = nuevaFecha;
	}
	
	
	public void setHoraInicio(String nuevaHoraInicio)
	{
		this.horaInicio = nuevaHoraInicio;
		actualizarTiempo();
	}
	
	
	public void setHoraFin(String nuevaHoraFin)
	{
		this.horaFin = nuevaHoraFin;
		actualizarTiempo();
	}
	
	
	// METODOS
	private int calcularTiempo(String horaInicio, String horaFin)
	{
	String[] t1 = horaInicio.split(":");
	String[] t2 = horaFin.split(":");
	
	int horas = Integer.parseInt(t2[0])-Integer.parseInt(t1[0]);
	int minutos = Integer.parseInt(t2[1]) - Integer.parseInt(t1[1]);
	int total = (horas*60)+minutos;
	return total;
	}
	
	
	private void actualizarTiempo()
	{
		this.tiempo = calcularTiempo(this.horaInicio, this.horaFin);
	}
	
	
}
