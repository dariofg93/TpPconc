package threads;

import java.util.ArrayList;

import testsConcurrentes.MonitorTests.UsersType;
import tpConcurrente.MonitorConcurDerivative;

public class GeneratorThreads {
	
	//Crea una instancia de un thread(segun de que tipo sea)
	private ConcurUser agregarThread(MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor,
									UsersType tipo,Integer funcion,Integer...setORget) {
		if(tipo.ordinal()==0){
			return new SimpleUser(monitor,funcion,setORget);
		}else{
			return new ComplexUser(monitor,otroMonitor,funcion,setORget);
		}
	}
	
	//crea n instancias de threads de un tipo correspondiente
	private ArrayList<ConcurUser> creacionDeThreads(UsersType tipo,
			MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor,
			Integer cantThreads, Integer funcion, Integer...setORget){
		
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
		for(int i = 0 ; i<cantThreads ; i++)
			threads.add(agregarThread(monitor,otroMonitor,tipo,funcion,setORget));
		
		return threads;
	}

	//Dada una lista de threads, le da los valores necesarios finales(si
	// es necesario) y los inicializa
	private void inicializarThreads(ArrayList<ConcurUser> users){

		Integer threadsFaltantes = users.size();
		ArrayList<Integer> indexOfVector;
		indexOfVector = users.get(0).getMonitor().numerosHastaSize();
	
		for(ConcurUser t: users){
			indexOfVector = t.getMonitor().asignarRecorrido(t, indexOfVector, threadsFaltantes);
			threadsFaltantes--;
			t.start();
		}
	}

	//Metodo publico para la generacion de threads desde un test
	public void comenzarThreads(UsersType user, MonitorConcurDerivative monitor, 
												MonitorConcurDerivative otroMonitor, 
												Integer cantThreads, Integer funcion,
												Integer...setORget) {
		
		ArrayList<ConcurUser> threads = creacionDeThreads(
				user,monitor,otroMonitor,cantThreads,funcion,setORget);
		inicializarThreads(threads);
	}
}
