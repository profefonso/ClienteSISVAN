package co.gov.cundinamarca.sisvan.repository;

import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;
import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;

public class CargaRepository {

	public static <T> void save(Session session, Pacientes paciente, Paciente_Detalles detalles, T entidad) {
		try {
			session.clear();
			session.beginTransaction();
			session.saveOrUpdate(paciente);
			session.saveOrUpdate(detalles);
			session.saveOrUpdate(entidad);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null && session.getTransaction().isActive())
				session.getTransaction().rollback();
			throw e;
		}finally{
			session.clear();
		}
	}
}
