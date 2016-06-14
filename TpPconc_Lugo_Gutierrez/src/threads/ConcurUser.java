package threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import recursos.Buffer;
import recursos.Task;
import tpConcurrente.MonitorConcurDerivative;

public class ConcurUser extends Thread{

	private MonitorConcurDerivative monitor;
	private ArrayList<Integer> recorrido;
	private Buffer buff;
	
	public ConcurUser(MonitorConcurDerivative monitor, Buffer unBuff){
		this.monitor = monitor;
		this.recorrido = new ArrayList<Integer>();
		this.buff = unBuff;
	}
	
	@Override
	public void run() {
		
		while(true){
			Task t = buff.consumir();
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
	}
	
	public void añadirAlRecorrido(Integer n) {
		recorrido.add(n);
	}
	
	public void imprimirRecorrido(){
		for(Integer n: recorrido)
			System.out.println(n+",");
		System.out.println("|||");
	}
}
