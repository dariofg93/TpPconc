package threads;

import java.util.ArrayList;

import tpConcurrente.MonitorConcurDerivative;

public class SimpleUser extends ConcurUser{

	public SimpleUser(int n, MonitorConcurDerivative monitor) {
		super(n, monitor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		
		switch (caso) {
		case 1 : System.out.println("NO CORRESPONDE LA OPERACION A UN USUARIO SIMPLE");
				 break;
		case 2 : concurDerivative.set(3,5);
				 break;
		}
	}

	@Override
	public Integer IdUser() {
		return 0;
	}

	@Override
	public void añadirAlRecorrido(Integer n) {}
	
	public ArrayList<Integer> getRecorrido(){
		return new ArrayList<Integer>();
	}
}
