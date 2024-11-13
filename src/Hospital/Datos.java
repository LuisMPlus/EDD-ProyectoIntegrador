package Hospital;

import Estructuras.*;
import Clases.*;

import java.util.ArrayList;

public class Datos {
    protected static Medicamento[] medicamentos; //Medicamentos disponibles

    //Pacientes con diagnostico preliminar 2
    protected static Queue<Paciente> prioridadMedia = new Queue<>(50);
    //Pacientes con diagnostico preliminar 1
    protected static Queue<Paciente> prioridadAlta = new Queue<>(50);


    //Almacenara cirugias que resultan de la atencion de los pacientes de la cola de prioridadAlta
    protected static Stack<Cirugia> programadas;


    //Que reulstan de la atencion de los pacientes de la cola prioridadMedia
    protected static DoubleLinkedList<ConsultaMedica> consultadRealizadas;

    //Las cirugías que se realizan (salen de la pila programadas) deben guardarse en una lista para su posterior control.
    protected static DoubleLinkedList<Cirugia> cirugiasRealizadas;


    //Guardara la informacion de los medicos disponibles
//El criterio de orden sera dado por la matriculaProfesional de cada medico
//Cuidado con las repetidas al generar las matriculas
    protected static BinarySearchTree<Medico> Medicosdisponibles = new BinarySearchTree<>();

    //Guarda la matricula de todos los medicos que esten disponibles
    protected static ArrayList<Integer> matriculas = new ArrayList<>();
    protected static ArrayList<Integer> dniRegistrados = new ArrayList<>();



    //Getters de las estructuras

    public void mostrarDatos(){
        System.out.println("Array de medicamentos: ");
        mostrarMedicamentos();
        System.out.println("Queue prioridadMedia");
        System.out.println(prioridadMedia.toString());
        System.out.println("Queue prioridadAlta");
        System.out.println(prioridadAlta.toString());
        System.out.println("Stack de programadas");
        System.out.println(programadas.toString());
        System.out.println("DoubleLinkedList de consultadas1");
        System.out.println(consultadRealizadas.toString());
        System.out.println("DoubleLinkedList de cirugias");
        System.out.println(cirugiasRealizadas.toString());
        System.out.println("BinarySearchTree de medicosdisponibles");
        System.out.println(Medicosdisponibles.toString());
    }

    public void mostrarMedicamentos(){
        System.out.print("[ ");
        for(int i = 0; i < medicamentos.length; i++){
            System.out.print(medicamentos[i].toString() + ", ");
        }
        System.out.print("] \n");
    }
}
