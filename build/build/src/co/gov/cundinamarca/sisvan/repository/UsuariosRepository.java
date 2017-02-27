package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import co.gov.cundinamarca.sisvan.model.parameters.Usuarios;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UsuariosRepository {
	Usuarios usuario;
	Session session;
	
	public UsuariosRepository(Session session){
		usuario=new Usuarios();
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public boolean saveUsuario(Usuarios usuario){
		boolean realizado=false;
		try {
			session.beginTransaction();
			session.saveOrUpdate(usuario);
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
	
	@SuppressWarnings("finally")
	public Usuarios findUsuario(String id){
		Usuarios usuarioRta=new Usuarios();
		try {
			session.beginTransaction();
			usuarioRta=(Usuarios) session.get(Usuarios.class, id);
		 }
		 catch (Exception e) {
			 e.printStackTrace();
			 usuarioRta=null;
		 }
		 finally {
		     return usuarioRta;
		 }
	}
	
	@SuppressWarnings("finally")
	public boolean updateUsuario(Usuarios usuario){
		boolean realizado=false;
		try {
			session.beginTransaction();
			session.update(usuario);
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
	
	@SuppressWarnings("finally")
	public List<Usuarios> selectEstado(String estado){
		List<Usuarios> list = null;
		try {
			Query query = session.createQuery("FROM Usuarios where usu_Estado = :estado ");
			query.setParameter("estado", estado);
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
	public Usuarios validaLogin(String idUsuario, String password){
		boolean okValidate=false;
		List<Usuarios> list = null;
		usuario=null;
		try {
			Query query = session.createQuery("FROM Usuarios u WHERE usu_Id=:idUsuario AND usu_Clave=:password");
			query.setParameter("idUsuario", idUsuario);
			query.setParameter("password", password);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				usuario=(Usuarios) iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
			 Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Error");
	        	alert.setHeaderText("Error en Login");
	        	alert.setContentText("Error al Acceder al base de datos\n"+e.toString());
	
	        	alert.showAndWait();
		 }
		 finally {
		     return usuario;
		 }
	}

}
