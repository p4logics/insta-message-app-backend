package com.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chat.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


	boolean existsBySenderOrReceiver(String userName, String userName2);

	List<Message> findAllBySenderOrReceiver(String userName, String userName2);

	@Query(value="select * from message_details where (sender = ?1 and receiver = ?2) or (sender = ?3 and receiver = ?4) order by id asc",nativeQuery = true)
	List<Message> findBySenderAndReceiverOrReceiverAndSender(String username, String username2, String username3,
			String username4);

}
