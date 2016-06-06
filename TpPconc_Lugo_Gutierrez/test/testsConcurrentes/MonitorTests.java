package testsConcurrentes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import tpConcurrente.ConcurUser;
import tpConcurrente.MonitorConcurDerivative;

public class MonitorTests {

	/**############### Creacion e inicializacion de Threads ##############*/
	
	private ArrayList<ConcurUser> creacionDeThreads(
			int cantThreads,int funcion,MonitorConcurDerivative monitor){
		
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
		for(int i = 0 ; i<cantThreads ; i++)
			threads.add(new ConcurUser(funcion,monitor));
		
		return threads;
	}
	
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
	
	/**##################################################################*/
	
	@Test
	public void setConIndex() {
		
		MonitorConcurDerivative monitor = new MonitorConcurDerivative(10, 5);
		inicializarThreads(creacionDeThreads(5,2, monitor));
		
		System.out.println(monitor.getVector()[3]);
		assertEquals(monitor.dimension(),10);
	}
	
	@Test
	public void add() {
		
		MonitorConcurDerivative monitor = new MonitorConcurDerivative(10, 5);
		
		inicializarThreads(creacionDeThreads(5,1, monitor));

		ArrayList<Integer> index = monitor.numerosHastaSize();
		System.out.print(index.get(1));
		/**for(int i = 0; i<10; i++){

			System.out.print(monitor.getVector()[i]+" Y ");
		}*/
	}
}
