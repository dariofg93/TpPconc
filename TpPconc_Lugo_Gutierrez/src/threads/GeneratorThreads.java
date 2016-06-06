package threads;

import java.util.ArrayList;

import testsConcurrentes.MonitorTests.UsersType;
import tpConcurrente.MonitorConcurDerivative;

public class GeneratorThreads {

	

	/**############### Creacion e inicializacion de Threads ##############*/
	
	private ConcurUser agregarThread(MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor,
									UsersType tipo,Integer funcion) {
		
		if(tipo.ordinal()==0){
			return new SimpleUser(monitor,funcion);
		}else{
			return new ComplexUser(monitor,otroMonitor,funcion);
		}
	}
	
	public ArrayList<ConcurUser> creacionDeThreads(UsersType tipo,
			MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor,
			Integer cantThreads, Integer funcion){
		
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
		for(int i = 0 ; i<cantThreads ; i++)
			threads.add(agregarThread(monitor,otroMonitor,tipo,funcion));
		
		return threads;
	}

	public void inicializarThreads(ArrayList<ConcurUser> users){

		if(users.get(0).IdUser().equals(1)){
			Integer threadsFaltantes = users.size();
			ArrayList<Integer> indexOfVector;
			indexOfVector = users.get(0).getMonitor().numerosHastaSize();
		
			for(ConcurUser t: users){
				indexOfVector = t.getMonitor().asignarRecorrido(t, indexOfVector, threadsFaltantes);
				threadsFaltantes--;
			}
		}
		
		for(ConcurUser t: users)
			t.start();
	}

	public void comenzarThreads(UsersType user, MonitorConcurDerivative monitorTest, 
												MonitorConcurDerivative otroMonitor, 
												Integer cantThreads, Integer funcion) {
		
		ArrayList<ConcurUser> threads = creacionDeThreads(
				user,monitorTest,null,5,2);
		inicializarThreads(threads);
	}
	
	/**##################################################################*/
}
