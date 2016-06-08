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
		monitorTest = new MonitorConcurDerivative(20, 4);
		generador = new GeneratorThreads();
	}
	
	@Test
	public void testGet() {
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,
				4,3,15,42);
		ArrayList<ConcurUser> threads = 
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,4,2,15);
		
		for(ConcurUser t: threads)
			System.out.println(t.getVariable());
		
		assertEquals(monitorTest.dimension(),20);
	}
	
	@Test
	public void testSetConIndex() {
		
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,
												4,3,5,50);

		System.out.println(monitorTest.getVector()[5]);
		assertEquals(monitorTest.dimension(),20);
	}
	
	@Test
	public void testSet() {
		
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,
												4,4,5,50);

		
		assertEquals(monitorTest.dimension(),20);
	}
}
