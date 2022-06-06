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
	
	
	public DialogReporteCalidadPlaneacion()
	{
		//this.report = reporte;
		
		setLayout(new FlowLayout());
		
		//this.add(tiempoChart());
		//this.add(tareasChart());

		int[] x = new int[] {0, 1, 2, 3, 4};
		int[] a = new int[] {1, 3, 1, 2, 1};
		int[] b = new int[] {2, 1, 1, 2, 2};
		int[] c = new int[] {1, 1, 2, 3, 3};
		
		CategoryChart chart = new CategoryChartBuilder().width(640).height(480).build();
		chart.getStyler().setLegendVisible(false);
		chart.getStyler().setAvailableSpaceFill(1);
		
		AxesChartSeriesCategory axesChartSeries = getSeriesMap().values().iterator().next();
		
		chart.addSeries("A", x, a);
		
		
		
		new SwingWrapper(chart).displayChart();
		
		//JPanel Chart = new XChartPanel(chart);
		
		//this.add(Chart);
		this.setSize(1250,550);
		this.setTitle("Reporte Avance del Proyecto");
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private JPanel tiempoChart()
	{
		return new JPanel();
	}
	
	
	public static void main(String[] args)
	{
		DialogReporteCalidadPlaneacion cosa = new DialogReporteCalidadPlaneacion();
	}
	
}
