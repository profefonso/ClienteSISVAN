package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "INSTITUCIONES")
public class Instituciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "IPS_ID")
    private String ips_Id;
    @Column(name = "DEP_IPS_ID")
    private String dep_Ips_Id;
    @Column(name = "MUN_IPS_ID")
    private String mun_Ips_Id;
    @Column(name = "TID_IPS_ID")
    private String tid_Ips_Id;
    @Column(name = "USU_IPS_ID")
    private String usu_Ips_Id;
    @Column(name = "IPS_NOMBRE")
    private String ips_Nombre;
    @Column(name = "IPS_DIGITOVERIFICACION")
    private Long ips_Digitoverificacion;
    @Column(name = "IPS_IDENTIFICACION")
    private String ips_Identificacion;
    @Column(name = "IPS_DIRECCION")
    private String ips_Direccion;
    @Column(name = "IPS_REPRESENTANTE_LEGAL")
    private String ips_Representante_Legal;
    @Column(name = "IPS_CORREO_ELECTRONICO")
    private String ips_Correo_Electronico;
    @Column(name = "IPS_RESPONSABLE_NOTIFICACION")
    private String ips_Responsable_Notificacion;
    @Column(name = "IPS_TELEFONO")
    private String ips_Telefono;
    @Column(name = "IPS_FECHA_CONSTITUCION")
    private Date ips_Fecha_Constitucion;
    @Column(name = "IPS_NIVEL")
    private String ips_Nivel;
    @Column(name = "IPS_CARACTERISTICA")
    private String ips_Caracteristica;
    @Column(name = "IPS_ESTADO")
    private String ips_Estado;
    @Column(name = "IPS_FECHA")
    private Date ips_Fecha;

    public Instituciones() {
    }

    public String getIps_Id() {
        return this.ips_Id;
    }

    public void setIps_Id(String ips_Id) {
        this.ips_Id = ips_Id;
    }

    public String getDep_Ips_Id() {
        return this.dep_Ips_Id;
    }

    public void setDep_Ips_Id(String dep_Ips_Id) {
        this.dep_Ips_Id = dep_Ips_Id;
    }

    public String getMun_Ips_Id() {
        return this.mun_Ips_Id;
    }

    public void setMun_Ips_Id(String mun_Ips_Id) {
        this.mun_Ips_Id = mun_Ips_Id;
    }

    public String getTid_Ips_Id() {
        return this.tid_Ips_Id;
    }

    public void setTid_Ips_Id(String tid_Ips_Id) {
        this.tid_Ips_Id = tid_Ips_Id;
    }

    public String getUsu_Ips_Id() {
        return this.usu_Ips_Id;
    }

    public void setUsu_Ips_Id(String usu_Ips_Id) {
        this.usu_Ips_Id = usu_Ips_Id;
    }

    public String getIps_Nombre() {
        return this.ips_Nombre;
    }

    public void setIps_Nombre(String ips_Nombre) {
        this.ips_Nombre = ips_Nombre;
    }

    public Long getIps_Digitoverificacion() {
        return this.ips_Digitoverificacion;
    }

    public void setIps_Digitoverificacion(Long ips_Digitoverificacion) {
        this.ips_Digitoverificacion = ips_Digitoverificacion;
    }

    public String getIps_Identificacion() {
        return this.ips_Identificacion;
    }

    public void setIps_Identificacion(String ips_Identificacion) {
        this.ips_Identificacion = ips_Identificacion;
    }

    public String getIps_Direccion() {
        return this.ips_Direccion;
    }

    public void setIps_Direccion(String ips_Direccion) {
        this.ips_Direccion = ips_Direccion;
    }

    public String getIps_Representante_Legal() {
        return this.ips_Representante_Legal;
    }

    public void setIps_Representante_Legal(String ips_Representante_Legal) {
        this.ips_Representante_Legal = ips_Representante_Legal;
    }

    public String getIps_Correo_Electronico() {
        return this.ips_Correo_Electronico;
    }

    public void setIps_Correo_Electronico(String ips_Correo_Electronico) {
        this.ips_Correo_Electronico = ips_Correo_Electronico;
    }

    public String getIps_Responsable_Notificacion() {
        return this.ips_Responsable_Notificacion;
    }

    public void setIps_Responsable_Notificacion(String ips_Responsable_Notificacion) {
        this.ips_Responsable_Notificacion = ips_Responsable_Notificacion;
    }

    public String getIps_Telefono() {
        return this.ips_Telefono;
    }

    public void setIps_Telefono(String ips_Telefono) {
        this.ips_Telefono = ips_Telefono;
    }

    public Date getIps_Fecha_Constitucion() {
        return this.ips_Fecha_Constitucion;
    }

    public void setIps_Fecha_Constitucion(Date ips_Fecha_Constitucion) {
        this.ips_Fecha_Constitucion = ips_Fecha_Constitucion;
    }

    public String getIps_Nivel() {
        return this.ips_Nivel;
    }

    public void setIps_Nivel(String ips_Nivel) {
        this.ips_Nivel = ips_Nivel;
    }

    public String getIps_Caracteristica() {
        return this.ips_Caracteristica;
    }

    public void setIps_Caracteristica(String ips_Caracteristica) {
        this.ips_Caracteristica = ips_Caracteristica;
    }

    public String getIps_Estado() {
        return this.ips_Estado;
    }

    public void setIps_Estado(String ips_Estado) {
        this.ips_Estado = ips_Estado;
    }

    public Date getIps_Fecha() {
        return this.ips_Fecha;
    }

    public void setIps_Fecha(Date ips_Fecha) {
        this.ips_Fecha = ips_Fecha;
    }
    
    @Override
    public String toString() {
        return this.ips_Nombre;
    }
}
