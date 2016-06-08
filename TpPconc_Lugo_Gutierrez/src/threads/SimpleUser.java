package threads;

import tpConcurrente.MonitorConcurDerivative;

public class SimpleUser extends ConcurUser{

	public SimpleUser(MonitorConcurDerivative monitor, Integer funcion, double...setORget){
		super(monitor,funcion,setORget);
	}
	
	@Override
	public void run() {
		
		switch (caso) {
		case 1 : //System.out.println("NO CORRESPONDE LA OPERACION A UN USUARIO SIMPLE");
				 break;
				 //Dimension
		case 2 : this.variable = concurDerivative.get((int) setORgetCase[0]);
				 break;
				 //get
		case 3 : concurDerivative.set((int) setORgetCase[0],setORgetCase[1]);
				 break;
				 //set(con index)
		case 4 : for(Integer i : recorrido)
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
