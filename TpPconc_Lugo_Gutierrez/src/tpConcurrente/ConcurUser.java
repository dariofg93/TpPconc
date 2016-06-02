package tpConcurrente;

public class ConcurUser extends Thread{

	private int caso;
	private MonitorConcurDerivative concurDerivative;
	private int[] recorrido;
	
	public ConcurUser(int n, MonitorConcurDerivative monitor){
		this.caso = n;
		this.concurDerivative = monitor;
		//this.recorrido = monitor.generarRecorrido();
	}
	
	@Override
	public void run(){
		switch (caso) {
			case 1 : concurDerivative.hayEspacio();
					 break;
			case 2 : concurDerivative.set(3,5);
					 break;	
		}
	}
}
