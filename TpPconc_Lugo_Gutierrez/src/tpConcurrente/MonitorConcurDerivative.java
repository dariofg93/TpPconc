package tpConcurrente;

import recursos.Barrier;
import recursos.Buffer;
import recursos.GeneratorThreads;
import recursos.Task;
import recursos.Functions.TipoDeFuncion;

public class MonitorConcurDerivative {

	private Integer threadsTotal; 		
	private double[] elements;			
	//private ArrayList<ConcurUser> workers;
	private Buffer buff;
	private Barrier barrera;
		
	public MonitorConcurDerivative(int size, Integer cantTotal) {
		elements = new double[size];
		threadsTotal = cantTotal;
		buff = new Buffer(1000);
		this.barrera = new Barrier(cantTotal+1);
		/**workers = */(new GeneratorThreads()).comenzarThreads(this);
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
		barrera.ready();
	}
	
	/** Copies the values from another vector into this vector.
	 * @param v, a vector from which values are to be copied.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void assign(MonitorConcurDerivative v) {
		distributte(new Task(TipoDeFuncion.ASSIGN,v));
	}
	
	
	/** Applies the absolute value operation to every element in this vector. */
	public synchronized void abs() {
		distributte(new Task(TipoDeFuncion.ABS));
	}
	
	
	/** Adds the elements of this vector with the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void add(MonitorConcurDerivative v) {
		distributte(new Task(TipoDeFuncion.ADD));
	}
	
	
	/** Subtracts from the elements of this vector the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void sub(MonitorConcurDerivative v) {
		distributte(new Task(TipoDeFuncion.SUB));
	}
	
	
	/** Multiplies the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void mul(MonitorConcurDerivative v) {
		distributte(new Task(TipoDeFuncion.MUL));
	}
	
	
	/** Divides the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void div(MonitorConcurDerivative v) {
		distributte(new Task(TipoDeFuncion.DIV));
	}
	
	public synchronized MonitorConcurDerivative differentiate() {
		MonitorConcurDerivative result = new MonitorConcurDerivative(dimension()-2,threadsTotal);
		distributte(new Task(TipoDeFuncion.DIFFERENTIATE,result));
		
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
