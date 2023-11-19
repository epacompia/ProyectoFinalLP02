package com.proyectofinal.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;


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
	private DocumentType document_type_id;
	
	@Column(name = "num_document", nullable=false)
	private String num_document;
	
	@Column(name = "email", length = 50, nullable=false)
	private String email;
	
	@Column(name = "password", nullable=false)
	private String password;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "rol_id", nullable = false, foreignKey = @ForeignKey(name
	 * = "fk_user_rol")) private List<Rol> rol_id;
	 */
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "rol_id"))
	private List<Rol> rol_id;
	
	private boolean flag;

	@Column(name = "create_at", updatable = false)
	private LocalDateTime create_at;
	
	@Column(name = "update_at", nullable = true)
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
	
	public DocumentType getDocument_type_id() {
		return document_type_id;
	}

	public void setDocument_type_id(DocumentType document_type_id) {
		this.document_type_id = document_type_id;
	}
	
	public String getNum_document() {
		return num_document;
	}

	public void setNum_document(String num_document) {
		this.num_document = num_document;
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

	public List<Rol> getRol_id() {
		return rol_id;
	}

	public void setRol_id(List<Rol> rol_id) {
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

	public String getDisplayName(){
		return getFirstname() + " " + getSurname1();
	}


}
