package testsConcurrentes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import threads.*;
import tpConcurrente.MonitorConcurDerivative;

public class MonitorTests {
	
	MonitorConcurDerivative monitorTest;

	private enum UsersType{SIMPLEUSER , COMPLEXUSER}

	/**############### Creacion e inicializacion de Threads ##############*/
	
	private ConcurUser agregarThread(Integer funcion, UsersType tipo,
			MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor) {
		
		if(tipo.ordinal()==0){
			return new SimpleUser(funcion,monitor);
		}else{
			return new ComplexUser(funcion,monitor,otroMonitor);
		}
	}
	
	private ArrayList<ConcurUser> creacionDeThreads(
			int cantThreads, UsersType tipo, Integer funcion, 
			MonitorConcurDerivative monitor, MonitorConcurDerivative otroMonitor){
		
		ArrayList<ConcurUser> threads = new ArrayList<ConcurUser>();
		for(int i = 0 ; i<cantThreads ; i++)
			//threads.add(new SimpleUser(funcion, monitor));
			threads.add(agregarThread(funcion,tipo,monitor,otroMonitor));
		
		return threads;
	}

	private void inicializarThreads(ArrayList<ConcurUser> users){

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
	
	/**##################################################################*/
	
	@Test
	public void setConIndex() {
		
		monitorTest = new MonitorConcurDerivative(10, 5);
		inicializarThreads(creacionDeThreads(5,UsersType.SIMPLEUSER,2,monitorTest,null));
		
		System.out.println(monitorTest.getVector()[3]);
		assertEquals(monitorTest.dimension(),10);
	}
	
	@Test
	public void add() {
		
		monitorTest = new MonitorConcurDerivative(10, 5);
		
		//monitorTest = new MonitorConcurDerivative(10, 5);
		//SimpleUser t = new SimpleUser(2,monitorTest);
		//inicializarThreads(creacionDeThreads(5,t));

		ArrayList<Integer> index = monitorTest.numerosHastaSize();
		System.out.print(index.get(1));
		/**for(int i = 0; i<10; i++){

			System.out.print(monitor.getVector()[i]+" Y ");
		}*/
	}
}
