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
		monitorTest = new MonitorConcurDerivative(10, 5);
		generador = new GeneratorThreads();
	}
	
	@Test
	public void setConIndex() {
		
		generador.comenzarThreads(UsersType.SIMPLEUSER,monitorTest,null,5,2);
		
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
