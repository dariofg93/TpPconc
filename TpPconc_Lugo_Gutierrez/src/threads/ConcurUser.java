package threads;

import java.util.ArrayList;

import tpConcurrente.MonitorConcurDerivative;

public abstract class ConcurUser extends Thread{

	protected MonitorConcurDerivative concurDerivative;
	protected Integer caso;
	protected double[] setORgetCase;
	protected ArrayList<Integer> recorrido;
	protected Object variable;
	
	public ConcurUser(MonitorConcurDerivative monitor, Integer funcion, double...setORget){
		this.caso = funcion;
		this.concurDerivative = monitor;
		this.setORgetCase = setORget;
		this.recorrido = new ArrayList<Integer>();
		this.variable = null;
	}
	
	public void añadirAlRecorrido(Integer n) {
		recorrido.add(n);
	}
	
	public abstract Integer IdUser();

	public Object getVariable() {
		return variable;
	}
}
