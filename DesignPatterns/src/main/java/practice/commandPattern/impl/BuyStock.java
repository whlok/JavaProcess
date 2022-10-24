package practice.commandPattern.impl;

import practice.commandPattern.Order;
import practice.commandPattern.Stock;

/**
 * @author wenhoulai
 */
public class BuyStock implements Order {

	private Stock abcStock;

	public BuyStock(Stock stock) {
		this.abcStock = stock;
	}

	@Override
	public void execute() {
		abcStock.buy();
	}
}
