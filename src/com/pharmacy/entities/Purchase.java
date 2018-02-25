package com.pharmacy.entities;

import java.util.Date;
import java.util.List;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

@Tableable(title="Purchase")
public class Purchase {
	private int id;
	private Date date;
	private int pharmacy_id;
	private int prescr_id;
	private String patient;
	private List<PurchaseRecord> purchaseList;
	
	public Purchase(int id, Date date, int pharmacyId, int prescrId, String patient, List<PurchaseRecord> purchaseList){
		this.id = id;
		this.date = date;
		this.pharmacy_id = pharmacyId;
		this.prescr_id = prescrId;
		this.patient = patient;
		this.purchaseList = purchaseList;
	}
	
	@TableColumn(name="Id")
	public int getId() {
		return id;
	}
	
	@TableColumn(name="Date")
	public Date getDate() {
		return date;
	}
	
	@TableColumn(name="Pharmacy")
	public int getPharmacyId() {
		return pharmacy_id;
	}

	public int getPrescriptionId() {
		return prescr_id;
	}
	
	@TableColumn(name="Patient")
	public String getPatient() {
		return patient;
	}

	public List<PurchaseRecord> getPurchaseList() {
		return purchaseList;
	}
}
