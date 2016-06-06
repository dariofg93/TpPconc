package threads;

import java.util.ArrayList;

import tpConcurrente.MonitorConcurDerivative;

public abstract class ConcurUser extends Thread{

	protected int caso;
	protected MonitorConcurDerivative concurDerivative;
	
	
	public ConcurUser(MonitorConcurDerivative monitor, Integer funcion){
		this.caso = funcion;
		this.concurDerivative = monitor;
	}
	
	public abstract void añadirAlRecorrido(Integer n);
	
	public abstract ArrayList<Integer> getRecorrido();
	
	public MonitorConcurDerivative getMonitor(){
		return concurDerivative;
	}
	
	public abstract Integer IdUser();
}
