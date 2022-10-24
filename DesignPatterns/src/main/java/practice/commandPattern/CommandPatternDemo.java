package practice.commandPattern;

import practice.commandPattern.impl.BuyStock;
import practice.commandPattern.impl.SellStock;

/**
 * @author wenhoulai
 */
public class CommandPatternDemo {
	public static void main(String[] args) {
		Stock abcStock = new Stock();

		BuyStock buyStock = new BuyStock(abcStock);
		SellStock sellStock = new SellStock(abcStock);

		Broker broker = new Broker();
		broker.takeOrder(buyStock);
		broker.takeOrder(sellStock);

		broker.placeOrders();
	}
}
