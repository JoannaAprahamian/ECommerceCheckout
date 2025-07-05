package exceptions;

public class emptyCart extends Exception{
	
	public emptyCart(){
		super();
	}
	
	public emptyCart(String msg){
		super(msg);
	}
}
