package Container;

public class BasicContainer extends Container{
	protected int weight;
	public BasicContainer(int weight, int ID)
	{
		super(ID,weight);
		this.weight = weight;
		System.out.println("Basic");
	}
	void refuel(int fuel)
	{
		fuel += fuel*0.3;
	}
}
