package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "PROGRAMAS")
public class Programas implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PRG_ID")
    private String prg_Id;
    @Column(name = "USU_PRG_ID")
    private String usu_Prg_Id;
    @Column(name = "PRG_NOMBRE")
    private String prg_Nombre;
    @Column(name = "PRG_APLICA_ADULTO")
    private String prg_Aplica_Adulto;
    @Column(name = "PRG_APLICA_GESTANTE")
    private String prg_Aplica_Gestante;
    @Column(name = "PRG_APLICA_MENOR")
    private String prg_Aplica_Menor;
    @Column(name = "PRG_ESTADO")
    private String prg_Estado;
    @Column(name = "PRG_FECHA")
    private Date prg_Fecha;

    public Programas() {
    }

    public String getPrg_Id() {
        return this.prg_Id;
    }

    public void setPrg_Id(String prg_Id) {
        this.prg_Id = prg_Id;
    }

    public String getUsu_Prg_Id() {
        return this.usu_Prg_Id;
    }

    public void setUsu_Prg_Id(String usu_Prg_Id) {
        this.usu_Prg_Id = usu_Prg_Id;
    }

    public String getPrg_Nombre() {
        return this.prg_Nombre;
    }

    public void setPrg_Nombre(String prg_Nombre) {
        this.prg_Nombre = prg_Nombre;
    }

    public String getPrg_Aplica_Adulto() {
        return this.prg_Aplica_Adulto;
    }

    public void setPrg_Aplica_Adulto(String prg_Aplica_Adulto) {
        this.prg_Aplica_Adulto = prg_Aplica_Adulto;
    }

    public String getPrg_Aplica_Gestante() {
        return this.prg_Aplica_Gestante;
    }

    public void setPrg_Aplica_Gestante(String prg_Aplica_Gestante) {
        this.prg_Aplica_Gestante = prg_Aplica_Gestante;
    }

    public String getPrg_Aplica_Menor() {
        return this.prg_Aplica_Menor;
    }

    public void setPrg_Aplica_Menor(String prg_Aplica_Menor) {
        this.prg_Aplica_Menor = prg_Aplica_Menor;
    }

    public String getPrg_Estado() {
        return this.prg_Estado;
    }

    public void setPrg_Estado(String prg_Estado) {
        this.prg_Estado = prg_Estado;
    }

    public Date getPrg_Fecha() {
        return this.prg_Fecha;
    }

    public void setPrg_Fecha(Date prg_Fecha) {
        this.prg_Fecha = prg_Fecha;
    }
    
    @Override
    public String toString() {
        return this.prg_Nombre;
    }
}
