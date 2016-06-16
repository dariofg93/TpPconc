package testsConcurrentes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tpConcurrente.MonitorConcurDerivative;

public class MonitorTests {
		
	MonitorConcurDerivative derivative;
	MonitorConcurDerivative derivative2;
	
	@Before
	public void setUp(){
		
		derivative = new MonitorConcurDerivative(10,5);
		derivative2 = new MonitorConcurDerivative(10,5);
		
		for(int i = 0; i<10; i++){
			derivative2.set(i,i+1);
		}
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
		
		for(int i = 0; i<10; i++){
			assertEquals(derivative.get(i),5,0);
		}
	}

	
	@Test
	public void testAssign() {
		
		derivative.assign(derivative2);
	
		for(int i = 0; i<10; i++){
			assertEquals(derivative.get(i),i+1,0);
		}
		
	}
	
	@Test
	public void testAbs() {
		
		derivative.set(0,-1);
		derivative.set(5,-6);
		derivative.set(9,-10);
		
		derivative.abs();
	
		assertTrue(derivative.get(0).equals(1.0));
		assertTrue(derivative.get(5).equals(6.0));
		assertTrue(derivative.get(9).equals(10.0));
	}
	
	@Test
	public void testAdd() {

		derivative.assign(derivative2);
		derivative.add(derivative2);
		
		assertTrue(derivative.get(0).equals(2.0));
		assertTrue(derivative.get(1).equals(4.0));
		assertTrue(derivative.get(2).equals(6.0));
		assertTrue(derivative.get(3).equals(8.0));
		assertTrue(derivative.get(4).equals(10.0));
		assertTrue(derivative.get(5).equals(12.0));
		assertTrue(derivative.get(6).equals(14.0));
		assertTrue(derivative.get(7).equals(16.0));
		assertTrue(derivative.get(8).equals(18.0));
		assertTrue(derivative.get(9).equals(20.0));
		
		
	}
	
	@Test
	public void testSub() {
		
		derivative.assign(derivative2);
		derivative.add(derivative2);
		
		derivative.sub(derivative2);
		
		assertEquals(derivative.get(0),1,0.01);
		assertEquals(derivative.get(1),2,0.01);
		assertEquals(derivative.get(2),3,0.01);
		assertEquals(derivative.get(3),4,0.01);
		assertEquals(derivative.get(4),5,0.01);
		assertEquals(derivative.get(5),6,0.01);
		assertEquals(derivative.get(6),7,0.01);
		assertEquals(derivative.get(7),8,0.01);
		assertEquals(derivative.get(8),9,0.01);
		assertEquals(derivative.get(9),10,0.01);
	}
	
	@Test
	public void testMul() {
		
		derivative.assign(derivative2);
		derivative.mul(derivative2);


		assertEquals(derivative.get(0),1,0);
		assertEquals(derivative.get(1),4,0);
		assertEquals(derivative.get(2),9,0);
		assertEquals(derivative.get(3),16,0);
		assertEquals(derivative.get(4),25,0);
		assertEquals(derivative.get(5),36,0);
		assertEquals(derivative.get(6),49,0);
		assertEquals(derivative.get(7),64,0);
		assertEquals(derivative.get(8),81,0);
		assertEquals(derivative.get(9),100,0);
	}
	

	@Test
	public void testDiv() {
	
		derivative.assign(derivative2);
		derivative.add(derivative2);
		derivative.div(derivative2);
		
		assertEquals(derivative.get(0),2,0);
		assertEquals(derivative.get(1),2,0);
		assertEquals(derivative.get(2),2,0);
		assertEquals(derivative.get(3),2,0);
		assertEquals(derivative.get(4),2,0);
		assertEquals(derivative.get(5),2,0);
		assertEquals(derivative.get(6),2,0);
		assertEquals(derivative.get(7),2,0);
		assertEquals(derivative.get(8),2,0);
		assertEquals(derivative.get(9),2,0);
	}
	
	@Test
	public void testDiferenttiate() {
				 
		MonitorConcurDerivative derivativeRes = derivative2.differentiate();

		assertTrue(derivativeRes.get(0)==1);
		assertTrue(derivativeRes.get(1)==1);
		assertTrue(derivativeRes.get(2)==1);
		assertTrue(derivativeRes.get(3)==1);
		assertTrue(derivativeRes.get(4)==1);
		assertTrue(derivativeRes.get(5)==1);
		assertTrue(derivativeRes.get(6)==1);
		assertTrue(derivativeRes.get(7)==1);
	}
}
