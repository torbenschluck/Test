package Model;

public class Ref_Group_VO {
	
	private String ref_group_name = "";
	private int ref_group_id = 0;
	
	public Ref_Group_VO(){
		
	}
	
	public Ref_Group_VO(String ref_group_name){
		this.setRef_group_name(ref_group_name);
	}

	public void setRef_group_name(String ref_group_name) {
		this.ref_group_name = ref_group_name;
	}

	public String getRef_group_name() {
		return ref_group_name;
	}

	public void setRef_group_id(int ref_group_id) {
		this.ref_group_id = ref_group_id;
	}

	public int getRef_group_id() {
		return ref_group_id;
	}		

}
