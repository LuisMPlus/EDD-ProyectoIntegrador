package Hospital;

import Clases.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class GestorQuirofano {
    private static final int cantQuirofanos = 3;
    private static Medico medico;

    public static void run(){
        System.out.println();
        ControladorMenu();

    }

    public static void menu(){
        System.out.println("1. Atender Pacientes en quirofano");
        System.out.println("2. Realizar Cirugia");
        System.out.println("3. Salir del gestor de cirugias");
    }

    public static void ControladorMenu(){
        int opcion;
        menu();
        while(true){
            opcion = Helper.validarEnIntervalo("Seleccione una opcion: ", 1, 3);

            switch (opcion){
                case 1:
                    atenderPacientes();
                    break;
                case 2:
                    realizarCirugia();
                    break;

            }
        }
    }

    public static void atenderPacientes(){
        int cantPacientes = Datos.prioridadAlta.size();

        if(cantPacientes < cantQuirofanos){
            for(int i = 0; i < cantPacientes; i++){

            }
        } else {
            for(int i = 0; i < cantQuirofanos; i++){
              Cirugia cirugia = pedirCirugia();
              Datos.programadas.push(cirugia);
            }
        }

    }

    protected static Cirugia pedirCirugia (){
        Medico medico = asignarMedico();

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
        medico = seleccionarMedico();

        System.out.println("El medico seleccionado es: ");
        System.out.println(medico.toString());

        System.out.println("Arbol: ");
        System.out.println(Datos.Medicosdisponibles);

        return medico;
    }

    public static Medico seleccionarMedico(){
        int cantMedicos = Datos.matriculas.size();
        Medico medico = new Medico();
        int matricula = 0;

        ArrayList<Integer> matriculasAux = new ArrayList();

        for (int i = 0; i < cantMedicos; i++) {
            matricula = seleccionarMatricula();

            try{
                medico = Datos.Medicosdisponibles.remove(new Medico(matricula));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            if(medico.getEspecialidad().equalsIgnoreCase("Cirujano")) break;

            matriculasAux.add(Datos.matriculas.remove(matricula));
            Datos.Medicosdisponibles.add(medico);
        }

        //Volver a llenar el arrayList de matriculas
        for(int i = matriculasAux.size(); i > 0; i--){
            int matricuAux = matriculasAux.remove(0);

            if(matricuAux != matricula){
                Datos.matriculas.add(matriculasAux.remove(0));
            }
        }

        return medico;
    }

    public static int seleccionarMatricula(){
        Random rand = new Random();
        //Se selecciona un indice random del arrayList
        int indice = rand.nextInt(Datos.matriculas.size());
        int matricula = Datos.matriculas.get(indice);

        return matricula;
    }

    public static void realizarCirugia(){
        if (Datos.programadas.isEmpty()){
            System.out.println("No hay cirujias programadas para el dia de hoy...");
            return;
        }

        if (Datos.programadas.count() >= cantQuirofanos){
            for (int i = 0; i < 3; i++) {
                Cirugia cirugia = Datos.programadas.pop();
                Datos.salaDeDescanso.add(cirugia.getMedicoResponsable());
                Datos.cirugiasRealizadas.addLast(cirugia);
            }
            System.out.println("Se han realizado 3 cirujias en simultaneo...");

        }else {
            for (int i = 0; i < Datos.programadas.count(); i++) {
                Cirugia cirugia = Datos.programadas.pop();
                Datos.salaDeDescanso.add(cirugia.getMedicoResponsable());
                Datos.cirugiasRealizadas.addLast(cirugia);
            }
            System.out.println("Se han realizado " + Datos.programadas.count() + " cirugias");
        }
    }
}
