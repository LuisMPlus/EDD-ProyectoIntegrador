import Hospital.*;

public class Main {
    public static void main(String[] args) {

        Datos.LLenarDatosPrueba();
        Principal principal = new Principal();

        principal.run();
//       GestorConsultorio gestor = new GestorConsultorio();
//        gestor.generarConsultas();
        // Medico m =gestor.buscarmedico();
        // System.out.println("ess: "+m);

    }
}