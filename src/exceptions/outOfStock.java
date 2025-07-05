package exceptions;

public class outOfStock extends Exception{
	
	public outOfStock(){
		super();
	}
	
	public outOfStock(String msg){
		super(msg);
	}
}
