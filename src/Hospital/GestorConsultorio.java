package Hospital;

import ClasesDadas.ConsultaMedica;
import ClasesDadas.Medico;
import ClasesDadas.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorConsultorio {

    public static void run(){
        try {
            Medico medico=new Medico();
            //ver el tamaño del array de prioridadMedia
            System.out.println("long: "+Datos.prioridadMedia.size());
            int i=0;
            while(Datos.prioridadMedia.size()!=0) {
                Paciente paciente = Datos.prioridadMedia.peek();
                if(i==0 || i==10) {
                    medico = buscarmedico();

                    if(medico == null) {
                        System.out.println("NO hay medicos disponibles para la consulta");
                        return;
                    }
                }
                mostrarInformacion(i,medico.getNombre(),paciente.getNombre());
                int ramdom= Helper.random.nextInt(Datos.medicamentos.length);
                String medicamento= Datos.medicamentos[ramdom].getDescripcion();
                int cantidadMedicamento= medicamentoExiste(ramdom);
                ConsultaMedica consultaMedica= new ConsultaMedica(medico,paciente,medicamento,cantidadMedicamento,LocalDate.now());
                Datos.consultasRealizadas.addLast(consultaMedica);
                Datos.prioridadMedia.remove();
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private static void mostrarInformacion(int consultaN,String nombreM,String nombreP){
        System.out.println("Consulta: "+consultaN);
        System.out.println("Medico: "+nombreM);
        System.out.println("Paciente: "+nombreP);
    }

    public static Medico buscarmedico(){
        int cantMedicos = Datos.Medicosdisponibles.NodeCount();

        ArrayList<Integer> matriculasAux = new ArrayList<>();
        int matricula = 0;

        try{
            for (int i = 0; i < cantMedicos; i++) {
                System.out.println(Datos.matriculas);

                if(Datos.matriculas.size() != 1){

                    int ramdom = Helper.random.nextInt(Datos.matriculas.size());
                     matricula = Datos.matriculas.remove(ramdom);
                     System.out.println("tam: "+matricula);
                } else{
                    matricula = Datos.matriculas.removeLast();
                }

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
        System.out.println(matriculasAux.size());
        for(int i = matriculasAux.size(); i > 0; i--){
            int matricuAux = matriculasAux.removeLast();
            if(matricuAux != matricula){
                Datos.matriculas.add(matricuAux);
            }
        }
        return null;
    }

    public static int medicamentoExiste(int ramdom) {
        String medicamento = Datos.medicamentos[ramdom].getDescripcion();
        //mostrarMedicamento();
        boolean band=false;
        int cantidad=0;
        while(!band) {
            System.out.println("Medicamento: "+medicamento);
            cantidad = Helper.getInteger("Ingrese cantidad de medicamento: ");
            if(Datos.medicamentos[ramdom].getStockDisponible()>=cantidad) {
                Datos.medicamentos[ramdom].setStock(Datos.medicamentos[ramdom].getStockDisponible()-cantidad);
                band=true;
            }else {
                System.out.println("Stock no disponible");	}
        }
        //consulta.setMedicacionAdministrada(medicamento);
        return cantidad;
    }
}
