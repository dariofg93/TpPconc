package recursos;

import java.util.ArrayList;
import java.util.List;

import threads.ConcurUser;
import tpConcurrente.MonitorConcurDerivative;

public class GeneratorThreads {
	
	//crea n instancias de threads de un tipo correspondiente
	private ArrayList<ConcurUser> creacionDeThreads(MonitorConcurDerivative monitor){
		
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
		for(int i = 0 ; i<monitor.limiteDeThreads(); i++)
			threads.add(new ConcurUser(monitor,monitor.getBuff()));
		
		return threads;
	}
	
	private List<Integer> indexSobrantes(int vectorSize, int cantThreads, MonitorConcurDerivative monitor) {
		
		
		if((vectorSize % cantThreads)==0){
		return numerosHastaSize(monitor).subList((
					vectorSize-(vectorSize % cantThreads)), vectorSize);
		}
		return numerosHastaSize(monitor).subList((
				vectorSize-(vectorSize % cantThreads)+1), vectorSize);
	}
	
	//Prop: Asigno un recorrido a un thread con un size correspondiente a
		// la cantidad de elementos que debe recorrer. Devuelve el 
		// recorrido sobrante
		private ArrayList<Integer> asignarRecorrido(ConcurUser user, ArrayList<Integer> rec,
												MonitorConcurDerivative monitor) {
			ArrayList<Integer> recCortado = rec;
			Integer i = monitor.getVector().length / monitor.limiteDeThreads();
			
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

	//Dada una lista de threads, le da los valores necesarios finales y los inicializa
	private ArrayList<ConcurUser> inicializarThreads(ArrayList<ConcurUser> users,MonitorConcurDerivative monitor){

		ArrayList<Integer> indexOfVector;
		indexOfVector = numerosHastaSize(monitor);
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
		List<Integer> restantes = indexSobrantes(monitor.getVector().length,
												 users.size(),monitor);
	
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

	//Metodo publico para la generacion de threads desde un test
	public ArrayList<ConcurUser> comenzarThreads(MonitorConcurDerivative monitor)
	{
		ArrayList<ConcurUser> threads = creacionDeThreads(monitor);
		return inicializarThreads(threads,monitor);
	}
}
