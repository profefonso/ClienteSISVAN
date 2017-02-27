package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Especialidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EspecialidadesRepository {
	Session session;
	Especialidades especialidades;
	ObservableList<Especialidades> especialidadesList=FXCollections.observableArrayList();
	
	public EspecialidadesRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Especialidades> selectAll(){
		List<Especialidades> list = null;
		try {
			Query query = session.createQuery("FROM Especialidades");
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
	public ObservableList<Especialidades> getListCombo(String poblacion){
		List<Especialidades> list = null;
		String SqlSentence;
		if(poblacion.equals("Menores")){
			SqlSentence="FROM Especialidades WHERE esp_Aplica_Menor='S' AND esp_Estado='A'";
		}else if(poblacion.equals("Adultos")){
			SqlSentence="FROM Especialidades WHERE esp_Aplica_Adulto='S' AND esp_Estado='A'";
		}else if(poblacion.equals("Gestantes")){
			SqlSentence="FROM Especialidades WHERE esp_Aplica_Gestante='S' AND esp_Estado='A'";
		}else{
			SqlSentence="FROM Especialidades WHERE esp_Estado='A'";
		}
		try {
			Query query = session.createQuery(SqlSentence);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				especialidadesList.add((Especialidades) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return especialidadesList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Especialidades findById(String id){
		List<Especialidades> list = null;
		especialidades=new Especialidades();
		especialidades=null;
		try {
			Query query = session.createQuery("FROM Especialidades e WHERE esp_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Especialidades> iter = list.iterator();
			while (iter.hasNext()){
				especialidades=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return especialidades;
		 }
	}
}