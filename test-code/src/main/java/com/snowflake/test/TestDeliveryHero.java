package com.snowflake.test;

import java.util.PriorityQueue;

public class TestDeliveryHero {

//	public static void main(String[] args) {
//		List<Integer> list = Arrays.asList(-9, -18, 0, 25, 4); 
//		  
//        Optional<Integer> var = list.stream().min(Integer::compare); 
//  
//        if(var.isPresent()){
//        	int count =0;
//            while(var.isPresent()){ 
//            	count++;
//            	if(count == 1) {
//            		System.out.println(var.get());
//            	}
//            } 
//        }
//        else{ 
//            System.out.println("NULL"); 
//        } 
//	}
	
	public static void main(String[] args) {
		System.out.println(main1());
	}
	
	public static int main() 
    { 
        // Creating empty priority queue 
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(2); 
  
        // Adding items to the pQueue using add() 
        pQueue.add(10); 
        pQueue.add(20); 
        pQueue.add(15); 
        pQueue.add(35); 
        pQueue.add(65); 
        pQueue.add(5); 
        
        // Printing the top element and removing it 
        // from the PriorityQueue container 
        pQueue.poll();
        
        // Printing the top element again 
        return pQueue.peek(); 
    } 
	
	public static int main1() 
    { 
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		int a[] = new int[]{9,1,23,-4,10};
		int n=2;
		
    	for(int i = 0; i < a.length; i++){
        	queue.offer(a[i]);    
    	}
    	
    	for(int i=0; i <n; i++ ){
        	queue.poll();
    	}
   	 
    	return queue.peek();

    } 

}
