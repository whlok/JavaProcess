package coffee;

/**
 * 首先，必须让Condiment Decorator 能够取代Beverage；
 * 所以将Condiment Decorator 扩展自Beverage类；
 *
 * @author wenhoulai
 */
public abstract class CondimentDecorator extends Beverage {
	// 所有的调料装饰者都必须重新实现getDescription()方法
	public abstract String getDescription();
}
