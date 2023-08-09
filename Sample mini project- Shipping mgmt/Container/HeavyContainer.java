package Container;

public class HeavyContainer extends Container {
	protected int weight;
	public HeavyContainer(int weight, int ID)
	{
		super(ID,weight);
		this.weight = weight;
		System.out.println("Heavy");
	}
	void refuel(int fuel)
	{
		fuel += fuel*1.3;
	}
}
