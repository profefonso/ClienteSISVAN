package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Departamentos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DepartamentosRepository {
	Session session;
	Departamentos departamentos;
	ObservableList<Departamentos> departamentosList=FXCollections.observableArrayList();
	
	public DepartamentosRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Departamentos> selectAll(){
		List<Departamentos> list = null;
		try {
			Query query = session.createQuery("FROM Departamentos");
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
	public ObservableList<Departamentos> getListCombo(){
		List<Departamentos> list = null;
		try {
			Query query = session.createQuery("FROM Departamentos");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				departamentosList.add((Departamentos) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return departamentosList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Departamentos findById(String id){
		List<Departamentos> list = null;
		departamentos=new Departamentos();
		departamentos=null;
		try {
			Query query = session.createQuery("FROM Departamentos e WHERE dep_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Departamentos> iter = list.iterator();
			while (iter.hasNext()){
				departamentos=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return departamentos;
		 }
	}
}