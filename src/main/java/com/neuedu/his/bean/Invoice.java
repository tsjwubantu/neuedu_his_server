package com.neuedu.his.bean;

/**
 *  发票对象 Bean
 *  
 */
public class Invoice {
	
	private String id;          
	private String registId;      
	private String invoiceNum;    
	private String money;     
	private String state;       
	private String creationTime;  
	private String userId;        
	private String expenseId;    
	private String settleId;    
	private String back;         
	private String dailyState;   
   
    
    public Invoice() {
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getRegistId() {
		return registId;
	}


	public void setRegistId(String registId) {
		this.registId = registId;
	}


	public String getInvoiceNum() {
		return invoiceNum;
	}


	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}


	public String getMoney() {
		return money;
	}


	public void setMoney(String money) {
		this.money = money;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getExpenseId() {
		return expenseId;
	}


	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}


	public String getSettleId() {
		return settleId;
	}


	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}


	public String getBack() {
		return back;
	}


	public void setBack(String back) {
		this.back = back;
	}


	public String getDailyState() {
		return dailyState;
	}


	public void setDailyState(String dailyState) {
		this.dailyState = dailyState;
	}
}
