package co.gov.cundinamarca.sisvan.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.transactions.Gestantes;


public class GestantesRepository {
	Session session;
	
	public GestantesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public boolean saveGestante(Gestantes gestante){
		boolean realizado=false;
		try {
			session.beginTransaction();
			session.saveOrUpdate(gestante);
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
	public List<Gestantes> selectNoSyncronizate(){
		List<Gestantes> list = null;
		try {
			Query query = session.createQuery("FROM Gestantes e WHERE ges_sync=:nsc");
			query.setParameter("nsc", "NS");
			list = query.list();
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return list;
		 }
	}
	
	@SuppressWarnings("finally")
	public Gestantes getGestante(String id){
		long idGestante=Long.parseLong(id);
		Gestantes gestante=new Gestantes();
		try {
			gestante=(Gestantes) session.get(Gestantes.class, idGestante);
		 }
		 catch (Exception e) {
			 gestante=null;
		 }
		 finally {
		     return gestante;
		 }
	}
	
	@SuppressWarnings("finally")
	public List<Gestantes> listGestantes(int top){
		List<Gestantes> list = null;
		try {
			Query query = session.createQuery("FROM Gestantes");
			query.setMaxResults(top);
			list = query.list();
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return list;
		 }
	}
	
	@SuppressWarnings("finally")
	public List<Gestantes> buscaGestanteByIdentificacion(String identificacion){
		List<Gestantes> list = null;
		try {
			Query query = session.createQuery("FROM Gestantes e WHERE pac_Ges_Id=:identificacion");
			query.setParameter("identificacion", identificacion);
			list = query.list();
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return list;
		 }
	}
	
	@SuppressWarnings("finally")
	public boolean buscarGestantesNoRepeat(String Identificacion, Date fechaReporte){
		boolean nuevo=false;
		try {
			Query query = session.createQuery("FROM Gestantes WHERE pac_Ges_Id = :Identificacion AND ges_Fecha_Reporte=:fechaReporte");
			query.setParameter("Identificacion", Identificacion);
			query.setParameter("fechaReporte", fechaReporte);
			if(query.list().size()<=0){
				nuevo=true;
			}
		 }
		 catch (Exception e) {
			 nuevo=false;
		 }
		 finally {
		     return nuevo;
		 }
	}
	
	@SuppressWarnings("finally")
	public long countNoSync() {
		long total=0;
		try {
			Query query = session.createQuery("FROM Gestantes e WHERE ges_sync=:nsc");
			query.setParameter("nsc", "NS");
			total = query.list().size();
		 }
		 catch (Exception e) {
			 total=0;
		 }
		 finally {
		     return total;
		 }
	}
}
