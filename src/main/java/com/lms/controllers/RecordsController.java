package com.lms.controllers;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dtos.RecordsData;
import com.lms.dtos.Response;
import com.lms.entities.Book;
import com.lms.entities.Records;
import com.lms.services.RecordServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/record")
public class RecordsController {
	
	@Autowired
	private RecordServiceImpl recordServiceImpl;
	
	@GetMapping("/showallrecords")
	public ResponseEntity<?> findallrecords(){
		List<Records> rec = recordServiceImpl.findallrecords();
		return Response.success(rec);
	}

	@PostMapping("/bookIssue")
	public ResponseEntity<?> bookIssue(@RequestBody Records records) {
		Map<String, Object> result = recordServiceImpl.saveRecord(records);
		return Response.success(result);
	}

	@GetMapping("/getAllRecordsByUserId/{userId}")
	public ResponseEntity<?> getAllRecordsByUserId(@PathVariable("userId") int userId){
		List<RecordsData> result = recordServiceImpl.getAllRecordsByUserId(userId);
		return Response.success(result);
	}
	/*
	@PutMapping("/returnbook")
	public ResponseEntity<?> returnbook(@RequestBody Records rec){
		Map<String, Object> result = recordServiceImpl.returnbook(rec);
		return Response.success(result);
	}
	*/
	//update book
		@PutMapping("/returnBook")
		public ResponseEntity<?> updateBook(@RequestBody Records rec) {
			Map<String, Object> result = recordServiceImpl.returnbooks(rec);

			if(result.toString().contains("Failed")) {
				return Response.error(result);
			}

			return Response.success(result);
		}
		
		//find all issued and returned books
		@GetMapping("/records/{status}")
		public ResponseEntity<?> findBookByStatus(@PathVariable("status") String status){
			List<Records> allrecordsofType = recordServiceImpl.findbystatus(status);
			return Response.success(allrecordsofType);
		}
		
		
	
}
