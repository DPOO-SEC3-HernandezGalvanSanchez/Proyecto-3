package modelo;

public interface Actividad
{
	public String getTipoActividad();
	public String getTitulo();
	public String getDescripcion();
	public String getFecha();
	public String getHoraInicio();
	public String getHoraFin();
	public Participante getAutor();
	public int getTiempo();
	public boolean getCierraTarea();
	public void setFecha(String nuevaFecha);
	public void setHoraInicio(String nuevaHoraInicio);
	public void setHoraFin(String nuevaHoraFin);
}
