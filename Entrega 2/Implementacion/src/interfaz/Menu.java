package interfaz;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel
{
	private int menuID;
	protected String header;
	
	public Menu(int id, String header)
	{
		this.menuID = id;
		this.header = header;
	}
	
	public int getID()
	{
		return menuID;
	}
	
	public String getHeader()
	{
		return header;
	}
}
