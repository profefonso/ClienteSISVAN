package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VERSIONES")
public class Versiones implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "VER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private String ver_Id;
    @Column(name = "VER_CODIGO")
    private String ver_Codigo;
    @Column(name = "VER_FECHA")
    private Date ver_Fecha;
    @Column(name = "VER_ESTADO")
    private String ver_Estado;
    
    public Versiones() {
	}
    
	public String getVer_Id() {
		return ver_Id;
	}
	public void setVer_Id(String ver_Id) {
		this.ver_Id = ver_Id;
	}
	public String getVer_Codigo() {
		return ver_Codigo;
	}
	public void setVer_Codigo(String ver_Codigo) {
		this.ver_Codigo = ver_Codigo;
	}
	public Date getVer_Fecha() {
		return ver_Fecha;
	}
	public void setVer_Fecha(Date ver_Fecha) {
		this.ver_Fecha = ver_Fecha;
	}
	public String getVer_Estado() {
		return ver_Estado;
	}
	public void setVer_Estado(String ver_Estado) {
		this.ver_Estado = ver_Estado;
	}
}
