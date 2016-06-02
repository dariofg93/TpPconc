package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import tpConcurrente.ConcurUser;
import tpConcurrente.MonitorConcurDerivative;

public class MonitorTests {

	
	public void creacionDeThreads(int cantThreads,int funcion,MonitorConcurDerivative monitor){
		
		ArrayList<Integer> totalIndex = monitor.numerosHasta();
		
		for(int i = 0 ; i<cantThreads ; i++){
			ConcurUser thread = new ConcurUser(funcion,monitor);
			for(int x = 0; x<2; x++)
				monitor.asignarUnLugarA(thread, i);
			thread.start();
		}
	}
	@Test
	public void setConIndex() {
		
		MonitorConcurDerivative monitor = new MonitorConcurDerivative(10, 5);
		creacionDeThreads(5,2,monitor);
		assertEquals(monitor.dimension(),10);
		
		
	}
	
	@Test
	public void add() {
		
		MonitorConcurDerivative monitor = new MonitorConcurDerivative(10, 5);
		//for(int i = 0 ; i<9 ; i++){
		//monitor.set(0,10);
		
		creacionDeThreads(5,1,monitor);
		/**for(int i = 0; i<10; i++){

			System.out.print(monitor.getVector()[i]);
		}*/
		
	}
}
