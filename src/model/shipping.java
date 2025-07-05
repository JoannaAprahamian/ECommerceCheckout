package model;

public interface shipping {
	double rate = 15; //assuming (from console example) shipping fee is constant at 15 for every started kilogram
	double getWeight();
	String getName();
	//a product that requires shipping will implement this interfaces
}

