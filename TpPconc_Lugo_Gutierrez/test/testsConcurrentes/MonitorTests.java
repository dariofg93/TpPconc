package testsConcurrentes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import threads.*;
import tpConcurrente.MonitorConcurDerivative;

public class MonitorTests {
		
	MonitorConcurDerivative monitorTest;
	GeneratorThreads generador;
	
	@Before
	public void setUp(){
		monitorTest = new MonitorConcurDerivative(10, 5);
		generador = new GeneratorThreads();
	}
	
	@Test
	public void testSetConIndex() {
		
		monitorTest.set(5,9);
		assertTrue(monitorTest.get(5)==9);
	}
	
	@Test
	public void testSet() {

		monitorTest.set(6);
		monitorTest.imprimirVector();
		assertEquals(monitorTest.dimension(),10);
	}
}
