package Container;

public class RefrigeratedContainer extends HeavyContainer {

	public RefrigeratedContainer(int weight, int ID) {
		super(weight, ID);
		System.out.println("Refrigerated");
	}
	void refuel(int fuel)
	{
		fuel += fuel*2;
	}

}
