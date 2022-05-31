package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class PanelEleccion2 extends JPanel implements ActionListener
{
	private MenuEleccionProyecto padre;
	
	private JTextField cuadroNombre;
	private JTextField cuadroDescripcion;
	private JComboBox<Integer> cuadroNumTipos;
	private JButton botonCrear;
	private JLabel textLabel;
	
	
	public PanelEleccion2(MenuEleccionProyecto padre)
	{
		this.padre = padre;
		setLayout(null);
		setBorder(new EtchedBorder());
		
		JLabel titulo = new JLabel("Si desea crear un nuevo proyecto:");
		titulo.setBounds(40, 10, 600, 30);
		titulo.setFont(new Font("Bold", Font.BOLD, 13));
		add(titulo);
		
		JLabel mensajeNombre = new JLabel("Ingrese el nombre:");
		mensajeNombre.setBounds(80, 55, 150, 30);
		mensajeNombre.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeNombre);
		
		cuadroNombre = new JTextField();
		cuadroNombre.setBounds(300, 59, 150, 23);
		add(cuadroNombre);
		
		JLabel mensajeDescripcion = new JLabel("Ingrese una corta descripcion:");
		mensajeDescripcion.setBounds(80, 90, 200, 30);
		mensajeDescripcion.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeDescripcion);
		
		cuadroDescripcion = new JTextField();
		cuadroDescripcion.setBounds(300, 94, 150, 23);
		add(cuadroDescripcion);
		
		JLabel mensajeNumTipos = new JLabel("Ingrese el # de tipos de actividad:");
		mensajeNumTipos.setBounds(80, 125, 250, 30);
		mensajeNumTipos.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeNumTipos);
		
		Integer[] opcionesTipos = {1,2,3,4,5};
		cuadroNumTipos = new JComboBox<Integer>(opcionesTipos);
		cuadroNumTipos.setBounds(300, 129, 150, 23);
		add(cuadroNumTipos);
		
		botonCrear = new JButton("Crear");
		botonCrear.setBounds(220, 170, 90, 25);
		botonCrear.addActionListener(this);
		add(botonCrear);
		
		textLabel = new JLabel("");
		textLabel.setBounds(40, 205, 600, 20);
		this.add(textLabel);

	}
	
	
	public void disableFields()
	{
		cuadroNombre.setEditable(false);
		cuadroDescripcion.setEditable(false);
		cuadroNumTipos.setEnabled(false);
		botonCrear.setEnabled(false);
	}
	
	
	public void successfulSave()
	{
		String texto = "Por favor, haga click en el boton 'Continuar'";
		textLabel.setForeground(new Color(105, 105, 105));
		textLabel.setText(texto);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonCrear)
        {
			String nombre = cuadroNombre.getText();
			String descripcion = cuadroDescripcion.getText();
            int numTipos = (int) cuadroNumTipos.getSelectedItem();
            
			if (descripcion.equals("") || nombre.equals(""))
			{
				String texto = "Por favor complete todos los campos";
				textLabel.setForeground(Color.RED);
				textLabel.setText(texto);
			}
			else
			{
				padre.newProjectSettings(nombre, descripcion, numTipos);
			}
        }
    }
}
