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
	private Queue<Task> q;
	private Barrier barrera;
	
	public ConcurUser(MonitorConcurDerivative monitor){
		this.monitor = monitor;
		this.q = new LinkedList<Task>();
		this.recorrido = new ArrayList<Integer>();
		this.barrera = new Barrier(1+1);
	}
	
	@Override
	public void run() {
		
		while(true){
			Task t = q.poll();
			switch(t.getFuncion()){
			
				case SET 		  : for(Integer i : recorrido)
				 						monitor.set(i,(int)t.getPrimario());
		 		 					break;
				case ASSIGN 	  : for(Integer i : recorrido)
										monitor.set(i,(int)t.getPrimario());
									break;
				case ABS 		  : for(Integer i : recorrido)
										monitor.set(i,(int)t.getPrimario());
									break;
				case ADD 		  : for(Integer i : recorrido)
										monitor.set(i,(int)t.getPrimario());
									break;
				case SUB 		  : for(Integer i : recorrido)
										monitor.set(i,(int)t.getPrimario());
									break;
				case MUL 		  : for(Integer i : recorrido)
										monitor.set(i,(int)t.getPrimario());
									break;
				case DIV 		  : for(Integer i : recorrido)
										monitor.set(i,(int)t.getPrimario());
									break;
				case DIFFERENTIATE: for(Integer i : recorrido)
										monitor.set(i,(int)t.getPrimario());
									break;
			} 			
			barrera.ready();
		}
	}
	
	public void añadirAlRecorrido(Integer n) {
		recorrido.add(n);
	}

	public void agregarTarea(Task tarea) {
		q.add(tarea);
	}
}
