import Hospital.*;

public class Main {
    public static void main(String[] args) {

        /*A MODO DE PRUEBA, SE INCLUYE EL SIGUIENTE METODO, QUE CARGA DATOS INICIALES PARA FACILITAR LA PRUEBA DEL PROGRAMA*/
        Datos.LLenarDatosPrueba();
        Principal principal = new Principal();

        principal.run();
//       GestorConsultorio gestor = new GestorConsultorio();
//        gestor.generarConsultas();
        // Medico m =gestor.buscarmedico();
        // System.out.println("ess: "+m);

    }
}