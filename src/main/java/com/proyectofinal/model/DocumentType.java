package com.proyectofinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "document_type")
public class DocumentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_type_id")
	private int document_type_id;
	
	@Column(name = "name_doc", length = 50)
	private String name_doc;
	
	private boolean flag;
	

	public int getDocument_type_id() {
		return document_type_id;
	}

	public void setDocument_type_id(int document_type_id) {
		this.document_type_id = document_type_id;
	}

	public String getName_doc() {
		return name_doc;
	}

	public void setName_doc(String name_doc) {
		this.name_doc = name_doc;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
