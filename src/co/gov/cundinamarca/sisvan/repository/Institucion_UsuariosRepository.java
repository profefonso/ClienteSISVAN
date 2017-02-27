package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Institucion_Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Institucion_UsuariosRepository {
	Session session;
	Institucion_Usuarios institucion_Usuarios;
	ObservableList<Institucion_Usuarios> institucion_UsuariosList=FXCollections.observableArrayList();
	
	public Institucion_UsuariosRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public List<Institucion_Usuarios> getListIpsByUsuario(String id){
		List<Institucion_Usuarios> list = null;
		try {
			Query query = session.createQuery("FROM Institucion_Usuarios e WHERE usu_Ipsu_Id=:id");
			query.setParameter("id", id);
			list = query.list();
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return list;
		 }
	}
	
	@SuppressWarnings("finally")
	public Institucion_Usuarios findByUsuarioId(String id){
		List<Institucion_Usuarios> list = null;
		institucion_Usuarios=new Institucion_Usuarios();
		institucion_Usuarios=null;
		try {
			Query query = session.createQuery("FROM Institucion_Usuarios e WHERE usu_Ipsu_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Institucion_Usuarios> iter = list.iterator();
			while (iter.hasNext()){
				institucion_Usuarios=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return institucion_Usuarios;
		 }
	}
}