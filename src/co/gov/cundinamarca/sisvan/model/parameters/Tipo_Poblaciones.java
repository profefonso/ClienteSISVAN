package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "TIPO_POBLACIONES")
public class Tipo_Poblaciones implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TPO_ID")
    private String tpo_Id;
    @Column(name = "USU_TPO_ID")
    private String usu_Tpo_Id;
    @Column(name = "TPO_NOMBRE")
    private String tpo_Nombre;
    @Column(name = "TPO_ESTADO")
    private String tpo_Estado;
    @Column(name = "TPO_FECHA")
    private Date tpo_Fecha;

    public Tipo_Poblaciones() {
    }

    public String getTpo_Id() {
        return this.tpo_Id;
    }

    public void setTpo_Id(String tpo_Id) {
        this.tpo_Id = tpo_Id;
    }

    public String getUsu_Tpo_Id() {
        return this.usu_Tpo_Id;
    }

    public void setUsu_Tpo_Id(String usu_Tpo_Id) {
        this.usu_Tpo_Id = usu_Tpo_Id;
    }

    public String getTpo_Nombre() {
        return this.tpo_Nombre;
    }

    public void setTpo_Nombre(String tpo_Nombre) {
        this.tpo_Nombre = tpo_Nombre;
    }

    public String getTpo_Estado() {
        return this.tpo_Estado;
    }

    public void setTpo_Estado(String tpo_Estado) {
        this.tpo_Estado = tpo_Estado;
    }

    public Date getTpo_Fecha() {
        return this.tpo_Fecha;
    }

    public void setTpo_Fecha(Date tpo_Fecha) {
        this.tpo_Fecha = tpo_Fecha;
    }
    
    @Override
    public String toString() {
        return this.tpo_Nombre;
    }
}
