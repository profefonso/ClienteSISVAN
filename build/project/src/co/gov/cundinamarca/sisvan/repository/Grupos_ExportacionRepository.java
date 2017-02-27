package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Grupos_Exportacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Grupos_ExportacionRepository {
	Session session;
	Grupos_Exportacion grupos_Exportacion;
	ObservableList<Grupos_Exportacion> grupos_ExportacionList=FXCollections.observableArrayList();
	
	public Grupos_ExportacionRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public boolean saveGrupo(Grupos_Exportacion grupos_Exportacion){
		boolean realizado=false;
		try {
			session.beginTransaction();
			session.saveOrUpdate(grupos_Exportacion);
			session.getTransaction().commit();
		    realizado=true;
		 }
		 catch (Exception e) {
			 e.printStackTrace();
			 realizado=false;
		 }
		 finally {
		     return realizado;
		 }
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public ObservableList<Grupos_Exportacion> getListCombo(){
		List<Grupos_Exportacion> list = null;
		try {
			Query query = session.createQuery("FROM Grupos_Exportacion");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				grupos_ExportacionList.add((Grupos_Exportacion) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return grupos_ExportacionList;
		 }
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public List<Grupos_Exportacion> findByIdGrupo(String idGrupo){
		List<Grupos_Exportacion> list = null;
		try {
			Query query = session.createQuery("FROM Grupos_Exportacion e WHERE gre_Grupo_Id=:idGrupo");
			query.setParameter("idGrupo", idGrupo);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				grupos_ExportacionList.add((Grupos_Exportacion) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return grupos_ExportacionList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Grupos_Exportacion findById(String id){
		List<Grupos_Exportacion> list = null;
		grupos_Exportacion=new Grupos_Exportacion();
		grupos_Exportacion=null;
		try {
			Query query = session.createQuery("FROM Grupos_Exportacion e WHERE gre_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Grupos_Exportacion> iter = list.iterator();
			while (iter.hasNext()){
				grupos_Exportacion=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return grupos_Exportacion;
		 }
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public List<Grupos_Exportacion> findExportGrupo(String idGrupo){
		List<Grupos_Exportacion> list = null;
		try {
			Query query = session.createQuery("FROM Grupos_Exportacion e WHERE gre_Grupo_Id=:idGrupo");
			query.setParameter("idGrupo", idGrupo);
			list = query.list();
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return list;
		 }
	}
}
