package Hospital;

import ClasesDadas.Cirugia;
import ClasesDadas.ConsultaMedica;
import ClasesDadas.Medicamento;

import java.time.LocalDate;
import java.util.Scanner;

public class Consulta {
    // CONSULTAS ------------------------------------------------------------------
    protected static Scanner entrada = new Scanner(System.in);
    protected static String [] antecedentes = {"diabetes", "hipertension", "celiaco", "obesidad", "problemas renales", "taquicardia"};

    protected static int menuConsultas(){
        System.out.println("""
                1. Medicos disponibles
                2. Medicamentos con stock mayor o igual a un valor indicado
                3. Cirujias realizadas
                4. Consultas medicas efectuadas
                5. Cantidad de pacientes atendidos en un rango de fechas indicadas
                6. Pacientes operados, dentro de un rango de edad especificado
                7. Cantidad de pacientes atendidos en consulta, que comparten un mismo antecedente
                8. Monto total al que ascienden los medicamentos que se encuentran en stock
                9. Volver al menu principal""");
        System.out.println();

        return Helper.validarEnIntervalo("Seleccione una opcion: ",1, 9);
    }


    public static void realizarConsultas(){
        int opcion;
        System.out.println();
        while (true){
            opcion = menuConsultas();

            switch (opcion){
                case 1:
                    mostrarMedicosDisponibles();
                    break;
                case 2:
                    mostrarMedicamentos();
                    break;
                case 3:
                    cirujiasRealizadas();
                    break;
                case 4:
                    consultasMedicas();
                    break;
                case 5:
                    pacientesAtendidos();
                    break;
                case 6:
                     mostrarPacientesOperados();
                    break;
                case 7:
                    int pacienteSegunAntecedente = pacientesAtendidosSegunAntecedente();
                    if (pacienteSegunAntecedente == 0){
                        System.out.println("No se han registrado paciente con el antecedente indicado");
                    }else {
                        System.out.println("La cantidad de pacientes atendidos, con el mismo antecedente es de: " + pacienteSegunAntecedente);
                    }
                    break;
                case 8:
                    double montoTotal = calcularMontoMedicamentos();
                    if (montoTotal == 0.0){
                        System.out.println("No hay medicamentos en stock");
                    }else {
                        System.out.println("El valor total de los medicamentos en stock es de: " + montoTotal);
                    }
                    break;
                case 9:
                    return;

            }
            System.out.println();
        }
    }

    protected static void mostrarMedicosDisponibles(){
        if(Datos.Medicosdisponibles.toString().equals("")){
            System.out.println("No hay medicos disponibles");
        }
        else {
            System.out.printf("%-25s %-15s %-15s%n", "Nombre", "Especialidad", "Matricula");
            Datos.Medicosdisponibles.InOrder();
        }
    }

    protected static void mostrarMedicamentos(){
        int valor = Helper.validarPositivo("Ingrese un valor y se mostraran los medicamentos con stock menor o igual a ese valor: ");
        if (Datos.medicamentos.length == 0){
            System.out.println("No hay medicamentos en stock");
        }else {
            System.out.printf("%-10s %-15s %-18s %-20s%n", "codigo", "descripcion", "precioUnitario", "stockDisponible");
            for (Medicamento medicamento : Datos.medicamentos) {
                if (medicamento.getStockDisponible() <= valor){
                    System.out.print(medicamento.toString());
                }
            }
        }
    }
    //PUEDE GENERAR EXCEPCION
    protected static void cirujiasRealizadas(){
        if (Datos.cirugiasRealizadas.toString().equals("[]")){
            System.out.println("No se realizaron cirujias en el dia de hoy");
        }else {
            System.out.println();
            System.out.printf("%-46s %-45s%n", "Datos del medico", "Datos del paciente");
            System.out.printf("%-25s %-20s %-20s %-15s %-10s%n", "Nombre", "Matricula", "Nombre", "DNI", "Edad");
            System.out.println("_____________________________________________________________________________________________");
            int cantCirujias = Datos.cirugiasRealizadas.size();
            for (int i = 0; i < cantCirujias; i++) {
                Cirugia actual = Datos.cirugiasRealizadas.removeFirst();
                System.out.print(actual.toString());
                System.out.println("_____________________________________________________________________________________________");
                Datos.cirugiasRealizadas.addLast(actual);
            }
        }
    }
    //PUEDE GENERAR EXCEPCION
    protected static void consultasMedicas() {
        if (Datos.consultasRealizadas.toString().equals("[]")){
            System.out.println("No se realizaron consultas medicas en el dia de hoy");
        }else {
            System.out.printf("%-34s %-41s%n", "Datos del Medico", "Datos del Paciente");
            System.out.printf("%-8s %-24s %-10s %-25s %-10s %-14s %-4s%n", "Matricula", "Nombre", "DNI", "Nombre", "Antecedente", "Medicacion", "Cantidad administrada");
            System.out.println("________________________________________________________________________________________________________________________");
            for (ConsultaMedica consulta : Datos.consultasRealizadas) {
                System.out.print(consulta.toString());
                System.out.println("________________________________________________________________________________________________________________________");
            }
        }
    }

    //FALTA CONTROLAR LAS EXCEPCIONES DE LAS FECHAS
    //PUEDE DEVOLVER CERO
    protected static void pacientesAtendidos(){
        System.out.println("Indique las fechas en formato yyyy-MM-dd");

        LocalDate desde = Helper.validarFecha("Desde", "yy-MM-dd");
        System.out.println();

        LocalDate hasta = Helper.validarFecha("Hasta", "yy-MM-dd");
        System.out.println();


        int cantidadPacientes = calcularPacientesAtendidos(desde, hasta);
        if (cantidadPacientes >= 1){
            System.out.println("La cantidad de pacientes atendidos en el rango de fechas indicado es de: " + cantidadPacientes);
        }else {
            System.out.println("No se han atendido pacientes en ese periodo");
        }

    }
    protected static int calcularPacientesAtendidos(LocalDate desde, LocalDate hasta){
        int contadorPacientes = 0;
        if (Datos.cirugiasRealizadas.size() >= 1){
            int tam = Datos.cirugiasRealizadas.size();
            for (int i = 0; i < tam; i++) {
                Cirugia cirugia = Datos.cirugiasRealizadas.getFirst();
                if (cirugia.getFecha().isAfter(desde) && cirugia.getFecha().isBefore(hasta)){
                    contadorPacientes++;
                }
                Datos.cirugiasRealizadas.addLast(cirugia);
            }
        }
        if (Datos.consultasRealizadas.size() >= 1){
            int tam = Datos.consultasRealizadas.size();
            for (int i = 0; i < tam; i++) {
                ConsultaMedica consulta = Datos.consultasRealizadas.getFirst();
                if (consulta.getFecha().isAfter(desde) && consulta.getFecha().isBefore(hasta)){
                    contadorPacientes++;
                }
                Datos.consultasRealizadas.addLast(consulta);
            }
        }
        return contadorPacientes;
    }
    //SI LA LISTA DE CIRUJIAS ESTA VACIA devuelve cero
    protected static int pacientesOperados(){
        int cantidadPacientes = 0;
        int edadDesde = Helper.validarPositivo("Ingrese la edad minima: ");
        int edadHasta = Helper.validarPositivo("Ingrese la edad maxima: ");

        for (Cirugia cirugia: Datos.cirugiasRealizadas) {
            System.out.println(cirugia);
            if (cirugia.getPaciente().getEdad() >= edadDesde && cirugia.getPaciente().getEdad() <= edadHasta){
                cantidadPacientes++;
            }
        }
        return cantidadPacientes;
    }

    protected static void mostrarPacientesOperados(){
        int cantidadPacientes = pacientesOperados();
        if (cantidadPacientes >= 1){
            System.out.println("La cantidad de pacientes operados es de: " + cantidadPacientes);
        }else {
            System.out.println("No se han realizado cirujias");
        }
    }
    //PUEDE RETORNAR CERO
    protected static int pacientesAtendidosSegunAntecedente(){
        String antecedente = seleccionarAntecedentes();
        int contadorPacientes = 0;

        if (Datos.consultasRealizadas.size() >= 1){
            int tam = Datos.consultasRealizadas.size();
            for (ConsultaMedica consulta: Datos.consultasRealizadas) {
                if (consulta.getPaciente().getAntecedentes().equalsIgnoreCase(antecedente)){
                    contadorPacientes++;
                }
            }
        }
        return contadorPacientes;
    }

    private static String seleccionarAntecedentes(){
        System.out.println("Seleccione el antecedente medico del paciente: ");

        for (int i = 0; i < antecedentes.length; i++) {
            System.out.println((i+1) + " " + antecedentes[i]);
        }
        int antecedente = Helper.validarEnIntervalo("_", 1, antecedentes.length);
        return antecedentes[--antecedente];
    }
    //PUEDE RETORNAR CERO
    protected static double calcularMontoMedicamentos(){
        double monto = 0.0;
        for (int i = 0; i < Datos.medicamentos.length; i++) {
            Medicamento medicamento = Datos.medicamentos[i];
            if (medicamento.getStockDisponible() >= 1){
                monto += (medicamento.getStockDisponible()*medicamento.getPrecioUnitario());
            }
        }
        return monto;
    }
}
