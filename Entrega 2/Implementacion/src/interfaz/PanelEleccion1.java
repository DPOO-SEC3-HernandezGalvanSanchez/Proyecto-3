package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class PanelEleccion1 extends JPanel implements ActionListener
{
		private MenuEleccionProyecto padre;
		private JComboBox<String> desplegable;
		private JButton botonSeleccionar;
		private JLabel textLabel;
	
		
		public PanelEleccion1(MenuEleccionProyecto padre)
		{
			this.padre = padre;
			setLayout(null);
			setBorder(new EtchedBorder());
			
			JLabel titulo = new JLabel("Si desea trabajar sobre un proyecto existente:");
			titulo.setBounds(40, 10, 600, 30);
			titulo.setFont(new Font("Bold", Font.BOLD, 13));
			add(titulo);			
			
			JLabel mensaje = new JLabel("Seleccione el proyecto:");
			mensaje.setFont(new Font("Bold", Font.PLAIN, 13));
			mensaje.setBounds(100, 75, 200, 30);
			add(mensaje);
			
			desplegable = new JComboBox<String>();
			desplegable.setBounds(250, 78, 200, 25);
			add(desplegable);
			
			botonSeleccionar = new JButton("Seleccionar");
			botonSeleccionar.setBounds(192, 125, 150, 25);
			botonSeleccionar.addActionListener(this);
			add(botonSeleccionar);
			
			textLabel = new JLabel("");
			textLabel.setBounds(40, 180, 600, 20);
			textLabel.setForeground(new Color(105, 105, 105));
			this.add(textLabel);
		}
		
		
		public void addProyectoDesplegable(String nombre)
		{
			desplegable.addItem(nombre);
		}
		
		
		public void disableFields()
		{
			desplegable.setEnabled(false);
			botonSeleccionar.setEnabled(false);
		}
		
		
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource()==botonSeleccionar)
	        {
	            String nombreProyecto = desplegable.getSelectedItem().toString();
	            padre.setProyectoEnUso(nombreProyecto);    
	            String texto = "Por favor, haga click en el boton 'Continuar'";
	    		textLabel.setText(texto);
	        }
	    }
		
	
}
