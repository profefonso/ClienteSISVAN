package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Desarrollos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DesarrollosRepository {
	Session session;
	Desarrollos desarrollos;
	ObservableList<Desarrollos> desarrollosList=FXCollections.observableArrayList();
	
	public DesarrollosRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Desarrollos> selectAll(){
		List<Desarrollos> list = null;
		try {
			Query query = session.createQuery("FROM Desarrollos");
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
	public ObservableList<Desarrollos> getListCombo(){
		List<Desarrollos> list = null;
		try {
			Query query = session.createQuery("FROM Desarrollos WHERE des_Estado='A'");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				desarrollosList.add((Desarrollos) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return desarrollosList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Desarrollos findById(String id){
		List<Desarrollos> list = null;
		desarrollos=new Desarrollos();
		desarrollos=null;
		try {
			Query query = session.createQuery("FROM Desarrollos e WHERE des_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Desarrollos> iter = list.iterator();
			while (iter.hasNext()){
				desarrollos=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return desarrollos;
		 }
	}
}