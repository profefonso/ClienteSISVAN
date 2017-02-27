package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Lugar_Residencias;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Lugar_ResidenciasRepository {
	Session session;
	Lugar_Residencias lugar_Residencias;
	ObservableList<Lugar_Residencias> lugar_ResidenciasList=FXCollections.observableArrayList();
	
	public Lugar_ResidenciasRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Lugar_Residencias> selectAll(){
		List<Lugar_Residencias> list = null;
		try {
			Query query = session.createQuery("FROM Lugar_Residencias");
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
	public ObservableList<Lugar_Residencias> getListCombo(){
		List<Lugar_Residencias> list = null;
		try {
			Query query = session.createQuery("FROM Lugar_Residencias WHERE lre_Estado='A'");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				lugar_ResidenciasList.add((Lugar_Residencias) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return lugar_ResidenciasList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Lugar_Residencias findById(String id){
		List<Lugar_Residencias> list = null;
		lugar_Residencias=new Lugar_Residencias();
		lugar_Residencias=null;
		try {
			Query query = session.createQuery("FROM Lugar_Residencias e WHERE lre_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Lugar_Residencias> iter = list.iterator();
			while (iter.hasNext()){
				lugar_Residencias=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return lugar_Residencias;
		 }
	}
}