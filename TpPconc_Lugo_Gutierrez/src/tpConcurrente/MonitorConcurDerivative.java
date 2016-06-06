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
	
	//redefinir
	public int dimension() {
		return elements.length;
	}
	
	//creo que ya esta bien.. lastima que los try lo deja tan feo al codigo...
	public synchronized void set(int lugar,double valor){
		
		cantThreadsActual ++;
		
		if(cantThreadsActual>1){
			elements[lugar] = valor;
			
			while(hayEspacio()){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.notifyAll();
			this.cantThreadsActual = 0;
		}
		else{
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void add(MonitorConcurDerivative monitor){
		//cantThreadsActual ++;
		//if(this.noEstaOcupado){
		//for(Integer actual: recorridos.get(0)){
		//elements[actual] = elements[actual] + monitor.getVector()[actual];
		//}
	}
	
	//Devuelve true si aun no han ingresado todos los threads
	public Boolean hayEspacio(){
		return threadsTotal != cantThreadsActual;
	}

	//Devuelve el vector del monitor(si no se usa en ningun lado se borrará)
	public double[] getVector() {
		return elements;
	}

	//Prop: Asigno un recorrido a un thread con un size correspondiente a
	// la cantidad de elementos que debe recorrer. Devuelve el 
	// recorrido sobrante
	public ArrayList<Integer> asignarRecorrido(ConcurUser user, ArrayList<Integer> rec, Integer threadsFaltantes) {
		ArrayList<Integer> recCortado = rec;
		Integer i = (elements.length / cantThreadsActual) + 
					(rec.size() % threadsFaltantes);
		while(i>0){
			user.añadirAlRecorrido(recCortado.get(0));
			recCortado.remove(0);
			i--;
		}
		return recCortado;
	}
	
	//Devuelve una lista desde 0 hasta el size-1 del vector del monitor
	public ArrayList<Integer> numerosHastaSize(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0 ; i < elements.length; i++)
			list.add(i);
		return list;
	}
}
