package modelo;

import java.util.ArrayList;

public class CalculadoraWBS extends WBS
{
	
	public CalculadoraWBS(String nombreProyecto)
	{
		super(nombreProyecto);
	}

	
	public ReporteAvance calcularAvanceProyecto()
	{
		ReporteAvance avance = calcularAvancePaquete(0);	
		return avance;
	}
	
	
	public ReporteAvance calcularAvancePaquete(int indexPaquete)
	{
		ReporteAvance avance = new ReporteAvance();
		PaqueteDeTrabajo paquete = listaPaquetes.get(indexPaquete);
		completarAvancePaquete(paquete, avance);
		return avance;
	}
	
	
	public void calcularCalidadPlaneacion()
	{
		ReporteCalidadPlaneacion calidad = new ReporteCalidadPlaneacion();
		
		for (PaqueteDeTrabajo paquete : listaPaquetes)
		{
			ArrayList<Tarea> lasTareas = paquete.getTareas();
			calidad.totalTareas += lasTareas.size();
			
			for (Tarea tarea : lasTareas)
			{
				int tiempoReal = tarea.calcularTiempoReal();
				int tiempoPlaneado = tarea.getTiempoEstimado();
				
				calidad.tiempoReal += tiempoReal;
				calidad.tiempoPlaneado += tiempoPlaneado;
				
				if (tiempoReal <= tiempoPlaneado)
				{
					calidad.tareasCumplenTiempo++;
				}
			}
		}
	}
	
	
	public void calcularDesempenoEquipo()
	{
		
	}
	
	public void calcularResumenProyecto()
	{
		
	}
	
	
	//AUXILIARES
	private void completarAvancePaquete(PaqueteDeTrabajo paquete, ReporteAvance avance)
	{
		ArrayList<Tarea> lasTareas = paquete.getTareas();
		avance.totalTareas += lasTareas.size();
		
		for (Tarea tarea : lasTareas)
		{
			avance.tiempoPlaneadoTotal += tarea.getTiempoEstimado();
			
			if (tarea.isFinalizada())
			{
				avance.tareasTerminadas ++;
				avance.tiempoPlaneadoTerminadas += tarea.getTiempoEstimado();
				
				String fechaEstimadaFin = tarea.getFechaEstimadaFin();
				String fechaRealFin = tarea.getUltimaFechaProgreso();
				
				if (fechaCumple(fechaRealFin, fechaEstimadaFin))
				{
					avance.tareasTerminadasATiempo++;
				}
			}
		}
		
		//Repetir para los subpaquetes
		for (Integer indexSubPaquete : paquete.getSubPaquetes())
		{
			PaqueteDeTrabajo subPaquete = listaPaquetes.get(indexSubPaquete);
			completarAvancePaquete(subPaquete, avance);
		}
		
	}
	
	
	private boolean fechaCumple(String fechaReal, String fechaEstimada)
	{
	
		Integer anoReal = Integer.parseInt(fechaReal.substring(6));
		Integer anoEstimado = Integer.parseInt(fechaEstimada.substring(6));
		
		if (anoReal < anoEstimado)
		{
			return true;
		}
		
		else if (anoReal > anoEstimado)
		{
			return false;
		}
		
		else
		{
			Integer mesReal = Integer.parseInt(fechaReal.substring(3,5));
			Integer mesEstimado = Integer.parseInt(fechaEstimada.substring(3,5));
			
			if (mesReal < mesEstimado)
			{
				return true;
			}
			
			else if (mesReal > mesEstimado)
			{
				return false;
			}
			
			else
			{
				Integer diaReal = Integer.parseInt(fechaReal.substring(0,2));
				Integer diaEstimado = Integer.parseInt(fechaEstimada.substring(0,2));
				
				if (diaReal <= diaEstimado)
				{
					return true;
				}
				
				else
				{
					return false;
				}
			}
		}		
	}
	
	
}
