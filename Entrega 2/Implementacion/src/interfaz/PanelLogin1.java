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
public class PanelLogin1 extends JPanel implements ActionListener
{
	private MenuLogin padre;
	
	private JTextField cuadroTexto;
	private JButton botonEntrar;
	private JLabel textLabel;
	
	
	public PanelLogin1(MenuLogin padre)
	{
		this.padre = padre;
		setLayout(null);
		setBorder(new EtchedBorder());
		
		JLabel titulo = new JLabel("Si ya se encuentra registrado en la aplicacion:");
		titulo.setBounds(40, 10, 600, 30);
		titulo.setFont(new Font("Bold", Font.BOLD, 13));
		add(titulo);
		
		JLabel mensaje = new JLabel("Ingrese su login:");
		mensaje.setBounds(170, 65, 150, 30);
		mensaje.setFont(new Font("Bold", Font.PLAIN, 13));
		add(mensaje);
		
		cuadroTexto = new JTextField();
		cuadroTexto.setBounds(280, 69, 100, 23);
		add(cuadroTexto);
		
		botonEntrar = new JButton("Entrar");
		botonEntrar.setBounds(220, 107, 100, 25);
		botonEntrar.addActionListener(this);
		add(botonEntrar);
		
		textLabel = new JLabel("");
		textLabel.setBounds(40, 180, 600, 20);
		this.add(textLabel);

	}
	
	
	public void userFound()
	{
		String texto = "Por favor, haga click en el boton 'Continuar'";
		textLabel.setForeground(new Color(105, 105, 105));
		textLabel.setText(texto);
	}
	
	
	public void userNotFound()
	{
		String texto = "Su usuario no se encuentra registrado"
			     + " en el sistema. Por favor intente de nuevo";
		textLabel.setForeground(Color.RED);
		textLabel.setText(texto);
	}
	
	
	public void disableFields()
	{
		cuadroTexto.setEditable(false);
		botonEntrar.setEnabled(false);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
        if (e.getSource()==botonEntrar)
        {
            String login = cuadroTexto.getText();
            
            if (login.equals(""))
			{
				String texto = "Por favor complete el campo";
				textLabel.setForeground(Color.RED);
				textLabel.setText(texto);
			}         
            else
            {
            	padre.ingresarLogin(login);
            }            
        }
    }

}
