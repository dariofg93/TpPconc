package threads;

import java.util.ArrayList;

import tpConcurrente.MonitorConcurDerivative;

public abstract class ConcurUser extends Thread{

	protected MonitorConcurDerivative concurDerivative;
	protected Integer caso;
	protected Integer[] setCase;
	protected ArrayList<Integer> recorrido;
	
	public ConcurUser(MonitorConcurDerivative monitor, Integer funcion, Integer...set){
		this.caso = funcion;
		this.concurDerivative = monitor;
		this.setCase = set;
		this.recorrido = new ArrayList<Integer>();
	}
	
	public void añadirAlRecorrido(Integer n) {
		recorrido.add(n);
	}
	
	public ArrayList<Integer> getRecorrido(){
		return recorrido;
	}
	
	public MonitorConcurDerivative getMonitor(){
		return concurDerivative;
	}
	
	public abstract Integer IdUser();
}
