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
        
		System.out.println("The options are:");
		System.out.println("1. Reach port to load/unload");
		System.out.println("2. Display history of ports visited");
		System.out.println("3. Display list of containers in ship");
		System.out.println("4. Display list of ports");
		System.out.println("5. Display remaining weight");
		
		Scanner sc = new Scanner(System.in);
		int portindex = -1;
		Container cont;
		System.out.println("Do you want to continue? Y/N");
		char yn = sc.next().charAt(0);
		while(yn == 'y' || yn == 'Y') {
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
	        System.out.println("Enter the total number of containers");
	        int loading = sc.nextInt();
	        for(int i=0;i<loading;i++) {
			System.out.println("Enter the weight of container "+(i+1));
	        wt = sc.nextInt();
			if(wt<=300)
			{
				System.out.println("Enter container ID");
				contID = sc.nextInt();
				cont = new BasicContainer(wt,contID);
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
					cont = new LiquidContainer(wt,contID);
				}
				else if(type == 'R' || type == 'r')
				{
					cont = new RefrigeratedContainer(wt,contID);
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
				c.unload(unloadwt);
//				containerlist.remove(containerlist.indexOf(unloadwt));
				
				for(int i=0;i<containerlist.size();i++)
				{
					Container contt = containerlist.get(i);
			        if(contt.ID == unloadwt)
			            containerlist.remove(containerlist.get(i));
				}
	        }
	        }
	        
			break;
			
		        
		case 2:
			p.displayHistory();
			break;
		case 3:
			for(int i=0;i<containerlist.size();i++)
			{
				containerlist.get(i).displaycont();
			}
			break;
		case 4:
			for(int i=0;i<4;i++)
	        {
	        	portlist.get(i).display();
	        }
			break;
		case 5:
			System.out.println(Container.getRemWeight());
		default:
			break;  
		}
		System.out.println("Do you want to continue? Y/N");
		yn = sc.next().charAt(0);
		}

	}

}
