package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Crecimiento_Desarrollos;

public class Crecimiento_DesarrolloRepository {
	
	Session session;
	Crecimiento_Desarrollos crecimeintoDesarrollo;
	
	public Crecimiento_DesarrolloRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Crecimiento_Desarrollos> getListCombo(){
		List<Crecimiento_Desarrollos> list = null;
		try {
			Query query = session.createQuery("FROM Crecimiento_Desarrollos");
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
	public Crecimiento_Desarrollos findById(String id){
		List<Crecimiento_Desarrollos> list = null;
		crecimeintoDesarrollo=new Crecimiento_Desarrollos();
		crecimeintoDesarrollo=null;
		try {
			Query query = session.createQuery("FROM Crecimiento_Desarrollos e WHERE cde_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				crecimeintoDesarrollo=(Crecimiento_Desarrollos) iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return crecimeintoDesarrollo;
		 }
	}

}
