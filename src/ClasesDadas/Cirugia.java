package ClasesDadas;

import java.time.LocalDate;

public class Cirugia {
    private Medico medicoResponsable;
    private Paciente paciente;
    private LocalDate fecha;

    public Cirugia() {}

    public Cirugia(Medico medicoResponsable, Paciente paciente, LocalDate fecha) {
        this.medicoResponsable = medicoResponsable;
        this.paciente = paciente;
        this.fecha = fecha;
    }

    public Medico getMedicoResponsable() {
        return medicoResponsable;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Cirugia{" +
                "medicoResponsable=" + medicoResponsable +
                ", paciente=" + paciente +
                ", fecha=" + fecha +
                '}';
    }
}
