package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.gov.cundinamarca.sisvan.model.parameters.Pacientes;

public class PacientesRepository {
	
	Session session;
	Pacientes paciente;
	
	public PacientesRepository(Session session) {
		this.session=session;
		paciente=new Pacientes();
	}
	
	@SuppressWarnings("finally")
	public boolean savePaciente(Pacientes paciente){
		boolean realizado=false;
		try {
			session.beginTransaction();
			session.saveOrUpdate(paciente);
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
	public List<Pacientes> selectAll(){
		List<Pacientes> list = null;
		try {
			Query query = session.createQuery("FROM Pacientes");
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
	public String nombreByIdentificacion(String identPaciente){
		List<Pacientes> list = null;
		String nombrePaciente="";
		try {
			Query query = session.createQuery("FROM Pacientes p WHERE pac_Id=:identPaciente");
			query.setParameter("identPaciente", identPaciente);
			list = query.list();
			Pacientes pacienteFind=new Pacientes();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				pacienteFind=(Pacientes) iter.next();
				nombrePaciente=pacienteFind.getNombreFullPaciente();
			}
		 }
		 catch (Exception e) {
			 nombrePaciente="";
		 }
		 finally {
		     return nombrePaciente;
		 }
	}
	
	@SuppressWarnings("finally")
	public List<Pacientes> findPacientesTop(int top){
		List<Pacientes> list = null;
		try {
			Query query = session.createQuery("FROM Pacientes");
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
	public List<Pacientes> findPacientesByIdentAndName(String buscar, int top){
		List<Pacientes> list = null;
		try {
			Query query = session.createQuery("FROM Pacientes WHERE pac_Id like :buscar OR pac_Primerapellido like :buscar OR pac_Segundoapellido like :buscar OR pac_Primernombre like :buscar OR pac_Segundonombre like :buscar");
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
	public Pacientes findById(String id){
		paciente=null;
		List<Pacientes> list = null;
		try {
			Query query = session.createQuery("FROM Pacientes e WHERE pac_Id=:id");
			query.setParameter("id", id);
			list = query.list();
			Iterator iter = list.iterator();
			while (iter.hasNext()){
				paciente=(Pacientes) iter.next();
			}
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		     return paciente;
		 }
	}
	
	@SuppressWarnings("finally")
	public List<Pacientes> selectNoSyncronizate(){
		List<Pacientes> list = null;
		try {
			Query query = session.createQuery("FROM Pacientes e WHERE pac_sync=:nsc");
			query.setParameter("nsc", "NS");
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
