package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Municipios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MunicipiosRepository {
	Session session;
	Municipios municipios;
	ObservableList<Municipios> municipiosList=FXCollections.observableArrayList();
	
	public MunicipiosRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Municipios> selectAll(){
		List<Municipios> list = null;
		try {
			Query query = session.createQuery("FROM Municipios");
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
	public ObservableList<Municipios> getListCombo(){
		List<Municipios> list = null;
		try {
			Query query = session.createQuery("FROM Municipios");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				municipiosList.add((Municipios) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return municipiosList;
		 }
	}
	
	
	
	@SuppressWarnings("finally")
	public Municipios findById(String id, String idDpto){
		List<Municipios> list = null;
		municipios=new Municipios();
		municipios=null;
		try {
			Query query = session.createQuery("FROM Municipios e WHERE mun_Id=:id AND dep_Mun_Id=:idDpto");
			query.setParameter("id", id);
			query.setParameter("idDpto", idDpto);
			list = query.list();
			Iterator<Municipios> iter = list.iterator();
			while (iter.hasNext()){
				municipios=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return municipios;
		 }
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public ObservableList<Municipios> findByDptoId(String id){
		List<Municipios> list = null;
		try {
			Query query = session.createQuery("FROM Municipios e WHERE dep_Mun_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				municipiosList.add((Municipios) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return municipiosList;
		 }
	}
}
