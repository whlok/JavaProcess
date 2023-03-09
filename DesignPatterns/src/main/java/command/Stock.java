package command;

/**
 * @autor wenhoulai
 * Created on 2023-02-06
 */
public class Stock {
	private String name = "ABC";
	private int quantity = 10;

	public void buy(){
		System.out.println("Stock [name: "+name+", Quantity:"+ quantity +" ] bought");
	}

	public void sell(){
		System.out.println("Stock [name: "+name+", Quantity:"+ quantity +" ] sold");
	}
}
