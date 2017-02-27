package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Regimenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RegimenesRepository {
	Session session;
	Regimenes regimenes;
	ObservableList<Regimenes> regimenesList=FXCollections.observableArrayList();
	
	public RegimenesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Regimenes> selectAll(){
		List<Regimenes> list = null;
		try {
			Query query = session.createQuery("FROM Regimenes");
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
	public ObservableList<Regimenes> getListCombo(){
		List<Regimenes> list = null;
		try {
			Query query = session.createQuery("FROM Regimenes WHERE reg_Estado='A'");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				regimenesList.add((Regimenes) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return regimenesList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Regimenes findById(String id){
		List<Regimenes> list = null;
		regimenes=new Regimenes();
		regimenes=null;
		try {
			Query query = session.createQuery("FROM Regimenes e WHERE reg_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Regimenes> iter = list.iterator();
			while (iter.hasNext()){
				regimenes=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return regimenes;
		 }
	}
}