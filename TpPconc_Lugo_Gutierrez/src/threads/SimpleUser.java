package threads;

import tpConcurrente.MonitorConcurDerivative;

public class SimpleUser extends ConcurUser{

	public SimpleUser(MonitorConcurDerivative monitor, Integer funcion, Integer[] set){
		super(monitor,funcion,set);
	}
	
	@Override
	public void run() {
		
		switch (caso) {
		case 1 : System.out.println("NO CORRESPONDE LA OPERACION A UN USUARIO SIMPLE");
				 break;
		case 2 : concurDerivative.set(setCase[0],setCase[1]);
				 break;
		}
	}

	@Override
	public Integer IdUser() {
		return 0;
	}
}
