package testsConcurrentes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import threads.*;
import tpConcurrente.MonitorConcurDerivative;

public class MonitorTests {
	
	public enum UsersType{SIMPLEUSER , COMPLEXUSER}
	
	MonitorConcurDerivative monitorTest;
	GeneratorThreads generador;
	
	@Before
	public void setUp(){
		monitorTest = new MonitorConcurDerivative(10, 5);
		generador = new GeneratorThreads();
	}
	
	@Test
	public void testSetConIndex() {

		monitorTest.set(5);
		assertTrue(monitorTest.get(9)==5);
	}
	
	@Test
	public void testSet() {

		for(double d : monitorTest.getVector())
			System.out.print(" "+d+" |||");
		assertEquals(monitorTest.dimension(),10);
	}
}
