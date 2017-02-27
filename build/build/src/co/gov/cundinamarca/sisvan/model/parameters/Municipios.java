package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "MUNICIPIOS")
public class Municipios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DEP_MUN_ID")
    private String dep_Mun_Id;
    @Id
    @Column(name = "MUN_ID")
    private String mun_Id;
    @Column(name = "PRO_MUN_ID")
    private String pro_Mun_Id;
    @Column(name = "MUN_NOMBRE")
    private String mun_Nombre;
    @Column(name = "DEP_MUN_NOMBRE", insertable = false, updatable = false)
    private String dep_Mun_Nombre;

    public Municipios() {
    }

    public String getDep_Mun_Id() {
        return this.dep_Mun_Id;
    }

    public void setDep_Mun_Id(String dep_Mun_Id) {
        this.dep_Mun_Id = dep_Mun_Id;
    }

    public String getMun_Id() {
        return this.mun_Id;
    }

    public void setMun_Id(String mun_Id) {
        this.mun_Id = mun_Id;
    }

    public String getPro_Mun_Id() {
        return this.pro_Mun_Id;
    }

    public void setPro_Mun_Id(String pro_Mun_Id) {
        this.pro_Mun_Id = pro_Mun_Id;
    }

    public String getMun_Nombre() {
        return this.mun_Nombre;
    }

    public void setMun_Nombre(String mun_Nombre) {
        this.mun_Nombre = mun_Nombre;
    }
    
    @Override
    public String toString() {
        return this.mun_Nombre;
    }
}
