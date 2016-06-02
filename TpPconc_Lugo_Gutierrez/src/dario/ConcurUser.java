package dario;

public class ConcurUser extends Thread{

	private int caso;
	private Concurrencia concurDerivative;
	
	public ConcurUser(int n, Concurrencia pconc){
		this.caso = n;
		this.concurDerivative = pconc;
	}
	
	@Override
	public void run(){
		switch (caso) {
			case 1 : concurDerivative.hayEspacio();
					 break;
			case 2 : concurDerivative.set(5);
					 break;
		}
	}
}
