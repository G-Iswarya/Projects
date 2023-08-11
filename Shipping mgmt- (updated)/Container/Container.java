package Container;
import java.util.*;
abstract class Cont
{
	abstract void displaycont();
}
public class Container extends Cont {
	Scanner sc = new Scanner(System.in);
	int weight, fuel = 1000; 
	
	 private int ID, shipID; static int shipCapacity = 1000;
	
	
	
	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public int getShipCapacity() {
		return shipCapacity;
	}


	public void setShipCapacity(int shipCapacity) {
		this.shipCapacity = shipCapacity;
	}
	public List<Integer> shipList = new ArrayList<Integer>();
	public void admintask(int auth)
	{
		if(auth == 1)
		{
			System.out.println("Only admin can allot ships");
		}
		else
		{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ship ID");
		shipID = sc.nextInt();
		shipList.add(shipID);
		System.out.println("Enter ship capacity");
		shipCapacity = sc.nextInt();
		}
	}


	public Container() {
		super();
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
		if(wt>shipCapacity)
		{
			System.out.println("Sorry, cannot load");
			return 0;
		}
		else
		{
			System.out.println("Container loaded!");
			shipCapacity -= wt;
//			System.out.println(shipCapacity);
			return 1;
		}
	}
	public static void unload(int wt)
	{
			shipCapacity += wt;
			System.out.println("Container unloaded");
	}
}
