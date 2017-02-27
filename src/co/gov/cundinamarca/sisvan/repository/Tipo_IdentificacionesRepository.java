package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Identificaciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tipo_IdentificacionesRepository {
	Session session;
	Tipo_Identificaciones tipo_Identificaciones;
	ObservableList<Tipo_Identificaciones> tipo_IdentificacionesList=FXCollections.observableArrayList();
	
	public Tipo_IdentificacionesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Tipo_Identificaciones> selectAll(){
		List<Tipo_Identificaciones> list = null;
		try {
			Query query = session.createQuery("FROM Tipo_Identificaciones");
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
	public ObservableList<Tipo_Identificaciones> getListCombo(String poblacion){
		List<Tipo_Identificaciones> list = null;
		String SqlSentence;
		if(poblacion.equals("Menores")){
			SqlSentence="FROM Tipo_Identificaciones WHERE tid_Aplica_Menores='S' AND tid_Estado='A'";
		}else if(poblacion.equals("Adultos")){
			SqlSentence="FROM Tipo_Identificaciones WHERE tid_Aplica_Adultos='S' AND tid_Estado='A'";
		}else if(poblacion.equals("Gestantes")){
			SqlSentence="FROM Tipo_Identificaciones WHERE tid_Aplica_Gestantes='S' AND tid_Estado='A'";
		}else{
			SqlSentence="FROM Tipo_Identificaciones";
		}
		try {
			Query query = session.createQuery(SqlSentence);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				tipo_IdentificacionesList.add((Tipo_Identificaciones) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return tipo_IdentificacionesList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Tipo_Identificaciones findById(String id){
		List<Tipo_Identificaciones> list = null;
		tipo_Identificaciones=new Tipo_Identificaciones();
		tipo_Identificaciones=null;
		try {
			Query query = session.createQuery("FROM Tipo_Identificaciones e WHERE tid_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Tipo_Identificaciones> iter = list.iterator();
			while (iter.hasNext()){
				tipo_Identificaciones=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return tipo_Identificaciones;
		 }
	}
}
