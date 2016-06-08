package tpConcurrente;

import java.util.ArrayList;

import threads.ConcurUser;

public class MonitorConcurDerivative {

	private Integer threadsTotal; 		//la cantidad maxima de threads a utilizar
	private double[] elements;			//el vector que se recorre de ser necesario
	private Integer cantThreadsActual;	//cantidad de threads que interactuan con el monitor actualmente
	
	public MonitorConcurDerivative(int size, Integer cantTotal) {
		elements = new double[size];
		cantThreadsActual = 0;
		threadsTotal = cantTotal;
	}
	
	//redefinir porque no hay concurrencia(aunque no se me ocurre como aún)
	public int dimension() {
		return elements.length;
	}
	
	public synchronized Double get(int lugar) {
		
		cantThreadsActual++;
				
		if(hayUnSoloThread()){
			
			while(hayEspacio()){
				try { this.wait(); 
				} catch (InterruptedException e) {}
			}
			this.notifyAll();
			cantThreadsActual = 0;
			return elements[lugar];
		}
		else{
			try {
				while(!noHayNadie()){
					this.notify();
					this.wait();
				}
				
			} catch (InterruptedException e) {}
			return 0.0;
		}
		
}

	//creo que ya esta bien.. lastima que los try lo deja tan feo al codigo...
	public synchronized void set(int lugar,double valor){
		
		cantThreadsActual ++;
		
		if(hayUnSoloThread()){
			elements[lugar] = valor;
			
			while(hayEspacio()){
				try { this.wait();
				} catch (InterruptedException e) {}
			}
			this.notifyAll();
		}
		else{
			try {
				while(!noHayNadie()){
					this.notify();
					this.wait();
				}
			} catch (InterruptedException e) {}
		}
		cantThreadsActual = 0;
	}
	
	public void set(Double n) {
		// TODO Auto-generated method stub
		
	}

	public void add(MonitorConcurDerivative monitor){
		//cantThreadsActual ++;
		//if(this.noEstaOcupado){
		//for(Integer actual: recorridos.get(0)){
		//elements[actual] = elements[actual] + monitor.getVector()[actual];
		//}
	}
	
	//Devuelve true si aun no han ingresado todos los threads
	private Boolean hayEspacio(){
		return threadsTotal != cantThreadsActual;
	}
	
	//Devuelve true si solo ha ingresado un solo thread
	private Boolean hayUnSoloThread(){
		return cantThreadsActual == 1;
	}
	
	//Devuelve true si hay 0 threads "registrados"
	private Boolean noHayNadie() {
		return cantThreadsActual == 0;
	}
	
	//Devuelve el vector del monitor(si no se usa en ningun lado se borrará)
	public double[] getVector() {
		return elements;
	}

	public Integer limiteDeThreads() {
		return threadsTotal;
	}
}
