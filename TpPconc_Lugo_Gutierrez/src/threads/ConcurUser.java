package threads;

import java.util.ArrayList;

import tpConcurrente.Funciones.TipoDeFuncion;
import tpConcurrente.MonitorConcurDerivative;

public abstract class ConcurUser extends Thread{

	protected MonitorConcurDerivative concurDerivative;
	protected TipoDeFuncion caso;
	protected double[] setORgetCase;
	protected ArrayList<Integer> recorrido;
	
	public ConcurUser(MonitorConcurDerivative monitor, TipoDeFuncion funcion, double...setORget){
		this.caso = funcion;
		this.concurDerivative = monitor;
		this.setORgetCase = setORget;
		this.recorrido = new ArrayList<Integer>();
	}
	
	public void añadirAlRecorrido(Integer n) {
		recorrido.add(n);
	}
	
	public abstract Integer IdUser();
}
