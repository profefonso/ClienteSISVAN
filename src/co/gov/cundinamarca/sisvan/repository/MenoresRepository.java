package co.gov.cundinamarca.sisvan.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.transactions.Menores;

public class MenoresRepository {
	Session session;
	
	public MenoresRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public boolean saveMenor(Menores menor){
		boolean realizado=false;
		try {
			session.beginTransaction();
			session.saveOrUpdate(menor);
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
	public Menores findMenor(String id){
		Menores menor=new Menores();
		try {
			session.beginTransaction();
			menor=(Menores) session.get(Menores.class, id);
		 }
		 catch (Exception e) {
			 e.printStackTrace();
			 menor=null;
		 }
		 finally {
		     return menor;
		 }
	}
	
	@SuppressWarnings("finally")
	public boolean updateMenor(Menores menor){
		boolean realizado=false;
		try {
			session.beginTransaction();
			session.update(menor);
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
	public List<Menores> listadoMenores(int top){
		List<Menores> list = null;
		try {
			Query query = session.createQuery("FROM Menores");
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
	public List<Menores> buscarMenoresByIdentificacion(String Identificacion){
		List<Menores> list = null;
		try {
			Query query = session.createQuery("FROM Menores WHERE pac_Mnor_Id = :Identificacion");
			query.setParameter("Identificacion", Identificacion);
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
	public List<Menores> selectNoSyncronizate(){
		List<Menores> list = null;
		try {
			Query query = session.createQuery("FROM Menores e WHERE mnor_sync=:nsc");
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
	public Menores getMenor(String id){
		long idMenor=Long.parseLong(id);
		Menores menor=new Menores();
		try {
			menor=(Menores) session.get(Menores.class, idMenor);
		 }
		 catch (Exception e) {
			 menor=null;
		 }
		 finally {
		     return menor;
		 }
	}
	
	@SuppressWarnings("finally")
	public boolean buscarMenoresNoRepeat(String Identificacion, Date fechaReporte){
		boolean nuevo=false;
		try {
			Query query = session.createQuery("FROM Menores WHERE pac_Mnor_Id = :Identificacion AND mnor_Fecha_Reporte=:fechaReporte");
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
			Query query = session.createQuery("FROM Menores e WHERE mnor_sync=:nsc");
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
