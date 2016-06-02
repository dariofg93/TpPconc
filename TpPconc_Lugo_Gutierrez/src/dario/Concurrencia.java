package dario;

public class Concurrencia {

	private Integer threadsTotal; //la cantidad maxima de threads a utilizar
	private double[] elements;
	//private Integer load; 		  //la cantidad de elementos en la que puede 
	//LO INGNORAMOS POR AHORA		  //diferir la asignacion a cada thread;	x>=1>0
	
	private Integer indiceActual;
	private Integer cantThreadsActual;
	
	public Concurrencia(int size, Integer cantTotal) {
		elements = new double[size];
		indiceActual = 0;
		cantThreadsActual = 0;
		threadsTotal = cantTotal;
	}
	
	public void set(double d) {
		Integer repeat = 2;
		
		while(!hayEspacio()){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(repeat>0){
			elements[indiceActual] = d;
			indiceActual++;
			repeat--;
		}
	}
	
	
	public Boolean hayEspacio(){
		return threadsTotal != cantThreadsActual;
	}

	public double[] getVector() {
		return elements;
	}
	
	/** Returns the dimension of this vector, that is, its width. 
	public int dimension() {
		return elements.length;
	}*/
}
