package interfaz;

import java.awt.FlowLayout;


import javax.swing.JDialog;
import javax.swing.JPanel;
import java.util.HashMap;

import modelo.ReporteResumenProyecto;

import org.knowm.xchart.*;




public class DialogReporteResumen extends JDialog {
	
	
	ReporteResumenProyecto report;
	
	
	public DialogReporteResumen(ReporteResumenProyecto reporte)
	{
		this.report = reporte;
		
		setLayout(new FlowLayout());
		
		this.add(TiempoChart());
		this.add(PendientesChart());
		this.setSize(1250,550);
		this.setTitle("Reporte Avance del Proyecto");
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
		
	}

	private JPanel TiempoChart()
	{
		PieChart chart = new PieChartBuilder().width(600).height(500).title("Tiempo por Tipo").build();
		
		this.report.tiempoPorTipo.forEach((k,v) -> 
		{
			chart.addSeries(k, v);
		});
		
		JPanel Ret = new XChartPanel(chart);
		return Ret;
	}
	
	
	private JPanel PendientesChart()
	{
		PieChart chart = new PieChartBuilder().width(600).height(500).title("Pendientes por Tipo").build();
		
		this.report.pendientesPorTipo.forEach((k,v) -> 
		{
			chart.addSeries(k, v);
		});
		
		JPanel Ret = new XChartPanel(chart);
		return Ret;
	}

}
