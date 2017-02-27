package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Instituciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class InstitucionesRepository {
	Session session;
	Instituciones instituciones;
	
	ObservableList<Instituciones> institucionesList=FXCollections.observableArrayList();
	
	public InstitucionesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Instituciones> selectAll(){
		List<Instituciones> list = null;
		try {
			Query query = session.createQuery("FROM Instituciones");
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
	public List<Instituciones> findInstirucionesTop(int top){
		List<Instituciones> list = null;
		try {
			Query query = session.createQuery("FROM Instituciones");
			query.setMaxResults(top);
			list = query.list();
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return list;
		 }
	}
	
	@SuppressWarnings("finally")
	public List<Instituciones> findInstitucionesByIdentAndName(String buscar, int top){
		List<Instituciones> list = null;
		try {
			Query query = session.createQuery("FROM Instituciones WHERE ips_Nombre like :buscar OR ips_Identificacion like :buscar");
			query.setParameter("buscar", "%"+buscar+"%");
			query.setMaxResults(top);
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
	public Instituciones findById(String id){
		List<Instituciones> list = null;
		instituciones=new Instituciones();
		instituciones=null;
		try {
			Query query = session.createQuery("FROM Instituciones e WHERE ips_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				instituciones=(Instituciones) iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return instituciones;
		 }
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public ObservableList<Instituciones> getListCombo(){
		List<Instituciones> list = null;
		try {
			Query query = session.createQuery("FROM Instituciones");
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				institucionesList.add((Instituciones) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return institucionesList;
		 }
	}

	@SuppressWarnings("finally")
	public Instituciones findByMunicipio(String mun_Usu_Id) {
		List<Instituciones> list = null;
		instituciones=new Instituciones();
		instituciones=null;
		try {
			Query query = session.createQuery("FROM Instituciones e WHERE mun_Ips_Id=:mun_Usu_Id");
			query.setParameter("mun_Usu_Id", mun_Usu_Id);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				instituciones=(Instituciones) iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return instituciones;
		 }
	}
	
	@SuppressWarnings("finally")
	public List<Instituciones> listByMunicipio(String mun_Usu_Id) {
		List<Instituciones> list = null;
		try {
			Query query = session.createQuery("FROM Instituciones e WHERE mun_Ips_Id=:mun_Usu_Id");
			query.setParameter("mun_Usu_Id", mun_Usu_Id);
			list = query.list();
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return list;
		 }
	}

}
