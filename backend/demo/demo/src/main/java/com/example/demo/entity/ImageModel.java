package com.example.demo.entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="file")
public class ImageModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String type;
	
	@Column(length = 500000000)
	private byte[] picbyte;

	public ImageModel(Integer id, String name, String type, byte[] picbyte) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.picbyte = picbyte;
	}
	
	

	public ImageModel(String name, String type, byte[] picbyte) {
		super();
		this.name = name;
		this.type = type;
		this.picbyte = picbyte;
	}



	public ImageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicbyte() {
		return picbyte;
	}

	public void setPicbyte(byte[] picbyte) {
		this.picbyte = picbyte;
	}

	@Override
	public String toString() {
		return "ImageModel [id=" + id + ", name=" + name + ", type=" + type + ", picbyte=" + Arrays.toString(picbyte)
				+ "]";
	}
	
	

}
