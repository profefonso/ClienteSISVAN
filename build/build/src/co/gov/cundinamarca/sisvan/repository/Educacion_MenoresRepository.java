package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Educacion_Menores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Educacion_MenoresRepository {
	Session session;
	Educacion_Menores educacion_Menores;
	ObservableList<Educacion_Menores> educacion_MenoresList=FXCollections.observableArrayList();
	
	public Educacion_MenoresRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Educacion_Menores> selectAll(){
		List<Educacion_Menores> list = null;
		try {
			Query query = session.createQuery("FROM Educacion_Menores");
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
	public ObservableList<Educacion_Menores> getListCombo(){
		List<Educacion_Menores> list = null;
		try {
			Query query = session.createQuery("FROM Educacion_Menores WHERE eme_Estado='A'");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				educacion_MenoresList.add((Educacion_Menores) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return educacion_MenoresList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Educacion_Menores findById(String id){
		List<Educacion_Menores> list = null;
		educacion_Menores=new Educacion_Menores();
		educacion_Menores=null;
		try {
			Query query = session.createQuery("FROM Educacion_Menores e WHERE eme_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Educacion_Menores> iter = list.iterator();
			while (iter.hasNext()){
				educacion_Menores=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return educacion_Menores;
		 }
	}
}