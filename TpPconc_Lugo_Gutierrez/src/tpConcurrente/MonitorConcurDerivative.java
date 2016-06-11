package tpConcurrente;

import java.util.ArrayList;

import threads.ConcurUser;
import recursos.GeneratorThreads;
import recursos.Task;
import recursos.Functions.TipoDeFuncion;

public class MonitorConcurDerivative {

	private Integer threadsTotal; 		
	private double[] elements;			
	private ArrayList<ConcurUser> workers;
	
	public MonitorConcurDerivative(int size, Integer cantTotal) {
		elements = new double[size];
		threadsTotal = cantTotal;
		workers = (new GeneratorThreads()).comenzarThreads(this);
		for(ConcurUser w : workers)
			w.start();
	}
	
	public int dimension() {
		return elements.length;
	}
	
	public Double get(int i) {
		return elements[i];	
	}

	public void set(int i, double d){
		elements[i] = d;
	}
	
	public synchronized void set(double d) {
		distributte(new Task(TipoDeFuncion.SET,d));
		for(int i = 0; i<threadsTotal; i++)
			this.notify();
	}
	
	//Devuelve el vector del monitor
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
	
	public void imprimirRecorridos(){
		for(ConcurUser w: workers)
			w.imprimirRecorrido();
	}
}
