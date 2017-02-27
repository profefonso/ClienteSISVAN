package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Paciente_Detalles;

public class Paciente_DetallesRepository {
	Session session;
	Paciente_Detalles paciente_detalles;
	
	public Paciente_DetallesRepository(Session session) {
		this.session=session;
		paciente_detalles=new Paciente_Detalles();
	}
	
	@SuppressWarnings("finally")
	public boolean saveDetallePaciente(Paciente_Detalles paciente_detalles){
		boolean realizado=false;
		try {
			session.beginTransaction();
			session.saveOrUpdate(paciente_detalles);
			session.getTransaction().commit();
		    realizado=true;
		 }
		 catch (Exception e) {
			 e.printStackTrace();
			 realizado=false;
		 }
		 finally {
		     return realizado;
		 }
	}
	
	@SuppressWarnings("finally")
	public Paciente_Detalles findById(String id){
		List<Paciente_Detalles> list = null;
		try {
			Query query = session.createQuery("FROM Paciente_Detalles WHERE pac_Pacd_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				paciente_detalles=(Paciente_Detalles) iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return paciente_detalles;
		 }
	}
	
	@SuppressWarnings("finally")
	public List selectNoSyncronizate(){
		List<Paciente_Detalles> list = null;
		try {
			Query query = session.createQuery("FROM Paciente_Detalles WHERE pacd_sync=:nsc");
			query.setParameter("nsc", "NS");
			list = query.list();
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return list;
		 }
	}
}
