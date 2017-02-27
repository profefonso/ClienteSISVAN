package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "CONTROLES")
public class Controles implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CTR_ID")
    private String ctr_Id;
    @Column(name = "USU_CTR_ID")
    private String usu_Ctr_Id;
    @Column(name = "CTR_NOMBRE")
    private String ctr_Nombre;
    @Column(name = "CTR_ESTADO")
    private String ctr_Estado;
    @Column(name = "CTR_FECHA")
    private Date ctr_Fecha;

    public Controles() {
    }

    public String getCtr_Id() {
        return this.ctr_Id;
    }

    public void setCtr_Id(String ctr_Id) {
        this.ctr_Id = ctr_Id;
    }

    public String getUsu_Ctr_Id() {
        return this.usu_Ctr_Id;
    }

    public void setUsu_Ctr_Id(String usu_Ctr_Id) {
        this.usu_Ctr_Id = usu_Ctr_Id;
    }

    public String getCtr_Nombre() {
        return this.ctr_Nombre;
    }

    public void setCtr_Nombre(String ctr_Nombre) {
        this.ctr_Nombre = ctr_Nombre;
    }

    public String getCtr_Estado() {
        return this.ctr_Estado;
    }

    public void setCtr_Estado(String ctr_Estado) {
        this.ctr_Estado = ctr_Estado;
    }

    public Date getCtr_Fecha() {
        return this.ctr_Fecha;
    }

    public void setCtr_Fecha(Date ctr_Fecha) {
        this.ctr_Fecha = ctr_Fecha;
    }

    @Override
    public String toString() {
        return this.ctr_Nombre;
    }
}
