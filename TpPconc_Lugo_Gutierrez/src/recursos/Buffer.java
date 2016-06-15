package recursos;

public class Buffer {

	private Task[] slots;
	private int begin=0;
	private int end=0;
	private Barrier barrera;
	private int actual=0;
	
	public Buffer(Integer cantThreads, int size){
		this.slots = new Task[size];				//Los productores tendran hasta 1000 lugares diferentes
													//antes de que venga algun thrad a consumir
		this.barrera = new Barrier(cantThreads+1);
	}
	
	private boolean isEmpty() {
		return begin==end;
	}
	
	private boolean isFull() {
		return (begin+1 % slots.length)==end;
	}
		
	public synchronized void producir(Task tarea){
		while (isFull()){
			try {this.wait();} 
			catch (InterruptedException e) {}
		}
		
		slots[begin]=tarea;
		begin = begin+1 % this.slots.length;
		
		this.notifyAll();
	}
	
	public synchronized Task consumir() {
		actual++;
		while (isEmpty()){
			try {this.wait();} 
			catch (InterruptedException e) {}
		}
		
		Task result = slots[end];
		if(actual==barrera.getPermisos())
			end = end+1 % this.slots.length;
		
		barrera.ready();
		
		actual = 0;
		return result;
	}
}