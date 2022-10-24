package practice.commandPattern.impl;

import practice.commandPattern.Order;
import practice.commandPattern.Stock;

/**
 * @author wenhoulai
 */
public class SellStock implements Order {

	private Stock abcStock;

	public SellStock(Stock stock) {
		this.abcStock = stock;
	}

	@Override
	public void execute() {
		abcStock.sell();
	}
}
