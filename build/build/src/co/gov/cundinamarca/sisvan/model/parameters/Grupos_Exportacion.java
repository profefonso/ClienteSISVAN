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
@Table(name = "GRUPOS_EXPORTACION")
public class Grupos_Exportacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @Column(name = "GRE_ID")
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long gre_Id;
	 @Column(name = "GRE_GRUPO_ID")
	 private String gre_Grupo_Id;
	 @Column(name = "GRE_TABLA")
	 private String gre_Tabla;
	 @Column(name = "GRE_REGISTRO")
	 private String gre_Registro;
	 @Column(name = "GRE_FECHA_REGISTRO")
	 private Date gre_Fecha_Registro;
	 @Column(name = "GRE_ESTADO")
	 private String gre_Estado;
	 @Column(name = "GRE_FECHA")
	 private Date gre_Fecha;
	 @Column(name = "GRE_DESCRIPCION")
	 private String gre_Descripcion;
	 
	public Long getGre_Id() {
		return gre_Id;
	}
	public void setGre_Id(Long gre_Id) {
		this.gre_Id = gre_Id;
	}
	public String getGre_Grupo_Id() {
		return gre_Grupo_Id;
	}
	public void setGre_Grupo_Id(String gre_Grupo_Id) {
		this.gre_Grupo_Id = gre_Grupo_Id;
	}
	public String getGre_Tabla() {
		return gre_Tabla;
	}
	public void setGre_Tabla(String gre_Tabla) {
		this.gre_Tabla = gre_Tabla;
	}
	public String getGre_Registro() {
		return gre_Registro;
	}
	public void setGre_Registro(String gre_Registro) {
		this.gre_Registro = gre_Registro;
	}
	public Date getGre_Fecha_Registro() {
		return gre_Fecha_Registro;
	}
	public void setGre_Fecha_Registro(Date gre_Fecha_Registro) {
		this.gre_Fecha_Registro = gre_Fecha_Registro;
	}
	public String getGre_Estado() {
		return gre_Estado;
	}
	public void setGre_Estado(String gre_Estado) {
		this.gre_Estado = gre_Estado;
	}
	public Date getGre_Fecha() {
		return gre_Fecha;
	}
	public void setGre_Fecha(Date gre_Fecha) {
		this.gre_Fecha = gre_Fecha;
	}
	public String getGre_Descripcion() {
		return gre_Descripcion;
	}
	public void setGre_Descripcion(String gre_Descripcion) {
		this.gre_Descripcion = gre_Descripcion;
	}
}
