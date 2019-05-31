//package molecule;

public class Carbon extends Thread {
	
	private static int carbonCounter =0;
	private int id;
	private Methane sharedMethane;
	
	public Carbon(Methane methane_obj) {
		Carbon.carbonCounter+=1;
		id=carbonCounter;
		this.sharedMethane = methane_obj;
	}
	
	public void run() {
	    try {	 
	    	 // you will need to fix below
	    	 sharedMethane.mutex.acquire();
	    	//	
	         sharedMethane.addCarbon();
	         
	         if(sharedMethane.getHydrogen()>=4){  // check if right amount of hydrogen is available
				 
				 //prevent deadlock from occuring by using semaphore
				 System.out.println("---Group ready for bonding---");
				 //sharedMethane.hydrogensQ.release(4);
				 sharedMethane.hydrogensQ.release();
				 sharedMethane.hydrogensQ.release();
				 sharedMethane.hydrogensQ.release();
				 sharedMethane.hydrogensQ.release();
				 
				 sharedMethane.removeHydrogen(4);

				 
				// sharedMethane.carbonQ.acquire();
				 sharedMethane.removeCarbon(1);
				 
				 }else{
					 sharedMethane.mutex.release();  // release mutex 
					 }
	        sharedMethane.carbonQ.acquire();  
	        
	    	sharedMethane.bond("C"+ this.id);  //bond
	    	sharedMethane.barrier.b_wait();
	    	sharedMethane.mutex.release(); //release mutex 
	    	 
	    	   	 
	    }
	    catch (InterruptedException ex) { /* not handling this  */}
	   // System.out.println(" ");
	}

}
