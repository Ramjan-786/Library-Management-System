package com.lms.services;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dao.BookDao;
import com.lms.dao.BooksDao;
import com.lms.dao.RecordDao;
import com.lms.dtos.RecordsData;
import com.lms.entities.Records;
import com.lms.entities.User;

@Service
public class RecordServiceImpl {
	
	@Autowired
	private RecordDao recorddao;
	
	@Autowired
	private BooksDao booksDao;
	
	public Records findById(int id) {
		Records rec = recorddao.findById(id);
		return rec;
	}
	public List<Records> findallrecords(){
		return recorddao.findAll();
	}
	
	public Map<String, Object> saveRecord(Records rec) {
		
		rec.setIssuedate(new Date());
		
		Date returnDate = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(returnDate); 
		c.add(Calendar.DATE, 10);
		returnDate = c.getTime();
		
		rec.setReturndate(returnDate);
		rec.setStatus("notreturned");
		
		
		Records result = recorddao.save(rec);
		
		//Get existing book copies
		String booksCopies = this.booksDao.getBooksCopies(rec.getBookid());
		
		int currentBooksCopies = Integer.parseInt(booksCopies) - 1;
		
		//update current books copies
		int bookCopies = this.booksDao.updateBooksCopies(String.valueOf(currentBooksCopies), rec.getBookid());
		return Collections.singletonMap("Book has been Issued having Id ", rec.getBookid());
	}
	
	public List<Records> findbyUserId(int userid) {
		List<Records> rec = recorddao.findAllById(userid);
		return rec;
	}

	public List<RecordsData> getAllRecordsByUserId(int userId) {
		
		List<RecordsData> recordsData = this.recorddao.getRecordsbyUserId(userId);
		return recordsData;
	}
	
	
	//Working
	//return book from user
		public Map<String, Object> returnbooks(Records rec) {

			Records oldData = this.findById(rec.getId());
			Records newData = oldData;
			
			if(!oldData.equals(null)) {			
				newData.setStatus("returned");
			}
			
			//Saving Data
			this.recorddao.save(newData);
			String booksCopies = this.booksDao.getBooksCopies(rec.getBookid());
			
			int currentBooksCopies = Integer.parseInt(booksCopies) + 1;
			
			//update current books copies
			int bookCopies = this.booksDao.updateBooksCopies(String.valueOf(currentBooksCopies), rec.getBookid());
			return Collections.singletonMap("Book has been returned having Id ", rec.getBookid());
		}
		
		public List<Records> findbystatus(String status) {
			List<Records> rec = recorddao.findByStatus(status);
			return rec;
		}
		
		


	
	
	
}
