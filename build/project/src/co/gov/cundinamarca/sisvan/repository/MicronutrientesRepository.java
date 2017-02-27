package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Micronutrientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MicronutrientesRepository {
	Session session;
	Micronutrientes micronutrientes;
	ObservableList<Micronutrientes> micronutrientesList=FXCollections.observableArrayList();
	
	public MicronutrientesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Micronutrientes> selectAll(){
		List<Micronutrientes> list = null;
		try {
			Query query = session.createQuery("FROM Micronutrientes");
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
	public ObservableList<Micronutrientes> getListCombo(String poblacion){
		List<Micronutrientes> list = null;
		String SqlSentence;
		if(poblacion.equals("Menores")){
			SqlSentence="FROM Micronutrientes WHERE mnu_Aplica_Menor='S' AND mnu_Estado='A'";
		}else if(poblacion.equals("Adultos")){
			SqlSentence="FROM Micronutrientes WHERE mnu_Aplica_Adulto='S' AND mnu_Estado='A'";
		}else if(poblacion.equals("Gestantes")){
			SqlSentence="FROM Micronutrientes WHERE mnu_Aplica_Gestante='S' AND mnu_Estado='A'";
		}else{
			SqlSentence="FROM Micronutrientes WHERE mnu_Estado='A'";
		}
		try {
			Query query = session.createQuery(SqlSentence);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				micronutrientesList.add((Micronutrientes) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return micronutrientesList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Micronutrientes findById(String id){
		List<Micronutrientes> list = null;
		micronutrientes=new Micronutrientes();
		micronutrientes=null;
		try {
			Query query = session.createQuery("FROM Micronutrientes e WHERE mnu_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Micronutrientes> iter = list.iterator();
			while (iter.hasNext()){
				micronutrientes=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return micronutrientes;
		 }
	}
}