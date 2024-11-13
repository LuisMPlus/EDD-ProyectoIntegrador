package Hospital;

import Clases.*;

import java.net.DatagramSocket;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Principal {
    Scanner entrada = new Scanner(System.in);
    String [] antecedentes = {"diabetes", "hipertension", "celiaco", "obesidad", "problemas renales", "taquicardia"};

    public void MEDICOSPRUEBA(){
        Datos.Medicosdisponibles.add(new Medico(1245, "Roberto Perez", "Cirujano"));
        Datos.Medicosdisponibles.add(new Medico(765, "Juan Carlos", "Clinico"));
        Datos.Medicosdisponibles.add(new Medico(999, "Elvio Gonz", "Cirujano"));
        Datos.Medicosdisponibles.add(new Medico(5435, "Lucas Lopez", "Clinico"));
        Datos.Medicosdisponibles.add(new Medico(6452, "Joaquin H.", "Cirujano"));
        Datos.matriculas.add(1245);
        Datos.matriculas.add(765);
        Datos.matriculas.add(999);
        Datos.matriculas.add(5435);
        Datos.matriculas.add(6452);
    }

    public void run(){
        System.out.println("Bienvenido al Hospital");
        this.registroMedicamentos();
        this.MEDICOSPRUEBA();

        int opcion = menuPrincipal();
        while (true){
            switch (opcion){
                case 1:
                    this.altaMedico();
                    break;
                case 2:
                    Paciente nuevoPaciente = this.ingresoNuevoPaciente();
                    try{
                        if (nuevoPaciente.getDiagnosticoPreliminar() == 1){
                            Datos.prioridadAlta.add(nuevoPaciente);
                        }else{
                            Datos.prioridadMedia.add(nuevoPaciente);
                        }
                    } catch (Exception e) {
                        System.out.println("Lamentablemente la cola de pacientes esta llena");;
                    }
                    break;
                case 3:
                    //atender paciente prioridad media
                    break;
                case 4:
                    //atender paciente prioridad alta
                    break;
                case 5:
                    this.realizarConsultas();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
            opcion = menuPrincipal();
        }
    }

    private void altaMedico(){
        Random rand = new Random();
        System.out.println("Ingrese el nombre completo del medico");
        String nombre = entrada.nextLine();
        int matricula = rand.nextInt(1000, 10000);
        while (Datos.matriculas.contains(matricula)){
            matricula++;
        }

        System.out.println("Ingrese la especialidad del medico ");
        int especialidad = Helper.validarEnIntervalo("1. Cirujano \n2. Clinico", 1, 2);
        if (especialidad == 1){
            Datos.Medicosdisponibles.add(new Medico(matricula, nombre, "Cirujano"));
        }
        else {
            Datos.Medicosdisponibles.add(new Medico(matricula, nombre, "Clinico"));
        }
    }
    private void registroMedicamentos(){
        int codigoMedicamentos = 1000;
        int cantMedicamentos = Helper.getInteger("Ingrese la cantidad de medicamentos a registrar: ");
        Datos.medicamentos = new Medicamento[cantMedicamentos];
        for (int i = 0; i < cantMedicamentos; i++) {
            System.out.println("Ingrese el nombre del medicamento");
            String descripcion = entrada.nextLine();
            while (verificarMedicamentosRepetidos(descripcion, cantMedicamentos)){
                System.out.println("Este medicamento ya existe, ingrese otro");
                descripcion = entrada.nextLine();
            }
            int stock = Helper.validarPositivo("Ingrese el stock del medicamento: ");
            double precio = Helper.validarDoublePositivo("Ingrese el precio unitario del medicamento: ");

            Datos.medicamentos[i] = new Medicamento(codigoMedicamentos, descripcion, precio, stock);
            codigoMedicamentos++;
        }
    }
    private boolean verificarMedicamentosRepetidos(String medicamento, int n){
        for (int i = 0; i < n; i++) {
            if (medicamento.equalsIgnoreCase(Datos.medicamentos[i].getDescripcion())){
                return true;
            }
        }
        return false;
    }
    private Paciente ingresoNuevoPaciente(){
        Random rand = new Random();

        int dni = Helper.validarPositivo("Ingrese el DNI del paciente: ");
        while (!validarDNI(dni)){
            dni = Helper.validarPositivo("DNI invalido, ingrese uno correcto: ");
        }
        System.out.println("Ingrese el nombre completo del paciente: ");
        String nombre = entrada.nextLine();
        int edad = Helper.validarPositivo("Ingrese la edad del paciente: ");
        String antecedenteMedico = seleccionarAntecedentes();
        int diagnostico = rand.nextInt(1,3);

        return new Paciente(dni, nombre, edad, antecedenteMedico, diagnostico);
    }

    private boolean validarDNI(int dni){
        int cantDigitos = String.valueOf(dni).length();
        if (cantDigitos == 8 || Datos.dniRegistrados.contains(dni)){
            return true;
        }
        return false;
    }
    private String seleccionarAntecedentes(){
        System.out.println("Seleccione el antecedente del paciente: ");
        for (int i = 0; i < antecedentes.length; i++) {
            System.out.println((i+1) + " " + antecedentes[i]);
        }
        int antecedente = Helper.validarEnIntervalo("_", 1, antecedentes.length);
        return antecedentes[--antecedente];
    }

    private void realizarConsultas(){
        int opcion = menuConsultas();
        while (opcion >=1 && opcion<= 8){
            switch (opcion){
                case 1:
                    try {
                        mostrarMedicosDisponibles();
                    }catch (Exception e){
                        System.out.println("No hay medicos registrados // no hay medicos disponibles");
                    }
                    break;
                case 2:
                    mostrarMedicamentos();
                    break;
                case 3:
                    try {
                        cirujiasRealizadas();
                    }catch (Exception e){
                        System.out.println("No hay cirujias registradas");
                    }
                    break;
                case 4:
                    try {
                        consultasMedicas();
                    }catch (Exception e){
                        System.out.println("No se registraron consultas medicas");
                    }
                    break;
                case 5:
                    try {
                        pacientesAtendidos();
                    }catch (Exception e){
                        System.out.println("El rango de fechas indicado es incorrecto");
                    }
                    break;
                case 6:
                    int cantidadPacientes = pacientesOperados();
                    if (cantidadPacientes >= 1){
                        System.out.println("La cantidad de pacientes operados es de: " + cantidadPacientes);
                    }else {
                        System.out.println("No se han realizado cirujias");
                    }
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
            }
            opcion = menuConsultas();
        }
    }
    //SI EL ARBOL ESTA VACIO PUEDE GENERAR EXCEPCION
    private void mostrarMedicosDisponibles(){
        System.out.println("Mostrando medicos disponibles");
        System.out.println(Datos.Medicosdisponibles.toString());
    }

    private void mostrarMedicamentos(){
        int valor = Helper.validarPositivo("Ingrese un valor y se mostraran los medicamentos con stock mayor a ese valor: ");
        if (Datos.medicamentos.length == 0){
            System.out.println("No hay medicamentos en stock");
        }else {
            for (Medicamento medicamento : Datos.medicamentos) {
                if (medicamento.getStockDisponible() >= valor){
                    System.out.println(medicamento.toString());
                }
            }
        }
    }
    //PUEDE GENERAR EXCEPCION
    private void cirujiasRealizadas(){
        if (Datos.cirugiasRealizadas.toString().equals("[]")){
            System.out.println("No se realizaron cirujias en el dia de hoy");
        }else {
            System.out.println("Cirujias realizadas en el dia de hoy");
            System.out.println(Datos.cirugiasRealizadas.toString());
        }
    }
    //PUEDE GENERAR EXCEPCION
    private void consultasMedicas() {
        if (Datos.consultadRealizadas.toString().equals("[]")){
            System.out.println("No se realizaron consultas medicas en el dia de hoy");
        }else {
            System.out.println("Consultas medicas realizadas en el dia de hoy");
            System.out.println(Datos.consultadRealizadas.toString());
        }
    }

    //FALTA CONTROLAR LAS EXCEPCIONES DE LAS FECHAS
    //PUEDE DEVOLVER CERO
    private void pacientesAtendidos(){
        System.out.println("Indique las fechas en formato yyyy-mm-dd");
        System.out.print("Desde: ");
        String desde = entrada.nextLine();
        System.out.println();
        System.out.print("Hasta: ");
        String hasta = entrada.nextLine();
        System.out.println();

        LocalDate fechaDesde = LocalDate.parse(desde);
        LocalDate fechaHasta = LocalDate.parse(hasta);

        int cantidadPacientes = calcularPacientesAtendidos(fechaDesde, fechaHasta);
        if (cantidadPacientes >= 1){
            System.out.println("La cantidad de pacientes atendidos en el rango de fechas indicado es de: " + cantidadPacientes);
        }else {
            System.out.println("No se han atendido pacientes en ese periodo");
        }

    }
    private int calcularPacientesAtendidos(LocalDate desde, LocalDate hasta){
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
        if (Datos.consultadRealizadas.size() >= 1){
            int tam = Datos.consultadRealizadas.size();
            for (int i = 0; i < tam; i++) {
                ConsultaMedica consulta = Datos.consultadRealizadas.getFirst();
                if (consulta.getFecha().isAfter(desde) && consulta.getFecha().isBefore(hasta)){
                    contadorPacientes++;
                }
                Datos.consultadRealizadas.addLast(consulta);
            }
        }
        return contadorPacientes;
    }
    //SI LA LISTA DE CIRUJIAS ESTA VACIA devuelve cero
    private int pacientesOperados(){
        int cantidadPacientes = 0;
        int edadDesde = Helper.validarPositivo("Ingrese la edad minima: ");
        int edadHasta = Helper.validarPositivo("Ingrese la edad maxima: ");
        int tam = Datos.cirugiasRealizadas.size();

        for (int i = 0; i < tam; i++) {
            Cirugia cirugia = Datos.cirugiasRealizadas.getFirst();
            if (cirugia.getPaciente().getEdad() >= edadDesde && cirugia.getPaciente().getEdad() <= edadHasta){
                cantidadPacientes++;
            }
            Datos.cirugiasRealizadas.addLast(cirugia);
        }
        return cantidadPacientes;
    }
    //PUEDE RETORNAR CERO
    private int pacientesAtendidosSegunAntecedente(){
        String antecedente = this.seleccionarAntecedentes();
        int contadorPacientes = 0;

        if (Datos.consultadRealizadas.size() >= 1){
            int tam = Datos.consultadRealizadas.size();
            for (int i = 0; i < tam; i++) {
                ConsultaMedica consulta = Datos.consultadRealizadas.getFirst();
                if (consulta.getPaciente().getAntecedentes().equals(antecedente)){
                    contadorPacientes++;
                }
            }
        }
        return contadorPacientes;
    }
    //PUEDE RETORNAR CERO
    private double calcularMontoMedicamentos(){
        double monto = 0.0;
        for (int i = 0; i < Datos.medicamentos.length; i++) {
            Medicamento medicamento = Datos.medicamentos[i];
            if (medicamento.getStockDisponible() >= 1){
                monto += (medicamento.getStockDisponible()*medicamento.getPrecioUnitario());
            }
        }
        return monto;
    }
    private int menuPrincipal() {
        System.out.println("""
                1. Dar de alta a un medico
                2. Ingreso de un nuevo paciente a la clinica
                3. Atender a un paciente de prioridad media
                4. Atender a un paciente de prioridad alta
                5. Realizar consultas
                6. Finalizar jornada""");
        return Helper.validarEnteroEnUnRango(1, 6, "Seleccione una opcion: ");
    }
    private int menuConsultas(){
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
        return Helper.validarEnteroEnUnRango(1, 9, "Seleccione una opcion: ");
    }

}
