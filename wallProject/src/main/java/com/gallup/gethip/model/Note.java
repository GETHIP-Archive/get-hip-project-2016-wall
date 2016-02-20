package com.gallup.gethip.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@XmlRootElement
@DatabaseTable(tableName="notes")
public class Note {
	
	@DatabaseField(generatedId = true, columnName = "note_id")
	private long id;
	
	@DatabaseField(columnName = "message")
	private String message;
	
	@DatabaseField(columnName = "date")
	private Date created;
	
	@DatabaseField(columnName = "author")
	private String author;
	
	@DatabaseField(columnName = "color")
	private String color;
	
	public Note() {
		
	}
	
	public Note(long id, String message, String author, String color) {
		this.id = id;
		this.message = message;
		this.created = new Date();
		this.author = author;
		this.color = color;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getColor() {
		return this.color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	

}
