package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Poblaciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tipo_PoblacionesRepository {
	Session session;
	Tipo_Poblaciones tipo_Poblaciones;
	ObservableList<Tipo_Poblaciones> tipo_PoblacionesList=FXCollections.observableArrayList();
	
	public Tipo_PoblacionesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Tipo_Poblaciones> selectAll(){
		List<Tipo_Poblaciones> list = null;
		try {
			Query query = session.createQuery("FROM Tipo_Poblaciones");
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
	public ObservableList<Tipo_Poblaciones> getListCombo(){
		List<Tipo_Poblaciones> list = null;
		try {
			Query query = session.createQuery("FROM Tipo_Poblaciones WHERE tpo_Estado='A'");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				tipo_PoblacionesList.add((Tipo_Poblaciones) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return tipo_PoblacionesList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Tipo_Poblaciones findById(String id){
		List<Tipo_Poblaciones> list = null;
		tipo_Poblaciones=new Tipo_Poblaciones();
		tipo_Poblaciones=null;
		try {
			Query query = session.createQuery("FROM Tipo_Poblaciones e WHERE tpo_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Tipo_Poblaciones> iter = list.iterator();
			while (iter.hasNext()){
				tipo_Poblaciones=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return tipo_Poblaciones;
		 }
	}
}
