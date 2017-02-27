package co.gov.cundinamarca.sisvan.model.parameters;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "TIPO_IDENTIFICACIONES")
public class Tipo_Identificaciones implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TID_ID")
    private String tid_Id;
    @Column(name = "TID_NOMBRE")
    private String tid_Nombre;
    @Column(name = "TID_APLICA_USUARIO")
    private String tid_Aplica_Usuario;
    @Column(name = "TID_APLICA_IPS")
    private String tid_Aplica_Ips;
    @Column(name = "TID_APLICA_PACIENTE")
    private String tid_Aplica_Paciente;
    @Column(name = "TID_APLICA_ADULTOS")
    private String tid_Aplica_Adultos;
    @Column(name = "TID_APLICA_MENORES")
    private String tid_Aplica_Menores;
    @Column(name = "TID_APLICA_GESTANTES")
    private String tid_Aplica_Gestantes;
    @Column(name = "TID_ESTADO")
    private String tid_Estado;

    public Tipo_Identificaciones() {
    }

    public String getTid_Id() {
        return this.tid_Id;
    }

    public void setTid_Id(String tid_Id) {
        this.tid_Id = tid_Id;
    }

    public String getTid_Nombre() {
        return this.tid_Nombre;
    }

    public void setTid_Nombre(String tid_Nombre) {
        this.tid_Nombre = tid_Nombre;
    }

    public String getTid_Aplica_Usuario() {
        return this.tid_Aplica_Usuario;
    }

    public void setTid_Aplica_Usuario(String tid_Aplica_Usuario) {
        this.tid_Aplica_Usuario = tid_Aplica_Usuario;
    }

    public String getTid_Aplica_Ips() {
        return this.tid_Aplica_Ips;
    }

    public void setTid_Aplica_Ips(String tid_Aplica_Ips) {
        this.tid_Aplica_Ips = tid_Aplica_Ips;
    }

    public String getTid_Aplica_Paciente() {
        return this.tid_Aplica_Paciente;
    }

    public void setTid_Aplica_Paciente(String tid_Aplica_Paciente) {
        this.tid_Aplica_Paciente = tid_Aplica_Paciente;
    }
    
    public String getTid_Aplica_Adultos() {
		return tid_Aplica_Adultos;
	}

	public void setTid_Aplica_Adultos(String tid_Aplica_Adultos) {
		this.tid_Aplica_Adultos = tid_Aplica_Adultos;
	}

	public String getTid_Aplica_Menores() {
		return tid_Aplica_Menores;
	}

	public void setTid_Aplica_Menores(String tid_Aplica_Menores) {
		this.tid_Aplica_Menores = tid_Aplica_Menores;
	}

	public String getTid_Aplica_Gestantes() {
		return tid_Aplica_Gestantes;
	}

	public void setTid_Aplica_Gestantes(String tid_Aplica_Gestantes) {
		this.tid_Aplica_Gestantes = tid_Aplica_Gestantes;
	}

	public String getTid_Estado() {
		return tid_Estado;
	}

	public void setTid_Estado(String tid_Estado) {
		this.tid_Estado = tid_Estado;
	}

	@Override
    public String toString() {
        return this.tid_Nombre;
    }
}
