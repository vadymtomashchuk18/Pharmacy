package com.pharmacy.entities;

import com.pharmacy.annotations.TableColumn;

public class Medcine {
	private int id;
	private String title;
	private String producer;
	private double boxPrice;
	private double quantityPerBox;
	
	public Medcine(int id, String title, String producer, double boxPrice, double quantityPerBox){
		this.id = id;
		this.title = title;
		this.producer = producer;
		this.boxPrice = boxPrice;
		this.quantityPerBox = quantityPerBox;
	}
	
	public Medcine setTitle(String newTitle){
		title = newTitle;
		return this;
	}	

	@TableColumn(name = "Title")
	public String getTitle(){
		return title;
	}
	
	@TableColumn(name = "producer")
	public String getProducer(){
		return producer;
	}
	
	public Medcine setProducer(String newProducer){
		producer = newProducer;
		return this;
	}

	@TableColumn(name = "box price")
	public double getBoxPrice(){
		return boxPrice;
	}
	
	public Medcine setBoxPrice(double newBoxPrice){
		boxPrice = newBoxPrice;
		return this;
	}

	@TableColumn(name = "quantity per box")
	public double getQuantityPerBox(){
		return quantityPerBox;
	}
	
	public Medcine setQuantityPerBox(double newQuantityPerBox){
		quantityPerBox = newQuantityPerBox;
		return this;
	}
	
	@TableColumn(name="id")
	public int getId(){
		return id;
	}
}
