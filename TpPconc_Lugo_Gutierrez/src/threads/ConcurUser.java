package threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import recursos.Task;
import tpConcurrente.MonitorConcurDerivative;
import recursos.Barrier;

public class ConcurUser extends Thread{

	private MonitorConcurDerivative monitor;
	private ArrayList<Integer> recorrido;
	private Queue<Task> cola;
	private Barrier barrera;
	
	public ConcurUser(MonitorConcurDerivative monitor){
		this.monitor = monitor;
		this.cola = new LinkedList<Task>();
		this.recorrido = new ArrayList<Integer>();
		this.barrera = new Barrier(1);
	}
	
	@Override
	public void run() {
		
		while(true){
			if(!cola.isEmpty()){
				Task t = cola.poll();
				switch(t.getFuncion()){
				
					case SET 		  : for(Integer i : recorrido)
					 						monitor.set(i,(double)t.getPrimario());
			 		 					break;
			 		 					
					case ASSIGN 	  : for(Integer i : recorrido)
											monitor.set(i,((MonitorConcurDerivative)t.getPrimario()).get(i));
										break;
										
					case ABS 		  : for(Integer i : recorrido)
											monitor.set(i,Math.abs(monitor.get(i)));
										break;
										
					case ADD 		  : for(Integer i : recorrido)
											monitor.set(i,monitor.get(i)+((MonitorConcurDerivative)t.getPrimario()).get(i));
										break;
										
					case SUB 		  : for(Integer i : recorrido)
											monitor.set(i,monitor.get(i)-((MonitorConcurDerivative)t.getPrimario()).get(i));
										break;
										
					case MUL 		  : for(Integer i : recorrido)
											monitor.set(i,monitor.get(i)*((MonitorConcurDerivative)t.getPrimario()).get(i));
										break;
										
					case DIV 		  : for(Integer i : recorrido)
											monitor.set(i,monitor.get(i)/((MonitorConcurDerivative)t.getPrimario()).get(i));
										break;
										
					case DIFFERENTIATE: for(Integer i : recorrido)
											monitor.set(i,(int)t.getPrimario());
										break;
				}
			}
			barrera.ready();
		}
	}
	
	public void añadirAlRecorrido(Integer n) {
		recorrido.add(n);
	}

	public void agregarTarea(Task tarea) {
		cola.add(tarea);
	}
	
	public void imprimirRecorrido(){
		for(Integer n: recorrido)
			System.out.println(n+",");
		System.out.println("|||");
	}

	public synchronized void despertar() {
		this.notify();
	}
}
