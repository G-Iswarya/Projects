import java.util.*;

import Container.BasicContainer;
import Container.Container;
import Container.HeavyContainer;
import Container.LiquidContainer;
import Container.RefrigeratedContainer;
import Port.port;
public class mainn {

	public static void main(String[] args) {
		//----------------create port--------------------------------
		
		List<port> portlist = new ArrayList<port>();
		List<Container> containerlist = new ArrayList<Container>();
		mainn m = new mainn();
		
		String[] ports = {"Chennai","Kochi","Mumbai","Dubai"};
		port p = new port();
		Container c = new Container();
        int [] portnum = {1,2,3,4};
        for(int i=0;i<4;i++)
        {
        	port n = new port(ports[i], portnum[i]);
        	portlist.add(n);
        }
        
        
        //----------------------------options-------------------------------
        
		
		Scanner sc = new Scanner(System.in);
		int portindex = -1,cost = 0;
		Container cont;
		System.out.println("Do you want to continue? Y/N");
		char yn = sc.next().charAt(0);
		while(yn == 'y' || yn == 'Y') {
			
			System.out.println("Enter 1 for user 2 for admin");
	        int auth = sc.nextInt();
			if(auth == 1)
	        {
	        	System.out.println("1. Reach port to load/unload");
	        }
	        else
	        {
			System.out.println("The options are:");
			System.out.println("1. Reach port to load/unload  // 2. Display history of ports visited");
			System.out.println("3. Display list of containers in ship  // 4. Display list of ports");
			System.out.println("5. Display remaining weight  // 6. New ship");
			System.out.println("7. Display ships");
	
	        }
			
		System.out.println("Enter your choice");
		int choice = sc.nextInt();
		switch(choice)
		{ 
		case 1:
	        while(true)
	        { portindex = -1; int contID,wt;
	        System.out.println("Enter the destination port: ");
	        String destPort = sc.next();
	        
	        for(int i=0;i<4;i++)
	        {
	        	if(portlist.get(i).name.equals(destPort))
	        	{
	        		portindex = i;
	        		portlist.get(portindex).history(portlist.get(portindex).name);
	        	}
	        }
	        if(portindex == -1)
	        {
	        	System.out.println("Invalid port name");
	        	break;
	        }
	        
	        System.out.println("Enter 1 to load 2 to unload");
	        int lu = sc.nextInt();
	        if(lu == 1)
	        {
	        	if(c.shipList.size() == 0)
	        	{
	        		System.out.println("No ships found. Admin have to allot a ship");
	        		c.admintask(auth);
	        		break;
	        		
	        		
	        	}
	        	else if(c.getShipCapacity() <= 0)
	        	{
//	        		System.out.println("Size full. Admin have to allot another ship");
//	        		c.admintask(auth);
	        		System.out.println("Ship is full. Cannot load containers");
	        		break;
	        	}
	        System.out.println("Enter the total number of containers");
	        int loading = sc.nextInt();
	        for(int i=0;i<loading;i++) {
			System.out.println("Enter the weight of container "+(i+1));
	        wt = sc.nextInt();
			if(wt<=300)
			{
				System.out.println("Enter container ID");
				contID = sc.nextInt();
//				cont = new BasicContainer(wt,contID);
				BasicContainer bc = new BasicContainer(wt,contID);
				bc.refuel(wt);
				cost+=bc.fuelcost(loading);
			}
			else
			{
				System.out.println("Enter container ID");
				contID = sc.nextInt();
				cont = new HeavyContainer(wt,contID);
				System.out.println("Enter the type of heavy container : R/L");
				char type = sc.next().charAt(0);
				if(type == 'L' || type == 'l')
				{
//					cont = new LiquidContainer(wt,contID);
					LiquidContainer lc = new LiquidContainer(wt,contID);
					lc.refuel(wt);
					cost+=lc.fuelcost(loading);
				}
				else if(type == 'R' || type == 'r')
				{
//					cont = new RefrigeratedContainer(wt,contID);
					RefrigeratedContainer rc = new RefrigeratedContainer(wt,contID);
					rc.refuel(wt);
					cost+=rc.fuelcost(loading);
				}
			}
			int bool = Container.load(wt); 
			if(bool == 1)
			{
		        Container c1 = new Container(contID, wt);
//				Container c1 = new Container(wt);
		        	containerlist.add(c1);
			}
	        }
	        }
	        
	        else
	        {
	        	System.out.println("Enter the container to unload");
				int unloadwt = sc.nextInt();
//				containerlist.remove(containerlist.indexOf(unloadwt));
				
				for(int i=0;i<containerlist.size();i++)
				{
					Container contt = containerlist.get(i);
			        if(contt.getID() == unloadwt)
			        	Container.unload(containerlist.get(i).getShipCapacity());
			            containerlist.remove(containerlist.get(i));
				}
	        }
	        }
	        System.out.println("Cost: "+cost);
	        if(cost!=0) {
	        System.out.println("Pay the charges");
	        while(sc.next().charAt(0)!='y')
	        {
	        	System.out.println("Please pay the amount...y/n?");
	        }
	        System.out.println("Payment successful");
	        cost = 0;
	        }
	        
			break;
			
		        
		case 2:
			if(auth == 1)
			{
				System.out.println("Restricted access to users");
				break;
			}
			p.displayHistory();
			break;
		case 3:
			if(auth == 1)
			{
				System.out.println("Restricted access to users");
				break;
			}
			if(containerlist.size() == 0)
			{
				System.out.println("No containers in ship");
				break;
			}
			for(int i=0;i<containerlist.size();i++)
			{
				containerlist.get(i).displaycont();
			}
			break;
		case 4:
			if(auth == 1)
			{
				System.out.println("Restricted access to users");
				break;
			}
			for(int i=0;i<4;i++)
	        {
	        	portlist.get(i).display();
	        }
			break;
		case 5:
			if(auth == 1)
			{
				System.out.println("Restricted access to users");
				break;
			}
			System.out.println(c.getShipCapacity());
			break;
		case 6:
			if(auth == 1)
			{
				System.out.println("Restricted access to users");
				break;
			}
			c.admintask(auth);
			break;
		case 7:
			if(auth == 1)
			{
				System.out.println("Restricted access to users");
				break;
			}
			if(c.shipList.size()==0)
			{
				System.out.println("No ship is added");
				break;
			}
			System.out.println(c.shipList);
			break;
		default:
			System.out.println("Invalid input");
			break;  
		}
		System.out.println("Do you want to continue? Y/N");
		yn = sc.next().charAt(0);
		}

	}

}
