package tpConcurrente;

import java.util.ArrayList;

public class MonitorConcurDerivative {

	private ArrayList<ArrayList<Integer>> recorridos;
	private Integer threadsTotal; //la cantidad maxima de threads a utilizar
	private double[] elements;
	//private Integer load; 		  //la cantidad de elementos en la que puede 
	//LO INGNORAMOS POR AHORA		  //diferir la asignacion a cada thread;	x>=1>0
	
	//private int[] indiceActual; // Array de 10 numeros que vayan de 0 a 9. 
	private Integer cantThreadsActual;
	private Boolean noEstaOcupado = true;
	
	
	//recorro el conjunto que me da numerosHasta() con una nueva funcion 
	//asignarRecorrido(int i) que le asigna a cada thread dos lugares y
	// devuelvo el set sin esos dos numeros usados.
	
	public MonitorConcurDerivative(int size, Integer cantTotal) {
		elements = new double[size];
		cantThreadsActual = 0;
		threadsTotal = cantTotal;
		recorridos = new ArrayList<ArrayList<Integer>>();
	}
	
	
	/**his.creacionDeThreads(threadsTotal,2); Este metodo va en el principio de cada 
	  test para inicializar los threads*/
	
	public int dimension() {
		return elements.length;
	}
	
	public synchronized void set(int lugar,double valor){
		
		cantThreadsActual ++;
		if(this.noEstaOcupado){
			this.noEstaOcupado = false;
			elements[lugar] = valor;
		
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
	
	public void add(MonitorConcurDerivative monitor){
		//cantThreadsActual ++;
		//if(this.noEstaOcupado){
		//for(Integer actual: recorridos.get(0)){
		//elements[actual] = elements[actual] + monitor.getVector()[actual];
		//}
	}
	
	public Boolean hayEspacio(){
		return threadsTotal != cantThreadsActual;
	}

	public double[] getVector() {
		return elements;
	}


	public void agregarRecorrido(ArrayList<Integer> recorrido) {
		recorridos.add(recorrido);
	}

	//Prop: Asigno un recorrido a un thread con un size correspondiente a
	// la cantidad de elementos que debe recorrer. Devuelve el 
	// recorrido sobrante
	public ArrayList<Integer> asignarRecorrido(ConcurUser user, ArrayList<Integer> rec) {
		ArrayList<Integer> recCortado = rec;
		Integer i = 2;
		
		while(i>0){
			asignarUnLugarA(user,recCortado.get(0));
			recCortado.remove(0);
			i--;
		}
		return recCortado;
	}
	
	public void asignarUnLugarA(ConcurUser t,Integer n){
		t.añadirAlRecorrido(n);
	}
	
	public ArrayList<Integer> numerosHastaSize(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0 ; i < elements.length; i++)
			list.add(i);
		return list;
	}
}
