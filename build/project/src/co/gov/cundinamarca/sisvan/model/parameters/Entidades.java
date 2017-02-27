package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "ENTIDADES")
public class Entidades implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "EPS_ID")
    private String eps_Id;
    @Column(name = "USU_EPS_ID")
    private String usu_Eps_Id;
    @Column(name = "EPS_NOMBRE")
    private String eps_Nombre;
    @Column(name = "EPS_NIT")
    private String eps_Nit;
    @Column(name = "EPS_DIRECCION")
    private String eps_Direccion;
    @Column(name = "EPS_TELEFONO")
    private String eps_Telefono;
    @Column(name = "EPS_EMAIL")
    private String eps_Email;
    @Column(name = "EPS_NOMBRE_REPRESENTANTE")
    private String eps_Nombre_Representante;
    @Column(name = "EPS_TIPO")
    private String eps_Tipo;
    @Column(name = "EPS_ESTADO")
    private String eps_Estado;
    @Column(name = "EPS_FECHA")
    private Date eps_Fecha;

    public Entidades() {
    }

    public String getEps_Id() {
        return this.eps_Id;
    }

    public void setEps_Id(String eps_Id) {
        this.eps_Id = eps_Id;
    }

    public String getUsu_Eps_Id() {
        return this.usu_Eps_Id;
    }

    public void setUsu_Eps_Id(String usu_Eps_Id) {
        this.usu_Eps_Id = usu_Eps_Id;
    }

    public String getEps_Nombre() {
        return this.eps_Nombre;
    }

    public void setEps_Nombre(String eps_Nombre) {
        this.eps_Nombre = eps_Nombre;
    }

    public String getEps_Nit() {
        return this.eps_Nit;
    }

    public void setEps_Nit(String eps_Nit) {
        this.eps_Nit = eps_Nit;
    }

    public String getEps_Direccion() {
        return this.eps_Direccion;
    }

    public void setEps_Direccion(String eps_Direccion) {
        this.eps_Direccion = eps_Direccion;
    }

    public String getEps_Telefono() {
        return this.eps_Telefono;
    }

    public void setEps_Telefono(String eps_Telefono) {
        this.eps_Telefono = eps_Telefono;
    }

    public String getEps_Email() {
        return this.eps_Email;
    }

    public void setEps_Email(String eps_Email) {
        this.eps_Email = eps_Email;
    }

    public String getEps_Nombre_Representante() {
        return this.eps_Nombre_Representante;
    }

    public void setEps_Nombre_Representante(String eps_Nombre_Representante) {
        this.eps_Nombre_Representante = eps_Nombre_Representante;
    }

    public String getEps_Tipo() {
        return this.eps_Tipo;
    }

    public void setEps_Tipo(String eps_Tipo) {
        this.eps_Tipo = eps_Tipo;
    }

    public String getEps_Estado() {
        return this.eps_Estado;
    }

    public void setEps_Estado(String eps_Estado) {
        this.eps_Estado = eps_Estado;
    }

    public Date getEps_Fecha() {
        return this.eps_Fecha;
    }

    public void setEps_Fecha(Date eps_Fecha) {
        this.eps_Fecha = eps_Fecha;
    }
    
    @Override
    public String toString() {
        return this.eps_Nombre;
    }
}
