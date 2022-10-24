package coffee;

/**
 * @author wenhoulai
 */
public class Soy extends CondimentDecorator {

	Beverage beverage;

	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		return .15;
	}

	@Override
	public String getDescription() {
		return beverage.description + ", Soy";
	}
}
