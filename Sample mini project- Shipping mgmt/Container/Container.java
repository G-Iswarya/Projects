package Container;
import java.util.*;
public class Container {
	Scanner sc = new Scanner(System.in);
	int weight;
	static int remWeight = 1000;
	public static int getRemWeight() {
		return remWeight;
	}

	public  void setRemWeight(int remWeight) {
		this.remWeight = remWeight;
	}
	public int ID;
	
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Container() {
		super();
	}
	
	public Container(int weight) {
		this.weight = weight;
		this.ID = ID;
	}

	public Container(int ID, int weight)
	{
		this.ID = ID;
		this.weight = weight;
	}
	public void displaycont()
	{
		System.out.println("ID: "+ID+" weight: "+weight);
//		System.out.println(" weight: "+weight);
	}


	public static int load(int wt)
	{
		if(wt>remWeight)
		{
			System.out.println("Sorry, cannot load");
			return 0;
		}
		else
		{
			System.out.println("Container loaded!");
			remWeight -= wt;
			return 1;
		}
	}
	public void unload(int wt)
	{
			remWeight += wt;
			System.out.println("Container unloaded");
	}
//	public void contdisplay()
//	{
//		System.out.println("ID: "+ID+ " weight: "+weight);
//	}
}
