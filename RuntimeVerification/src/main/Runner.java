package main;

import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

class MyTask extends TimerTask { 
   public static WebsiteAPI api = new WebsiteAPI(); 
   public void run(){
	   try {
//			api.uploadToAPI();
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
//		JsonArray eventList;
//		int eventType;
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
	    
//		while(true){
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			try {
////				api.uploadToAPI();
//				api.getEventsLog();
//				api.getEventType();
////				eventList = api.getEventType();
////				for(int i = 0; i < eventList.size(); i++){
////			    	JsonObject jObj = eventList.get(i).getAsJsonObject();
////			    	eventType = jObj.get("eventLogType").getAsInt();
////			    	
////			    	switch(eventType){
////			    		case 0: api.createAlert();break;
////			    		case 1: api.deleteAlerts();break;
////			    		case 5: api.goodLogin();break;
////			    		case 6: api.logout();break;
////			    		case 7: api.viewAlerts();break;
////			    		default: System.out.println("Invalid Event Log");break;
////			    	}
////			    }
////				api.deleteFromAPI();
//			} catch (Exception e) {
//			// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		

	}

}
