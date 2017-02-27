package co.gov.cundinamarca.sisvan.model.parameters;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "DATO_DINAMICOS")
public class Dato_Dinamicos implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DAD_ID")
    private String dad_Id;
    @Column(name = "DAD_NOMBRE")
    private String dad_Nombre;
    @Column(name = "DAD_TIPO")
    private String dad_Tipo;
    @Column(name = "DAD_NULO")
    private String dad_Nulo;
    @Column(name = "DAD_ESTADO")
    private String dad_Estado;
    @Column(name = "USU_DAD_ID")
    private String usu_Dad_Id;
    @Column(name = "DAD_FECHA")
    private Date dad_Fecha;

    public Dato_Dinamicos() {
    }
    
    
    public String getDad_Id() {
		return dad_Id;
	}


	public void setDad_Id(String dad_Id) {
		this.dad_Id = dad_Id;
	}


	public String getDad_Nombre() {
		return dad_Nombre;
	}


	public void setDad_Nombre(String dad_Nombre) {
		this.dad_Nombre = dad_Nombre;
	}


	public String getDad_Tipo() {
		return dad_Tipo;
	}


	public void setDad_Tipo(String dad_Tipo) {
		this.dad_Tipo = dad_Tipo;
	}


	public String getDad_Nulo() {
		return dad_Nulo;
	}


	public void setDad_Nulo(String dad_Nulo) {
		this.dad_Nulo = dad_Nulo;
	}


	public String getDad_Estado() {
		return dad_Estado;
	}


	public void setDad_Estado(String dad_Estado) {
		this.dad_Estado = dad_Estado;
	}


	public String getUsu_Dad_Id() {
		return usu_Dad_Id;
	}


	public void setUsu_Dad_Id(String usu_Dad_Id) {
		this.usu_Dad_Id = usu_Dad_Id;
	}


	public Date getDad_Fecha() {
		return dad_Fecha;
	}


	public void setDad_Fecha(Date dad_Fecha) {
		this.dad_Fecha = dad_Fecha;
	}

	@Override
    public String toString() {
        return this.dad_Nombre;
    }
}
