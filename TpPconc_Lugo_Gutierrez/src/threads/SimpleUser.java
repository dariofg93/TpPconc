package threads;

import tpConcurrente.MonitorConcurDerivative;

public class SimpleUser extends ConcurUser{

	public SimpleUser(MonitorConcurDerivative monitor, Integer funcion, Integer...setORget){
		super(monitor,funcion,setORget);
	}
	
	@Override
	public void run() {
		
		switch (caso) {
		case 1 : System.out.println("NO CORRESPONDE LA OPERACION A UN USUARIO SIMPLE");
				 break;
				 //Dimension
		case 2 : variable = concurDerivative.get(setORgetCase[0]);
				 break;
				 //get
		case 3 : concurDerivative.set(setORgetCase[0],setORgetCase[1]);
				 break;
				 //set(con index)
		}
	}

	@Override
	public Integer IdUser() {
		return 0;
	}
}
