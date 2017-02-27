package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Actividades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ActividadesRepository {
	Session session;
	Actividades actividades;
	ObservableList<Actividades> actividadesList=FXCollections.observableArrayList();
	
	public ActividadesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Actividades> selectAll(){
		List<Actividades> list = null;
		try {
			Query query = session.createQuery("FROM Actividades");
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
	public ObservableList<Actividades> getListCombo(){
		List<Actividades> list = null;
		try {
			Query query = session.createQuery("FROM Actividades WHERE act_Estado='A'");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				actividadesList.add((Actividades) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return actividadesList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Actividades findById(String id){
		List<Actividades> list = null;
		actividades=new Actividades();
		actividades=null;
		try {
			Query query = session.createQuery("FROM Actividades e WHERE act_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Actividades> iter = list.iterator();
			while (iter.hasNext()){
				actividades=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return actividades;
		 }
	}
}