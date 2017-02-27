package co.gov.cundinamarca.sisvan.model.parameters;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "CRECIMIENTO_DESARROLLOS")
public class Crecimiento_Desarrollos implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CDE_ID")
    private String cde_Id;
    @Column(name = "USU_CDE_ID")
    private String usu_Cde_Id;
    @Column(name = "CDE_NOMBRE")
    private String cde_Nombre;
    @Column(name = "CDE_ESTADO")
    private String cde_Estado;
    @Column(name = "CDE_FECHA")
    private Date cde_Fecha;

    public Crecimiento_Desarrollos() {
    }

    public String getCde_Id() {
        return this.cde_Id;
    }

    public void setCde_Id(String cde_Id) {
        this.cde_Id = cde_Id;
    }

    public String getUsu_Cde_Id() {
        return this.usu_Cde_Id;
    }

    public void setUsu_Cde_Id(String usu_Cde_Id) {
        this.usu_Cde_Id = usu_Cde_Id;
    }

    public String getCde_Nombre() {
        return this.cde_Nombre;
    }

    public void setCde_Nombre(String cde_Nombre) {
        this.cde_Nombre = cde_Nombre;
    }

    public String getCde_Estado() {
        return this.cde_Estado;
    }

    public void setCde_Estado(String cde_Estado) {
        this.cde_Estado = cde_Estado;
    }

    public Date getCde_Fecha() {
        return this.cde_Fecha;
    }

    public void setCde_Fecha(Date cde_Fecha) {
        this.cde_Fecha = cde_Fecha;
    }

    @Override
    public String toString() {
        return this.cde_Nombre;
    }
}
