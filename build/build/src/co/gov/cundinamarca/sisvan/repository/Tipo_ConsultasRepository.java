package co.gov.cundinamarca.sisvan.repository;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Tipo_Consultas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tipo_ConsultasRepository {
	Session session;
	Tipo_Consultas tipo_Consultas;
	ObservableList<Tipo_Consultas> tipo_ConsultasList=FXCollections.observableArrayList();
	
	public Tipo_ConsultasRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Tipo_Consultas> selectAll(){
		List<Tipo_Consultas> list = null;
		try {
			Query query = session.createQuery("FROM Tipo_Consultas");
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
	public ObservableList<Tipo_Consultas> getListCombo(String poblacion){
		List<Tipo_Consultas> list = null;
		String SqlSentence;
		if(poblacion.equals("Menores")){
			SqlSentence="FROM Tipo_Consultas WHERE tco_Aplica_Menores='S' AND tco_Estado='A'";
		}else if(poblacion.equals("Adultos")){
			SqlSentence="FROM Tipo_Consultas WHERE tco_Aplica_Adultos='S' AND tco_Estado='A'";
		}else if(poblacion.equals("Gestantes")){
			SqlSentence="FROM Tipo_Consultas WHERE tco_Aplica_Gestantes='S' AND tco_Estado='A'";
		}else{
			SqlSentence="FROM Tipo_Consultas WHERE tco_Estado='A'";
		}
		try {
			Query query = session.createQuery(SqlSentence);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				tipo_ConsultasList.add((Tipo_Consultas) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return tipo_ConsultasList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Tipo_Consultas findById(String id){
		List<Tipo_Consultas> list = null;
		tipo_Consultas=new Tipo_Consultas();
		tipo_Consultas=null;
		try {
			Query query = session.createQuery("FROM Tipo_Consultas e WHERE tco_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Tipo_Consultas> iter = list.iterator();
			while (iter.hasNext()){
				tipo_Consultas=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return tipo_Consultas;
		 }
	}
}