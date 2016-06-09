package testsConcurrentes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

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
	public void testGet() {
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,
				5,3,0,42);
		ArrayList<ConcurUser> threads = 
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,5,2,0);
		
		for(ConcurUser t: threads)
			System.out.println(t.getVariable());
		
		assertEquals(monitorTest.dimension(),10);
	}
	
	@Test
	public void testSetConIndex() {
		
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,
												5,3,4,50);

		System.out.println("+"+monitorTest.getVector()[4]+"+");
		assertEquals(monitorTest.dimension(),10);
	}
	
	@Test
	public void testSet() {
		
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,
												5,4,25.9);
		
		for(double d : monitorTest.getVector())
			System.out.print(" "+d+" |||");
		assertEquals(monitorTest.dimension(),10);
	}
}
