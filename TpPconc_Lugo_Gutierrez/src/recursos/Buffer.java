package recursos;

public class Buffer {

	private Task[] slots;
	private int begin=0;
	private int end=0;
	private int actual=0;
	private int cantThreads;
	
	public Buffer(int size, int threadsTotal){
		this.slots = new Task[size];
		this.cantThreads = threadsTotal;
	}

	/**
	 * Prop.: Devuelve true si no hay elementos en el Buffer
	 */
	private boolean isEmpty() {
		return begin==end;
	}

	/**
	 * Prop.: Devuelve true si el Buffer esta lleno
	 */
	private boolean isFull() {
		return (begin+1 % slots.length)==end;
	}
	
	/**
	 * Prop.: Agrega una nueva tarea al Buffer
	 */
	public synchronized void producir(Task tarea){
		while (isFull()){
			try {this.wait();} 
			catch (InterruptedException e) {}
		}
		
		slots[begin]=tarea;
		begin = begin+1 % this.slots.length;
		
		this.notifyAll();
	}
	
	/**
	 * Prop.: Quita la primer tarea disponible del Buffer y la devuelve,
	 */
	public synchronized Task consumir() {
		
		while (isEmpty()){
			try {this.wait();} 
			catch (InterruptedException e) {}
		}
		actual++;
		Task result = slots[end];
		if(actual==cantThreads){		
		//El ultimo thread que debe pedir la tarea es quien 
			//cambia dice cual es la siguiente tarea del Buffer
			end = end+1 % this.slots.length;
			actual = 0;
		}
		return result;
	}
}