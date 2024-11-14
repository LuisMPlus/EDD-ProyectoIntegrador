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
        Datos.Medicosdisponibles.add(new Medico(5435, "Lucas Lopez", "Clinico"));
        Datos.Medicosdisponibles.add(new Medico(765, "Juan Carlos", "Clinico"));
        Datos.Medicosdisponibles.add(new Medico(1245, "Roberto Perez", "Cirujano"));
        Datos.Medicosdisponibles.add(new Medico(766, "Juan Carlos", "Clinico"));
        Datos.Medicosdisponibles.add(new Medico(999, "Elvio Gonz", "Cirujano"));
        Datos.Medicosdisponibles.add(new Medico(5436, "Lucas Lopez", "Clinico"));
        Datos.Medicosdisponibles.add(new Medico(6452, "Joaquin H.", "Cirujano"));
        Datos.matriculas.add(5435);
        Datos.matriculas.add(765);
        Datos.matriculas.add(1245);
        Datos.matriculas.add(766);
        Datos.matriculas.add(999);
        Datos.matriculas.add(5436);
        Datos.matriculas.add(6452);

        Datos.medicamentos = new Medicamento[4];
        Datos.medicamentos[0] = new Medicamento(123, "lasjfd", 231.323, 23);
        Datos.medicamentos[1] = new Medicamento(124, "lasjfd", 231.323, 23);
        Datos.medicamentos[2] = new Medicamento(125, "lasjfd", 231.323, 23);
        Datos.medicamentos[3] = new Medicamento(126, "lasjfd", 231.323, 23);

        Datos.prioridadMedia.offer(new Paciente(43407120, "Alejo", 30, "Diabetes", 2));
        Datos.prioridadMedia.offer(new Paciente(4537, "tua", 42, "angina", 2));
        Datos.prioridadMedia.offer(new Paciente(6971, "enzo", 10, "Diabetes", 2));
        Datos.prioridadMedia.offer(new Paciente(8163, "pedro", 26, "angina", 2));
        Datos.prioridadMedia.offer(new Paciente(48659, "luca", 15, "hepatitis", 2));

        Datos.prioridadAlta.offer(new Paciente(1232, "Juan", 20, "Diabetes", 1));
        Datos.prioridadAlta.offer(new Paciente(1232, "Pepe", 25, "Obesidad", 1));
        Datos.prioridadAlta.offer(new Paciente(1232, "Jorge", 50, "Problemas renales", 1));
        Datos.prioridadAlta.offer(new Paciente(1232, "Ernesto", 37, "Taquicardia", 1));

        //Datos.programadas.push(new Cirugia(Datos.Medicosdisponibles.remove(new Medico(5435)), Datos.prioridadAlta.remove(), LocalDate.now()));
        //Datos.programadas.push(new Cirugia(Datos.Medicosdisponibles.remove(new Medico(765)), Datos.prioridadAlta.remove(), LocalDate.now().plusDays(2)));
        //Datos.programadas.push(new Cirugia(Datos.Medicosdisponibles.remove(new Medico(1245)), Datos.prioridadAlta.remove(), LocalDate.now().plusWeeks(1)));

        //Datos.consultasRealizadas.addFirst(new ConsultaMedica(Datos.Medicosdisponibles.remove(new Medico(766)), Datos.prioridadMedia.remove(), "Ibuprofeno", 2, LocalDate.now()));
        //Datos.consultasRealizadas.addFirst(new ConsultaMedica(Datos.Medicosdisponibles.remove(new Medico(5436)), Datos.prioridadMedia.remove(), "Nada", 1, LocalDate.now()));
        //Datos.consultasRealizadas.addFirst(new ConsultaMedica(Datos.Medicosdisponibles.remove(new Medico(999)), Datos.prioridadMedia.remove(), "certal", 4, LocalDate.now()));

        //Datos.cirugiasRealizadas.addFirst(Datos.programadas.pop());
        //Datos.cirugiasRealizadas.addFirst(Datos.programadas.pop());
        //Datos.cirugiasRealizadas.addFirst(Datos.programadas.pop());

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

    public void run(){
        System.out.println("Bienvenido al Hospital");
        this.registroMedicamentos();

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
                    //Atender paciente prioridad media
                    GestorConsultorio.run();
                    break;
                case 4:
                    //atender paciente prioridad alta
                    GestorQuirofano.run();
                    break;
                case 5:
                    Consulta.realizarConsultas();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
            opcion = menuPrincipal();
        }
    }

    //Registro de Medicamentos ----------------------------------------------------------------

    private void registroMedicamentos(){
        int codigoMedicamentos = 1000;
        int cantMedicamentos = Helper.validarEnteroPositivo("Ingrese la cantidad de medicamentos a registrar: ");

        Datos.medicamentos = new Medicamento[cantMedicamentos];

        for (int i = 0; i < cantMedicamentos; i++) {
            Datos.medicamentos[i] = pedirMedicamento(codigoMedicamentos);
            codigoMedicamentos++;
        }
    }


    private Medicamento pedirMedicamento(int codigoMedicamentos){
        String descripcion = Helper.validarString("Ingrese la descripcion del medicamento");

        while (verificarMedicamentosRepetidos(descripcion)){
            descripcion = Helper.validarString("Este medicamento ya existe, ingrese otro");
        }

        int stock = Helper.validarPositivo("Ingrese el stock del medicamento: ");
        double precio = Helper.validarDoublePositivo("Ingrese el precio unitario del medicamento: ");

        return new Medicamento(codigoMedicamentos, descripcion, precio, stock);
    }

    private boolean verificarMedicamentosRepetidos(String medicamento){
        for (int i = 0; i < Datos.medicamentos.length; i++) {
            if (medicamento.equalsIgnoreCase(Datos.medicamentos[i].getDescripcion())){
                return true;
            }
        }
        return false;
    }

    //REgistro de medicos --------------------------------------------------------------------------------

    private void altaMedico(){
        Random rand = new Random();

        String nombre = Helper.validarString("Ingrese el nombre completo del medico");
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



    // Ingreso de pacientes --------------------------------------------------------------------------------
    private Paciente ingresoNuevoPaciente(){
        Random rand = new Random();

        int dni = Helper.validarPositivo("Ingrese el DNI del paciente: ");
        while (!validarDNI(dni)){
            dni = Helper.validarPositivo("DNI invalido, ingrese uno correcto: ");
        }

        String nombre = Helper.validarString("Ingrese el nombre completo del paciente: ");
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
        System.out.println("Seleccione el antecedente medicos del paciente: ");

        for (int i = 0; i < antecedentes.length; i++) {
            System.out.println((i+1) + " " + antecedentes[i]);
        }
        int antecedente = Helper.validarEnIntervalo("_", 1, antecedentes.length);
        return antecedentes[--antecedente];
    }






}
