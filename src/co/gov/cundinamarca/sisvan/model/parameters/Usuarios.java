package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuarios implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USU_ID")
    private String usu_Id;
    @Column(name = "PER_USU_ID")
    private String per_Usu_Id;
    @Column(name = "DEP_USU_ID")
    private String dep_Usu_Id;
    @Column(name = "MUN_USU_ID")
    private String mun_Usu_Id;
    @Column(name = "TID_USU_ID")
    private String tid_Usu_Id;
    @Column(name = "USU_CLAVE")
    private String usu_Clave;
    @Column(name = "USU_IDENTIFICACION")
    private String usu_Identificacion;
    @Column(name = "USU_PRIMER_APELLIDO")
    private String usu_Primer_Apellido;
    @Column(name = "USU_SEGUNDO_APELLIDO")
    private String usu_Segundo_Apellido;
    @Column(name = "USU_PRIMER_NOMBRE")
    private String usu_Primer_Nombre;
    @Column(name = "USU_SEGUNDO_NOMBRE")
    private String usu_Segundo_Nombre;
    @Column(name = "USU_DIRECCION")
    private String usu_Direccion;
    @Column(name = "USU_EMAIL")
    private String usu_Email;
    @Column(name = "USU_TELEFONO_INDICATIVO")
    private Long usu_Telefono_Indicativo;
    @Column(name = "USU_TELEFONO_NUMERO")
    private Long usu_Telefono_Numero;
    @Column(name = "USU_TELEFONO_EXTENCION")
    private Long usu_Telefono_Extencion;
    @Column(name = "USU_TELEFONO_CELULAR")
    private Long usu_Telefono_Celular;
    @Column(name = "USU_NIVEL")
    private String usu_Nivel;
    @Column(name = "USU_CARGO")
    private String usu_Cargo;
    @Column(name = "USU_ESTADO")
    private String usu_Estado;
    @Column(name = "EPS_USU_ID")
    private String eps_Usu_Id;

    public Usuarios() {
    }

    public String getUsu_Id() {
        return this.usu_Id;
    }

    public void setUsu_Id(String usu_Id) {
        this.usu_Id = usu_Id;
    }

    public String getPer_Usu_Id() {
        return this.per_Usu_Id;
    }

    public void setPer_Usu_Id(String per_Usu_Id) {
        this.per_Usu_Id = per_Usu_Id;
    }

    public String getDep_Usu_Id() {
        return this.dep_Usu_Id;
    }

    public void setDep_Usu_Id(String dep_Usu_Id) {
        this.dep_Usu_Id = dep_Usu_Id;
    }

    public String getMun_Usu_Id() {
        return this.mun_Usu_Id;
    }

    public void setMun_Usu_Id(String mun_Usu_Id) {
        this.mun_Usu_Id = mun_Usu_Id;
    }

    public String getTid_Usu_Id() {
        return this.tid_Usu_Id;
    }

    public void setTid_Usu_Id(String tid_Usu_Id) {
        this.tid_Usu_Id = tid_Usu_Id;
    }

    public String getUsu_Clave() {
        return this.usu_Clave;
    }

    public void setUsu_Clave(String usu_Clave) {
        this.usu_Clave = usu_Clave;
    }

    public String getUsu_Identificacion() {
        return this.usu_Identificacion;
    }

    public void setUsu_Identificacion(String usu_Identificacion) {
        this.usu_Identificacion = usu_Identificacion;
    }

    public String getUsu_Primer_Apellido() {
        return this.usu_Primer_Apellido;
    }

    public void setUsu_Primer_Apellido(String usu_Primer_Apellido) {
        this.usu_Primer_Apellido = usu_Primer_Apellido;
    }

    public String getUsu_Segundo_Apellido() {
        return this.usu_Segundo_Apellido;
    }

    public void setUsu_Segundo_Apellido(String usu_Segundo_Apellido) {
        this.usu_Segundo_Apellido = usu_Segundo_Apellido;
    }

    public String getUsu_Primer_Nombre() {
        return this.usu_Primer_Nombre;
    }

    public void setUsu_Primer_Nombre(String usu_Primer_Nombre) {
        this.usu_Primer_Nombre = usu_Primer_Nombre;
    }

    public String getUsu_Segundo_Nombre() {
        return this.usu_Segundo_Nombre;
    }

    public void setUsu_Segundo_Nombre(String usu_Segundo_Nombre) {
        this.usu_Segundo_Nombre = usu_Segundo_Nombre;
    }

    public String getUsu_Direccion() {
        return this.usu_Direccion;
    }

    public void setUsu_Direccion(String usu_Direccion) {
        this.usu_Direccion = usu_Direccion;
    }

    public String getUsu_Email() {
        return this.usu_Email;
    }

    public void setUsu_Email(String usu_Email) {
        this.usu_Email = usu_Email;
    }

    public Long getUsu_Telefono_Indicativo() {
        return this.usu_Telefono_Indicativo;
    }

    public void setUsu_Telefono_Indicativo(Long usu_Telefono_Indicativo) {
        this.usu_Telefono_Indicativo = usu_Telefono_Indicativo;
    }

    public Long getUsu_Telefono_Numero() {
        return this.usu_Telefono_Numero;
    }

    public void setUsu_Telefono_Numero(Long usu_Telefono_Numero) {
        this.usu_Telefono_Numero = usu_Telefono_Numero;
    }

    public Long getUsu_Telefono_Extencion() {
        return this.usu_Telefono_Extencion;
    }

    public void setUsu_Telefono_Extencion(Long usu_Telefono_Extencion) {
        this.usu_Telefono_Extencion = usu_Telefono_Extencion;
    }

    public Long getUsu_Telefono_Celular() {
        return this.usu_Telefono_Celular;
    }

    public void setUsu_Telefono_Celular(Long usu_Telefono_Celular) {
        this.usu_Telefono_Celular = usu_Telefono_Celular;
    }

    public String getUsu_Nivel() {
        return this.usu_Nivel;
    }

    public void setUsu_Nivel(String usu_Nivel) {
        this.usu_Nivel = usu_Nivel;
    }

    public String getUsu_Cargo() {
        return this.usu_Cargo;
    }

    public void setUsu_Cargo(String usu_Cargo) {
        this.usu_Cargo = usu_Cargo;
    }

    public String getUsu_Estado() {
        return this.usu_Estado;
    }

    public void setUsu_Estado(String usu_Estado) {
        this.usu_Estado = usu_Estado;
    }
    
    public String getEps_Usu_Id() {
		return eps_Usu_Id;
	}

	public void setEps_Usu_Id(String eps_Usu_Id) {
		this.eps_Usu_Id = eps_Usu_Id;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash = (this.usu_Id != null ? this.usu_Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        boolean aux = true;
        if (!(object instanceof Usuarios)) {
            aux = false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usu_Id == null && other.usu_Id != null) || (this.usu_Id != null && !this.usu_Id.equals(other.usu_Id))) {
            aux = false;
        }
        return aux;
    }
}
