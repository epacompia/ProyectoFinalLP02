package com.proyectofinal.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int user_id;
	
	@Column(name = "firstname", length = 30, nullable = false)
	private String firstname;
	
	@Column(name = "secondname", length = 30, nullable = false)
	private String secondname;
	
	@Column(name = "surname1", length = 30, nullable = false)
	private String surname1;
	
	@Column(name = "surname2", length = 30, nullable = false)
	private String surname2;
	
	@ManyToOne
	@JoinColumn(name = "document_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_document_type"))
	private DocumentType documentType_id;
	
	@Column(name = "numdocument", nullable=false)
	private String numDocument;
	
	@Column(name = "email", length = 50, nullable=false)
	private String email;
	
	@Column(name = "password", nullable=false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "rol_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_rol"))
	private Rol rol_id;
	
	private boolean flag;

	@Column(name = "create_at", updatable = false)
	private LocalDateTime create_at;
	
	@Column(name = "update_at")
	private LocalDateTime update_at;
	
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public DocumentType getDocumentType_id() {
		return documentType_id;
	}

	public void setDocumentType_id(DocumentType documentType_id) {
		this.documentType_id = documentType_id;
	}

	public String getNumDocument() {
		return numDocument;
	}

	public void setNumDocument(String numDocument) {
		this.numDocument = numDocument;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol_id() {
		return rol_id;
	}

	public void setRol_id(Rol rol_id) {
		this.rol_id = rol_id;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public LocalDateTime getCreate_at() {
		return create_at;
	}

	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}

	public LocalDateTime getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}
	


}
