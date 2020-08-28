package com.spark.vending.coin;

import org.springframework.stereotype.Service;

@Service
public class CoinCountService {

	private CoinCountRepository coinCountRepository;
	
	public CoinCountService(CoinCountRepository coinCountRepository) {
		this.coinCountRepository = coinCountRepository;
	}
	
	public CoinCount addCoinCount(CoinCount coinCount) {
		return coinCountRepository.save(coinCount);
	}
	
	public CoinCount getCoinCountByType(String coinType) {
		return coinCountRepository.findCoinCountByType(coinType);
	}
	
}
