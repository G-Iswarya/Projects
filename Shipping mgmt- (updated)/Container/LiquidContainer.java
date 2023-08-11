package Container;

public class LiquidContainer extends HeavyContainer {
 protected int weight,ID,fuel;
	public LiquidContainer(int weight, int ID) {
		super(weight, ID);
		this.weight = weight;
		this.ID = ID;
		System.out.println("Liquid");
	}
	public void refuel(int weight)
	{
		fuel += weight*1.7;
		System.out.println("fuel: "+fuel);
	}
	public int fuelcost(int a)
	{
		return a*fuel;
	}

}
