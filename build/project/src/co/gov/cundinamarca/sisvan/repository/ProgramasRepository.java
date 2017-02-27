package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Programas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProgramasRepository {
	Session session;
	Programas programas;
	ObservableList<Programas> programasList=FXCollections.observableArrayList();
	
	public ProgramasRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public List<Programas> selectAll(){
		List<Programas> list = null;
		try {
			Query query = session.createQuery("FROM Programas");
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
	public ObservableList<Programas> getListCombo(String poblacion){
		List<Programas> list = null;
		String SqlSentence;
		if(poblacion.equals("Menores")){
			SqlSentence="FROM Programas WHERE prg_Aplica_Menor='S' AND prg_Estado='A'";
		}else if(poblacion.equals("Adultos")){
			SqlSentence="FROM Programas WHERE prg_Aplica_Adulto='S' AND prg_Estado='A'";
		}else if(poblacion.equals("Gestantes")){
			SqlSentence="FROM Programas WHERE prg_Aplica_Gestante='S' AND prg_Estado='A'";
		}else{
			SqlSentence="FROM Programas WHERE prg_Estado='A'";
		}
		try {
			Query query = session.createQuery(SqlSentence);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				programasList.add((Programas) iter.next());
			}
		 }
		 catch (Exception e) {
			 list=null;
		 }
		 finally {
		     return programasList;
		 }
	}
	
	@SuppressWarnings("finally")
	public Programas findById(String id){
		List<Programas> list = null;
		programas=new Programas();
		programas=null;
		try {
			Query query = session.createQuery("FROM Programas e WHERE prg_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator<Programas> iter = list.iterator();
			while (iter.hasNext()){
				programas=iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return programas;
		 }
	}
}