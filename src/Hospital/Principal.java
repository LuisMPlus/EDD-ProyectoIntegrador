package Hospital;

import ClasesDadas.*;

import java.util.Random;
import java.util.Scanner;

public class Principal {
    String [] antecedentes = {"diabetes", "hipertension", "celiaco", "obesidad", "problemas renales", "taquicardia"};

    private int menuPrincipal() {
        System.out.println("""
                1. Dar de alta a un medico
                2. Ingreso de un nuevo paciente a la clinica
                3. Atender a un paciente en el consultorio
                4. Ingresar a la atencion del quirofano
                5. Realizar consultas
                6. Finalizar jornada""");
        return Helper.validarEnteroEnUnRango(1, 6, "Seleccione una opcion: ");
    }

    public void run(){
        System.out.println("Bienvenido al Hospital");
        this.registroMedicamentos();
        System.out.println();

        while (true){
            int opcion = menuPrincipal();
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
            System.out.println();
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
        System.out.println("Ingese datos del medicamento");
        String descripcion = Helper.validarString("Descripcion: ");

        while (verificarMedicamentosRepetidos(descripcion)){
            descripcion = Helper.validarString("Este medicamento ya existe, ingrese otro: ");
        }

        int stock = Helper.validarPositivo("Stock: ");
        double precio = Helper.validarDoublePositivo("Precio unitario: ");

        return new Medicamento(codigoMedicamentos, descripcion, precio, stock);
    }

    private boolean verificarMedicamentosRepetidos(String medicamento){
        int length = Datos.medicamentos.length;

        for (int i = 0; i < length; i++) {
            if(Datos.medicamentos[i] == null) continue;

            if (medicamento.equalsIgnoreCase(Datos.medicamentos[i].getDescripcion())){
                return true;
            }
        }
        return false;
    }

    //REgistro de medicos --------------------------------------------------------------------------------

    private void altaMedico(){
        Random rand = new Random();

        String nombre = Helper.validarString("Ingrese el nombre completo del medico: ");
        int matricula = rand.nextInt(1000, 10000);

        while (Datos.matriculas.contains(matricula)){
            matricula++;
        }

        System.out.println("Ingrese la especialidad del medico: ");
        int especialidad = Helper.validarEnIntervalo("1. Cirujano \n2. Clinico\n", 1, 2);

        if (especialidad == 1){
            Datos.Medicosdisponibles.add(new Medico(matricula, nombre, "Cirujano"));
        }
        else {
            Datos.Medicosdisponibles.add(new Medico(matricula, nombre, "Clinico"));
        }
        Datos.matriculas.add(matricula);
    }



    // Ingreso de pacientes --------------------------------------------------------------------------------
    private Paciente ingresoNuevoPaciente(){
        Random rand = new Random();

        int dni = Helper.validarPositivo("Ingrese el DNI del paciente: ");
        while (!validarDNI(dni)){
            dni = Helper.validarPositivo("DNI invalido debe ser de 8 caracteres, ingrese uno correcto: ");
        }
        while (Datos.dniRegistrados.contains(dni)){
            dni = Helper.validarPositivo("El dni ya esta registrado, ingrese uno correcto: ");
        }
        Datos.dniRegistrados.add(dni);
        String nombre = Helper.validarString("Ingrese el nombre completo del paciente: ");
        int edad = Helper.validarPositivo("Ingrese la edad del paciente: ");
        String antecedenteMedico = seleccionarAntecedentes();
        int diagnostico = rand.nextInt(1,3);


        return new Paciente(dni, nombre, edad, antecedenteMedico, diagnostico);
    }

    private boolean validarDNI(int dni){
        int cantDigitos = String.valueOf(dni).length();
        if (cantDigitos == 8){
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
