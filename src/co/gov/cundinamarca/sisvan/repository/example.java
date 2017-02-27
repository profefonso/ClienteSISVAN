package co.gov.cundinamarca.sisvan.repository;

import java.util.Iterator;
import java.util.List;

import co.gov.cundinamarca.sisvan.model.parameters.Usuarios;
import co.gov.cundinamarca.sisvan.model.transactions.Menores;

public class example {
	
	static UsuariosRepository repoUsu=new UsuariosRepository(null);
	static MenoresRepository repomenor =new MenoresRepository(null);
	
	public static void main(String[] args) {
		//Usuarios findUsu=new Usuarios();
		//findUsu=buscar("Cliente2");
		//findUsu.setUsu_Primer_Nombre("Actualiza nombre");
		//actualizar(findUsu);
		
		selectMenor();
		
	}
	
	public static void validaUser(){
		if((repoUsu.validaLogin("Admin", "daas"))!=null){
			System.out.println("Ingresa");
		}else{
			System.out.println("No Ingresa");
		}
	}
	
	public static void select(){
		List<Usuarios> listUsuarios=repoUsu.selectEstado("A");
		Usuarios usuFind=new Usuarios();
		Iterator iter = listUsuarios.iterator();
		while (iter.hasNext()){
			usuFind=(Usuarios) iter.next();
			System.out.println(usuFind.getUsu_Primer_Nombre()+" "+usuFind.getUsu_Identificacion());
		}
	}
	
	public static Usuarios buscar(String id){
		Usuarios findUsu=new Usuarios();
		findUsu=repoUsu.findUsuario(id);
		
		System.out.println(findUsu.getUsu_Primer_Nombre()+" "+findUsu.getUsu_Identificacion());
		return findUsu;
	}
	
	public static void actualizar(Usuarios findUsu){
		
		if(repoUsu.updateUsuario(findUsu)){
			System.out.println("Logrado");
		}else{
			System.out.println("No Logrado");
		}
	}
	
	public void crear(){
		Usuarios usu=new Usuarios();
		usu.setUsu_Id("Cliente2");
		usu.setPer_Usu_Id("Cliente2");
		usu.setDep_Usu_Id("25");
		usu.setTid_Usu_Id("0");
		usu.setUsu_Clave("Cliente2");
		usu.setUsu_Identificacion("444");
		usu.setUsu_Primer_Apellido("Cliente");
		usu.setUsu_Primer_Nombre("Cf");
		usu.setUsu_Direccion("1");
		usu.setUsu_Nivel("D");
		usu.setUsu_Cargo("C");
		usu.setUsu_Estado("A");
		
		if(repoUsu.saveUsuario(usu)){
			System.out.println("Logrado");
		}else{
			System.out.println("No Logrado");
		}
	}
	
	public static void crearMenores(){
		Menores menor=new Menores();
		menor.setMnor_Id((long) 3);
		
		
		if(repomenor.saveMenor(menor)){
			System.out.println("Logrado");
		}else{
			System.out.println("No Logrado");
		}
		
	}

	public static void selectMenor(){
		List<Menores> listMenores=repomenor.listadoMenores(10);
		Menores menorFind=new Menores();
		Iterator iter = listMenores.iterator();
		while (iter.hasNext()){
			menorFind=(Menores) iter.next();
			System.out.println(menorFind.getPac_Mnor_Id());
		}
	}
}
