package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Riesgos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RiesgosRepository {
	Session session;
	Riesgos riesgos;
	ObservableList<Riesgos> riesgosList=FXCollections.observableArrayList();
	
	public RiesgosRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Riesgos> selectAll(){
		List<Riesgos> list = null;
		try {
			Query query = session.createQuery("FROM Riesgos");
			list = query.list();
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return list;
		 }
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public ObservableList<Riesgos> getListCombo(){
		List<Riesgos> list = null;
		try {
			Query query = session.createQuery("FROM Riesgos WHERE rie_Estado='A'");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				riesgosList.add((Riesgos) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return riesgosList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Riesgos findById(String id){
		List<Riesgos> list = null;
		riesgos=new Riesgos();
		riesgos=null;
		try {
			Query query = session.createQuery("FROM Riesgos e WHERE rie_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Riesgos> iter = list.iterator();
			while (iter.hasNext()){
				riesgos=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return riesgos;
		 }
	}
}
