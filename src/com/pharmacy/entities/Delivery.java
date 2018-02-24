package com.pharmacy.entities;

import java.util.Date;
import java.util.List;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

@Tableable(title="pharmacy")
public class Delivery {
	private int id;
	private int pharmacy_id;
	private Date date;
	private List<DeliveryRecord> deliveryRecords;
	
	public Delivery(int id, int pharmacyId, Date date, List<DeliveryRecord> deliveryRecords){
		this.id = id;
		this.pharmacy_id = pharmacyId;
		this.date = date;
		this.deliveryRecords = deliveryRecords;
	}
	
	public List<DeliveryRecord> getDeliveryRecords() {
		return deliveryRecords;
	}

	@TableColumn(name = "Date")
	public Date getDate() {
		return date;
	}

	@TableColumn(name = "pharmacy Id")
	public int getPharmacy_id() {
		return pharmacy_id;
	}

	@TableColumn(name = "Id")
	public int getId() {
		return id;
	}
}
