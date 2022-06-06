package interfaz;

import java.awt.FlowLayout;


import javax.swing.JDialog;
import javax.swing.JPanel;

import modelo.ReporteAvance;

import org.knowm.xchart.*;

@SuppressWarnings("serial")
public class DialogReporteAvance extends JDialog{
	
	// Aqui irian atributos a utilizar
	
	private ReporteAvance report;
	
	
	
	
	
	public DialogReporteAvance(ReporteAvance reporte)
	{
		this.report = reporte;
		
		setLayout(new FlowLayout());
		
		
		//PieChart chart = new PieChartBuilder().width(600).height(500).title("Tareas").build();
		//chart.addSeries("Pussy", 5);
		
		//JPanel Chart = new XChartPanel(chart);
		
		this.add(TareasChart());
		this.add(TiempoChart());
		
		this.setSize(1250,550);
		this.setTitle("Reporte Avance del Proyecto");
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
		
	}
	
	
	private JPanel TareasChart()
	{
		PieChart chart = new PieChartBuilder().width(600).height(500).title("Tareas").build();
		chart.addSeries("Tareas terminadas", this.report.tareasTerminadas);
		chart.addSeries("Tareas sin Terminar",this.report.totalTareas-this.report.tareasTerminadas);
		JPanel Ret = new XChartPanel(chart);	
		return Ret;
	}
	
	
	private JPanel TiempoChart()
	{
		PieChart chart = new PieChartBuilder().width(600).height(500).title("Tiempo").build();
		chart.addSeries("Tiempo planeado terminado", this.report.tiempoPlaneadoTerminadas);
		chart.addSeries("Tareas sin Terminar",this.report.tiempoPlaneadoTotal-this.report.tiempoPlaneadoTerminadas);
		JPanel Ret = new XChartPanel(chart);
		return Ret;
	}
}
