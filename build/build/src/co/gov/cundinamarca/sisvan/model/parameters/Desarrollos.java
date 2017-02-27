package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "DESARROLLOS")
public class Desarrollos implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DES_ID")
    private String des_Id;
    @Column(name = "USU_DES_ID")
    private String usu_Des_Id;
    @Column(name = "DES_NOMBRE")
    private String des_Nombre;
    @Column(name = "DES_ESTADO")
    private String des_Estado;
    @Column(name = "DES_FECHA")
    private Date des_Fecha;

    public Desarrollos() {
    }

    public String getDes_Id() {
        return this.des_Id;
    }

    public void setDes_Id(String des_Id) {
        this.des_Id = des_Id;
    }

    public String getUsu_Des_Id() {
        return this.usu_Des_Id;
    }

    public void setUsu_Des_Id(String usu_Des_Id) {
        this.usu_Des_Id = usu_Des_Id;
    }

    public String getDes_Nombre() {
        return this.des_Nombre;
    }

    public void setDes_Nombre(String des_Nombre) {
        this.des_Nombre = des_Nombre;
    }

    public String getDes_Estado() {
        return this.des_Estado;
    }

    public void setDes_Estado(String des_Estado) {
        this.des_Estado = des_Estado;
    }

    public Date getDes_Fecha() {
        return this.des_Fecha;
    }

    public void setDes_Fecha(Date des_Fecha) {
        this.des_Fecha = des_Fecha;
    }

    @Override
    public String toString() {
        return this.des_Nombre;
    }
}
