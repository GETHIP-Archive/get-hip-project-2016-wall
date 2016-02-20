package com.gallup.gethip.resources;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.gallup.gethip.DataSourceManager;
import com.gallup.gethip.model.Note;
import com.j256.ormlite.dao.Dao;

@Path("/notes")
public class NoteResource {

	@POST
    @Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Note addNote(Note message) {
		try {
			Note note = getDao().createIfNotExists(message);
			if(note == null) {
				System.out.println("Error. Could not add note.");
			} else {
				return note;
			}
		} catch (SQLException e) {
			System.out.println("An error has occurred while adding note.");
			e.printStackTrace();
		}
		return null;
		
	}
	
	@PUT
	@Path("/{note_id}")
    @Produces("text/plain")
    @Consumes("application/json")
	public String updateNote(@PathParam("note_id") String id, Note message) {
		Note note = null;
    	try {
			note = getDao().queryForId(id);
			if(note == null){
				System.out.println("Error: Note not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error.");
		}
		try {
			message.setId(Long.parseLong(id));
			int num = getDao().update(message);
			if(num == 1){
				return "Update successful for note " + message.getId();
			}else{
				return "Could not update note id: " + message.getId();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return "Error updating note id: " + message.getId();
		}
	}
	
	@DELETE
	@Path("/{note_id}")
	@Produces("text/plain")
	public String removeNote(@QueryParam("note_id") String note) {
		try {
			int num = getDao().deleteById(note);
			if(num == 1){
				return "Delete successful";
			}else{
				return "Unable to delete";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error in sql statement";
		}
	}
	
	@GET
	@Path("/{note_id}")
	@Produces("application/json")
	public Note getNote(@PathParam("note_id") String note_id) {
		
		Note note = null;
    	try {
			note = getDao().queryForId(note_id);
			if(note == null){
				System.out.println("Error: Note not found.");
			}else{
				return note;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error.");
		}
    	return note;
	}
	
    private Dao<Note, String> getDao(){
    	Dao<Note, String> dao = DataSourceManager.getInstance().getDao(Note.class);
    	return dao;
    }
}
