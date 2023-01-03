package main;

import java.util.*;

class MyTask extends TimerTask { 
   public static WebsiteAPI api = new WebsiteAPI(); 
   public void run(){
	   try {
			api.getEventsLog();
			api.getEventType();
		} catch (Exception e) {
			e.printStackTrace();
		}
      
   } 
} 

public class Runner {

	public static void main(String[] args) {
		WebsiteAPI api = new WebsiteAPI();

		Timer timer = new Timer(); 
	    TimerTask task = new MyTask(); 

	    timer.schedule(task, 0, 5000);
	    
	    int input;
	    Scanner sc= new Scanner(System.in);
	    while(true){
	    	System.out.println("1. Create Alert");
	    	System.out.println("2. Delete All Alerts");
	    	System.out.print("Enter choice: ");  
	    	input = sc.nextInt();  
	    	
	    	try{
	    		switch(input){
	    			case 1: api.uploadToAPI();break;
	    			case 2: api.deleteFromAPI();break;
	    			default: System.out.println("Invalid choice");break;
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	}

}
