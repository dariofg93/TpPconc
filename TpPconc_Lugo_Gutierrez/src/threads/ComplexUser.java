package threads;

import java.util.ArrayList;

import tpConcurrente.MonitorConcurDerivative;

public class ComplexUser extends ConcurUser {
	
	private MonitorConcurDerivative repuesto;
	private ArrayList<Integer> recorrido;

	public ComplexUser(MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor, Integer funcion) {
		super(monitor,funcion);
		this.repuesto = otroMonitor;
		this.recorrido = new ArrayList<Integer>();
	}

	@Override
	public void run() {
		
		switch (caso) {
		case 1 : System.out.println("NO CORRESPONDE LA OPERACION A UN USUARIO SIMPLE");
				 break;
		case 2 : concurDerivative.set(3,5);
				 break;
		}
	}
	
	@Override
	public Integer IdUser() {
		return 1;
	}
	@Override
	public void añadirAlRecorrido(Integer n) {
		recorrido.add(n);
	}
	
	@Override
	public ArrayList<Integer> getRecorrido(){
		return recorrido;
	}
}
