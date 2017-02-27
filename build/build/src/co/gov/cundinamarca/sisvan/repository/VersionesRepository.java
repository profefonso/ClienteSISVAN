package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Versiones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VersionesRepository {
	Session session;
	Versiones versiones;
	ObservableList<Versiones> versionesList=FXCollections.observableArrayList();
	
	public VersionesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public ObservableList<Versiones> getListCombo(){
		List<Versiones> list = null;
		try {
			Query query = session.createQuery("FROM Versiones");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				versionesList.add((Versiones) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return versionesList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Versiones findById(String id){
		List<Versiones> list = null;
		versiones=new Versiones();
		versiones=null;
		try {
			Query query = session.createQuery("FROM Versiones e WHERE ver_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Versiones> iter = list.iterator();
			while (iter.hasNext()){
				versiones=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return versiones;
		 }
	}
	
	@SuppressWarnings("finally")
	public Versiones lastVersion(){
		List list = null;
		versiones=new Versiones();
		String idVersionActual="";
		try {
			Query query = session.createQuery("SELECT MAX(ver_Id) FROM Versiones e");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				idVersionActual=(String) iter.next();
			}
			versiones=findById(idVersionActual);
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return versiones;
		 }
	}
	
}