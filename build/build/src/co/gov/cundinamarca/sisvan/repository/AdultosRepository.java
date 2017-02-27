package co.gov.cundinamarca.sisvan.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.transactions.Adultos;

public class AdultosRepository {
	Session session;
	
	public AdultosRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public boolean saveAdulto(Adultos adulto){
		boolean realizado=false;
		try {
			session.beginTransaction();
			session.saveOrUpdate(adulto);
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
	public List<Adultos> selectNoSyncronizate(){
		List<Adultos> list = null;
		try {
			Query query = session.createQuery("FROM Adultos e WHERE adu_sync=:nsc");
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
	public Adultos getAdulto(String id){
		long idAdulto=Long.parseLong(id);
		Adultos adulto=new Adultos();
		try {
			adulto=(Adultos) session.get(Adultos.class, idAdulto);
		 }
		 catch (Exception e) {
			 adulto=null;
		 }
		 finally {
		     return adulto;
		 }
	}
	
	@SuppressWarnings("finally")
	public List<Adultos> listadoAdultos(int top){
		List<Adultos> list = null;
		try {
			Query query = session.createQuery("FROM Adultos");
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
	public List<Adultos> buscarAdultoByIdentificacion(String identificacion){
		List<Adultos> list = null;
		try {
			Query query = session.createQuery("FROM Adultos e WHERE pac_Adu_Id like :identificacion");
			query.setParameter("identificacion", "%"+identificacion+"%");
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
	public boolean buscarAdultosNoRepeat(String Identificacion, Date fechaReporte){
		boolean nuevo=false;
		try {
			Query query = session.createQuery("FROM Adultos WHERE pac_Adu_Id = :Identificacion AND adu_Fecha_Reporte=:fechaReporte");
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
			Query query = session.createQuery("FROM Adultos e WHERE adu_sync=:nsc");
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
