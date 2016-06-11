package tpConcurrente;

import java.util.ArrayList;

import threads.ConcurUser;
import recursos.Barrier;
import recursos.GeneratorThreads;
import recursos.Task;

public class MonitorConcurDerivative {

	private Integer threadsTotal; 		
	private double[] elements;			
	private ArrayList<ConcurUser> workers;
	private Barrier barrera;
	
	public MonitorConcurDerivative(int size, Integer cantTotal) {
		elements = new double[size];
		threadsTotal = cantTotal;
		workers = (new GeneratorThreads()).comenzarThreads(this);
		barrera = new Barrier(size+1);
	}
	
	public int dimension() {
		return elements.length;
	}
	
	public Double get(int lugar) {
		return elements[lugar];	
	}

	public void set(int lugar,double valor){
		elements[lugar] = valor;
	}
	
	public void set(int i) {
		
	}

	public void add(MonitorConcurDerivative monitor){
		
	}
	
	//Devuelve el vector del monitor(si no se usa en ningun lado se borrará)
	public double[] getVector() {
		return elements;
	}
	
	public Integer limiteDeThreads(){
		return threadsTotal;
	}

	private void distributte(Task tarea){
		for(ConcurUser w : workers)
			w.agregarTarea(tarea);
	}
		
	public void imprimirVector(){
		for(double e : elements)
			System.out.print(" "+e+" |||");
	}
}
