package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "TIPO_CONSULTAS")
public class Tipo_Consultas implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TCO_ID")
    private String tco_Id;
    @Column(name = "USU_TCO_ID")
    private String usu_Tco_Id;
    @Column(name = "TCO_NOMBRE")
    private String tco_Nombre;
    @Column(name = "TCO_ESTADO")
    private String tco_Estado;
    @Column(name = "TCO_FECHA")
    private Date tco_Fecha;
    @Column(name = "TCO_APLICA_ADULTOS")
    private String tco_Aplica_Adultos;
    @Column(name = "TCO_APLICA_MENORES")
    private String tco_Aplica_Menores;
    @Column(name = "TCO_APLICA_GESTANTES")
    private String tco_Aplica_Gestantes;

    public Tipo_Consultas() {
    }

    public String getTco_Id() {
        return this.tco_Id;
    }

    public void setTco_Id(String tco_Id) {
        this.tco_Id = tco_Id;
    }

    public String getUsu_Tco_Id() {
        return this.usu_Tco_Id;
    }

    public void setUsu_Tco_Id(String usu_Tco_Id) {
        this.usu_Tco_Id = usu_Tco_Id;
    }

    public String getTco_Nombre() {
        return this.tco_Nombre;
    }

    public void setTco_Nombre(String tco_Nombre) {
        this.tco_Nombre = tco_Nombre;
    }

    public String getTco_Estado() {
        return this.tco_Estado;
    }

    public void setTco_Estado(String tco_Estado) {
        this.tco_Estado = tco_Estado;
    }

    public Date getTco_Fecha() {
        return this.tco_Fecha;
    }

    public void setTco_Fecha(Date tco_Fecha) {
        this.tco_Fecha = tco_Fecha;
    }
    
    public String getTco_Aplica_Adultos() {
		return tco_Aplica_Adultos;
	}

	public void setTco_Aplica_Adultos(String tco_Aplica_Adultos) {
		this.tco_Aplica_Adultos = tco_Aplica_Adultos;
	}

	public String getTco_Aplica_Menores() {
		return tco_Aplica_Menores;
	}

	public void setTco_Aplica_Menores(String tco_Aplica_Menores) {
		this.tco_Aplica_Menores = tco_Aplica_Menores;
	}

	public String getTco_Aplica_Gestantes() {
		return tco_Aplica_Gestantes;
	}

	public void setTco_Aplica_Gestantes(String tco_Aplica_Gestantes) {
		this.tco_Aplica_Gestantes = tco_Aplica_Gestantes;
	}

	@Override
    public String toString() {
        return this.tco_Nombre;
    }
}
