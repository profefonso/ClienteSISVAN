package co.gov.cundinamarca.sisvan.model.transactions;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "GESTANTES")
@XStreamAlias("entity")
public class Gestantes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "GES_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XStreamAsAttribute
    private Long ges_Id;
    @Column(name = "IPS_GES_ID")
    @XStreamAsAttribute
    private String ips_Ges_Id;
    @Column(name = "TID_PAC_GES_ID")
    @XStreamAsAttribute
    private String tid_Pac_Ges_Id;
    @Column(name = "PAC_GES_ID")
    @XStreamAsAttribute
    private String pac_Ges_Id;
    @Column(name = "PACD_GES_ID")
    @XStreamAsAttribute
    private Long pacd_Ges_Id;
    @Column(name = "PRG_GES_ID")
    @XStreamAsAttribute
    private String prg_Ges_Id;
    @Column(name = "CTR_GES_ID")
    @XStreamAsAttribute
    private String ctr_Ges_Id;
    @Column(name = "MNU_GES_ID")
    @XStreamAsAttribute
    private String mnu_Ges_Id;
    @Column(name = "ACT_GES_ID")
    @XStreamAsAttribute
    private String act_Ges_Id;
    @Column(name = "RIE_GES_ID")
    @XStreamAsAttribute
    private String rie_Ges_Id;
    @Column(name = "USU_GES_ID")
    @XStreamAsAttribute
    private String usu_Ges_Id;
    @Column(name = "GES_FECHA_REPORTE")
    @XStreamAsAttribute
    private Date ges_Fecha_Reporte;
    @Column(name = "GES_PESO_PREGESTACION")
    @XStreamAsAttribute
    private Double ges_Peso_Pregestacion;
    @Column(name = "GES_PESO_ACTUAL")
    @XStreamAsAttribute
    private Double ges_Peso_Actual;
    @Column(name = "GES_TALLA")
    @XStreamAsAttribute
    private Double ges_Talla;
    @Column(name = "GES_PAQUETE_NUTRICIONAL")
    @XStreamAsAttribute
    private String ges_Paquete_Nutricional;
    @Column(name = "GES_REMISION")
    @XStreamAsAttribute
    private String ges_Remision;
    @Column(name = "GES_EDADGESTACIONAL")
    @XStreamAsAttribute
    private Long ges_Edadgestacional;
    @Column(name = "GES_EDUCACION_NUTRICIONAL")
    @XStreamAsAttribute
    private String ges_Educacion_Nutricional;
    @Column(name = "GES_ESTADO")
    @XStreamAsAttribute
    private String ges_Estado;
    @Column(name = "GES_FECHA")
    @XStreamAsAttribute
    private Date ges_Fecha;
    @Column(name = "GES_ESTADO_NUTRICIONAL")
    @XStreamAsAttribute
    private String ges_Estado_Nutricional;
    
    @Column(name = "GESCAMPO1")
    @XStreamAsAttribute
    private String ges_Campo1;
    @Column(name = "GESCAMPO2")
    @XStreamAsAttribute
    private String ges_Campo2;
    @Column(name = "GESCAMPO3")
    @XStreamAsAttribute
    private String ges_Campo3;
    @Column(name = "GESCAMPO4")
    @XStreamAsAttribute
    private String ges_Campo4;
    
    @Column(name = "GES_SYNC")
    @XStreamOmitField
    private String ges_sync;
    
    @Column(name = "GES_ADU_NOMBRE", insertable = false, updatable = false)
    @XStreamOmitField
    private String ges_Adu_Nombre;

    public Gestantes() {
    }

    public Long getGes_Id() {
        return this.ges_Id;
    }

    public void setGes_Id(Long ges_Id) {
        this.ges_Id = ges_Id;
    }

    public String getIps_Ges_Id() {
        return this.ips_Ges_Id;
    }

    public void setIps_Ges_Id(String ips_Ges_Id) {
        this.ips_Ges_Id = ips_Ges_Id;
    }

    public String getTid_Pac_Ges_Id() {
        return this.tid_Pac_Ges_Id;
    }

    public void setTid_Pac_Ges_Id(String tid_Pac_Ges_Id) {
        this.tid_Pac_Ges_Id = tid_Pac_Ges_Id;
    }

    public String getPac_Ges_Id() {
        return this.pac_Ges_Id;
    }

    public void setPac_Ges_Id(String pac_Ges_Id) {
        this.pac_Ges_Id = pac_Ges_Id;
    }

    public Long getPacd_Ges_Id() {
        return this.pacd_Ges_Id;
    }

    public void setPacd_Ges_Id(Long pacd_Ges_Id) {
        this.pacd_Ges_Id = pacd_Ges_Id;
    }

    public String getPrg_Ges_Id() {
        return this.prg_Ges_Id;
    }

    public void setPrg_Ges_Id(String prg_Ges_Id) {
        this.prg_Ges_Id = prg_Ges_Id;
    }

    public String getCtr_Ges_Id() {
        return this.ctr_Ges_Id;
    }

    public void setCtr_Ges_Id(String ctr_Ges_Id) {
        this.ctr_Ges_Id = ctr_Ges_Id;
    }

    public String getMnu_Ges_Id() {
        return this.mnu_Ges_Id;
    }

    public void setMnu_Ges_Id(String mnu_Ges_Id) {
        this.mnu_Ges_Id = mnu_Ges_Id;
    }

    public String getAct_Ges_Id() {
        return this.act_Ges_Id;
    }

    public void setAct_Ges_Id(String act_Ges_Id) {
        this.act_Ges_Id = act_Ges_Id;
    }

    public String getRie_Ges_Id() {
        return this.rie_Ges_Id;
    }

    public void setRie_Ges_Id(String rie_Ges_Id) {
        this.rie_Ges_Id = rie_Ges_Id;
    }

    public String getUsu_Ges_Id() {
        return this.usu_Ges_Id;
    }

    public void setUsu_Ges_Id(String usu_Ges_Id) {
        this.usu_Ges_Id = usu_Ges_Id;
    }

    public Date getGes_Fecha_Reporte() {
        return this.ges_Fecha_Reporte;
    }

    public void setGes_Fecha_Reporte(Date ges_Fecha_Reporte) {
        this.ges_Fecha_Reporte = ges_Fecha_Reporte;
    }

    public Double getGes_Peso_Pregestacion() {
        return this.ges_Peso_Pregestacion;
    }

    public void setGes_Peso_Pregestacion(Double ges_Peso_Pregestacion) {
        this.ges_Peso_Pregestacion = ges_Peso_Pregestacion;
    }

    public Double getGes_Peso_Actual() {
        return this.ges_Peso_Actual;
    }

    public void setGes_Peso_Actual(Double ges_Peso_Actual) {
        this.ges_Peso_Actual = ges_Peso_Actual;
    }

    public Double getGes_Talla() {
        return this.ges_Talla;
    }

    public void setGes_Talla(Double ges_Talla) {
        this.ges_Talla = ges_Talla;
    }

    public String getGes_Paquete_Nutricional() {
        return this.ges_Paquete_Nutricional;
    }

    public void setGes_Paquete_Nutricional(String ges_Paquete_Nutricional) {
        this.ges_Paquete_Nutricional = ges_Paquete_Nutricional;
    }

    public String getGes_Remision() {
        return this.ges_Remision;
    }

    public void setGes_Remision(String ges_Remision) {
        this.ges_Remision = ges_Remision;
    }

    public Long getGes_Edadgestacional() {
        return this.ges_Edadgestacional;
    }

    public void setGes_Edadgestacional(Long ges_Edadgestacional) {
        this.ges_Edadgestacional = ges_Edadgestacional;
    }

    public String getGes_Educacion_Nutricional() {
        return this.ges_Educacion_Nutricional;
    }

    public void setGes_Educacion_Nutricional(String ges_Educacion_Nutricional) {
        this.ges_Educacion_Nutricional = ges_Educacion_Nutricional;
    }

    public String getGes_Estado() {
        return this.ges_Estado;
    }

    public void setGes_Estado(String ges_Estado) {
        this.ges_Estado = ges_Estado;
    }

    public Date getGes_Fecha() {
        return this.ges_Fecha;
    }

    public void setGes_Fecha(Date ges_Fecha) {
        this.ges_Fecha = ges_Fecha;
    }
    
	public String getGes_Estado_Nutricional() {
		return ges_Estado_Nutricional;
	}

	public void setGes_Estado_Nutricional(String ges_Estado_Nutricional) {
		this.ges_Estado_Nutricional = ges_Estado_Nutricional;
	}

	public String getGes_sync() {
		return ges_sync;
	}

	public String getGes_Campo1() {
		return ges_Campo1;
	}

	public void setGes_Campo1(String ges_Campo1) {
		this.ges_Campo1 = ges_Campo1;
	}

	public String getGes_Campo2() {
		return ges_Campo2;
	}

	public void setGes_Campo2(String ges_Campo2) {
		this.ges_Campo2 = ges_Campo2;
	}

	public String getGes_Campo3() {
		return ges_Campo3;
	}

	public void setGes_Campo3(String ges_Campo3) {
		this.ges_Campo3 = ges_Campo3;
	}

	public String getGes_Campo4() {
		return ges_Campo4;
	}

	public void setGes_Campo4(String ges_Campo4) {
		this.ges_Campo4 = ges_Campo4;
	}

	public void setGes_sync(String adu_sync) {
		this.ges_sync = adu_sync;
	}

	public String getGes_Adu_Nombre() {
		return ges_Adu_Nombre;
	}

	public void setGes_Adu_Nombre(String ges_Adu_Nombre) {
		this.ges_Adu_Nombre = ges_Adu_Nombre;
	}
}
