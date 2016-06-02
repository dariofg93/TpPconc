package tpConcurrente;

import java.util.HashSet;
import java.util.Set;

public class MonitorConcurDerivative {

	private Integer threadsTotal; //la cantidad maxima de threads a utilizar
	private double[] elements;
	//private Integer load; 		  //la cantidad de elementos en la que puede 
	//LO INGNORAMOS POR AHORA		  //diferir la asignacion a cada thread;	x>=1>0
	
	private int[] indiceActual; // Array de 10 numeros que vayan de 0 a 9. 
	private Integer cantThreadsActual;
	private Boolean noEstaOcupado = true;
	
	
	public MonitorConcurDerivative(int size, Integer cantTotal) {
		elements = new double[size];
		cantThreadsActual = 0;
		threadsTotal = cantTotal;
	}
	
	
	/**his.creacionDeThreads(threadsTotal,2); Este metodo va en el principio de cada 
	  test para inicializar los threads*/
	
	public int dimension() {
		return elements.length;
	}
	
	public synchronized void set(int i,double d){
		
		cantThreadsActual ++;
		if(this.noEstaOcupado){
		elements[i] = d;
		
		this.noEstaOcupado = false;
		/** Si el primer entra, como el metodo es synchronized el segundo thread 
			necesariamente debe esperar que termine el primero para ejecutarse o no.*/
		while(cantThreadsActual != threadsTotal);
			this.notifyAll();
			this.noEstaOcupado = true;
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
	
	public Boolean hayEspacio(){
		return threadsTotal != cantThreadsActual;
	}

	public double[] getVector() {
		return elements;
	}

	public void setEn(int value, int[] recorrido) {
		
		
	}
	
	/** Returns the dimension of this vector, that is, its width. 
	public int dimension() {
		return elements.length;
	}*/
}
