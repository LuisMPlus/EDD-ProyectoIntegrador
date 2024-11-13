package Clases;

public class Paciente {
    private int dni;
    private String nombre;
    private int edad;
    private String antecedentes;
    private int diagnosticoPreliminar;

    public Paciente() {}

    public Paciente(int dni, String nombre, int edad, String antecedentes, int diagnosticoPreliminar) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.antecedentes = antecedentes;
        this.diagnosticoPreliminar = diagnosticoPreliminar;
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public int getDiagnosticoPreliminar() {
        return diagnosticoPreliminar;
    }



    @Override
    public String toString() {
        return "Paciente{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", antecedentes='" + antecedentes + '\'' +
                ", diagnosticoPreliminar=" + diagnosticoPreliminar +
                '}';
    }
}
