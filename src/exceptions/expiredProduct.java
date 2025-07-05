package exceptions;

public class expiredProduct extends Exception{
	
	public expiredProduct(){
		super();
	}
	
	public expiredProduct(String msg){
		super(msg);
	}
}
