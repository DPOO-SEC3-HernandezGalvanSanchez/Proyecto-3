package interfaz;

import javax.swing.JDialog;
import java.util.HashMap;

import modelo.ReporteDesempenoEquipo;

@SuppressWarnings("serial")
public class DialogReporteDesempeno extends JDialog {
	
	//atributos
	
	private ReporteDesempenoEquipo report;
	
	
	
	public DialogReporteDesempeno(ReporteDesempenoEquipo reporte)
	{
		this.report = reporte;
		
	}

}
