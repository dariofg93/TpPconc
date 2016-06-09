package tpConcurrente;

import testsConcurrentes.MonitorTests.UsersType;
import threads.GeneratorThreads;
import tpConcurrente.Funciones.TipoDeFuncion;

public class MonitorConcurDerivative {

	private Integer threadsTotal; 		//la cantidad maxima de threads a utilizar
	private double[] elements;			//el vector que se recorre de ser necesario
	private GeneratorThreads generador; 
	
	public MonitorConcurDerivative(int size, Integer cantTotal) {
		elements = new double[size];
		threadsTotal = cantTotal;
		generador = new GeneratorThreads();
	}
	
	public int dimension() {
		return elements.length;
	}
	
	public synchronized Double get(int lugar) {
		return elements[lugar];	
	}

	//creo que ya esta bien.. lastima que los try lo deja tan feo al codigo...
	public void set(int lugar,double valor){
		elements[lugar] = valor;
	}
	
	public void set(int i) {
		generador.comenzarThreads(UsersType.SIMPLEUSER, this, null, threadsTotal,TipoDeFuncion.SET,i);
	}

	public void add(MonitorConcurDerivative monitor){
		
	}
	
	//Devuelve el vector del monitor(si no se usa en ningun lado se borrará)
	public double[] getVector() {
		return elements;
	}

	public Integer limiteDeThreads() {
		return threadsTotal;
	}
}
