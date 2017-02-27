package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "LUGAR_RESIDENCIAS")
public class Lugar_Residencias implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LRE_ID")
    private String lre_Id;
    @Column(name = "USU_LRE_ID")
    private String usu_Lre_Id;
    @Column(name = "LRE_NOMBRE")
    private String lre_Nombre;
    @Column(name = "LRE_ESTADO")
    private String lre_Estado;
    @Column(name = "LRE_FECHA")
    private Date lre_Fecha;

    public Lugar_Residencias() {
    }

    public String getLre_Id() {
        return this.lre_Id;
    }

    public void setLre_Id(String lre_Id) {
        this.lre_Id = lre_Id;
    }

    public String getUsu_Lre_Id() {
        return this.usu_Lre_Id;
    }

    public void setUsu_Lre_Id(String usu_Lre_Id) {
        this.usu_Lre_Id = usu_Lre_Id;
    }

    public String getLre_Nombre() {
        return this.lre_Nombre;
    }

    public void setLre_Nombre(String lre_Nombre) {
        this.lre_Nombre = lre_Nombre;
    }

    public String getLre_Estado() {
        return this.lre_Estado;
    }

    public void setLre_Estado(String lre_Estado) {
        this.lre_Estado = lre_Estado;
    }

    public Date getLre_Fecha() {
        return this.lre_Fecha;
    }

    public void setLre_Fecha(Date lre_Fecha) {
        this.lre_Fecha = lre_Fecha;
    }
    
    @Override
    public String toString() {
        return this.lre_Nombre;
    }
}
