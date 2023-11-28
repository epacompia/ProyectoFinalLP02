package com.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepo extends JpaRepository<User, Integer>{
	//Consulta para mi login
	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);

	@Query(value= "select u.user_id, u.firstname, u.secondname, u.surname1, u.surname2, u.document_type_id, u.num_document, u.email, u.password, u.create_at,  u.update_at, u.flag from users u \n" +
			"inner join usuario_rol ur \n" +
			"on u.user_id = ur.user_id\n" +
			"inner join rol r\n" +
			"on ur.rol_id = r.rol_id\n" +
			"where r.rol_id = 3 and u.flag = 1", nativeQuery = true)
	List<User> getSupport();
}
