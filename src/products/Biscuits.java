package products;

import model.expires;
import model.product;
import model.shipping;

public class Biscuits extends product implements shipping, expires{
	public static int quantity = 10; 
	
	public Biscuits(){
		name = "Biscuits";
		price = 150;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void decreaseQuantity() {
		quantity--;
	}
	
	@Override
	public boolean isExpired() {
		return false;
	}

	@Override
	public double getWeight() {
		return 700;
	}

	@Override
	public String getName() {
		return name;
	}

}
