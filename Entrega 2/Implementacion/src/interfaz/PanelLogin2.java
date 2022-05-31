package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class PanelLogin2 extends JPanel implements ActionListener
{
	private MenuLogin padre;
	
	private JTextField cuadroLogin;
	private JTextField cuadroNombre;
	private JButton botonEntrar;
	private JLabel textLabel;
	
	
	public PanelLogin2(MenuLogin padre)
	{
		this.padre = padre;
		setLayout(null);
		setBorder(new EtchedBorder());
		
		JLabel titulo = new JLabel("Si desea registrarse como un usuario nuevo:");
		titulo.setBounds(40, 10, 600, 30);
		titulo.setFont(new Font("Bold", Font.BOLD, 13));
		add(titulo);
		
		JLabel mensajeLogin = new JLabel("Ingrese su login:");
		mensajeLogin.setBounds(140, 65, 150, 30);
		mensajeLogin.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeLogin);
		
		cuadroLogin = new JTextField();
		cuadroLogin.setBounds(280, 69, 150, 23);
		add(cuadroLogin);
		
		JLabel mensajeNombre = new JLabel("Ingrese su nombre:");
		mensajeNombre.setBounds(140, 95, 150, 30);
		mensajeNombre.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensajeNombre);
		
		cuadroNombre = new JTextField();
		cuadroNombre.setBounds(280, 99, 150, 23);
		add(cuadroNombre);
		
		botonEntrar = new JButton("Registrar");
		botonEntrar.setBounds(220, 137, 100, 25);
		botonEntrar.addActionListener(this);
		add(botonEntrar);
		
		textLabel = new JLabel("");
		textLabel.setBounds(40, 180, 600, 23);
		this.add(textLabel);

	}
	
	
	public void userNonExistent()
	{
		String texto = "Por favor, haga click en el boton 'Continuar'";
		textLabel.setForeground(new Color(105, 105, 105));
		textLabel.setText(texto);
	}
	
	
	public void userExistent()
	{
		String texto = "El usuario ya se encuentra registrado"
			    + " en el sistema. Por favor intente con otro";
		textLabel.setForeground(Color.RED);
		textLabel.setText(texto);
	}
	
	
	public void disableFields()
	{
		cuadroLogin.setEditable(false);
		cuadroNombre.setEditable(false);
		botonEntrar.setEnabled(false);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonEntrar)
        {
			String login = cuadroLogin.getText();
            String nombre = cuadroNombre.getText();
            
			if (login.equals("") || nombre.equals(""))
			{
				String texto = "Por favor complete todos los campos";
				textLabel.setForeground(Color.RED);
				textLabel.setText(texto);
			}
			else
			{
				padre.ingresarLogin(login, nombre);
			}
        }
    }

	
}
