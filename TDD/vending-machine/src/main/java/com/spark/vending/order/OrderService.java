package com.spark.vending.order;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	public ProductOrder saveOrder(ProductOrder order) {
		
		/*
		 * 
		 * Sort(Desc => coin enum values) => {25, 10, 5, 1} 
		 * 
		 * BalanceAmount = AcceptedAmount - (ProductPrice * count)
		 * 
		 * int[] cointCount = new int[enum values.length] => [1,1,0,0] => 
		 * 
		 * int tempBalanceAmount = BalanceAmount;
		 * 
		 * int enumIndex = 0;
		 * 
		 * for(each Enum :  Enum Values)
		 * {
		 * 
		 * while(balanceAmount >= Enum.value)
		 * {
		 * 		coinCount[enumIndex] ++;
		 * 		tempBalanceAmount = tempBalanceAmount - Enum.value;
		 * }
		 * enumIndex++;
		 * 
		 * }
		 * 
		 * 
		 */
		
		
		return null;
	}
}
