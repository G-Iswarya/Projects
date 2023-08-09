package Container;

public class LiquidContainer extends HeavyContainer {
 protected int weight;
	public LiquidContainer(int weight, int ID) {
		super(weight, ID);
		System.out.println("Liquid");
	}
	void refuel(int fuel)
	{
		fuel += fuel*1.7;
	}

}
