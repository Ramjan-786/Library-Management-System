package com.lms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.dtos.RecordsData;
import com.lms.entities.Records;

public interface RecordDao extends JpaRepository<Records, Integer> {
	public Records findById(int id);
	
	public List<Records> findByStatus(String status);

	public List<Records> findAllById(int userid);
	
	@Query(value = "select r.id, r.userid, r.issuedate, r.returndate, r.bookid,r.status, b.bookname "
			+ "from records r inner join book b on r.bookid = b.id where r.userid =:userId "
			+ "order by issuedate desc", nativeQuery = true)
	public List<RecordsData> getRecordsbyUserId(@Param("userId") int userId);

}
