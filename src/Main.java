import Clases.Paciente;
import Hospital.*;

public class Main {
    public static void main(String[] args) {

        Principal principal = new Principal();
        principal.MEDICOSPRUEBA();

        GestorConsultorio.run();
//       GestorConsultorio gestor = new GestorConsultorio();
//        gestor.generarConsultas();
        // Medico m =gestor.buscarmedico();
        // System.out.println("ess: "+m);

    }
}