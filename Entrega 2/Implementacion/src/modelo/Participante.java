package modelo;

public class Participante
{

	private String loginCorreo;
	private String nombre;
	private String correo;
	
	public Participante(String loginCorreo, String nombre)
	{
		this.loginCorreo = loginCorreo;
		this.nombre = nombre;
		this.correo = loginCorreo + "@uniandes.edu.co";
	}

	
	public String getNombre()
	{
		return nombre;
	}

	
	public String getLogin()
	{
		return loginCorreo;
	}
	
	
	public String getCorreo()
	{
		return correo;
	}
	
	
}
