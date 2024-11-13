package Hospital;

import Clases.Medicamento;
import Clases.Medico;
import Clases.Paciente;

import java.net.DatagramSocket;
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
        System.out.println("Seleccione el antecedente medico del paciente: ");
        for (int i = 0; i < antecedentes.length; i++) {
            System.out.println((i+1) + " " + antecedentes[i]);
        }
        int antecedente = Helper.validarEnIntervalo("_", 1, antecedentes.length);
        return antecedentes[--antecedente];
    }

    private void realizarConsultas(){

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
