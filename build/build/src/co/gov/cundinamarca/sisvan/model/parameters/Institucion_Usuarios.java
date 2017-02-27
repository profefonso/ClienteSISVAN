package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "INSTITUCION_USUARIOS")
public class Institucion_Usuarios implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USU_IPSU_ID")
    private String usu_Ipsu_Id;
    @Id
    @Column(name = "IPS_IPSU_ID")
    private String ips_Ipsu_Id;
    @Column(name = "IPSU_ESTADO")
    private String ipsu_Estado;

    public Institucion_Usuarios() {
    }

    public String getUsu_Ipsu_Id() {
        return this.usu_Ipsu_Id;
    }

    public void setUsu_Ipsu_Id(String usu_Ipsu_Id) {
        this.usu_Ipsu_Id = usu_Ipsu_Id;
    }

    public String getIps_Ipsu_Id() {
        return this.ips_Ipsu_Id;
    }

    public void setIps_Ipsu_Id(String ips_Ipsu_Id) {
        this.ips_Ipsu_Id = ips_Ipsu_Id;
    }

    public String getIpsu_Estado() {
        return this.ipsu_Estado;
    }

    public void setIpsu_Estado(String ipsu_Estado) {
        this.ipsu_Estado = ipsu_Estado;
    }
}
