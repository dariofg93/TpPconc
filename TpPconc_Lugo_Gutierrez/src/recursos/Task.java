package recursos;

import recursos.Functions.TipoDeFuncion;
import tpConcurrente.MonitorConcurDerivative;

public class Task {

	private TipoDeFuncion funcion;
	private MonitorConcurDerivative monitor;
	private double num;
	
	/** :::::::::::::::: Constructores :::::::::::::::::: */
	public Task(TipoDeFuncion f){
		this.funcion = f;
	}
	
	public Task(TipoDeFuncion f, MonitorConcurDerivative m){
		this.funcion = f;
		this.monitor = m;
	}
	
	public Task(TipoDeFuncion f, double n){
		this.funcion = f;
		this.num = n;
	}
	/** ::::::::::::::::::::::::::::::::::::::::::::::::: */
	
	//getters
	public TipoDeFuncion getFuncion() {
		return funcion;
	}

	public MonitorConcurDerivative getMonitor() {
		return monitor;
	}

	public double getNum() {
		return num;
	}
}
