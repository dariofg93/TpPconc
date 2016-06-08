package threads;

import java.util.ArrayList;

import testsConcurrentes.MonitorTests.UsersType;
import tpConcurrente.MonitorConcurDerivative;

public class GeneratorThreads {
	
	//Crea una instancia de un thread(segun de que tipo sea)
	private ConcurUser agregarThread(MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor,
									UsersType tipo,Integer funcion,double...setORget) {
		if(tipo.ordinal()==0){
			return new SimpleUser(monitor,funcion,setORget);
		}else{
			return new ComplexUser(monitor,otroMonitor,funcion,setORget);
		}
	}
	
	//crea n instancias de threads de un tipo correspondiente
	private ArrayList<ConcurUser> creacionDeThreads(UsersType tipo,
			MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor,
			Integer cantThreads, Integer funcion, double...setORget){
		
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
		for(int i = 0 ; i<cantThreads ; i++)
			threads.add(agregarThread(monitor,otroMonitor,tipo,funcion,setORget));
		
		return threads;
	}

	//Dada una lista de threads, le da los valores necesarios finales(si
	// es necesario) y los inicializa
	private ArrayList<ConcurUser> inicializarThreads(ArrayList<ConcurUser> users,MonitorConcurDerivative monitor){

		Integer threadsFaltantes = users.size();
		ArrayList<Integer> indexOfVector;
		indexOfVector = monitor.numerosHastaSize();
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
	
		for(ConcurUser t: users){
			indexOfVector = monitor.asignarRecorrido(t, indexOfVector, threadsFaltantes);
			threadsFaltantes--;
			t.start();
			threads.add(t);
		}
		return threads;
	}

	//Metodo publico para la generacion de threads desde un test
	public ArrayList<ConcurUser> comenzarThreads(UsersType user, MonitorConcurDerivative monitor, 
															MonitorConcurDerivative otroMonitor, 
															Integer cantThreads, Integer funcion,
															double...setORget) {
		ArrayList<ConcurUser> threads = creacionDeThreads(
				user,monitor,otroMonitor,cantThreads,funcion,setORget);
		return inicializarThreads(threads,monitor);
	}
}
