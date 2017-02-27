package co.gov.cundinamarca.sisvan.model.transactions;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

import javax.persistence.Column;

@Entity
@Table(name = "MENORES")
@XStreamAlias("entity")
public class Menores implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MNOR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XStreamAsAttribute
    private Long mnor_Id;
    @Column(name = "IPS_MNOR_ID")
    @XStreamAsAttribute
    private String ips_Mnor_Id;
    @Column(name = "TID_PAC_MNOR_ID")
    @XStreamAsAttribute
    private String tid_Pac_Mnor_Id;
    @Column(name = "PAC_MNOR_ID")
    @XStreamAsAttribute
    private String pac_Mnor_Id;
    @Column(name = "PACD_MNOR_ID")
    @XStreamAsAttribute
    private Long pacd_Mnor_Id;
    @Column(name = "CDE_MNOR_ID")
    @XStreamAsAttribute
    private String cde_Mnor_Id;
    @Column(name = "LAC_MNOR_ID")
    @XStreamAsAttribute
    private String lac_Mnor_Id;
    @Column(name = "PRG_MNOR_ID")
    @XStreamAsAttribute
    private String prg_Mnor_Id;
    @Column(name = "MNU_MNOR_ID")
    @XStreamAsAttribute
    private String mnu_Mnor_Id;
    @Column(name = "ACT_MNOR_ID")
    @XStreamAsAttribute
    private String act_Mnor_Id;
    @Column(name = "CDI_MNOR_ID")
    @XStreamAsAttribute
    private String cdi_Mnor_Id;
    @Column(name = "TCO_MNOR_ID")
    @XStreamAsAttribute
    private String tco_Mnor_Id;
    @Column(name = "DES_MNOR_ID")
    @XStreamAsAttribute
    private String des_Mnor_Id;
    @Column(name = "ESP_MNOR_ID")
    @XStreamAsAttribute
    private String esp_Mnor_Id;
    @Column(name = "EME_MNOR_ID")
    @XStreamAsAttribute
    private String eme_Mnor_Id;
    @Column(name = "CTR_MNOR_ID")
    @XStreamAsAttribute
    private String ctr_Mnor_Id;
    @Column(name = "USU_MNOR_ID")
    @XStreamAsAttribute
    private String usu_Mnor_Id;
    @Column(name = "MNOR_FECHA_REPORTE")
    @XStreamAsAttribute
    private Date mnor_Fecha_Reporte;
    @Column(name = "MNOR_CONTROL")
    @XStreamAsAttribute
    private String mnor_Control;
    @Column(name = "MNOR_PESO")
    @XStreamAsAttribute
    private Double mnor_Peso;
    @Column(name = "MNOR_TALLA")
    @XStreamAsAttribute
    private Double mnor_Talla;
    @Column(name = "MNOR_PERIMETRO_CEFALICO")
    @XStreamAsAttribute
    private Double mnor_Perimetro_Cefalico;
    @Column(name = "MNOR_BRAZO")
    @XStreamAsAttribute
    private Double mnor_Brazo;
    @Column(name = "MNOR_TIEMPO_LACTANCIA")
    @XStreamAsAttribute
    private Long mnor_Tiempo_Lactancia;
    @Column(name = "MNOR_PAQUETE_NUTRICIONAL")
    @XStreamAsAttribute
    private String mnor_Paquete_Nutricional;
    @Column(name = "MNOR_MOTRICIDAD_GRUESA")
    @XStreamAsAttribute
    private Double mnor_Motricidad_Gruesa;
    @Column(name = "MNOR_MOTRICIDAD_FINA")
    @XStreamAsAttribute
    private Double mnor_Motricidad_Fina;
    @Column(name = "MNOR_AUDICION_LENGUAJE")
    @XStreamAsAttribute
    private Double mnor_Audicion_Lenguaje;
    @Column(name = "MNOR_PERSONAL_SOCIAL")
    @XStreamAsAttribute
    private Double mnor_Personal_Social;
    @Column(name = "MNOR_ALTERACION_VISUAL")
    @XStreamAsAttribute
    private String mnor_Alteracion_Visual;
    @Column(name = "MNOR_ALTERACION_AUDITIVA")
    @XStreamAsAttribute
    private String mnor_Alteracion_Auditiva;
    @Column(name = "MNOR_ERA")
    @XStreamAsAttribute
    private String mnor_Era;
    @Column(name = "MNOR_EDA")
    @XStreamAsAttribute
    private String mnor_Eda;
    @Column(name = "MNOR_DESPARASITACION")
    @XStreamAsAttribute
    private String mnor_Desparasitacion;
    @Column(name = "MNOR_APLICACION_CANCER")
    @XStreamAsAttribute
    private String mnor_Aplicacion_Cancer;
    @Column(name = "MNOR_ESTADO")
    @XStreamAsAttribute
    private String mnor_Estado;
    @Column(name = "MNOR_FECHA")
    @XStreamAsAttribute
    private Date mnor_Fecha;
    @Column(name = "MNOR_ZSTALLA_EDAD")
    @XStreamAsAttribute
    private Double mnor_Zstalla_Edad;
    @Column(name = "MNOR_ZSPESO_EDAD")
    @XStreamAsAttribute
    private Double mnor_Zspeso_Edad;
    @Column(name = "MNOR_ZSIMC_EDAD")
    @XStreamAsAttribute
    private Double mnor_Zsimc_Edad;
    @Column(name = "MNOR_ZSPESO_TALLA")
    @XStreamAsAttribute
    private Double mnor_Zspeso_Talla;
    @Column(name = "MNOR_ZSPCEFALICO_EDAD")
    @XStreamAsAttribute
    private Double mnor_Zspcefalico_Edad;
    
    @Column(name = "MNORCAMPO1")
    @XStreamAsAttribute
    private String mnor_Campo1;
    @Column(name = "MNORCAMPO2")
    @XStreamAsAttribute
    private String mnor_Campo2;
    @Column(name = "MNORCAMPO3")
    @XStreamAsAttribute
    private String mnor_Campo3;
    @Column(name = "MNORCAMPO4")
    @XStreamAsAttribute
    private String mnor_Campo4;
    
    @Column(name = "MNOR_SYNC")
    @XStreamOmitField
    private String mnor_sync;
    @XStreamOmitField
    private String pacienteNombreFull;

	public Menores() {
    }

    public Long getMnor_Id() {
        return this.mnor_Id;
    }

    public void setMnor_Id(Long mnor_Id) {
        this.mnor_Id = mnor_Id;
    }

    public String getIps_Mnor_Id() {
        return this.ips_Mnor_Id;
    }

    public void setIps_Mnor_Id(String ips_Mnor_Id) {
        this.ips_Mnor_Id = ips_Mnor_Id;
    }

    public String getTid_Pac_Mnor_Id() {
        return this.tid_Pac_Mnor_Id;
    }

    public void setTid_Pac_Mnor_Id(String tid_Pac_Mnor_Id) {
        this.tid_Pac_Mnor_Id = tid_Pac_Mnor_Id;
    }

    public String getPac_Mnor_Id() {
        return this.pac_Mnor_Id;
    }

    public void setPac_Mnor_Id(String pac_Mnor_Id) {
        this.pac_Mnor_Id = pac_Mnor_Id;
    }

    public Long getPacd_Mnor_Id() {
        return this.pacd_Mnor_Id;
    }

    public void setPacd_Mnor_Id(Long pacd_Mnor_Id) {
        this.pacd_Mnor_Id = pacd_Mnor_Id;
    }

    public String getCde_Mnor_Id() {
        return this.cde_Mnor_Id;
    }

    public void setCde_Mnor_Id(String cde_Mnor_Id) {
        this.cde_Mnor_Id = cde_Mnor_Id;
    }

    public String getLac_Mnor_Id() {
        return this.lac_Mnor_Id;
    }

    public void setLac_Mnor_Id(String lac_Mnor_Id) {
        this.lac_Mnor_Id = lac_Mnor_Id;
    }

    public String getPrg_Mnor_Id() {
        return this.prg_Mnor_Id;
    }

    public void setPrg_Mnor_Id(String prg_Mnor_Id) {
        this.prg_Mnor_Id = prg_Mnor_Id;
    }

    public String getMnu_Mnor_Id() {
        return this.mnu_Mnor_Id;
    }

    public void setMnu_Mnor_Id(String mnu_Mnor_Id) {
        this.mnu_Mnor_Id = mnu_Mnor_Id;
    }

    public String getAct_Mnor_Id() {
        return this.act_Mnor_Id;
    }

    public void setAct_Mnor_Id(String act_Mnor_Id) {
        this.act_Mnor_Id = act_Mnor_Id;
    }

    public String getCdi_Mnor_Id() {
        return this.cdi_Mnor_Id;
    }

    public void setCdi_Mnor_Id(String cdi_Mnor_Id) {
        this.cdi_Mnor_Id = cdi_Mnor_Id;
    }

    public String getTco_Mnor_Id() {
        return this.tco_Mnor_Id;
    }

    public void setTco_Mnor_Id(String tco_Mnor_Id) {
        this.tco_Mnor_Id = tco_Mnor_Id;
    }

    public String getDes_Mnor_Id() {
        return this.des_Mnor_Id;
    }

    public void setDes_Mnor_Id(String des_Mnor_Id) {
        this.des_Mnor_Id = des_Mnor_Id;
    }

    public String getEsp_Mnor_Id() {
        return this.esp_Mnor_Id;
    }

    public void setEsp_Mnor_Id(String esp_Mnor_Id) {
        this.esp_Mnor_Id = esp_Mnor_Id;
    }

    public String getEme_Mnor_Id() {
        return this.eme_Mnor_Id;
    }

    public void setEme_Mnor_Id(String eme_Mnor_Id) {
        this.eme_Mnor_Id = eme_Mnor_Id;
    }

    public String getCtr_Mnor_Id() {
        return this.ctr_Mnor_Id;
    }

    public void setCtr_Mnor_Id(String ctr_Mnor_Id) {
        this.ctr_Mnor_Id = ctr_Mnor_Id;
    }

    public String getUsu_Mnor_Id() {
        return this.usu_Mnor_Id;
    }

    public void setUsu_Mnor_Id(String usu_Mnor_Id) {
        this.usu_Mnor_Id = usu_Mnor_Id;
    }

    public Date getMnor_Fecha_Reporte() {
        return this.mnor_Fecha_Reporte;
    }

    public void setMnor_Fecha_Reporte(Date mnor_Fecha_Reporte) {
        this.mnor_Fecha_Reporte = mnor_Fecha_Reporte;
    }

    public String getMnor_Control() {
        return this.mnor_Control;
    }

    public void setMnor_Control(String mnor_Control) {
        this.mnor_Control = mnor_Control;
    }

    public Double getMnor_Peso() {
        return this.mnor_Peso;
    }

    public void setMnor_Peso(Double mnor_Peso) {
        this.mnor_Peso = mnor_Peso;
    }

    public Double getMnor_Talla() {
        return this.mnor_Talla;
    }

    public void setMnor_Talla(Double mnor_Talla) {
        this.mnor_Talla = mnor_Talla;
    }

    public Double getMnor_Perimetro_Cefalico() {
        return this.mnor_Perimetro_Cefalico;
    }

    public void setMnor_Perimetro_Cefalico(Double mnor_Perimetro_Cefalico) {
        this.mnor_Perimetro_Cefalico = mnor_Perimetro_Cefalico;
    }

    public Double getMnor_Brazo() {
        return this.mnor_Brazo;
    }

    public void setMnor_Brazo(Double mnor_Brazo) {
        this.mnor_Brazo = mnor_Brazo;
    }

    public Long getMnor_Tiempo_Lactancia() {
        return this.mnor_Tiempo_Lactancia;
    }

    public void setMnor_Tiempo_Lactancia(Long mnor_Tiempo_Lactancia) {
        this.mnor_Tiempo_Lactancia = mnor_Tiempo_Lactancia;
    }

    public String getMnor_Paquete_Nutricional() {
        return this.mnor_Paquete_Nutricional;
    }

    public void setMnor_Paquete_Nutricional(String mnor_Paquete_Nutricional) {
        this.mnor_Paquete_Nutricional = mnor_Paquete_Nutricional;
    }

    public Double getMnor_Motricidad_Gruesa() {
        return this.mnor_Motricidad_Gruesa;
    }

    public void setMnor_Motricidad_Gruesa(Double mnor_Motricidad_Gruesa) {
        this.mnor_Motricidad_Gruesa = mnor_Motricidad_Gruesa;
    }

    public Double getMnor_Motricidad_Fina() {
        return this.mnor_Motricidad_Fina;
    }

    public void setMnor_Motricidad_Fina(Double mnor_Motricidad_Fina) {
        this.mnor_Motricidad_Fina = mnor_Motricidad_Fina;
    }

    public Double getMnor_Audicion_Lenguaje() {
        return this.mnor_Audicion_Lenguaje;
    }

    public void setMnor_Audicion_Lenguaje(Double mnor_Audicion_Lenguaje) {
        this.mnor_Audicion_Lenguaje = mnor_Audicion_Lenguaje;
    }

    public Double getMnor_Personal_Social() {
        return this.mnor_Personal_Social;
    }

    public void setMnor_Personal_Social(Double mnor_Personal_Social) {
        this.mnor_Personal_Social = mnor_Personal_Social;
    }

    public String getMnor_Alteracion_Visual() {
        return this.mnor_Alteracion_Visual;
    }

    public void setMnor_Alteracion_Visual(String mnor_Alteracion_Visual) {
        this.mnor_Alteracion_Visual = mnor_Alteracion_Visual;
    }

    public String getMnor_Alteracion_Auditiva() {
        return this.mnor_Alteracion_Auditiva;
    }

    public void setMnor_Alteracion_Auditiva(String mnor_Alteracion_Auditiva) {
        this.mnor_Alteracion_Auditiva = mnor_Alteracion_Auditiva;
    }

    public String getMnor_Era() {
        return this.mnor_Era;
    }

    public void setMnor_Era(String mnor_Era) {
        this.mnor_Era = mnor_Era;
    }

    public String getMnor_Eda() {
        return this.mnor_Eda;
    }

    public void setMnor_Eda(String mnor_Eda) {
        this.mnor_Eda = mnor_Eda;
    }

    public String getMnor_Desparasitacion() {
        return this.mnor_Desparasitacion;
    }

    public void setMnor_Desparasitacion(String mnor_Desparasitacion) {
        this.mnor_Desparasitacion = mnor_Desparasitacion;
    }

    public String getMnor_Aplicacion_Cancer() {
        return this.mnor_Aplicacion_Cancer;
    }

    public void setMnor_Aplicacion_Cancer(String mnor_Aplicacion_Cancer) {
        this.mnor_Aplicacion_Cancer = mnor_Aplicacion_Cancer;
    }

    public String getMnor_Estado() {
        return this.mnor_Estado;
    }

    public void setMnor_Estado(String mnor_Estado) {
        this.mnor_Estado = mnor_Estado;
    }

    public Date getMnor_Fecha() {
        return this.mnor_Fecha;
    }

    public void setMnor_Fecha(Date mnor_Fecha) {
        this.mnor_Fecha = mnor_Fecha;
    }
        
	public Double getMnor_Zstalla_Edad() {
		return mnor_Zstalla_Edad;
	}

	public void setMnor_Zstalla_Edad(Double mnor_Zstalla_Edad) {
		this.mnor_Zstalla_Edad = mnor_Zstalla_Edad;
	}

	public Double getMnor_Zspeso_Edad() {
		return mnor_Zspeso_Edad;
	}

	public void setMnor_Zspeso_Edad(Double mnor_Zspeso_Edad) {
		this.mnor_Zspeso_Edad = mnor_Zspeso_Edad;
	}

	public Double getMnor_Zsimc_Edad() {
		return mnor_Zsimc_Edad;
	}

	public void setMnor_Zsimc_Edad(Double mnor_Zsimc_Edad) {
		this.mnor_Zsimc_Edad = mnor_Zsimc_Edad;
	}

	public Double getMnor_Zspeso_Talla() {
		return mnor_Zspeso_Talla;
	}

	public void setMnor_Zspeso_Talla(Double mnor_Zspeso_Talla) {
		this.mnor_Zspeso_Talla = mnor_Zspeso_Talla;
	}
	
	public Double getMnor_Zspcefalico_Edad() {
		return mnor_Zspcefalico_Edad;
	}

	public void setMnor_Zspcefalico_Edad(Double mnor_Zspcefalico_Edad) {
		this.mnor_Zspcefalico_Edad = mnor_Zspcefalico_Edad;
	}

	public String getMnor_Campo1() {
		return mnor_Campo1;
	}

	public void setMnor_Campo1(String mnor_Campo1) {
		this.mnor_Campo1 = mnor_Campo1;
	}

	public String getMnor_Campo2() {
		return mnor_Campo2;
	}

	public void setMnor_Campo2(String mnor_Campo2) {
		this.mnor_Campo2 = mnor_Campo2;
	}

	public String getMnor_Campo3() {
		return mnor_Campo3;
	}

	public void setMnor_Campo3(String mnor_Campo3) {
		this.mnor_Campo3 = mnor_Campo3;
	}

	public String getMnor_Campo4() {
		return mnor_Campo4;
	}

	public void setMnor_Campo4(String mnor_Campo4) {
		this.mnor_Campo4 = mnor_Campo4;
	}

	public String getMnor_sync() {
		return mnor_sync;
	}

	public void setMnor_sync(String mnor_sync) {
		this.mnor_sync = mnor_sync;
	}

	public String getPacienteNombreFull() {
		return pacienteNombreFull;
	}

	public void setPacienteNombreFull(String pacienteNombreFull) {
		this.pacienteNombreFull = pacienteNombreFull;
	}
}
