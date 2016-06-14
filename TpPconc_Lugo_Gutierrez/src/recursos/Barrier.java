package recursos;

public class Barrier {

	private int permisos; 
	private int actuales;

	public Barrier(int total){
		this.permisos = total;
		this.actuales = 0;
	}

	public synchronized void ready(){
		actuales++;
	
		if(actuales < permisos){
			try { wait(); } 
			catch (InterruptedException e) {}
		}
		
		this.notifyAll();
		actuales = 0;
	}

	public int getPermisos() {
		return permisos;
	}
	
}
