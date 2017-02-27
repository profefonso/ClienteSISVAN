package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "EDUCACION_MENORES")
public class Educacion_Menores implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "EME_ID")
    private String eme_Id;
    @Column(name = "USU_EME_ID")
    private String usu_Eme_Id;
    @Column(name = "EME_NOMBRE")
    private String eme_Nombre;
    @Column(name = "EME_ESTADO")
    private String eme_Estado;
    @Column(name = "EME_FECHA")
    private Date eme_Fecha;

    public Educacion_Menores() {
    }

    public String getEme_Id() {
        return this.eme_Id;
    }

    public void setEme_Id(String eme_Id) {
        this.eme_Id = eme_Id;
    }

    public String getUsu_Eme_Id() {
        return this.usu_Eme_Id;
    }

    public void setUsu_Eme_Id(String usu_Eme_Id) {
        this.usu_Eme_Id = usu_Eme_Id;
    }

    public String getEme_Nombre() {
        return this.eme_Nombre;
    }

    public void setEme_Nombre(String eme_Nombre) {
        this.eme_Nombre = eme_Nombre;
    }

    public String getEme_Estado() {
        return this.eme_Estado;
    }

    public void setEme_Estado(String eme_Estado) {
        this.eme_Estado = eme_Estado;
    }

    public Date getEme_Fecha() {
        return this.eme_Fecha;
    }

    public void setEme_Fecha(Date eme_Fecha) {
        this.eme_Fecha = eme_Fecha;
    }

    @Override
    public String toString() {
        return this.eme_Nombre;
    }
}
