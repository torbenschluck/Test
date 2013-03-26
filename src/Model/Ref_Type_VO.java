package Model;

public class Ref_Type_VO {
	
	private String ref_type_name = "";
	private int ref_type_id = 0;
	
	public Ref_Type_VO(){
		
	}
	
	public Ref_Type_VO(String ref_type_name){
		this.setRef_type_name(ref_type_name);
	}

	public void setRef_type_name(String ref_type_name) {
		this.ref_type_name = ref_type_name;
	}

	public String getRef_type_name() {
		return ref_type_name;
	}

	public void setRef_type_id(int ref_type_id) {
		this.ref_type_id = ref_type_id;
	}

	public int getRef_type_id() {
		return ref_type_id;
	}		

}


