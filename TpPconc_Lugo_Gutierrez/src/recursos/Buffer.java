package recursos;

public class Buffer {

	private Task[] slots;
	private int begin=0;
	private int end=0;
	private Barrier barrera;
	
	public Buffer(Integer n){
		this.slots = new Task[n];
		this.barrera = new Barrier(5+1);
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
		
		Task result = slots[end];
		end = end+1 % this.slots.length;
		
		barrera.ready(); 					//No deberia ni estar	
		//this.notifyAll();					//Solo va si no esta la barrera
		
		return result;
	}
}

/**package productoresYconsumidores;

public class Consumidor extends Thread{

	private final Buffer buff;
	
	public Consumidor(Buffer b) {
		this.buff = b;
	}
	
	public void run() {
		while (true) {
			Integer n = buff.consumir();
			System.out.println("Consumo el numero " + n.toString());
		}
	}
}

package productoresYconsumidores;

public class Productor extends Thread{

	private final Buffer buff;
	private final Integer[] products;
	
	public Productor(Buffer b, Integer[] ns) {
		this.buff = b;
		this.products = ns;
	}
	
	public void run() {
		for(Integer i : this.products){
			buff.producir(i);
		}
	}
}

package productoresYconsumidores;

public class PRODUCIRyCONSUMIR {

	public static void main(String[] args) {
		Buffer buff = new Buffer(2);
		
		Productor p = new Productor(buff,new Integer[]{1,6,3,9,23,7,4,4,6,4});
		p.start();
		
		Consumidor c = new Consumidor(buff);
		c.start();
		}
}*/
