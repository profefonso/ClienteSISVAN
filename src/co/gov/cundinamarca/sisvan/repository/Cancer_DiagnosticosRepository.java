package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Cancer_Diagnosticos;

public class Cancer_DiagnosticosRepository {
	
	Session session;
	Cancer_Diagnosticos cancer_Diagnosticos;
	
	public Cancer_DiagnosticosRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Cancer_Diagnosticos> getListCombo(){
		List<Cancer_Diagnosticos> list = null;
		try {
			Query query = session.createQuery("FROM Cancer_Diagnosticos");
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
	public Cancer_Diagnosticos findById(String id){
		List<Cancer_Diagnosticos> list = null;
		cancer_Diagnosticos=new Cancer_Diagnosticos();
		cancer_Diagnosticos=null;
		try {
			Query query = session.createQuery("FROM Cancer_Diagnosticos e WHERE cdi_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				cancer_Diagnosticos=(Cancer_Diagnosticos) iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return cancer_Diagnosticos;
		 }
	}

}
