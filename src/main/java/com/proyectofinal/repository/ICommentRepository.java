package com.proyectofinal.repository;

import com.proyectofinal.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Integer> {

    @Query(value = "select * from comments where ticket_id = (:idTicket)", nativeQuery = true)
    List<Comment> findByTicketId(@Param("idTicket") int idTIcket);

}
