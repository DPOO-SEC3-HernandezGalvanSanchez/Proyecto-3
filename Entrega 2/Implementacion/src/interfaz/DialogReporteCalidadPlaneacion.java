package interfaz;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import org.knowm.xchart.*;

import modelo.ReporteCalidadPlaneacion;

@SuppressWarnings("serial")
public class DialogReporteCalidadPlaneacion extends JDialog {
	
	// Atributos
	
	private ReporteCalidadPlaneacion report;
	
	
	public DialogReporteCalidadPlaneacion(ReporteCalidadPlaneacion reporte)
	{
		this.report = reporte;
		
		setLayout(new FlowLayout());
		
		this.add(tiempoChart());
		this.add(tareasChart());


		this.setSize(1250,550);
		this.setTitle("Reporte Avance del Proyecto");
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private JPanel tiempoChart()
	{
		CategoryChart chart = new CategoryChartBuilder().width(600).height(500).title("Tareas que cumplen Tiempo").build();
		chart.getStyler().setLegendVisible(false);
		chart.getStyler().setAvailableSpaceFill(1);
		int[] x = new int[] {1,2};
		int[] a = new int[] {this.report.tareasCumplenTiempo, this.report.totalTareas-this.report.tareasCumplenTiempo};
		chart.addSeries("A", x, a);
		JPanel ret = new XChartPanel(chart);
		
		return ret;
	}
	
	private JPanel tareasChart()
	{
		PieChart chart = new PieChartBuilder().width(600).height(500).title("Tareas").build();
		chart.addSeries("Tareas terminadas", this.report.tareasCumplenTiempo);
		chart.addSeries("Tareas sin Terminar",this.report.totalTareas-this.report.tareasCumplenTiempo);
		JPanel Ret = new XChartPanel(chart);	
		return Ret;
	}
	
}
