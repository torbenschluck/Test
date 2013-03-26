package Model;

import java.util.*;

public class Reference_VO {

	private int ref_id = 0;
	private int rt_id = 0;
	private long date = 0;
	private String keywords = "";
	private String ref_abstract = "";
	private String notes = "";
	private String quote = "";
	
	public Reference_VO ()
	{
		
	}

	public int getRef_id() {
		return ref_id;
	}

	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}

	public int getRt_id() {
		return rt_id;
	}

	public void setRt_id(int rt_id) {
		this.rt_id = rt_id;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getRef_abstract() {
		return ref_abstract;
	}

	public void setRef_abstract(String ref_abstract) {
		this.ref_abstract = ref_abstract;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getQuote() {
		return quote;
	}
	
}
