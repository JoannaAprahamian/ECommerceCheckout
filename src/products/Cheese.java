package products;

import model.expires;
import model.product;
import model.shipping;

public class Cheese extends product implements shipping, expires{
	public static int quantity = 10;
	
	public Cheese(){
		name = "Cheese";
		price = 100;
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
		return 200;
	}

	@Override
	public String getName() {
		return name;
	}

	
	
}
