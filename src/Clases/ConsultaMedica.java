package Clases;

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
    public String toString() {
        return "ConsultaMedica{" +
                "medicoACargo=" + medicoACargo +
                ", paciente=" + paciente +
                ", medicacionAdministrada='" + medicacionAdministrada + '\'' +
                ", cantidadAplicada=" + cantidadAplicada +
                ", fecha=" + fecha +
                '}';
    }
}
