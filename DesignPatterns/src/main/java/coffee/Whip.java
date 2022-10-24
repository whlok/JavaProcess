package coffee;

/**
 * @author wenhoulai
 */
public class Whip extends CondimentDecorator {
	Beverage beverage;

	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		return .10;
	}

	@Override
	public String getDescription() {
		return beverage.description + ", Whip";
	}
}
