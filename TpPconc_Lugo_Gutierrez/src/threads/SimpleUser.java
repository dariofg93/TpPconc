package threads;


import tpConcurrente.Funciones.TipoDeFuncion;
import tpConcurrente.MonitorConcurDerivative;

public class SimpleUser extends ConcurUser{

	public SimpleUser(MonitorConcurDerivative monitor, TipoDeFuncion funcion, double...setORget){
		super(monitor,funcion,setORget);
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
		return 0;
	}
}
