package com.dev.enterprise.entities.enums;

public enum OrderStatus {
	CONFIRMED(1),
	WAITING_PAYMENT(2),
	PAYD(3),
	SENT(4),
	DELIVERED(5),
	CANCELED(6);

	private Integer num;
	
	private OrderStatus(Integer num) {
		this.num = num;
	}
	
	public Integer getNum() {
		return num;
	}
	
	public static OrderStatus valueOf(Integer num) { //compara se o valor inserido é compativel com algum tipo de enum
		for(OrderStatus value: OrderStatus.values()) {
			if(value.getNum() == num) {
				return value;
			}
		}
		
		throw new IllegalArgumentException("Invalid Code of Status.");
	}
	
}
	
	
