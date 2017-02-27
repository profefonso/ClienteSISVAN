package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Controles;

public class ControlesRepository {
	Session session;
	Controles controles;
	
	public ControlesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Controles> getListCombo(){
		List<Controles> list = null;
		try {
			Query query = session.createQuery("FROM Controles");
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
	public Controles findById(String id){
		List<Controles> list = null;
		controles=new Controles();
		controles=null;
		try {
			Query query = session.createQuery("FROM Controles e WHERE ctr_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				controles=(Controles) iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return controles;
		 }
	}
}
