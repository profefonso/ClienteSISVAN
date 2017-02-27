package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.beans.property.StringProperty;

import javax.persistence.Column;

@Entity
@Table(name = "PACIENTES")
@XStreamAlias("Pacientes")
public class Pacientes implements Serializable {
    
    private static final long serialVersionUID = 1L;
   
    @Id
    @Column(name = "PAC_ID")
    @XStreamAsAttribute
    private String pac_Id;
    @Column(name = "TID_PAC_ID")
    @XStreamAsAttribute
    private String tid_Pac_Id;
    @Column(name = "USU_PAC_ID")
    @XStreamAsAttribute
    private String usu_Pac_Id;
    @Column(name = "PAC_PRIMERAPELLIDO")
    @XStreamAsAttribute
    private String pac_Primerapellido;
    @Column(name = "PAC_SEGUNDOAPELLIDO")
    @XStreamAsAttribute
    private String pac_Segundoapellido;
    @Column(name = "PAC_PRIMERNOMBRE")
    @XStreamAsAttribute
    private String pac_Primernombre;
    @Column(name = "PAC_SEGUNDONOMBRE")
    @XStreamAsAttribute
    private String pac_Segundonombre;
    @Column(name = "PAC_SEXO")
    @XStreamAsAttribute
    private String pac_Sexo;
    @Column(name = "PAC_FECHANACIMIENTO")
    @XStreamAsAttribute
    private Date pac_Fechanacimiento;
    @Column(name = "PAC_ESTADO")
    @XStreamAsAttribute
    private String pac_Estado;
    @Column(name = "PAC_FECHA")
    @XStreamAsAttribute
    private Date pac_Fecha;
    @Column(name = "PAC_SYNC")
    @XStreamOmitField
    private String pac_sync;
    @XStreamOmitField
    private String nombreFullPaciente;
   

	public Pacientes() {
    }

    public String getTid_Pac_Id() {
        return this.tid_Pac_Id;
    }

    public void setTid_Pac_Id(String tid_Pac_Id) {
        this.tid_Pac_Id = tid_Pac_Id;
    }

    public String getPac_Id() {
        return this.pac_Id;
    }

    public void setPac_Id(String pac_Id) {
        this.pac_Id = pac_Id;
    }

    public String getUsu_Pac_Id() {
        return this.usu_Pac_Id;
    }

    public void setUsu_Pac_Id(String usu_Pac_Id) {
        this.usu_Pac_Id = usu_Pac_Id;
    }

    public String getPac_Primerapellido() {
        return this.pac_Primerapellido;
    }

    public void setPac_Primerapellido(String pac_Primerapellido) {
        this.pac_Primerapellido = pac_Primerapellido;
    }

    public String getPac_Segundoapellido() {
        return this.pac_Segundoapellido;
    }

    public void setPac_Segundoapellido(String pac_Segundoapellido) {
        this.pac_Segundoapellido = pac_Segundoapellido;
    }

    public String getPac_Primernombre() {
        return this.pac_Primernombre;
    }

    public void setPac_Primernombre(String pac_Primernombre) {
        this.pac_Primernombre = pac_Primernombre;
    }

    public String getPac_Segundonombre() {
        return this.pac_Segundonombre;
    }

    public void setPac_Segundonombre(String pac_Segundonombre) {
        this.pac_Segundonombre = pac_Segundonombre;
    }

    public String getPac_Sexo() {
        return this.pac_Sexo;
    }

    public void setPac_Sexo(String pac_Sexo) {
        this.pac_Sexo = pac_Sexo;
    }

    public Date getPac_Fechanacimiento() {
        return this.pac_Fechanacimiento;
    }

    public void setPac_Fechanacimiento(Date pac_Fechanacimiento) {
        this.pac_Fechanacimiento = pac_Fechanacimiento;
    }

    public String getPac_Estado() {
        return this.pac_Estado;
    }

    public void setPac_Estado(String pac_Estado) {
        this.pac_Estado = pac_Estado;
    }

    public Date getPac_Fecha() {
        return this.pac_Fecha;
    }

    public void setPac_Fecha(Date pac_Fecha) {
        this.pac_Fecha = pac_Fecha;
    }
    
    public String getNombreFullPaciente() {
    	nombreFullPaciente="";
    	if(pac_Primerapellido!=null && !pac_Primerapellido.equals("")){
    		nombreFullPaciente=nombreFullPaciente+pac_Primerapellido+" ";
    	}
    		
    	if(pac_Segundoapellido!=null && !pac_Segundoapellido.equals("")){
    		nombreFullPaciente=nombreFullPaciente+pac_Segundoapellido+" ";
    	}
    	
    	if(pac_Primernombre!=null && !pac_Primernombre.equals("")){
    		nombreFullPaciente=nombreFullPaciente+pac_Primernombre+" ";
    	}
    		
    	if(pac_Segundonombre!=null && !pac_Segundonombre.equals("")){
    		nombreFullPaciente=nombreFullPaciente+pac_Segundonombre+" ";
    	}
    	
		return nombreFullPaciente;
	}
    
	public String getPac_sync() {
		return pac_sync;
	}

	public void setPac_sync(String pac_sync) {
		this.pac_sync = pac_sync;
	}

	public void setNombreFullPaciente(String nombreFullPaciente) {
		this.nombreFullPaciente = nombreFullPaciente;
	}
}
