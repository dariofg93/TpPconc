package testsConcurrentes;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		monitorTest = new MonitorConcurDerivative(20, 10);
		generador = new GeneratorThreads();
	}
	
	@Test
	public void testGet() {
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,
				10,3,15,42);
		ArrayList<ConcurUser> threads = 
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,10,2,15);
		
		for(ConcurUser t: threads)
			System.out.println(t.getVariable());
		
		assertEquals(monitorTest.dimension(),20);
	}
	
	@Test
	public void testSetConIndex() {
		
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,
												10,3,5,50);

		System.out.println(monitorTest.getVector()[5]);
		assertEquals(monitorTest.dimension(),20);
	}
	
	@Test
	public void testSet() {
		
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,
												10,4,5,50);

		System.out.println(monitorTest.getVector()[5]);
		assertEquals(monitorTest.dimension(),20);
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
