package tpConcurrente;

import java.util.ArrayList;

public class ConcurUser extends Thread{

	private int caso;
	private MonitorConcurDerivative concurDerivative;
	private ArrayList<Integer> recorrido;
	
	public ConcurUser(int n, MonitorConcurDerivative monitor){
		this.caso = n;
		this.concurDerivative = monitor;
		this.recorrido = new ArrayList<Integer>();
	}
	
	private MonitorConcurDerivative nuevoMonitor(){
		MonitorConcurDerivative n = new MonitorConcurDerivative(10,5);
		for(int i = 0 ; i<10 ; i++)
			n.set(i,10); 
		return n;
	}
	
	@Override
	public void run(){
		
		switch (caso) {
			case 1 : concurDerivative.agregarRecorrido(recorrido);
					 concurDerivative.add(nuevoMonitor());
					 break;
			case 2 : concurDerivative.set(3,5);
					 break;
			
		}
	}

	public void añadirAlRecorrido(Integer n) {
		recorrido.add(n);
	}
	
	public MonitorConcurDerivative getMonitor(){
		return concurDerivative;
	}
}
