package dario;

public class probandoTP {

	public static void main(String[] args) {
		Concurrencia vector = new Concurrencia(10,5);
		
		for(int i = 0 ; i<5 ; i++){
			ConcurUser thread = new ConcurUser(2,vector);
			thread.start();
		}
		print(vector);
	}

	public static void print(Concurrencia pConc){
		for(int i = 0; i < pConc.getVector().length; i++)
			System.out.println(pConc.getVector()[i]+"  "+(i+1));
	}
}