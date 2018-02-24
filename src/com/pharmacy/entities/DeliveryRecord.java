package com.pharmacy.entities;

import com.pharmacy.annotations.*;

@Tableable(title="Delivery Record")
public class DeliveryRecord {
	private int deliveryId;
	private int medcineId;
	private int quantity;
	
	public DeliveryRecord(int deliveryId, int medcineId, int quantity){
		this.deliveryId = deliveryId;
		this.medcineId = medcineId;
		this.quantity = quantity;
	}
	
	public int getDeliveryId() {
		return deliveryId;
	}
	
	@TableColumn(name="Quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@TableColumn(name="Medcine")
	public int getMedcineId() {
		return medcineId;
	} 
}
