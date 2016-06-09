package threads;

import java.util.ArrayList;
import java.util.List;

import testsConcurrentes.MonitorTests.UsersType;
import tpConcurrente.Funciones.TipoDeFuncion;
import tpConcurrente.MonitorConcurDerivative;

public class GeneratorThreads {
	
	//Crea una instancia de un thread(segun de que tipo sea)
	private ConcurUser agregarThread(MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor,
									UsersType tipo,TipoDeFuncion funcion,double...setORget) {
		if(tipo.ordinal()==0){
			return new SimpleUser(monitor,funcion,setORget);
		}
		return new ComplexUser(monitor,otroMonitor,funcion,setORget);
	}
	
	//crea n instancias de threads de un tipo correspondiente
	private ArrayList<ConcurUser> creacionDeThreads(UsersType tipo,
			MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor,
			Integer cantThreads, TipoDeFuncion funcion, double...setORget){
		
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
		for(int i = 0 ; i<cantThreads ; i++)
			threads.add(agregarThread(monitor,otroMonitor,tipo,funcion,setORget));
		
		return threads;
	}

	//Dada una lista de threads, le da los valores necesarios finales y los inicializa
	private ArrayList<ConcurUser> inicializarThreads(ArrayList<ConcurUser> users,MonitorConcurDerivative monitor){

		ArrayList<Integer> indexOfVector;
		indexOfVector = numerosHastaSize(monitor);
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
		List<Integer> restantes = numerosHastaSize(monitor).subList((monitor.getVector().length) - 
																		((monitor.getVector().length % users.size())+1)
																		, monitor.getVector().length);
	
		for(ConcurUser t: users){
			indexOfVector = asignarRecorrido(t, indexOfVector,monitor);
			if(!restantes.isEmpty()){
				t.añadirAlRecorrido(restantes.get(0));
			    restantes.remove(0);
			}
			threads.add(t);
			t.start();
		}
		return threads;
	}

	//Prop: Asigno un recorrido a un thread con un size correspondiente a
	// la cantidad de elementos que debe recorrer. Devuelve el 
	// recorrido sobrante
	private ArrayList<Integer> asignarRecorrido(ConcurUser user, ArrayList<Integer> rec,
											MonitorConcurDerivative monitor) {
		ArrayList<Integer> recCortado = rec;
		Integer i = (monitor.getVector().length / monitor.limiteDeThreads());// + 
		//			(rec.size() % threadsFaltantes);
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
															Integer cantThreads, TipoDeFuncion funcion,
															double...setORget) {
		ArrayList<ConcurUser> threads = creacionDeThreads(
				user,monitor,otroMonitor,cantThreads,funcion,setORget);
		return inicializarThreads(threads,monitor);
	}
}
