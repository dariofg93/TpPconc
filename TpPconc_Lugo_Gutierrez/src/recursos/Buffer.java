package recursos;

public class Buffer {

	private Task[] slots;
	private int begin=0;
	private int end=0;
	private int actual=0;
	
	public Buffer(int size){
		this.slots = new Task[size];
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
		
		while (isEmpty()){
			try {this.wait();} 
			catch (InterruptedException e) {}
		}
		actual++;
		Task result = slots[end];
		if(actual==5+1){
			end = end+1 % this.slots.length;
			actual = 0;
		}
		return result;
	}
}