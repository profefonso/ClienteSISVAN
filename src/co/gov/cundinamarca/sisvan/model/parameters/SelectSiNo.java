package co.gov.cundinamarca.sisvan.model.parameters;

public class SelectSiNo {
	private String id;
	private String value;
	
	public SelectSiNo(String id, String value) {
		this.id=id;
		this.value=value;
	}
	
	public SelectSiNo(String id) {
		this.id=id;
		if(id.equals("1")){
			this.value="Si";
		}else if(id.equals("2")){
			this.value="No";
		}else{
			this.value="";
		}
		
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
    public String toString() {
        return this.value;
    }
}
