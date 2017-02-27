package co.gov.cundinamarca.sisvan.model.parameters;

import java.util.List;

public class Sincronizacion {
	private String version;
	private List<String> sentenciasSQL;
	private String message;
	
	public Sincronizacion() {
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<String> getSentenciasSQL() {
		return sentenciasSQL;
	}
	public void setSentenciasSQL(List<String> sentenciasSQL) {
		this.sentenciasSQL = sentenciasSQL;
	}
}