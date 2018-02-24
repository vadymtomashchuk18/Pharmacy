package com.pharmacy.entities;

import java.util.Date;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

@Tableable(title="Purchase")
public class Purchase {
	private int id;
	private Date date;
	private int pharmacy_id;
	private int prescr_id;
	private int id_patient;
	
	public Purchase(int id, Date date, int pharmacyId, int prescrId, int patientId){
		this.id = id;
		this.date = date;
		this.pharmacy_id = pharmacyId;
		this.prescr_id = prescrId;
		this.id_patient = patientId;
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

	@TableColumn(name="Prescription")
	public int getPrescriptionId() {
		return prescr_id;
	}

	@TableColumn(name="Patient")
	public int getPatientId() {
		return id_patient;
	}
}
