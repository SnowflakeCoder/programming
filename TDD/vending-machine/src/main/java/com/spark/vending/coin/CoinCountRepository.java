package com.spark.vending.coin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("coinCountRepository")
public interface CoinCountRepository extends JpaRepository<CoinCount, Long>{
	
	@Query("Select c from CoinCount c where c.coinType = ?1")
	CoinCount findCoinCountByType(String coinType);

	
}
