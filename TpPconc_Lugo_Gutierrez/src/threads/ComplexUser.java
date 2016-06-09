package threads;

import tpConcurrente.Funciones.TipoDeFuncion;
import tpConcurrente.MonitorConcurDerivative;

public class ComplexUser extends ConcurUser {
	
	private MonitorConcurDerivative repuesto;

	public ComplexUser(MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor, TipoDeFuncion funcion, double...setORget) {
		super(monitor,funcion,setORget);
		this.repuesto = otroMonitor;
	}

	@Override
	public void run() {
		
		switch (caso) {
		case DIMENSION : concurDerivative.dimension();
				 break;
				 //Dimension
		case GET : concurDerivative.get((int) setORgetCase[0]);
				 break;
				 //get
		case SETCONINDEX : concurDerivative.set((int) setORgetCase[0],setORgetCase[1]);
				 break;
				 //set(con index)
		case SET : for(Integer i : recorrido)
				 	concurDerivative.set(i,setORgetCase[0]);
		 		 break;
		 		 //set
		}
	}
	
	@Override
	public Integer IdUser() {
		return 1;
	}
}
