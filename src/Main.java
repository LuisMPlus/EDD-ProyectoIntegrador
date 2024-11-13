import Clases.Paciente;
import Hospital.*;

public class Main {
    public static void main(String[] args) {

        Principal principal = new Principal();
        principal.MEDICOSPRUEBA();
        GestorConsultorio gestor = new GestorConsultorio();
        gestor.buscarmedico();
    }
}