package co.gov.cundinamarca.sisvan.model.parameters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamentos implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DEP_ID")
    private String dep_Id;
    @Column(name = "DEP_NOMBRE")
    private String dep_Nombre;

    public Departamentos() {
    }

    public String getDep_Id() {
        return this.dep_Id;
    }

    public void setDep_Id(String dep_Id) {
        this.dep_Id = dep_Id;
    }

    public String getDep_Nombre() {
        return this.dep_Nombre;
    }

    public void setDep_Nombre(String dep_Nombre) {
        this.dep_Nombre = dep_Nombre;
    }
    
    @Override
    public String toString() {
        return this.dep_Nombre.toUpperCase();
    }
}
