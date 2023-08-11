package Container;

public class BasicContainer extends Container{
	protected int weight,fuel=100;
	public BasicContainer(int weight, int ID)
	{
		super(ID,weight);
		this.weight = weight;
		System.out.println("Basic");
	}
	public void refuel(int weight)
	{
		fuel += weight*0.3;
		System.out.println("Fuel: "+fuel);
	}
	public int fuelcost(int a)
	{
		return a*fuel;
	}
}
