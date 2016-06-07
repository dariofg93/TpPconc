package threads;

import tpConcurrente.MonitorConcurDerivative;

public class ComplexUser extends ConcurUser {
	
	private MonitorConcurDerivative repuesto;

	public ComplexUser(MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor, Integer funcion, Integer...set) {
		super(monitor,funcion,set);
		this.repuesto = otroMonitor;
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
		return 1;
	}
}
