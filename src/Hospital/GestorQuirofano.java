package Hospital;

import ClasesDadas.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorQuirofano {
    private static final int cantQuirofanos = 3;

    public static void run(){
        System.out.println();
        ControladorMenu();
    }

    public static void menu(){
        System.out.println("------------- QUIROFANO ------------------");
        System.out.println("1. Atender Paciente");
        System.out.println("2. Realizar Cirugia");
        System.out.println("3. Salir del quirofano");
    }

    public static void ControladorMenu(){
        int opcion;
        while(true){
            menu();
            opcion = Helper.validarEnIntervalo("Seleccione una opcion: ", 1, 3);

            switch (opcion){
                case 1:
                    atenderPacientes();
                    break;
                case 2:
                    realizarCirugia();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opcion invalida");
            }
            System.out.println();
        }
    }

    public static void atenderPacientes(){
        int cantPacientes = Datos.prioridadAlta.size();

        if(cantPacientes == 0){
            System.out.println("No hay pacientes en cola");
            return;
        }

        //Iteramos la cantidad de veces de aux
        Cirugia cirugia = pedirCirugia();
        if(cirugia == null) {
            System.out.println("No se pudo programar la cirugia. Lo sentimos");
            return;
        }
        Datos.programadas.push(cirugia);
        System.out.println("Cirugia programada con exito");

    }

    protected static Cirugia pedirCirugia (){
        Medico medico = asignarMedico();

        if(medico == null){
            System.out.println("No hay medicos disponibles");
            return null;
        }

        Paciente  paciente= null;
        try{
            paciente = Datos.prioridadAlta.remove();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        LocalDate fecha = Helper.validarFecha("Ingrese la fecha de la cirugia", "yy-MM-dd");

        return new Cirugia(medico, paciente, fecha);
    }

    public static Medico asignarMedico(){
        int cantMedicos = Datos.Medicosdisponibles.NodeCount();
        Medico encontrado = null;

        ArrayList<Integer> matriculasAux = new ArrayList<>();
        int matricula = 0;

        try{
            for (int i = 0; i < cantMedicos; i++) {
                //System.out.println("matriculas" + Datos.matriculas);

                int ramdom = Helper.random.nextInt(Datos.matriculas.size());
                matricula = Datos.matriculas.remove(ramdom);

                //System.out.println("matricula elegida aleatoriamente: " + matricula);
                matriculasAux.add(matricula);

                //Datos.Medicosdisponibles.InOrder();
                encontrado = Datos.Medicosdisponibles.remove(new Medico(matricula));

                if (encontrado.getEspecialidad().equals("Clinico")){
                    break;
                }
                Datos.Medicosdisponibles.add(encontrado);
                Datos.matriculas.add(matricula);

            }
        }catch (Exception e){
            throw new RuntimeException("Error al asignar el medico..." + e.getMessage());
        }

        //Volver a llenar el arrayList de matriculas del arrayListAux
        for(int i = matriculasAux.size(); i > 0; i--){
            int matricuAux = matriculasAux.removeLast();
            if(matricuAux != matricula){
                Datos.matriculas.add(matricuAux);
            }
        }

        if(encontrado != null){
            return encontrado;
        } else {
            return null;
        }
    }

    public static void realizarCirugia(){
        if (Datos.programadas.isEmpty()){
            System.out.println("No hay cirujias programadas para el dia de hoy...");
            return;
        }

        int aux = 0;
        if(Datos.programadas.count() < cantQuirofanos){
            aux = Datos.programadas.count();
        } else {
            aux = cantQuirofanos;
        }
        System.out.println("aux = " + aux);

        for (int i = 0; i < aux; i++) {
            Cirugia cirugia = Datos.programadas.pop();
            Datos.salaDeDescanso.add(cirugia.getMedicoResponsable());
            Datos.cirugiasRealizadas.addLast(cirugia);
        }
        System.out.println("Se han realizado " + aux + " cirujias en simultaneo...");

    }
}
