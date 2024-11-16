package Hospital;

import ClasesDadas.ConsultaMedica;
import ClasesDadas.Medicamento;
import ClasesDadas.Medico;
import ClasesDadas.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorConsultorio {

	 public static void run(){
	        try {
	            Medico medico=new Medico();
	            int i=1;
	            
	            System.out.println("Cantidad pacientes: "+Datos.prioridadMedia.size());
	            System.out.println("Medicos disponibles: "+Datos.Medicosdisponibles.NodeCount());
	           
	            while(Datos.prioridadMedia.size()!=0) {
	                Paciente paciente = Datos.prioridadMedia.peek();
	                if(i==1 || i==10) {
	                	i=1;
	                    medico = buscarmedico();
	                    if(medico == null) {
	                        System.out.println("NO hay medicos clinicos disponibles para la consulta");
	                        return;}
	                }
	                
	                mostrarInformacion(i,medico.getNombre(),paciente.getNombre());
	                i++;
	                generarConsulta(medico, paciente);             
	            }
	        }catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }
	    }
	    
	    private static void generarConsulta(Medico medico,Paciente paciente){///int random,Medico medico, Paciente paciente,String medicamentoAdministrado,int cantidadMedicamento) {
	    	 int ramdom= Helper.random.nextInt(Datos.medicamentos.length);
	         String medicamento= Datos.medicamentos[ramdom].getDescripcion();
	         int cantidadMedicamento= medicamentoExiste(ramdom);
	         ConsultaMedica consultaMedica= new ConsultaMedica(medico,paciente,medicamento,cantidadMedicamento,LocalDate.now());
	         Datos.consultasRealizadas.addLast(consultaMedica);
	         Datos.prioridadMedia.remove();
	    }

	  
	    private static void mostrarInformacion(int consultaN,String nombreM,String nombreP){
	        System.out.println("*****Consulta: "+consultaN+" ******");
	        System.out.println("Medico: "+nombreM);
	        System.out.println("Paciente: "+nombreP);
	    }

	    public static Medico buscarmedico(){
	        int cantMedicos = Datos.Medicosdisponibles.NodeCount();
	        ArrayList<Integer> matriculasAux = new ArrayList<>();
	        int matricula = 0;

	        try{
	            for (int i = 0; i < cantMedicos; i++) {

	                matricula= removerMatricula();
	                
	                matriculasAux.add(matricula);

	                Medico buscar = new Medico(matricula);
	                Medico encontrado =  Datos.Medicosdisponibles.remove(buscar);
	                if(encontrado.getEspecialidad().equals("Clinico"))
	                    return encontrado;
	                else {
	                    Datos.Medicosdisponibles.add(encontrado);
	                    Datos.matriculas.add(matricula);
	                }
	            }
	        }catch (Exception e){
	            throw new RuntimeException("Error al buscar medico..."+e.getMessage());
	        }
	        //Volver a llenar el arrayList de matriculas del arrayListAux
	        reLLenarmatriculas(matriculasAux, matricula);        
	        return null;
	    }

	    private static int removerMatricula() {
	    	int matricula=0;
	    	if(Datos.matriculas.size() != 1){

	            int ramdom = Helper.random.nextInt(Datos.matriculas.size());
	             matricula = Datos.matriculas.remove(ramdom);
	            
	        } else{
	            matricula = Datos.matriculas.removeLast();
	        }
	    	return matricula;
	    }
	    
	    private static void reLLenarmatriculas(ArrayList<Integer> matriculasAux,int matricula) {
	    	 
	        System.out.println(matriculasAux.size());
	        for(int i = matriculasAux.size(); i > 0; i--){
	            int matricuAux = matriculasAux.removeLast();
	            if(matricuAux != matricula){
	                Datos.matriculas.add(matricuAux);
	            }
	        }
	    }

	    public static int medicamentoExiste(int ramdom) {
	        Medicamento medicamento = Datos.medicamentos[ramdom]; 
	        
	        int cantidad=0,continuar=0;
	        while(continuar!=1) {
	            
	            cantidad = ingresarCantidad(medicamento);
	            
	            int cantDisponible=Datos.medicamentos[ramdom].getStockDisponible();
	            continuar = stockDisponible(cantDisponible, cantidad, ramdom);
	            
	            if(continuar==2) {            
	            	 ramdom = Helper.random.nextInt(Datos.medicamentos.length);
	            	 medicamento = Datos.medicamentos[ramdom];}
	        }
	        return cantidad;
	    }
	    
	    private static int ingresarCantidad(Medicamento medicamento) {
	    	System.out.println("**** Medicamento: "+medicamento.getDescripcion()+", Cantidad Disponible: "+ medicamento.getStockDisponible());
	    	return Helper.getInteger("Ingrese cantidad de medicamento: ");
	    }
	    
	    private static int stockDisponible(int cantDisponible, int cantidad,int ramdom) {
	    	  if(cantDisponible >= cantidad) {
	              Datos.medicamentos[ramdom].setStock(Datos.medicamentos[ramdom].getStockDisponible()-cantidad);
	              return 1;
	          }else {
	              System.out.println("*** Stock no disponible, cantidad Disponible: "+cantDisponible);
	              return 2; 
	          }
	    }
}
