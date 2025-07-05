package model;

import java.util.ArrayList;

import products.Biscuits;
import products.Cheese;
//exceptions
import exceptions.emptyCart;
import exceptions.expiredProduct;
import exceptions.insufficientBalance;
import exceptions.outOfStock;

public class cart {
	ArrayList<product> list; 
	ArrayList<shipping> toShipSer; //list of items that require shipping
	double total; //total of products' prices
	boolean anyExpired; //flag for expired items
	String receipt; //receipt updated with each added product

	public cart(){
		list = new ArrayList<product>(); //anything added to cart
		toShipSer = new ArrayList<shipping>(); //list of any products that implement shipping interface
		total = 0;
		anyExpired = false; //flag in case any are expired
		receipt = ""; //string to print formatted receipt in the end
	}
	
	public void add(product p, int quantity) throws outOfStock{
		if(quantity > p.getQuantity()) //check if stock satisfies needed quantity
	        throw new outOfStock("Requested quantity exceeds available stock.");
		for(int i = 0; i<quantity; i++){
			total += p.price; 
			p.decreaseQuantity();
			if (p instanceof expires && ((expires) p).isExpired() == true)
				anyExpired = true; //raise flag if expired
			if (p instanceof shipping)
				toShipSer.add((shipping)p); 
			list.add(p);
		}
		if (quantity>0) //number of items added to receipt must be at least 1
			receipt += String.format("%-15s %5.0f\n", quantity + "x " + p.name, p.price * quantity);
	}
	
	public static void checkout(customer customer,cart cart) throws Exception{
		if (cart.list.isEmpty())
			throw new emptyCart("Your cart is empty; there's nothing to checkout");
		if (customer.balance<cart.total)
			throw new insufficientBalance("The total amount to be paid exceeds your balance");
		//if any are out of stock, an exception is thrown when it is added to the cart
		if (cart.anyExpired)
			throw new expiredProduct("A product in your cart is expired");		
		
		double shippingWeight = 0;
		if(cart.toShipSer.isEmpty() == false)
			shippingWeight = shippingService(cart.toShipSer); //sets value of shipping weight to get shipping fee AND prints shipment notice
		System.out.println();
		System.out.println("** Checkout receipt **");
		System.out.print(cart.receipt);
		System.out.println("--------------------");
		System.out.println(String.format("%-15s %5.0f", "Subtotal", cart.total));
		System.out.println(String.format("%-15s %5.0f", "Shipping", shippingWeight*shipping.rate)); 
		double finalAmount = cart.total + shippingWeight*shipping.rate;
		System.out.println(String.format("%-15s %5.0f", "Amount", finalAmount));           		
		//print remaining amount in balance
		customer.balance = customer.balance - finalAmount;
		System.out.println("\nThe remaining amount in your balance is: " + (customer.balance)); 
	}
	

	
	public static double shippingService(ArrayList<shipping> list){
		System.out.println("** Shipment notice **");
		shipping p = (shipping) list.get(0);
		list.remove(0);
		int i = 1;
		double total=0;
		while(list.isEmpty() == false){//count occurrences of the product in the list
			if (((shipping)list.get(0)).getName().equals(p.getName())){
				i++;
				list.remove(0);
			}
			else{
				System.out.println(String.format("%-15s %5.0f", i + "x " + ((shipping)p).getName(), ((shipping)p).getWeight()*i) + "g");
				total = total + i*((shipping)p).getWeight(); //updates totalWeight
				i = 1;
				p = (shipping) list.get(0); //reset counter and move to the next product type
				list.remove(0);
			}
		}
		System.out.println(String.format("%-15s %5.0f", i + "x " + ((shipping)p).getName(), ((shipping)p).getWeight()*i) + "g");
		total = total + i*((shipping)p).getWeight(); //prints and updates totalWeight for the last product in the list
		total = total/1000;
		System.out.println("Total package weight " + total + "kg");
		double roundedUp = Math.ceil(total); // Use to get the shipping fee (assumed to be rate * weight rounded up)
		return roundedUp;
	}
	
	public static void main(String[] args) throws Exception{
		customer c = new customer(5000);
		Cheese cheese = new Cheese();
		Biscuits biscuits = new Biscuits();
		cart cart = new cart();
		cart.add(cheese, 2);
		cart.add(biscuits, 1);
		checkout(c, cart);
		/*Cheese ch = new Cheese();
		System.out.print(ch.getQuantity());*/
	}
	
}
