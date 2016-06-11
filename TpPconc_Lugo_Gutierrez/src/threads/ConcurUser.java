package threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import recursos.Functions.TipoDeFuncion;
import tpConcurrente.MonitorConcurDerivative;

public class ConcurUser extends Thread{

	private MonitorConcurDerivative monitor;
	private ArrayList<Integer> recorrido;
	private Queue<TipoDeFuncion> q;
	
	public ConcurUser(MonitorConcurDerivative monitor){
		this.monitor = monitor;
		this.q = new LinkedList<TipoDeFuncion>();
		this.recorrido = new ArrayList<Integer>();
	}
	
	@Override
	public void run() {
		
		switch (caso) {
		case DIMENSION 	 : 	monitor.dimension();
				 break;
		case GET 		 : 	monitor.get((int) setORgetCase[0]);
				 			break;
		case SETCONINDEX :  monitor.set((int) setORgetCase[0],setORgetCase[1]);
				 break;
		case SET 		 : 	for(Integer i : recorrido)
				 				monitor.set(i,setORgetCase[0]);
		 		 			break;
		}
	}
	
	public void añadirAlRecorrido(Integer n) {
		recorrido.add(n);
	}
	
	public void añadirALaCola(TipoDeFuncion f) {
		q.add(f);
	}
}
