package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import modelo.Participante;


@SuppressWarnings("serial")
public class DialogNuevaTarea extends JDialog implements ActionListener, KeyListener
{
	int y = 25;
	final int x = 220;
	final int spacing = 40;
	
	private PanelWBS1 padre;
	private int index;
	
	private JPanel settingsTask;
	private JComboBox<String> desplegableT;
	private JTextField cuadroTitulo;
	private JTextField cuadroDescripcion;
	private JTextField cuadroFechaFin;
	private JTextField cuadroTiempo;
	private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>(); 
	private JButton botonAceptar;
	private JLabel textLabel;
	
	
	public DialogNuevaTarea(PanelWBS1 padre, int index)
	{	
		this.padre = padre;
		this.index = index;
		
		settingsTask = new JPanel();
		settingsTask.setLayout(null);
		
		//Tipo
		JLabel mensajeTipo = new JLabel("Seleccione el tipo:");
		mensajeTipo.setBounds(20, y, 150, 30);
		mensajeTipo.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsTask.add(mensajeTipo);
		
		desplegableT = new JComboBox<String>();
		desplegableT.addKeyListener(this);
		desplegableT.setBounds(x, y+4, 170, 23);
		settingsTask.add(desplegableT);
		y += spacing;
		
		//Titulo
		JLabel mensajeTitulo = new JLabel("Ingrese el titulo:");
		mensajeTitulo.setBounds(20, y, 200, 30);
		mensajeTitulo.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsTask.add(mensajeTitulo);
		
		cuadroTitulo = new JTextField();
		cuadroTitulo.addKeyListener(this);
		cuadroTitulo.setBounds(x, y+4, 170, 23);
		settingsTask.add(cuadroTitulo);
		y += spacing;
		
		//Descripcion
		JLabel mensajeDescripcion = new JLabel("Ingrese una corta descripcion:");
		mensajeDescripcion.setBounds(20, y, 200, 30);
		mensajeDescripcion.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsTask.add(mensajeDescripcion);
		
		cuadroDescripcion = new JTextField();
		cuadroDescripcion.addKeyListener(this);
		cuadroDescripcion.setBounds(x, y+4, 170, 23);
		settingsTask.add(cuadroDescripcion);
		y += spacing;
		
		//Fecha Fin
		JLabel mensajeFechaFin = new JLabel("Fecha estimada de finalizacion:");
		mensajeFechaFin.setBounds(20, y, 200, 30);
		mensajeFechaFin.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsTask.add(mensajeFechaFin);
		
		cuadroFechaFin = new HintTextField("Ej: 05/06/2022");
		cuadroFechaFin.addKeyListener(this);
		cuadroFechaFin.setBounds(x, y+4, 170, 23);
		settingsTask.add(cuadroFechaFin);
		y += spacing;
		
		//Tiempo Estimado
		JLabel mensajeTiempo = new JLabel("Duracion estimada (en minutos):");
		mensajeTiempo.setBounds(20, y, 200, 30);
		mensajeTiempo.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsTask.add(mensajeTiempo);
		
		cuadroTiempo = new JTextField();
		cuadroTiempo.addKeyListener(this);
		cuadroTiempo.setBounds(x, y+4, 170, 23);
		settingsTask.add(cuadroTiempo);
		y += spacing;
		
		//Tiempo Estimado
		JLabel mensajeResp = new JLabel("Seleccione los responsables:");
		mensajeResp.setBounds(20, y, 200, 30);
		mensajeResp.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsTask.add(mensajeResp);
		
		//Boton de aceptar
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(167, y+80, 100, 25);
		botonAceptar.addActionListener(this);
		add(botonAceptar);
		
		//Mensaje de advertencia
		textLabel = new JLabel("");
		textLabel.setBounds(20, y+120, 600, 23);
		textLabel.setForeground(Color.RED);
		this.add(textLabel);
		
		//Settings del JDialog
		add(settingsTask);
		setTitle("Datos de la tarea");
		setModal(true);
		setSize(440, y+190);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	
	public void addTipoDesplegable(String tipo)
	{
		desplegableT.addItem(tipo);
	}
	
	public void addParticipanteCB(String login)
	{
		checkBoxes.add(new JCheckBox(login));
	}
	
	public void addParticipantesScroll()
	{
		Box box = Box.createVerticalBox();
		
		for (JCheckBox cb : checkBoxes)
		{
			box.add(cb);
		}
		
		JScrollPane scroll = new JScrollPane(box);
		scroll.setBounds(x, y+4, 170, 50);
		settingsTask.add(scroll);
	}
	
	
	//METODOS DE LISTENER
	private void continuar()
	{
		String tipo = desplegableT.getSelectedItem().toString();
		String titulo = cuadroTitulo.getText();
		String descripcion = cuadroDescripcion.getText();
		String fechaFin = cuadroFechaFin.getText();
		String tiempoEstimado = cuadroTiempo.getText();
		
		if (titulo.equals("") || descripcion.equals("")
				|| fechaFin.equals("") || tiempoEstimado.equals(""))
		{
			String texto = "Por favor complete todos los campos";
			textLabel.setText(texto);
		}
		
		else
		{
			try
			{
				Integer tiempo = Integer.parseInt(tiempoEstimado);	
				ArrayList<Participante> responsables = new ArrayList<Participante>();
				
				for (JCheckBox cb : checkBoxes)
				{
					if (cb.isSelected())
					{
						String login = cb.getText();
						Participante p = padre.getParticipante(login);
						responsables.add(p);
					}
				}
				
				if (responsables.size()==0)
				{
					String texto = "Debe seleccionar al menos un responsable";
					textLabel.setText(texto);
				}
				else
				{
					padre.agregarTarea(index, tipo, titulo, descripcion,
							fechaFin, tiempo, responsables);
					this.dispose();
				}				
			}
			catch (Exception e)
			{
				this.dispose();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error al agregar actividad",
						  JOptionPane.ERROR_MESSAGE);
			}								
		}
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonAceptar)
		{
			continuar();
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e)
	{	
		if (e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			continuar();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{	
	}

	@Override
	public void keyReleased(KeyEvent e)
	{	
	}
	
	
}
