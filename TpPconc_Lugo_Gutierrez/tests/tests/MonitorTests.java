package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import tpConcurrente.ConcurUser;
import tpConcurrente.MonitorConcurDerivative;

public class MonitorTests {

	
	public void creacionDeThreads(int cantThreads,int funcion,MonitorConcurDerivative monitor){
		
		for(int i = 0 ; i<cantThreads ; i++){
			ConcurUser thread = new ConcurUser(funcion,monitor);
			thread.start();
		}
	}
	@Test
	public void setConIndex() {
		
		MonitorConcurDerivative monitor = new MonitorConcurDerivative(10, 5);
		creacionDeThreads(5,2,monitor);
		assertEquals(monitor.dimension(),10);
		
		
	}

}
