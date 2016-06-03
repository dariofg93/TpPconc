package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tpConcurrente.ConcurDerivative;

public class TestConcurDerivative {
	
	ConcurDerivative derivative;
	
	@Before
	public void setUp(){
		
		derivative = new ConcurDerivative(10);
	}

	@Test
	public void testDimension() {
		
		assertEquals(derivative.dimension(),10);
	}
	
	@Test
	public void testSet1yGet() {
		
		derivative.set(1,2);
		derivative.set(2,3);
		
		assertEquals(derivative.get(1),2,0);
		assertEquals(derivative.get(2),3,0);
	}
	
	@Test
	public void testSet2() {
		
		derivative.set(5);
		
		for(int i = 0; i<9; i++){
			assertEquals(derivative.get(i),5,0);
		}
	}
	
		
}
