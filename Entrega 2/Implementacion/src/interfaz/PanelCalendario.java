package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelCalendario extends JPanel {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private HashMap<String, Integer> Fechas;
	
	private int x_origin;
	private int y_origin;
	private int boxSize = 12;
	private int width;
	private int height;

	
	public PanelCalendario(HashMap<String, Integer> fechas, String fechaInicio) throws ParseException
	{
		this.Fechas = fechas;
	}
	
	
	public void paint(Graphics g)
	{
		width = this.getWidth();
		height = this.getHeight();
		
		this.y_origin = (int) Math.round(0.1*height);
		this.x_origin = (int) Math.round(width/2 - (boxSize*26));
		g.clearRect(0, 0, getWidth(), getHeight());
		Graphics2D g2d = (Graphics2D) g;
		
		
		pintarDias(g2d);
		pintarLineas(g2d);
		//pintarDetalles(g2d);
	}
	
	
	
	
	private void pintarLineas(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
		for (int i =0 ; i<8; i++ ) g2d.drawLine(x_origin,y_origin+(i*boxSize),x_origin+(52*boxSize),y_origin+(i*boxSize));
		for (int j = 0; j<53;j++) g2d.drawLine(x_origin+(j*boxSize),y_origin,x_origin+(j*boxSize),y_origin+(7*boxSize));
	}
	
	
	private void pintarDias(Graphics2D g2d)
	{	
		Set<String> dias = Fechas.keySet();
		for(String s: dias)
		{
			int reg = Fechas.get(s);
			LocalDate dia_reg = LocalDate.parse(s, formatter);
			if (reg>=1 && reg<3) {
				g2d.setColor(Color.YELLOW);
			}
			else if (reg>=3 && reg<5) {
				g2d.setColor(Color.ORANGE);
			}
			else if (reg>=5) {
				g2d.setColor(Color.RED);
			}
			int index = dia_reg.getDayOfYear();
			int x = x_origin + (index/7)*boxSize;
			int y = y_origin + (index%7)*boxSize;
			Rectangle2D.Double rectangle = new Rectangle2D.Double(x,y,boxSize,boxSize);
			g2d.fill(rectangle);
			
			
		}
	}
}
