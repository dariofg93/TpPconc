package threads;

import java.util.ArrayList;

import tpConcurrente.MonitorConcurDerivative;

public abstract class ConcurUser extends Thread{

	protected MonitorConcurDerivative concurDerivative;
	protected Integer caso;
	protected Integer[] setCase;
	
	public ConcurUser(MonitorConcurDerivative monitor, Integer funcion, Integer[] set){
		this.caso = funcion;
		this.concurDerivative = monitor;
		this.setCase = set;
	}
	
	public abstract void añadirAlRecorrido(Integer n);
	
	public abstract ArrayList<Integer> getRecorrido();
	
	public MonitorConcurDerivative getMonitor(){
		return concurDerivative;
	}
	
	public abstract Integer IdUser();
}
