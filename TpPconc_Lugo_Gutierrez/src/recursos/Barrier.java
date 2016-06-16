package recursos;

public class Barrier {

	private int permisos; 
	private int actuales;

	public Barrier(int total){
		this.permisos = total;
		this.actuales = 0;
	}

	/**
	 * Prop.: Pone a todos los threads que lo ejecutan en modo "dormir", a menos 
	 * que el que llega tenga permisos==actuales, en tal cazo "despierta" a todos.
	 */
	public synchronized void ready(){
		actuales++;
	
		if(actuales < permisos){
			try { wait(); } 
			catch (InterruptedException e) {}
		}
		
		this.notifyAll();
		actuales = 0;
	}
}
