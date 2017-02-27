package co.gov.cundinamarca.sisvan.model.parameters;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "LACTANCIAS")
public class Lactancias implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LAC_ID")
    private String lac_Id;
    @Column(name = "USU_LAC_ID")
    private String usu_Lac_Id;
    @Column(name = "LAC_NOMBRE")
    private String lac_Nombre;
    @Column(name = "LAC_ESTADO")
    private String lac_Estado;
    @Column(name = "LAC_FECHA")
    private Date lac_Fecha;

    public Lactancias() {
    }

    public String getLac_Id() {
        return this.lac_Id;
    }

    public void setLac_Id(String lac_Id) {
        this.lac_Id = lac_Id;
    }

    public String getUsu_Lac_Id() {
        return this.usu_Lac_Id;
    }

    public void setUsu_Lac_Id(String usu_Lac_Id) {
        this.usu_Lac_Id = usu_Lac_Id;
    }

    public String getLac_Nombre() {
        return this.lac_Nombre;
    }

    public void setLac_Nombre(String lac_Nombre) {
        this.lac_Nombre = lac_Nombre;
    }

    public String getLac_Estado() {
        return this.lac_Estado;
    }

    public void setLac_Estado(String lac_Estado) {
        this.lac_Estado = lac_Estado;
    }

    public Date getLac_Fecha() {
        return this.lac_Fecha;
    }

    public void setLac_Fecha(Date lac_Fecha) {
        this.lac_Fecha = lac_Fecha;
    }
    
    @Override
    public String toString() {
        return this.lac_Nombre;
    }

}
