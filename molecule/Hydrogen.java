//package molecule;

public class Hydrogen extends Thread {

	private static int carbonCounter =0;
	private int id;
	private Methane sharedMethane;
	
	
	public Hydrogen(Methane methane_obj) {
		Hydrogen.carbonCounter+=1;
		id=carbonCounter;
		this.sharedMethane = methane_obj;
		
	}
	
	
	// method run is modified below
	
	public void run() {
	    try {
	    	 // you will need to fix below
	    	 sharedMethane.mutex.acquire();
	    	 sharedMethane.addHydrogen();
	    	 
	    	 if(sharedMethane.getHydrogen()>=4 && sharedMethane.getCarbon()>=1){  // if statement used to check if have right amount of hydrogen and carbon before bonding can occur
				 	   System.out.println("---Group ready for bonding---");	   
				 	   
				 	   // ensure protection when dealin with hydrogen and carbon bonding to prevent any bad interleavings/deadlocks or bonding
				 	   
				 	   // sharedMethane.hydrogensQ.release(4);
				 	   sharedMethane.hydrogensQ.release();
				 	   sharedMethane.hydrogensQ.release();
				 	   sharedMethane.hydrogensQ.release();
				 	   sharedMethane.hydrogensQ.release();
				 	   
				 	   
				 	    sharedMethane.removeHydrogen(4);
				 	   

				 	    sharedMethane.carbonQ.release();
				 	    sharedMethane.removeCarbon(1);		 
	    	            //
				 }else{
					 sharedMethane.mutex.release();  //release mutex lock
					 }
				sharedMethane.hydrogensQ.acquire();   // acquire semaphore
					 
	    	   sharedMethane.bond("H"+ this.id);
	    	    sharedMethane.barrier.b_wait();
	    	 

	    }
	   catch (InterruptedException ex) { /* not handling this  */}
	    //System.out.println(" ");
	}

}
