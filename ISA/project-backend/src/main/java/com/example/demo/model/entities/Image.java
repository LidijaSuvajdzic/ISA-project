package com.example.demo.model.entities;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name="image")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;
	@Column(name="filePath", unique=false, nullable=false)
	private String filePath;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "entity_id")
	@JsonIgnoreProperties("image")
	private EntityClass entity;

	public Image(int id, String filePath, EntityClass entity) {
		super();
		this.id = id;
		this.filePath = filePath;
		this.entity = entity;
	}

	public Image() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EntityClass getEntity() {
		return entity;
	}

	public void setEntity(EntityClass entity) {
		this.entity = entity;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
