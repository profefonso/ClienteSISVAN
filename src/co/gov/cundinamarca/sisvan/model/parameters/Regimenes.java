package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "REGIMENES")
public class Regimenes implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "REG_ID")
    private String reg_Id;
    @Column(name = "USU_REG_ID")
    private String usu_Reg_Id;
    @Column(name = "REG_NOMBRE")
    private String reg_Nombre;
    @Column(name = "REG_ESTADO")
    private String reg_Estado;
    @Column(name = "REG_FECHA")
    private Date reg_Fecha;

    public Regimenes() {
    }

    public String getReg_Id() {
        return this.reg_Id;
    }

    public void setReg_Id(String reg_Id) {
        this.reg_Id = reg_Id;
    }

    public String getUsu_Reg_Id() {
        return this.usu_Reg_Id;
    }

    public void setUsu_Reg_Id(String usu_Reg_Id) {
        this.usu_Reg_Id = usu_Reg_Id;
    }

    public String getReg_Nombre() {
        return this.reg_Nombre;
    }

    public void setReg_Nombre(String reg_Nombre) {
        this.reg_Nombre = reg_Nombre;
    }

    public String getReg_Estado() {
        return this.reg_Estado;
    }

    public void setReg_Estado(String reg_Estado) {
        this.reg_Estado = reg_Estado;
    }

    public Date getReg_Fecha() {
        return this.reg_Fecha;
    }

    public void setReg_Fecha(Date reg_Fecha) {
        this.reg_Fecha = reg_Fecha;
    }
    
    @Override
    public String toString() {
        return this.reg_Nombre;
    }
}
