package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "BARRIO_VEREDAS")
public class Barrio_Veredas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DEP_BVE_ID")
    private String dep_Bve_Id;
    @Id
    @Column(name = "MUN_BVE_ID")
    private String mun_Bve_Id;
    @Id
    @Column(name = "BVE_ID")
    private String bve_Id;
    @Column(name = "USU_BVE_ID")
    private String usu_Bve_Id;
    @Column(name = "BVE_NOMBRE")
    private String bve_Nombre;
    @Column(name = "BVE_TIPO")
    private String bve_Tipo;
    @Column(name = "BVE_ESTADO")
    private String bve_Estado;
    @Column(name = "BVE_FECHA")
    private Date bve_Fecha;

    public Barrio_Veredas() {
    }

    public String getDep_Bve_Id() {
        return this.dep_Bve_Id;
    }

    public void setDep_Bve_Id(String dep_Bve_Id) {
        this.dep_Bve_Id = dep_Bve_Id;
    }

    public String getMun_Bve_Id() {
        return this.mun_Bve_Id;
    }

    public void setMun_Bve_Id(String mun_Bve_Id) {
        this.mun_Bve_Id = mun_Bve_Id;
    }

    public String getBve_Id() {
        return this.bve_Id;
    }

    public void setBve_Id(String bve_Id) {
        this.bve_Id = bve_Id;
    }

    public String getUsu_Bve_Id() {
        return this.usu_Bve_Id;
    }

    public void setUsu_Bve_Id(String usu_Bve_Id) {
        this.usu_Bve_Id = usu_Bve_Id;
    }

    public String getBve_Nombre() {
        return this.bve_Nombre;
    }

    public void setBve_Nombre(String bve_Nombre) {
        this.bve_Nombre = bve_Nombre;
    }

    public String getBve_Tipo() {
        return this.bve_Tipo;
    }

    public void setBve_Tipo(String bve_Tipo) {
        this.bve_Tipo = bve_Tipo;
    }

    public String getBve_Estado() {
        return this.bve_Estado;
    }

    public void setBve_Estado(String bve_Estado) {
        this.bve_Estado = bve_Estado;
    }

    public Date getBve_Fecha() {
        return this.bve_Fecha;
    }

    public void setBve_Fecha(Date bve_Fecha) {
        this.bve_Fecha = bve_Fecha;
    }
    
    @Override
    public String toString() {
        return this.bve_Nombre;
    }
}
