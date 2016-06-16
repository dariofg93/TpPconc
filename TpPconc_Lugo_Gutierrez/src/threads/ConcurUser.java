package threads;

import java.util.ArrayList;

import recursos.Task;
import tpConcurrente.MonitorConcurDerivative;

public class ConcurUser extends Thread{

	private MonitorConcurDerivative monitor;
	private ArrayList<Integer> recorrido;
	
	public ConcurUser(MonitorConcurDerivative monitor){
		this.monitor = monitor;
		this.recorrido = new ArrayList<Integer>();
	}
	
	@Override
	public void run() {
		
		while(true){
			Task t = monitor.getBuff().consumir();
			switch(t.getFuncion()){
				
				case SET 		  : for(Integer i : recorrido)
					 					monitor.set(i,t.getNum());
			 	 					break;
			 		 					
				case ASSIGN 	  : for(Integer i : recorrido)
										monitor.set(i,t.getMonitor().get(i));
									break;
									
				case ABS 		  : for(Integer i : recorrido)
										monitor.set(i,Math.abs(monitor.get(i)));
									break;
									
				case ADD 		  : for(Integer i : recorrido)
										monitor.set(i,monitor.get(i) + t.getMonitor().get(i));
									break;
										
				case SUB 		  : for(Integer i : recorrido)
										monitor.set(i,monitor.get(i) - t.getMonitor().get(i));
									break;
									
				case MUL 		  : for(Integer i : recorrido)
										monitor.set(i,monitor.get(i) * t.getMonitor().get(i));
									break;
									
				case DIV 		  : for(Integer i : recorrido)
										monitor.set(i,monitor.get(i) / t.getMonitor().get(i));
									break;
									
				case DIFFERENTIATE: for(Integer i : recorrido){
										if(!(i==0 || i==(monitor.dimension()-1)))
											t.getMonitor().set(i -1, (monitor.get(i+1) - monitor.get(i-1)) / 2);
									}
									break;
			}
			monitor.irABarrera();
		}
	}
	
	public void añadirAlRecorrido(Integer n) {
		recorrido.add(n);
	}
}
