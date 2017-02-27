package co.gov.cundinamarca.sisvan.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Dato_Dinamicos;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;

public class Dato_DinamicosRepository {
	Session session;
	
	public Dato_DinamicosRepository(Session session) {
		this.session=session;
	}
	
	@SuppressWarnings("finally")
	public Dato_Dinamicos findDinamicoById(String id){
		Dato_Dinamicos campoDinamico=new Dato_Dinamicos();
		try {
			campoDinamico=(Dato_Dinamicos) session.get(Dato_Dinamicos.class, id);
		 }
		 catch (Exception e) {
			 e.printStackTrace();
			 campoDinamico=null;
		 }
		 finally {
		     return campoDinamico;
		 }
	}
}
