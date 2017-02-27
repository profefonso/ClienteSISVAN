package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.Column;

@Entity
@Table(name = "PACIENTE_DETALLES")
@XStreamAlias("Paciente_Detalles")
public class Paciente_Detalles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TID_PAC_PACD_ID")
    @XStreamAsAttribute
    private String tid_Pac_Pacd_Id;
    @Id
    @Column(name = "PAC_PACD_ID")
    @XStreamAsAttribute
    private String pac_Pacd_Id;
    @Id
    @Column(name = "PACD_ID")
    @XStreamAsAttribute
    private Long pacd_Id;
    @Column(name = "USU_PACD_ID")
    @XStreamAsAttribute
    private String usu_Pacd_Id;
    @Column(name = "DEP_PACD_ID")
    @XStreamAsAttribute
    private String dep_Pacd_Id;
    @Column(name = "MUN_PACD_ID")
    @XStreamAsAttribute
    private String mun_Pacd_Id;
    @Column(name = "BVE_PACD_ID")
    @XStreamAsAttribute
    private String bve_Pacd_Id;
    @Column(name = "LRE_PACD_ID")
    @XStreamAsAttribute
    private String lre_Pacd_Id;
    @Column(name = "EPS_PACD_ID")
    @XStreamAsAttribute
    private String eps_Pacd_Id;
    @Column(name = "TPO_PACD_ID")
    @XStreamAsAttribute
    private String tpo_Pacd_Id;
    @Column(name = "REG_PACD_ID")
    @XStreamAsAttribute
    private String reg_Pacd_Id;
    @Column(name = "PACD_DIRECCION")
    @XStreamAsAttribute
    private String pacd_Direccion;
    @Column(name = "PACD_TELEFONO")
    @XStreamAsAttribute
    private String pacd_Telefono;
    @Column(name = "PACD_CORREO")
    @XStreamAsAttribute
    private String pacd_Correo;
    @Column(name = "PACD_CELULAR")
    @XStreamAsAttribute
    private String pacd_Celular;
    @Column(name = "PACD_FECHA")
    @XStreamAsAttribute
    private Date pacd_Fecha;
    @Column(name = "PACD_SYNC")
    @XStreamOmitField
    private String pacd_sync;

    public Paciente_Detalles() {
    }

    public String getTid_Pac_Pacd_Id() {
        return this.tid_Pac_Pacd_Id;
    }

    public void setTid_Pac_Pacd_Id(String tid_Pac_Pacd_Id) {
        this.tid_Pac_Pacd_Id = tid_Pac_Pacd_Id;
    }

    public String getPac_Pacd_Id() {
        return this.pac_Pacd_Id;
    }

    public void setPac_Pacd_Id(String pac_Pacd_Id) {
        this.pac_Pacd_Id = pac_Pacd_Id;
    }

    public Long getPacd_Id() {
        return this.pacd_Id;
    }

    public void setPacd_Id(Long pacd_Id) {
        this.pacd_Id = pacd_Id;
    }

    public String getUsu_Pacd_Id() {
        return this.usu_Pacd_Id;
    }

    public void setUsu_Pacd_Id(String usu_Pacd_Id) {
        this.usu_Pacd_Id = usu_Pacd_Id;
    }

    public String getDep_Pacd_Id() {
        return this.dep_Pacd_Id;
    }

    public void setDep_Pacd_Id(String dep_Pacd_Id) {
        this.dep_Pacd_Id = dep_Pacd_Id;
    }

    public String getMun_Pacd_Id() {
        return this.mun_Pacd_Id;
    }

    public void setMun_Pacd_Id(String mun_Pacd_Id) {
        this.mun_Pacd_Id = mun_Pacd_Id;
    }

    public String getBve_Pacd_Id() {
        return this.bve_Pacd_Id;
    }

    public void setBve_Pacd_Id(String bve_Pacd_Id) {
        this.bve_Pacd_Id = bve_Pacd_Id;
    }

    public String getLre_Pacd_Id() {
        return this.lre_Pacd_Id;
    }

    public void setLre_Pacd_Id(String lre_Pacd_Id) {
        this.lre_Pacd_Id = lre_Pacd_Id;
    }

    public String getEps_Pacd_Id() {
        return this.eps_Pacd_Id;
    }

    public void setEps_Pacd_Id(String eps_Pacd_Id) {
        this.eps_Pacd_Id = eps_Pacd_Id;
    }

    public String getTpo_Pacd_Id() {
        return this.tpo_Pacd_Id;
    }

    public void setTpo_Pacd_Id(String tpo_Pacd_Id) {
        this.tpo_Pacd_Id = tpo_Pacd_Id;
    }

    public String getReg_Pacd_Id() {
        return this.reg_Pacd_Id;
    }

    public void setReg_Pacd_Id(String reg_Pacd_Id) {
        this.reg_Pacd_Id = reg_Pacd_Id;
    }

    public String getPacd_Direccion() {
        return this.pacd_Direccion;
    }

    public void setPacd_Direccion(String pacd_Direccion) {
        this.pacd_Direccion = pacd_Direccion;
    }

    public String getPacd_Telefono() {
        return this.pacd_Telefono;
    }

    public void setPacd_Telefono(String pacd_Telefono) {
        this.pacd_Telefono = pacd_Telefono;
    }

    public String getPacd_Correo() {
        return this.pacd_Correo;
    }

    public void setPacd_Correo(String pacd_Correo) {
        this.pacd_Correo = pacd_Correo;
    }

    public String getPacd_Celular() {
        return this.pacd_Celular;
    }

    public void setPacd_Celular(String pacd_Celular) {
        this.pacd_Celular = pacd_Celular;
    }

    public Date getPacd_Fecha() {
        return this.pacd_Fecha;
    }

    public void setPacd_Fecha(Date pacd_Fecha) {
        this.pacd_Fecha = pacd_Fecha;
    }

	public String getPacd_sync() {
		return pacd_sync;
	}

	public void setPacd_sync(String pacd_sync) {
		this.pacd_sync = pacd_sync;
	}
    
}
