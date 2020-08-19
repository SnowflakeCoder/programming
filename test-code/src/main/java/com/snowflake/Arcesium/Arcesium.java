package com.snowflake.Arcesium;

import java.util.List;

public class Arcesium {

	public static void main(String[] args) {

	}
	
	class HoldingPage{
	    int totalRecords;
	    int recordsPerPage;
	    int page;
	    String nextPage;
	    List<Holding> data;
	    
	    
	    
		public int getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(int totalRecords) {
			this.totalRecords = totalRecords;
		}
		public int getRecordsPerPage() {
			return recordsPerPage;
		}
		public void setRecordsPerPage(int recordsPerPage) {
			this.recordsPerPage = recordsPerPage;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public String getNextPage() {
			return nextPage;
		}
		public void setNextPage(String nextPage) {
			this.nextPage = nextPage;
		}
		public List<Holding> getData() {
			return data;
		}
		public void setData(List<Holding> data) {
			this.data = data;
		}

	    

	    
	}
	
	public class Holding {
		   private final String date;
		   private final String security;
		   private final Integer quantity;
		   private final String portfolio;

		   public Holding(String date, String security, Integer quantity, String portfolio) {
		       this.date = date;
		       this.security = security;
		       this.quantity = quantity;
		       this.portfolio = portfolio;
		   }

		   public String getDate() {
		       return date;
		   }

		   public String getSecurity() {
		       return security;
		   }

		   public Integer getQuantity() {
		       return quantity;
		   }

		   public String getPortfolio() {
		       return portfolio;
		   }
		}

}
