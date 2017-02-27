package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Barrio_Veredas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Barrio_VeredasRepository {
	Session session;
	Barrio_Veredas barrio_Veredas;
	ObservableList<Barrio_Veredas> barrio_VeredasList=FXCollections.observableArrayList();
	
	public Barrio_VeredasRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Barrio_Veredas> selectAll(){
		List<Barrio_Veredas> list = null;
		try {
			Query query = session.createQuery("FROM Barrio_Veredas");
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
	public ObservableList<Barrio_Veredas> getListCombo(String idDepartamento, String idMunicipio){
		List<Barrio_Veredas> list = null;
		int find=0;
		try {
			Query query = session.createQuery("FROM Barrio_Veredas e WHERE dep_Bve_id=:idDepartamento AND mun_Bve_Id=:idMunicipio");
			query.setParameter("idDepartamento", idDepartamento);
			query.setParameter("idMunicipio", idMunicipio);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				barrio_VeredasList.add((Barrio_Veredas) iter.next());
				find++;
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
			 
		     return barrio_VeredasList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Barrio_Veredas findById(String idMunicipio, String idBarrio_Vereda){
		List<Barrio_Veredas> list = null;
		barrio_Veredas=new Barrio_Veredas();
		barrio_Veredas=null;
		try {
			Query query = session.createQuery("FROM Barrio_Veredas e WHERE mun_Bve_Id=:idMunicipio AND bve_Id=:idBarrio_Vereda");
			query.setParameter("idMunicipio", idMunicipio);
			query.setParameter("idBarrio_Vereda", idBarrio_Vereda);
			list = query.list();
			Iterator<Barrio_Veredas> iter = list.iterator();
			while (iter.hasNext()){
				barrio_Veredas=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return barrio_Veredas;
		 }
	}
}