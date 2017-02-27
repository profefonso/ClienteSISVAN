package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Provincias;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProvinciasRepository {
	Session session;
	Provincias provincias;
	ObservableList<Provincias> provinciasList=FXCollections.observableArrayList();
	
	public ProvinciasRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public ObservableList<Provincias> getListCombo(){
		List<Provincias> list = null;
		try {
			Query query = session.createQuery("FROM Provincias WHERE lac_Estado='A'");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				provinciasList.add((Provincias) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return provinciasList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Provincias findById(String id){
		List<Provincias> list = null;
		provincias=new Provincias();
		provincias=null;
		try {
			Query query = session.createQuery("FROM Provincias e WHERE pro_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Provincias> iter = list.iterator();
			while (iter.hasNext()){
				provincias=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return provincias;
		 }
	}
}