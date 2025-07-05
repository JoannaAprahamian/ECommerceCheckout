package model;

public abstract class product {
	public String name;
	public double price;
	//basic features of a product

	public abstract int getQuantity();
	public abstract void decreaseQuantity();
	//not an attribute so that we can make the attribute static for each product class
}
