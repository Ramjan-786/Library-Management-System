
package com.lms.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "records")
public class Records {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int userid;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column
	private Date issuedate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column
	private Date returndate;
	
	@Column
	private int bookid;
	
	@Column
	private String status;
	
//	@ManyToMany
//	@JoinColumn(name = "id")
//	private List<User> userlist;
//	
//	@ManyToMany
//	@JoinColumn(name = "id")
//	private List<Book> booklist;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}

	public Date getReturndate() {
		return returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

//	public List<User> getUserlist() {
//		return userlist;
//	}
//
//	public void setUserlist(List<User> userlist) {
//		this.userlist = userlist;
//	}
//
//	public List<Book> getBooklist() {
//		return booklist;
//	}
//
//	public void setBooklist(List<Book> booklist) {
//		this.booklist = booklist;
//	}
/*
	public Records(int id, int userid, Date issuedate, Date returndate, int bookid, List<User> userlist,
			List<Book> booklist) {
		super();
		this.id = id;
		this.userid = userid;
		this.issuedate = issuedate;
		this.returndate = returndate;
		this.bookid = bookid;
//		this.userlist = userlist;
//		this.booklist = booklist;
	}
*/
	public Records() {
		super();
	}

	@Override
	public String toString() {
		return "Records [id=" + id + ", userid=" + userid + ", issuedate=" + issuedate + ", returndate=" + returndate
				+ ", bookid=" + bookid + ", status=" + status + "]";
	}

	public Records(int id, int userid, Date issuedate, Date returndate, int bookid, String status) {
		super();
		this.id = id;
		this.userid = userid;
		this.issuedate = issuedate;
		this.returndate = returndate;
		this.bookid = bookid;
		this.status = status;
	}

//	@Override
//	public String toString() {
//		return "Records [id=" + id + ", userid=" + userid + ", issuedate=" + issuedate + ", returndate=" + returndate
//				+ ", bookid=" + bookid + ", userlist=" + userlist + ", booklist=" + booklist + "]";
//	}
//	
	
	
	

}
