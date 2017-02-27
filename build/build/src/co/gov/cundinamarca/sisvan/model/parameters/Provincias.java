package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "PROVINCIAS")
public class Provincias implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DEP_PRO_ID")
    private String dep_Pro_Id;
    @Id
    @Column(name = "PRO_ID")
    private String pro_Id;
    @Column(name = "PRO_NOMBRE")
    private String pro_Nombre;
    @Column(name = "PRO_ESTADO")
    private String pro_Estado;

    public Provincias() {
    }

    public String getDep_Pro_Id() {
        return this.dep_Pro_Id;
    }

    public void setDep_Pro_Id(String dep_Pro_Id) {
        this.dep_Pro_Id = dep_Pro_Id;
    }

    public String getPro_Id() {
        return this.pro_Id;
    }

    public void setPro_Id(String pro_Id) {
        this.pro_Id = pro_Id;
    }

    public String getPro_Nombre() {
        return this.pro_Nombre;
    }

    public void setPro_Nombre(String pro_Nombre) {
        this.pro_Nombre = pro_Nombre;
    }

    public String getPro_Estado() {
        return this.pro_Estado;
    }

    public void setPro_Estado(String pro_Estado) {
        this.pro_Estado = pro_Estado;
    }
}
