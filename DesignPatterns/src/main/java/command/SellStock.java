package command;

/**
 * @autor wenhoulai
 * Created on 2023-02-06
 */
public class SellStock implements Order{

	private Stock abcStock;
	public SellStock(Stock stock){
		this.abcStock = stock;
	}

	@Override
	public void execute() {
		abcStock.sell();
	}
}
