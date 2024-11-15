package Hospital;

import Estructuras.*;
import ClasesDadas.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Datos {
    protected static Medicamento[] medicamentos =new Medicamento[4]; //Medicamentos disponibles

    //Pacientes con diagnostico preliminar 2
    protected static Queue<Paciente> prioridadMedia = new Queue<>(15);
    //Pacientes con diagnostico preliminar 1
    protected static Queue<Paciente> prioridadAlta = new Queue<>(15);

    //Almacenara cirugias que resultan de la atencion de los pacientes de la cola de prioridadAlta
    protected static Stack<Cirugia> programadas = new Stack<>(10);


    //Que reulstan de la atencion de los pacientes de la cola prioridadMedia
    protected static DoubleLinkedList<ConsultaMedica> consultasRealizadas = new DoubleLinkedList<>();

    //Las cirugías que se realizan (salen de la pila programadas) deben guardarse en una lista para su posterior control.
    protected static DoubleLinkedList<Cirugia> cirugiasRealizadas = new DoubleLinkedList<>();


    //Guardara la informacion de los medicos disponibles
//El criterio de orden sera dado por la matriculaProfesional de cada medico
//Cuidado con las repetidas al generar las matriculas
    protected static BinarySearchTree<Medico> Medicosdisponibles = new BinarySearchTree<>();

    //Guarda la matricula de todos los medicos que esten disponibles
    protected static ArrayList<Integer> matriculas = new ArrayList<>();
    protected static ArrayList<Integer> dniRegistrados = new ArrayList<>();

    //Guarda a los medicos cansados
    protected static ArrayList<Medico> salaDeDescanso = new ArrayList<>();


    //Mostrar datos de las estructuras
    public static void mostrarDatos(){
        System.out.println("Array de medicamentos: ");
        mostrarMedicamentos();
        System.out.println("Queue prioridadMedia");
        System.out.println(prioridadMedia.toString());
        System.out.println("Queue prioridadAlta");
        System.out.println(prioridadAlta.toString());
        System.out.println("Stack de programadas");
        System.out.println(programadas.toString());
        System.out.println("DoubleLinkedList de consultadas1");
        System.out.println(consultasRealizadas.toString());
        System.out.println("DoubleLinkedList de cirugias");
        System.out.println(cirugiasRealizadas.toString());
        System.out.println("BinarySearchTree de medicosdisponibles");
        System.out.println(Medicosdisponibles.toString());
    }

    public static void mostrarMedicamentos(){
        for(int i = 0; i < medicamentos.length; i++){
            System.out.println(medicamentos[i].toString());
        }
    }


    //Solo para probar y no tener que llenar datos cada vez en la consola
    public static void LLenarDatosPrueba(){
        Medicosdisponibles.add(new Medico(1000, "Lucas Lopez", "Clinico"));
        Medicosdisponibles.add(new Medico(1001, "Juan Carlos", "Clinico"));
        Medicosdisponibles.add(new Medico(1002, "Roberto Perez", "Cirujano"));
        Medicosdisponibles.add(new Medico(1003, "Juan Carlos", "Clinico"));
        Medicosdisponibles.add(new Medico(1004, "Elvio Gonz", "Cirujano"));
        Medicosdisponibles.add(new Medico(1005, "Lucas Lopez", "Clinico"));
        Medicosdisponibles.add(new Medico(1006, "Joaquin H.", "Cirujano"));

        Medicosdisponibles.add(new Medico(1007, "Lucas Lopez", "Clinico"));
        Medicosdisponibles.add(new Medico(1008, "Juan Carlos", "Clinico"));
        Medicosdisponibles.add(new Medico(1009, "Roberto Perez", "Cirujano"));
        Medicosdisponibles.add(new Medico(1010, "Juan Carlos", "Clinico"));
        Medicosdisponibles.add(new Medico(1011, "Elvio Gonz", "Cirujano"));
        Medicosdisponibles.add(new Medico(1012, "Lucas Lopez", "Clinico"));
        //matriculas.add(1000);
        //matriculas.add(1001);
        //matriculas.add(1002);
        //matriculas.add(1003);
        //matriculas.add(999);
        //matriculas.add(5436);
        matriculas.add(1006);
        matriculas.add(1007);
        matriculas.add(1008);
        matriculas.add(1009);
        matriculas.add(1010);
        matriculas.add(1011);
        matriculas.add(1012);

//        medicamentos = new Medicamento[4];
//        medicamentos[0] = new Medicamento(123, "lasjfd", 231.323, 23);
//        medicamentos[1] = new Medicamento(124, "lasjfd", 231.323, 23);
//        medicamentos[2] = new Medicamento(125, "lasjfd", 231.323, 23);
//        medicamentos[3] = new Medicamento(126, "lasjfd", 231.323, 23);

        prioridadMedia.offer(new Paciente(43407120, "Alejo", 30, "Diabetes", 2));
        prioridadMedia.offer(new Paciente(4537, "tua", 42, "angina", 2));
        prioridadMedia.offer(new Paciente(6971, "enzo", 10, "Diabetes", 2));
        prioridadMedia.offer(new Paciente(8163, "pedro", 26, "angina", 2));
        prioridadMedia.offer(new Paciente(48659, "luca", 15, "hepatitis", 2));

        prioridadAlta.offer(new Paciente(1232, "Juan", 20, "Diabetes", 1));
        prioridadAlta.offer(new Paciente(1232, "Pepe", 25, "Obesidad", 1));
        prioridadAlta.offer(new Paciente(43407120, "Gaston Valdiviezo", 14, "Problemas renales", 1));
        prioridadAlta.offer(new Paciente(1232, "Ernesto", 37, "Taquicardia", 1));

        programadas.push(new Cirugia(Medicosdisponibles.remove(new Medico(1000)), prioridadAlta.remove(), LocalDate.now()));
        programadas.push(new Cirugia(Medicosdisponibles.remove(new Medico(1001)), prioridadAlta.remove(), LocalDate.now().plusDays(2)));
        programadas.push(new Cirugia(Medicosdisponibles.remove(new Medico(1002)), prioridadAlta.remove(), LocalDate.now()));

        consultasRealizadas.addFirst(new ConsultaMedica(Medicosdisponibles.remove(new Medico(1003)), prioridadMedia.remove(), "Ibuprofeno", 2, LocalDate.now()));
        consultasRealizadas.addFirst(new ConsultaMedica(Medicosdisponibles.remove(new Medico(1004)), prioridadMedia.remove(), "Nada", 1, LocalDate.now()));
        consultasRealizadas.addFirst(new ConsultaMedica(Medicosdisponibles.remove(new Medico(1005)), prioridadMedia.remove(), "certal", 4, LocalDate.now()));

        cirugiasRealizadas.addFirst(programadas.pop());
        cirugiasRealizadas.addFirst(programadas.pop());
        cirugiasRealizadas.addFirst(programadas.pop());


    }
}
