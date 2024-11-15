package ClasesDadas;

import java.time.LocalDate;

public class ConsultaMedica {
    private Medico medicoACargo;
    private Paciente paciente;
    private String medicacionAdministrada;
    private int cantidadAplicada;
    private LocalDate fecha;

    public ConsultaMedica() {}



    public ConsultaMedica(Medico medicoACargo, Paciente paciente, String medicacionAdministrada, int cantidadAplicada, LocalDate fecha) {
        this.medicoACargo = medicoACargo;
        this.paciente = paciente;
        this.medicacionAdministrada = medicacionAdministrada;
        this.cantidadAplicada = cantidadAplicada;
        this.fecha = fecha;
    }

    public void setMedicacionAdministrada(String medicacion) {
        this.medicacionAdministrada=medicacion;
    }

    public Medico getMedicoACargo() {
        return medicoACargo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getMedicacionAdministrada() {
        return medicacionAdministrada;
    }

    public int getCantidadAplicada() {
        return cantidadAplicada;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    /*public String toString() {
        return "ConsultaMedica{" +
                "medicoACargo=" + medicoACargo +
                ", paciente=" + paciente +
                ", medicacionAdministrada='" + medicacionAdministrada + '\'' +
                ", cantidadAplicada=" + cantidadAplicada +
                ", fecha=" + fecha +
                '}';
    }*/
    public String toString(){
        return String.format("%-8s %-25s %-10s %-25s %-11s %-15s %-4s%n", medicoACargo.getMatriculaProfesional(), medicoACargo.getNombre(),
                paciente.getDni(), paciente.getNombre(), paciente.getAntecedentes(),
                medicacionAdministrada, cantidadAplicada);
    }
}
