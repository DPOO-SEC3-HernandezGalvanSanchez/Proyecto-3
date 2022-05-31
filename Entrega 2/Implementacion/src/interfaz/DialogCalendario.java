package interfaz;

import java.util.HashMap;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.text.ParseException;

@SuppressWarnings("serial")
public class DialogCalendario extends JDialog {
	
	public DialogCalendario(HashMap<String, Integer> fechas, String fechaInicio) throws ParseException
	{
		setLayout(new BorderLayout());
		PanelCalendario Calendario = new PanelCalendario(fechas,fechaInicio);
		this.add(Calendario);
		this.setSize(700,200);
		this.setTitle("Calendario de proyecto");
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
		
		
		
		
		
	}

}
