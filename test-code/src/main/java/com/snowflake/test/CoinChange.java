package com.snowflake.test;

public class CoinChange {

	public static void main(String[] args) {
//		System.out.println(change(10, new int[] { 1, 2, 5 }));

//		System.out.println(change(20, new int[] { 1, 2, 5, 30 }));
		
		System.out.println(minCoin(11, new int[] { 1, 2, 5}));


		// System.out.println(change(3, new int[] { 2 }));
		// System.out.println(change(10, new int[] { 10 }));

		// System.out.println(change(500, new int[] { 3,5,7,8,9,10,11 }));
	}

	private static int change(int amount, int[] coins) {

		int ways[] = new int[amount + 1];
		ways[0] = 1;

		for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
			for (int index = 0; index <= amount; index++) {
				if (index < coins[coinIndex]) {
					continue;
				}
				ways[index] = ways[index - coins[coinIndex]] + ways[index];
			}
		}

		return ways[amount];

	}

	private static int minCoin(int amount, int[] coins) {
		int ways[] = new int[amount + 1];

		for (int index = 1; index <= amount; index++) {
			ways[index] = -1;
		}

		for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
			for (int index = 0; index <= amount; index++) {
				if (index < coins[coinIndex]) {
					continue;
				}
				if(ways[index - coins[coinIndex]] != -1) {
					if(ways[index] == -1) {
						ways[index] = ways[index - coins[coinIndex]] + 1;					
					}else {
						ways[index] = Math.min(ways[index - coins[coinIndex]] + 1, ways[index]);					
					}
				}
			}
		}

		return ways[amount];
	}

}
