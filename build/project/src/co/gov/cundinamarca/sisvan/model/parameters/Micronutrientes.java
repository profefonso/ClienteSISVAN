package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "MICRONUTRIENTES")
public class Micronutrientes implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MNU_ID")
    private String mnu_Id;
    @Column(name = "USU_MNU_ID")
    private String usu_Mnu_Id;
    @Column(name = "MNU_NOMBRE")
    private String mnu_Nombre;
    @Column(name = "MNU_APLICA_ADULTO")
    private String mnu_Aplica_Adulto;
    @Column(name = "MNU_APLICA_GESTANTE")
    private String mnu_Aplica_Gestante;
    @Column(name = "MNU_APLICA_MENOR")
    private String mnu_Aplica_Menor;
    @Column(name = "MNU_ESTADO")
    private String mnu_Estado;
    @Column(name = "MNU_FECHA")
    private Date mnu_Fecha;

    public Micronutrientes() {
    }

    public String getMnu_Id() {
        return this.mnu_Id;
    }

    public void setMnu_Id(String mnu_Id) {
        this.mnu_Id = mnu_Id;
    }

    public String getUsu_Mnu_Id() {
        return this.usu_Mnu_Id;
    }

    public void setUsu_Mnu_Id(String usu_Mnu_Id) {
        this.usu_Mnu_Id = usu_Mnu_Id;
    }

    public String getMnu_Nombre() {
        return this.mnu_Nombre;
    }

    public void setMnu_Nombre(String mnu_Nombre) {
        this.mnu_Nombre = mnu_Nombre;
    }

    public String getMnu_Aplica_Adulto() {
        return this.mnu_Aplica_Adulto;
    }

    public void setMnu_Aplica_Adulto(String mnu_Aplica_Adulto) {
        this.mnu_Aplica_Adulto = mnu_Aplica_Adulto;
    }

    public String getMnu_Aplica_Gestante() {
        return this.mnu_Aplica_Gestante;
    }

    public void setMnu_Aplica_Gestante(String mnu_Aplica_Gestante) {
        this.mnu_Aplica_Gestante = mnu_Aplica_Gestante;
    }

    public String getMnu_Aplica_Menor() {
        return this.mnu_Aplica_Menor;
    }

    public void setMnu_Aplica_Menor(String mnu_Aplica_Menor) {
        this.mnu_Aplica_Menor = mnu_Aplica_Menor;
    }

    public String getMnu_Estado() {
        return this.mnu_Estado;
    }

    public void setMnu_Estado(String mnu_Estado) {
        this.mnu_Estado = mnu_Estado;
    }

    public Date getMnu_Fecha() {
        return this.mnu_Fecha;
    }

    public void setMnu_Fecha(Date mnu_Fecha) {
        this.mnu_Fecha = mnu_Fecha;
    }

    @Override
    public String toString() {
        return this.mnu_Nombre;
    }
}
