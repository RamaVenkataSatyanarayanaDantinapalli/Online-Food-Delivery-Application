package com.cg.fds.exceptions;

public class OrderNotFoundException extends Exception {

	
	private String errorMsg;
	private String actionType;
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	public OrderNotFoundException()
	{
		setErrorMsg("OrderId Not Available");
		setActionType("Order should Added");
	}
	
	

}
