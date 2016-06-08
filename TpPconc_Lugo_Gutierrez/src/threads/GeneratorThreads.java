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
		}
		return new ComplexUser(monitor,otroMonitor,funcion,setORget);
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
		indexOfVector = numerosHastaSize(monitor);
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
	
		for(ConcurUser t: users){
			indexOfVector = asignarRecorrido(t, indexOfVector, threadsFaltantes,monitor);
			threadsFaltantes--;
			t.start();
			threads.add(t);
		}
		return threads;
	}
	
/**##################   Asigna Recorrido   ##########################*/

	//Prop: Asigno un recorrido a un thread con un size correspondiente a
	// la cantidad de elementos que debe recorrer. Devuelve el 
	// recorrido sobrante
	private ArrayList<Integer> asignarRecorrido(ConcurUser user, ArrayList<Integer> rec,
												Integer threadsFaltantes,MonitorConcurDerivative monitor) {
		ArrayList<Integer> recCortado = rec;
		Integer i = (monitor.getVector().length / monitor.limiteDeThreads()) + 
					(rec.size() % threadsFaltantes);
		while(i>0){
			user.añadirAlRecorrido(recCortado.get(0));
			recCortado.remove(0);
			i--;
		}
		return recCortado;
	}
	
	//Devuelve una lista desde 0 hasta el size-1 del vector del monitor
	private ArrayList<Integer> numerosHastaSize(MonitorConcurDerivative monitor){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0 ; i < monitor.getVector().length; i++)
			list.add(i);
		return list;
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
