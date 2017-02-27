package co.gov.cundinamarca.sisvan.model.parameters;

public class Sexo {
	
	private String id;
	private String value;
	
	public Sexo() {
		
	}
	
	public Sexo(String id) {
		this.id=id;
		switch (id){
		case "0":
			this.value="Masculino";
			break;
		case "1":
			this.value="Femenino";
			break;
		case "2":
			this.value="Indeterminado";
			break;
			default:
				this.value="";
				break;
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
