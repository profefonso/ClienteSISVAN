package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Entidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EntidadesRepository {
	Session session;
	Entidades entidades;
	ObservableList<Entidades> entidadesList=FXCollections.observableArrayList();
	
	public EntidadesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Entidades> selectAll(){
		List<Entidades> list = null;
		try {
			Query query = session.createQuery("FROM Entidades");
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
	public ObservableList<Entidades> getListCombo(){
		List<Entidades> list = null;
		try {
			Query query = session.createQuery("FROM Entidades WHERE eps_Estado='A'");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				entidadesList.add((Entidades) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return entidadesList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Entidades findById(String id){
		List<Entidades> list = null;
		entidades=new Entidades();
		entidades=null;
		try {
			Query query = session.createQuery("FROM Entidades e WHERE eps_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Entidades> iter = list.iterator();
			while (iter.hasNext()){
				entidades=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return entidades;
		 }
	}
}
