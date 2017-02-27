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
@Table(name = "ADULTOS")
@XStreamAlias("entity")
public class Adultos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ADU_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XStreamAsAttribute
    private Long adu_Id;
    @Column(name = "IPS_ADU_ID")
    @XStreamAsAttribute
    private String ips_Adu_Id;
    @Column(name = "TID_PAC_ADU_ID")
    @XStreamAsAttribute
    private String tid_Pac_Adu_Id;
    @Column(name = "PAC_ADU_ID")
    @XStreamAsAttribute
    private String pac_Adu_Id;
    @Column(name = "PACD_ADU_ID")
    @XStreamAsAttribute
    private Long pacd_Adu_Id;
    @Column(name = "TAD_ADU_ID")
    @XStreamAsAttribute
    private String tad_Adu_Id;
    @Column(name = "PRG_ADU_ID")
    @XStreamAsAttribute
    private String prg_Adu_Id;
    @Column(name = "CTR_ADU_ID")
    @XStreamAsAttribute
    private String ctr_Adu_Id;
    @Column(name = "MNU_ADU_ID")
    @XStreamAsAttribute
    private String mnu_Adu_Id;
    @Column(name = "ACT_ADU_ID")
    @XStreamAsAttribute
    private String act_Adu_Id;
    @Column(name = "DIA_ADU_ID")
    @XStreamAsAttribute
    private String dia_Adu_Id;
    @Column(name = "USU_ADU_ID")
    @XStreamAsAttribute
    private String usu_Adu_Id;
    @Column(name = "ADU_FECHA_REPORTE")
    @XStreamAsAttribute
    private Date adu_Fecha_Reporte;
    @Column(name = "ADU_MUJER_LACTANTE")
    @XStreamAsAttribute
    private String adu_Mujer_Lactante;
    @Column(name = "ADU_PESO")
    @XStreamAsAttribute
    private Double adu_Peso;
    @Column(name = "ADU_TALLA")
    @XStreamAsAttribute
    private Double adu_Talla;
    @Column(name = "ADU_CINTURA")
    @XStreamAsAttribute
    private Double adu_Cintura;
    @Column(name = "ADU_MUNECA")
    @XStreamAsAttribute
    private Double adu_Muneca;
    @Column(name = "ADU_PAQUETE_NUTRICIONAL")
    @XStreamAsAttribute
    private String adu_Paquete_Nutricional;
    @Column(name = "ADU_REMISION")
    @XStreamAsAttribute
    private String adu_Remision;
    @Column(name = "ADU_EDUCACION_NUTRICIONAL")
    @XStreamAsAttribute
    private String adu_Educacion_Nutricional;
    @Column(name = "ADU_ESTADO")
    @XStreamAsAttribute
    private String adu_Estado;
    @Column(name = "ADU_FECHA")
    @XStreamAsAttribute
    private Date adu_Fecha;
    
    @Column(name = "ADUCAMPO1")
    @XStreamAsAttribute
    private String adu_Campo1;
    @Column(name = "ADUCAMPO2")
    @XStreamAsAttribute
    private String adu_Campo2;
    @Column(name = "ADUCAMPO3")
    @XStreamAsAttribute
    private String adu_Campo3;
    @Column(name = "ADUCAMPO4")
    @XStreamAsAttribute
    private String adu_Campo4;
    
    @Column(name = "ADU_SYNC")
    @XStreamOmitField
    private String adu_sync;
    
    @Column(name = "PAC_ADU_NOMBRE", insertable = false, updatable = false)
    @XStreamOmitField
    private String pac_Adu_Nombre;

    public Adultos() {
    }

    public Long getAdu_Id() {
        return this.adu_Id;
    }

    public void setAdu_Id(Long adu_Id) {
        this.adu_Id = adu_Id;
    }

    public String getIps_Adu_Id() {
        return this.ips_Adu_Id;
    }

    public void setIps_Adu_Id(String ips_Adu_Id) {
        this.ips_Adu_Id = ips_Adu_Id;
    }

    public String getTid_Pac_Adu_Id() {
        return this.tid_Pac_Adu_Id;
    }

    public void setTid_Pac_Adu_Id(String tid_Pac_Adu_Id) {
        this.tid_Pac_Adu_Id = tid_Pac_Adu_Id;
    }

    public String getPac_Adu_Id() {
        return this.pac_Adu_Id;
    }

    public void setPac_Adu_Id(String pac_Adu_Id) {
        this.pac_Adu_Id = pac_Adu_Id;
    }

    public Long getPacd_Adu_Id() {
        return this.pacd_Adu_Id;
    }

    public void setPacd_Adu_Id(Long pacd_Adu_Id) {
        this.pacd_Adu_Id = pacd_Adu_Id;
    }

    public String getTad_Adu_Id() {
        return this.tad_Adu_Id;
    }

    public void setTad_Adu_Id(String tad_Adu_Id) {
        this.tad_Adu_Id = tad_Adu_Id;
    }

    public String getPrg_Adu_Id() {
        return this.prg_Adu_Id;
    }

    public void setPrg_Adu_Id(String prg_Adu_Id) {
        this.prg_Adu_Id = prg_Adu_Id;
    }

    public String getCtr_Adu_Id() {
        return this.ctr_Adu_Id;
    }

    public void setCtr_Adu_Id(String ctr_Adu_Id) {
        this.ctr_Adu_Id = ctr_Adu_Id;
    }

    public String getMnu_Adu_Id() {
        return this.mnu_Adu_Id;
    }

    public void setMnu_Adu_Id(String mnu_Adu_Id) {
        this.mnu_Adu_Id = mnu_Adu_Id;
    }

    public String getAct_Adu_Id() {
        return this.act_Adu_Id;
    }

    public void setAct_Adu_Id(String act_Adu_Id) {
        this.act_Adu_Id = act_Adu_Id;
    }

    public String getDia_Adu_Id() {
        return this.dia_Adu_Id;
    }

    public void setDia_Adu_Id(String dia_Adu_Id) {
        this.dia_Adu_Id = dia_Adu_Id;
    }

    public String getUsu_Adu_Id() {
        return this.usu_Adu_Id;
    }

    public void setUsu_Adu_Id(String usu_Adu_Id) {
        this.usu_Adu_Id = usu_Adu_Id;
    }

    public Date getAdu_Fecha_Reporte() {
        return this.adu_Fecha_Reporte;
    }

    public void setAdu_Fecha_Reporte(Date adu_Fecha_Reporte) {
        this.adu_Fecha_Reporte = adu_Fecha_Reporte;
    }

    public String getAdu_Mujer_Lactante() {
        return this.adu_Mujer_Lactante;
    }

    public void setAdu_Mujer_Lactante(String adu_Mujer_Lactante) {
        this.adu_Mujer_Lactante = adu_Mujer_Lactante;
    }

    public Double getAdu_Peso() {
        return this.adu_Peso;
    }

    public void setAdu_Peso(Double adu_Peso) {
        this.adu_Peso = adu_Peso;
    }

    public Double getAdu_Talla() {
        return this.adu_Talla;
    }

    public void setAdu_Talla(Double adu_Talla) {
        this.adu_Talla = adu_Talla;
    }

    public Double getAdu_Cintura() {
        return this.adu_Cintura;
    }

    public void setAdu_Cintura(Double adu_Cintura) {
        this.adu_Cintura = adu_Cintura;
    }

    public Double getAdu_Muneca() {
        return this.adu_Muneca;
    }

    public void setAdu_Muneca(Double adu_Muneca) {
        this.adu_Muneca = adu_Muneca;
    }

    public String getAdu_Paquete_Nutricional() {
        return this.adu_Paquete_Nutricional;
    }

    public void setAdu_Paquete_Nutricional(String adu_Paquete_Nutricional) {
        this.adu_Paquete_Nutricional = adu_Paquete_Nutricional;
    }

    public String getAdu_Remision() {
        return this.adu_Remision;
    }

    public void setAdu_Remision(String adu_Remision) {
        this.adu_Remision = adu_Remision;
    }

    public String getAdu_Educacion_Nutricional() {
        return this.adu_Educacion_Nutricional;
    }

    public void setAdu_Educacion_Nutricional(String adu_Educacion_Nutricional) {
        this.adu_Educacion_Nutricional = adu_Educacion_Nutricional;
    }

    public String getAdu_Estado() {
        return this.adu_Estado;
    }

    public void setAdu_Estado(String adu_Estado) {
        this.adu_Estado = adu_Estado;
    }

    public Date getAdu_Fecha() {
        return this.adu_Fecha;
    }

    public void setAdu_Fecha(Date adu_Fecha) {
        this.adu_Fecha = adu_Fecha;
    }

	public String getAdu_sync() {
		return adu_sync;
	}

	public String getAdu_Campo1() {
		return adu_Campo1;
	}

	public void setAdu_Campo1(String adu_Campo1) {
		this.adu_Campo1 = adu_Campo1;
	}

	public String getAdu_Campo2() {
		return adu_Campo2;
	}

	public void setAdu_Campo2(String adu_Campo2) {
		this.adu_Campo2 = adu_Campo2;
	}

	public String getAdu_Campo3() {
		return adu_Campo3;
	}

	public void setAdu_Campo3(String adu_Campo3) {
		this.adu_Campo3 = adu_Campo3;
	}

	public String getAdu_Campo4() {
		return adu_Campo4;
	}

	public void setAdu_Campo4(String adu_Campo4) {
		this.adu_Campo4 = adu_Campo4;
	}

	public void setAdu_sync(String adu_sync) {
		this.adu_sync = adu_sync;
	}

	public String getPac_Adu_Nombre() {
		return pac_Adu_Nombre;
	}

	public void setPac_Adu_Nombre(String pac_Adu_Nombre) {
		this.pac_Adu_Nombre = pac_Adu_Nombre;
	}
}
