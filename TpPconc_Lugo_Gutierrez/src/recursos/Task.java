package recursos;

import recursos.Functions.TipoDeFuncion;

public class Task {

	private TipoDeFuncion funcion;
	private Object primario;
	private Object segundario;
	
	public Task(TipoDeFuncion f, Object p){
		this.funcion = f;
		this.primario = p;
	}
	
	public Task(TipoDeFuncion f, Object p, Object s){
		this.funcion = f;
		this.primario = p;
		this.segundario = s;
	}
	
	public TipoDeFuncion getFuncion() {
		return funcion;
	}

	public Object getPrimario() {
		return primario;
	}

	public Object getSegundario() {
		return segundario;
	}
}
