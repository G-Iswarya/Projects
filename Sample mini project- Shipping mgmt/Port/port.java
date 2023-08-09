package Port;
import java.util.*;
public class port {
	static List <port>porthistory = new ArrayList<port>();
	public String name; public int num;
	public port() {
		super();
	}
	public port(String name, int num)
	{
		this.name = name;
		this.num = num;
	}
	public void display()
	{
		System.out.println(name+" "+num);
	}
	public void history(String portname)
	{
		port p1 = new port(portname,num);
		porthistory.add(p1);
	}
	public void displayHistory()
	{
		for(int i=0;i<porthistory.size();i++)
		System.out.println("Port name:"+ porthistory.get(i).name+" Port ID:"+porthistory.get(i).num);
	}

}
