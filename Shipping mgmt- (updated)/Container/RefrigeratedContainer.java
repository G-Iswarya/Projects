package Container;

public class RefrigeratedContainer extends HeavyContainer {
	public int ID,weight,fuel;
	public RefrigeratedContainer(int weight, int ID) {
		super(weight, ID);
		this.weight = weight;
		this.ID = ID;
		System.out.println("Refrigerated");
	}
	public void refuel(int weight)
	{
		fuel += weight*2;
		System.out.println("fuel: "+fuel);
	}
	public int fuelcost(int a)
	{
		return a*fuel;
	}

}
