package tpConcurrente;

import java.util.ArrayList;

import threads.ConcurUser;
import recursos.Barrier;
import recursos.GeneratorThreads;
import recursos.Task;
import recursos.Functions.TipoDeFuncion;

public class MonitorConcurDerivative {

	private Integer threadsTotal; 		
	private double[] elements;			
	private ArrayList<ConcurUser> workers;
	private Barrier barrera;
	
	public MonitorConcurDerivative(int size, Integer cantTotal) {
		elements = new double[size];
		threadsTotal = cantTotal;
		workers = (new GeneratorThreads()).comenzarThreads(this);
		barrera = new Barrier(size-1);
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
	
	public void set(double d) {
		distributte(new Task(TipoDeFuncion.SET,d));
		barrera.ready();
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
}
