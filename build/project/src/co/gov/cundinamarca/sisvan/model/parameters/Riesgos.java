package co.gov.cundinamarca.sisvan.model.parameters;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "RIESGOS")
public class Riesgos implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RIE_ID")
    private String rie_Id;
    @Column(name = "USU_RIE_ID")
    private String usu_Rie_Id;
    @Column(name = "RIE_NOMBRE")
    private String rie_Nombre;
    @Column(name = "RIE_ESTADO")
    private String rie_Estado;
    @Column(name = "RIE_FECHA")
    private Date rie_Fecha;

    public Riesgos() {
    }

    public String getRie_Id() {
        return this.rie_Id;
    }

    public void setRie_Id(String rie_Id) {
        this.rie_Id = rie_Id;
    }

    public String getUsu_Rie_Id() {
        return this.usu_Rie_Id;
    }

    public void setUsu_Rie_Id(String usu_Rie_Id) {
        this.usu_Rie_Id = usu_Rie_Id;
    }

    public String getRie_Nombre() {
        return this.rie_Nombre;
    }

    public void setRie_Nombre(String rie_Nombre) {
        this.rie_Nombre = rie_Nombre;
    }

    public String getRie_Estado() {
        return this.rie_Estado;
    }

    public void setRie_Estado(String rie_Estado) {
        this.rie_Estado = rie_Estado;
    }

    public Date getRie_Fecha() {
        return this.rie_Fecha;
    }

    public void setRie_Fecha(Date rie_Fecha) {
        this.rie_Fecha = rie_Fecha;
    }
    
    @Override
    public String toString() {
        return this.rie_Nombre;
    }
}
