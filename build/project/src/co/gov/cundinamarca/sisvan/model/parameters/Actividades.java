package co.gov.cundinamarca.sisvan.model.parameters;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "ACTIVIDADES")
public class Actividades implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ACT_ID")
    private String act_Id;
    @Column(name = "USU_ACT_ID")
    private String usu_Act_Id;
    @Column(name = "ACT_NOMBRE")
    private String act_Nombre;
    @Column(name = "ACT_ESTADO")
    private String act_Estado;
    @Column(name = "ACT_FECHA")
    private Date act_Fecha;

    public Actividades() {
    }

    public String getAct_Id() {
        return this.act_Id;
    }

    public void setAct_Id(String act_Id) {
        this.act_Id = act_Id;
    }

    public String getUsu_Act_Id() {
        return this.usu_Act_Id;
    }

    public void setUsu_Act_Id(String usu_Act_Id) {
        this.usu_Act_Id = usu_Act_Id;
    }

    public String getAct_Nombre() {
        return this.act_Nombre;
    }

    public void setAct_Nombre(String act_Nombre) {
        this.act_Nombre = act_Nombre;
    }

    public String getAct_Estado() {
        return this.act_Estado;
    }

    public void setAct_Estado(String act_Estado) {
        this.act_Estado = act_Estado;
    }

    public Date getAct_Fecha() {
        return this.act_Fecha;
    }

    public void setAct_Fecha(Date act_Fecha) {
        this.act_Fecha = act_Fecha;
    }

    @Override
    public String toString() {
        return this.act_Nombre;
    }
}
