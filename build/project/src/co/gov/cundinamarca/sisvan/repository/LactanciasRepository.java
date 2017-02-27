package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Lactancias;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LactanciasRepository {
	Session session;
	Lactancias lactancias;
	ObservableList<Lactancias> lactanciasList=FXCollections.observableArrayList();
	
	public LactanciasRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Lactancias> selectAll(){
		List<Lactancias> list = null;
		try {
			Query query = session.createQuery("FROM Lactancias");
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
	public ObservableList<Lactancias> getListCombo(){
		List<Lactancias> list = null;
		try {
			Query query = session.createQuery("FROM Lactancias WHERE lac_Estado='A'");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				lactanciasList.add((Lactancias) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return lactanciasList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Lactancias findById(String id){
		List<Lactancias> list = null;
		lactancias=new Lactancias();
		lactancias=null;
		try {
			Query query = session.createQuery("FROM Lactancias e WHERE lac_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Lactancias> iter = list.iterator();
			while (iter.hasNext()){
				lactancias=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return lactancias;
		 }
	}
}