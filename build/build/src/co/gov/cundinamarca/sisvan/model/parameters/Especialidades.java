package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "ESPECIALIDADES")
public class Especialidades implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ESP_ID")
    private String esp_Id;
    @Column(name = "USU_ESP_ID")
    private String usu_Esp_Id;
    @Column(name = "ESP_NOMBRE")
    private String esp_Nombre;
    @Column(name = "ESP_APLICA_ADULTO")
    private String esp_Aplica_Adulto;
    @Column(name = "ESP_APLICA_GESTANTE")
    private String esp_Aplica_Gestante;
    @Column(name = "ESP_APLICA_MENOR")
    private String esp_Aplica_Menor;
    @Column(name = "ESP_ESTADO")
    private String esp_Estado;
    @Column(name = "ESP_FECHA")
    private Date esp_Fecha;

    public Especialidades() {
    }

    public String getEsp_Id() {
        return this.esp_Id;
    }

    public void setEsp_Id(String esp_Id) {
        this.esp_Id = esp_Id;
    }

    public String getUsu_Esp_Id() {
        return this.usu_Esp_Id;
    }

    public void setUsu_Esp_Id(String usu_Esp_Id) {
        this.usu_Esp_Id = usu_Esp_Id;
    }

    public String getEsp_Nombre() {
        return this.esp_Nombre;
    }

    public void setEsp_Nombre(String esp_Nombre) {
        this.esp_Nombre = esp_Nombre;
    }

    public String getEsp_Aplica_Adulto() {
        return this.esp_Aplica_Adulto;
    }

    public void setEsp_Aplica_Adulto(String esp_Aplica_Adulto) {
        this.esp_Aplica_Adulto = esp_Aplica_Adulto;
    }

    public String getEsp_Aplica_Gestante() {
        return this.esp_Aplica_Gestante;
    }

    public void setEsp_Aplica_Gestante(String esp_Aplica_Gestante) {
        this.esp_Aplica_Gestante = esp_Aplica_Gestante;
    }

    public String getEsp_Aplica_Menor() {
        return this.esp_Aplica_Menor;
    }

    public void setEsp_Aplica_Menor(String esp_Aplica_Menor) {
        this.esp_Aplica_Menor = esp_Aplica_Menor;
    }

    public String getEsp_Estado() {
        return this.esp_Estado;
    }

    public void setEsp_Estado(String esp_Estado) {
        this.esp_Estado = esp_Estado;
    }

    public Date getEsp_Fecha() {
        return this.esp_Fecha;
    }

    public void setEsp_Fecha(Date esp_Fecha) {
        this.esp_Fecha = esp_Fecha;
    }

    @Override
    public String toString() {
        return this.esp_Nombre;
    }
}
