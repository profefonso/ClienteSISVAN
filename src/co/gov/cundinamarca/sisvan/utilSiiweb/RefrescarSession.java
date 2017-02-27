package co.gov.cundinamarca.sisvan.utilSiiweb;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Actividades;
import co.gov.cundinamarca.sisvan.model.parameters.Barrio_Veredas;
import co.gov.cundinamarca.sisvan.model.parameters.Cancer_Diagnosticos;
import co.gov.cundinamarca.sisvan.model.parameters.Controles;
import co.gov.cundinamarca.sisvan.model.parameters.Crecimiento_Desarrollos;
import co.gov.cundinamarca.sisvan.model.parameters.Desarrollos;
import co.gov.cundinamarca.sisvan.model.parameters.Educacion_Menores;
import co.gov.cundinamarca.sisvan.model.parameters.Entidades;
import co.gov.cundinamarca.sisvan.model.parameters.Especialidades;
import co.gov.cundinamarca.sisvan.model.parameters.Grupos_Exportacion;
import co.gov.cundinamarca.sisvan.model.parameters.Institucion_Usuarios;
import co.gov.cundinamarca.sisvan.model.parameters.Instituciones;
import co.gov.cundinamarca.sisvan.model.parameters.Lactancias;
import co.gov.cundinamarca.sisvan.model.parameters.Lugar_Residencias;
import co.gov.cundinamarca.sisvan.model.parameters.Micronutrientes;
import co.gov.cundinamarca.sisvan.model.parameters.Municipios;
import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;
import co.gov.cundinamarca.sisvan.model.parameters.Programas;
import co.gov.cundinamarca.sisvan.model.parameters.Regimenes;
import co.gov.cundinamarca.sisvan.model.parameters.Riesgos;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Consultas;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Identificaciones;
import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Poblaciones;
import co.gov.cundinamarca.sisvan.model.parameters.Usuarios;
import co.gov.cundinamarca.sisvan.model.parameters.Versiones;;

public class RefrescarSession {
	
	Session session;
	Usuarios usuarios;
	Pacientes pacientes;
	Paciente_Detalles paciente_detalles;
	Instituciones instituciones;
	Crecimiento_Desarrollos crecimiento_desarrollos;
	Lactancias lactancias;
	Programas programas;
	Micronutrientes micronutrientes;
	Actividades actividades;
	Cancer_Diagnosticos cancer_diagnosticos;
	Tipo_Consultas tipo_consultas;
	Desarrollos desarrollos;
	Especialidades especialidades;
	Educacion_Menores educacion_menores;
	Controles controles;
	Tipo_Identificaciones tipo_identificaciones;
	Municipios municipios;
	Barrio_Veredas barrio_veredas;
	Lugar_Residencias lugar_residencias;
	Tipo_Poblaciones tipo_poblaciones;
	Regimenes regimenes;
	Entidades entidades;
	Riesgos riesgos;
	Grupos_Exportacion grupos_exportacion;
	Institucion_Usuarios institucion_usuarios;
	Versiones versiones;

	public RefrescarSession(Session session) {
		this.session=session;
	}

	public void updateSessionParameters() {	
		session.refresh(usuarios);
		session.refresh(pacientes);
		session.refresh(paciente_detalles);
		session.refresh(instituciones);
		session.refresh(crecimiento_desarrollos);
		session.refresh(lactancias);
		session.refresh(programas);
		session.refresh(micronutrientes);
		session.refresh(actividades);
		session.refresh(cancer_diagnosticos);
		session.refresh(tipo_consultas);
		session.refresh(desarrollos);
		session.refresh(especialidades);
		session.refresh(educacion_menores);
		session.refresh(controles);
		session.refresh(tipo_identificaciones);
		session.refresh(municipios);
		session.refresh(barrio_veredas);
		session.refresh(lugar_residencias);
		session.refresh(tipo_poblaciones);
		session.refresh(regimenes);
		session.refresh(entidades);
		session.refresh(riesgos);
		session.refresh(grupos_exportacion);
		session.refresh(institucion_usuarios);
		session.refresh(versiones);
	}

	private void crearObjetos() {
		
		usuarios=new Usuarios();
		pacientes=new Pacientes();
		paciente_detalles=new Paciente_Detalles();
		instituciones=new Instituciones();
		crecimiento_desarrollos=new Crecimiento_Desarrollos();
		lactancias=new Lactancias();
		programas=new Programas();
		micronutrientes=new Micronutrientes();
		actividades=new Actividades();
		cancer_diagnosticos=new Cancer_Diagnosticos();
		tipo_consultas=new Tipo_Consultas();
		desarrollos=new Desarrollos();
		especialidades=new Especialidades();
		educacion_menores=new Educacion_Menores();
		controles=new Controles();
		tipo_identificaciones=new Tipo_Identificaciones();
		municipios=new Municipios();
		barrio_veredas=new Barrio_Veredas();
		lugar_residencias=new Lugar_Residencias();
		tipo_poblaciones=new Tipo_Poblaciones();
		regimenes=new Regimenes();
		entidades=new Entidades();
		riesgos=new Riesgos();
		grupos_exportacion=new Grupos_Exportacion();
		institucion_usuarios=new Institucion_Usuarios();
		versiones=new Versiones();
	}

}
