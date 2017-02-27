package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "CANCER_DIAGNOSTICOS")
public class Cancer_Diagnosticos implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CDI_ID")
    private String cdi_Id;
    @Column(name = "USU_CDI_ID")
    private String usu_Cdi_Id;
    @Column(name = "CDI_NOMBRE")
    private String cdi_Nombre;
    @Column(name = "CDI_ESTADO")
    private String cdi_Estado;
    @Column(name = "CDI_FECHA")
    private Date cdi_Fecha;

    public Cancer_Diagnosticos() {
    }

    public String getCdi_Id() {
        return this.cdi_Id;
    }

    public void setCdi_Id(String cdi_Id) {
        this.cdi_Id = cdi_Id;
    }

    public String getUsu_Cdi_Id() {
        return this.usu_Cdi_Id;
    }

    public void setUsu_Cdi_Id(String usu_Cdi_Id) {
        this.usu_Cdi_Id = usu_Cdi_Id;
    }

    public String getCdi_Nombre() {
        return this.cdi_Nombre;
    }

    public void setCdi_Nombre(String cdi_Nombre) {
        this.cdi_Nombre = cdi_Nombre;
    }

    public String getCdi_Estado() {
        return this.cdi_Estado;
    }

    public void setCdi_Estado(String cdi_Estado) {
        this.cdi_Estado = cdi_Estado;
    }

    public Date getCdi_Fecha() {
        return this.cdi_Fecha;
    }

    public void setCdi_Fecha(Date cdi_Fecha) {
        this.cdi_Fecha = cdi_Fecha;
    }

    @Override
    public String toString() {
        return this.cdi_Nombre;
    }

}
