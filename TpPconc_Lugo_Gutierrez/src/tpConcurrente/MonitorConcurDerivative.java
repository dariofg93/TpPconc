package tpConcurrente;

import recursos.Barrier;
import recursos.Buffer;
import recursos.GeneratorThreads;
import recursos.Task;
import recursos.Functions.TipoDeFuncion;

public class MonitorConcurDerivative {

	private Integer threadsTotal; 		
	private double[] elements;			
	private Buffer buff;
	private Barrier barrera;
		
	public MonitorConcurDerivative(int size, Integer cantTotal) {
		elements = new double[size];
		threadsTotal = cantTotal;
		buff = new Buffer(1000,cantTotal);
		this.barrera = new Barrier(cantTotal+1);
		(new GeneratorThreads()).comenzarThreads(this);
	}
	
	/** Returns the dimension of this vector, that is, its width. */
	public int dimension() {
		return elements.length;
	}
	
	/** Returns the element at position i.
	 * @param i, the position of the element to be returned.
	 * @precondition 0 <= i < dimension(). */
	public Double get(int i) {
		return elements[i];	
	}

	/** Assigns the value d to the position i of this vector. 
	 * @param i, the position to be set.
	 * @param d, the value to assign at i.
	 * @precondition 0 <= i < dimension. */
	public void set(int i, double d){
		elements[i] = d;
	}
	
	/** Assigns the value d to every position of this vector. 
	 * @param d, the value to assigned. */
	public synchronized void set(double d) {
		distributte(new Task(TipoDeFuncion.SET,d));
		barrera.ready();
	}
	
	/** Copies the values from another vector into this vector.
	 * @param v, a vector from which values are to be copied.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void assign(MonitorConcurDerivative v) {
		distributte(new Task(TipoDeFuncion.ASSIGN,v));
		barrera.ready();
	}
	
	
	/** Applies the absolute value operation to every element in this vector. */
	public synchronized void abs() {
		distributte(new Task(TipoDeFuncion.ABS));
		barrera.ready();
	}
	
	
	/** Adds the elements of this vector with the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void add(MonitorConcurDerivative v) {
		distributte(new Task(TipoDeFuncion.ADD,v));
		barrera.ready();
	}
	
	
	/** Subtracts from the elements of this vector the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void sub(MonitorConcurDerivative v) {
		distributte(new Task(TipoDeFuncion.SUB,v));
		barrera.ready();
	}
	
	
	/** Multiplies the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void mul(MonitorConcurDerivative v) {
		distributte(new Task(TipoDeFuncion.MUL,v));
		barrera.ready();
	}
	
	
	/** Divides the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void div(MonitorConcurDerivative v) {
		distributte(new Task(TipoDeFuncion.DIV,v));
		barrera.ready();
	}
	
	public synchronized MonitorConcurDerivative differentiate() {
		MonitorConcurDerivative result = new MonitorConcurDerivative(dimension()-2,threadsTotal);
		distributte(new Task(TipoDeFuncion.DIFFERENTIATE,result));
		barrera.ready();
		
		return result;
	}
	
	//Devuelve el vector del monitor
	public double[] getVector() {
		return elements;
	}
	
	public Integer limiteDeThreads(){
		return threadsTotal;
	}
	
	public Buffer getBuff(){
		return buff;
	}

	private void distributte(Task tarea){
		buff.producir(tarea);
	}
	
	public void imprimirVector(){
		for(double e : elements)
			System.out.print(" "+e+" |||");
	}
	
	public Barrier getBarrera(){
		return barrera;
	}
}
