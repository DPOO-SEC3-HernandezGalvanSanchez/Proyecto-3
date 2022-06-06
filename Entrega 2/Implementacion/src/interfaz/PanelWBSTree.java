package interfaz;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import modelo.Actividad;
import modelo.PaqueteDeTrabajo;
import modelo.Participante;
import modelo.Tarea;
import modelo.WBS;


@SuppressWarnings("serial")
public class PanelWBSTree extends JPanel implements ActionListener, TreeSelectionListener
{
	private MenuWBS padre;
	private WBS wbs;
	
	private DefaultTreeModel treeModel;
	private JTree tree;
	private JPanel panelDerecho = new JPanel();
	
	private DefaultMutableTreeNode selectedNode;
	private PaqueteDeTrabajo paqueteSeleccionado;
	private Tarea tareaSeleccionada;
	
	
	public PanelWBSTree(MenuWBS padre, WBS wbs)
	{  
		super(new GridLayout(1,2));
		setBorder(new EtchedBorder());
		
		this.padre = padre;
		this.wbs = wbs;
		
		DefaultMutableTreeNode wbsTree = new DefaultMutableTreeNode("WBS");
		crearNodos(wbsTree, wbs.getPaquete(0));
		
		tree = new JTree(wbsTree);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		treeModel = (DefaultTreeModel) tree.getModel();
		
		JScrollPane treeView = new JScrollPane(tree); 
		
		JLabel mensaje = new JLabel("Por favor, seleccione un elemento");
		mensaje.setFont(new Font("Bold", Font.BOLD, 13));
		panelDerecho.setLayout(new GridBagLayout());
		panelDerecho.add(mensaje);
		
		add(treeView);
		add(panelDerecho);
	}
	
	
	public Participante getParticipante(String login)
	{
		return padre.getParticipante(login);
	}
	
	
	public void agregarTarea(int index, String tipo, String titulo, String descripcion,
			String fechaFin, int tiempoEstimado, ArrayList<Participante> responsables)
	{
		Tarea nuevaTarea = new Tarea(titulo, descripcion, tipo, fechaFin,
									 tiempoEstimado, index, responsables);
		wbs.agregarTarea(nuevaTarea);
		
		DefaultMutableTreeNode nodoTarea = new DefaultMutableTreeNode(nuevaTarea);
		treeModel.insertNodeInto(nodoTarea, selectedNode, selectedNode.getChildCount());
		padre.guardarArchivo();
	}
	
	
	public void agregarActividad(String tipo, String titulo, String descripcion,
			   String horaInicio, String loginAutor, boolean cierra) throws Exception
	{
		Actividad nuevaAct = padre.agregarActividad(tipo, titulo, descripcion, horaInicio, 
						       						loginAutor, tareaSeleccionada, cierra);
		
		DefaultMutableTreeNode nodoAct = new DefaultMutableTreeNode(nuevaAct);
		treeModel.insertNodeInto(nodoAct, selectedNode, selectedNode.getChildCount());
		padre.guardarArchivo();
	}
	
	
	private void crearNodos(DefaultMutableTreeNode tree, PaqueteDeTrabajo paquete)
	{
		for (int indexHijo : paquete.getSubPaquetes())
		{
			PaqueteDeTrabajo hijo = wbs.getPaquete(indexHijo);
			DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(hijo);
			crearNodos(nodoHijo, hijo);
			tree.add(nodoHijo);
		}
		
		for (Tarea tarea : paquete.getTareas())
		{
			DefaultMutableTreeNode nodoTarea = new DefaultMutableTreeNode(tarea);
		
			for (Actividad act : tarea.getActividades())
			{
				DefaultMutableTreeNode nodoAct = new DefaultMutableTreeNode(act);
				nodoTarea.add(nodoAct);
			}
			
			tree.add(nodoTarea);
		}
	}
	
	
	private void panelWBS()
	{
		panelDerecho.setVisible(false);
		panelDerecho.removeAll();
		
		JButton agregarPaquete = new JButton("Agregar un Paquete");
		agregarPaquete.addActionListener(this);
		agregarPaquete.setActionCommand("Agregar SubPaquete");
		
		JButton agregarTarea = new JButton("Agregar una Tarea");
		agregarTarea.addActionListener(this);
		agregarTarea.setActionCommand("Agregar Tarea");
		
		panelDerecho.setLayout(new GridLayout(0,1));
		panelDerecho.add(agregarPaquete);
		panelDerecho.add(agregarTarea);
		panelDerecho.setVisible(true);
	}
	
	
	private void panelPaquete(String titulo)
	{
		panelDerecho.setVisible(false);
		panelDerecho.removeAll();
		
		JLabel m1 = new JLabel("Paquete seleccionado: " + titulo);
		m1.setFont(new Font("Bold", Font.BOLD, 13));
		
		JButton agregarSubPaquete = new JButton("Agregar un SubPaquete");
		agregarSubPaquete.addActionListener(this);
		agregarSubPaquete.setActionCommand("Agregar SubPaquete");
		
		JButton agregarTarea = new JButton("Agregar una Tarea");
		agregarTarea.addActionListener(this);
		agregarTarea.setActionCommand("Agregar Tarea");
		
		JButton borrarPaquete = new JButton("Borrar este Paquete");
		borrarPaquete.addActionListener(this);
		borrarPaquete.setActionCommand("Borrar Paquete");
		
		panelDerecho.setLayout(new GridLayout(0,1));
		panelDerecho.add(m1);
		panelDerecho.add(agregarSubPaquete);
		panelDerecho.add(agregarTarea);
		panelDerecho.add(borrarPaquete);
		panelDerecho.setVisible(true);
	}
	
	
	private void panelTarea(String titulo)
	{
		panelDerecho.setVisible(false);
		panelDerecho.removeAll();
		
		JLabel m1 = new JLabel("Tarea seleccionada: " + titulo);
		m1.setFont(new Font("Bold", Font.BOLD, 13));
		
		JButton agregarActividad = new JButton("Agregar Actividad");
		agregarActividad.addActionListener(this);
		agregarActividad.setActionCommand("Agregar Actividad");
		
		JButton borrarTarea = new JButton("Borrar esta Tarea");
		borrarTarea.addActionListener(this);
		borrarTarea.setActionCommand("Borrar Tarea");
		
		panelDerecho.setLayout(new GridLayout(0,1));
		panelDerecho.add(m1);
		panelDerecho.add(agregarActividad);
		panelDerecho.add(borrarTarea);
		panelDerecho.setVisible(true);
	}
	
	
	private void panelActividad(Actividad act)
	{
		panelDerecho.setVisible(false);
		panelDerecho.removeAll();
		panelDerecho.setLayout(new GridLayout(0,1));
		
		JLabel m1 = new JLabel("INFORMACION DE LA ACTIVIDAD");
		m1.setFont(new Font("Bold", Font.BOLD, 13));
		panelDerecho.add(m1);
		
		JLabel m2 = new JLabel("Titulo: " + act.getTitulo());
		m2.setFont(new Font("Bold", Font.PLAIN, 12));
		panelDerecho.add(m2);
		
		JLabel m3 = new JLabel("Descripcion: " + act.getDescripcion());
		m3.setFont(new Font("Bold", Font.PLAIN, 12));
		panelDerecho.add(m3);
		
		JLabel m8 = new JLabel("Tipo: " + act.getTipoActividad());
		m8.setFont(new Font("Bold", Font.PLAIN, 12));
		panelDerecho.add(m8);
		
		JLabel m4 = new JLabel("Autor: " + act.getAutor().getNombre());
		m4.setFont(new Font("Bold", Font.PLAIN, 12));
		panelDerecho.add(m4);
		
		JLabel m5 = new JLabel("Fecha: " + act.getFecha());
		m5.setFont(new Font("Bold", Font.PLAIN, 12));
		panelDerecho.add(m5);
		
		JLabel m6 = new JLabel("Hora de inicio: " + act.getHoraInicio());
		m6.setFont(new Font("Bold", Font.PLAIN, 12));
		panelDerecho.add(m6);
		
		JLabel m7 = new JLabel("Hora de finalizacion: " + act.getHoraFin());
		m7.setFont(new Font("Bold", Font.PLAIN, 12));
		panelDerecho.add(m7);
		
		panelDerecho.setVisible(true);
	}
	
	
	@Override
	public void valueChanged(TreeSelectionEvent e)
	{
		selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		
		if (selectedNode != null)
		{
			Object nodeInfo = selectedNode.getUserObject();
			
			if (nodeInfo instanceof String)
			{
				paqueteSeleccionado = wbs.getPaquete(0);
				panelWBS();
			}
			
			else if (nodeInfo instanceof PaqueteDeTrabajo)
			{
				paqueteSeleccionado = (PaqueteDeTrabajo) nodeInfo;
				panelPaquete(paqueteSeleccionado.getTitulo());
			}
			
			else if (nodeInfo instanceof Tarea)
			{
				tareaSeleccionada = (Tarea) nodeInfo;
				panelTarea(tareaSeleccionada.getNombreTarea());
			}
			
			else if (nodeInfo instanceof Actividad)
			{
				Actividad act = (Actividad) nodeInfo;
				panelActividad(act);
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Agregar SubPaquete"))
		{
			try
			{
				int indexActual = paqueteSeleccionado.getIndex();
				String titulo = JOptionPane.showInputDialog(this, "Escriba el titulo del paquete");
				String descripcion = JOptionPane.showInputDialog(this, "Escriba una breve descripcion");
				PaqueteDeTrabajo nuevoPaquete = new PaqueteDeTrabajo(titulo, descripcion, indexActual);
				wbs.agregarPaquete(nuevoPaquete);
				
				DefaultMutableTreeNode nodoPaquete = new DefaultMutableTreeNode(nuevoPaquete);
				treeModel.insertNodeInto(nodoPaquete, selectedNode, selectedNode.getChildCount());
				padre.guardarArchivo();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(this, "No se pudo agregar el SubPaquete", "Error al crear SubPaquete",
						  JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		else if (e.getActionCommand().equals("Agregar Tarea"))
		{
			try
			{
				int indexActual = paqueteSeleccionado.getIndex();
				padre.newTaskSettings(new DialogNuevaTarea(this, indexActual));
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(this, "No se pudo crear la tarea", "Error al agregar tarea",
						  JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		
		else if (e.getActionCommand().equals("Borrar Paquete"))
		{
			int indexActual = paqueteSeleccionado.getIndex();
			
			try
			{
				wbs.borrarPaquete(indexActual);
				treeModel.removeNodeFromParent(selectedNode);
				padre.guardarArchivo();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al borrar paquete",
											  JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if (e.getActionCommand().equals("Agregar Actividad"))
		{
			DialogRegistrarActividad settingsAct = new DialogRegistrarActividad(this,
													tareaSeleccionada.getTipoTarea());
			
			for (Participante p : tareaSeleccionada.getResponsables())
			{
				settingsAct.addParticipanteDesplegable(p.getLogin());
			}
			
			settingsAct.setVisible(true);
		}
		
		else if (e.getActionCommand().equals("Borrar Tarea"))
		{
			try
			{
				wbs.borrarTarea(tareaSeleccionada);
				treeModel.removeNodeFromParent(selectedNode);
				padre.guardarArchivo();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al borrar tarea",
											  JOptionPane.ERROR_MESSAGE);
			}
		}
	}


}