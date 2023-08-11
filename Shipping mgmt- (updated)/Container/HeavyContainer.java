package Container;

public class HeavyContainer extends Container {
	protected int weight,fuel = 100;
	public HeavyContainer(int weight, int ID)
	{
		super(ID,weight);
		this.weight = weight;
		System.out.println("Heavy");
	}
	public void refuel(int fuel)
	{
		fuel += weight*1.3;
		System.out.println("fuel: "+fuel);
	}
	public int fuelcost(int a)
	{
		return a*fuel;
	}
}
