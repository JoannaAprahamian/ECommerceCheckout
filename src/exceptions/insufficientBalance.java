package exceptions;

public class insufficientBalance extends Exception{

	public insufficientBalance(){
		super();
	}
	
	public insufficientBalance(String msg){
		super(msg);
	}
}
