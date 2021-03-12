package com.CalandarProject.v1;

public class Note {

	private String noteText;
	
	public Note( ) {
		noteText = "";
	}
	
	public Note( String note ) {
		noteText = note;
	}
	
	public String getNoteText() {
		return noteText;
	}
}
