package Clases;

public class Medico implements Comparable<Medico>{
    private int matriculaProfesional;
    private String nombre;
    private String especialidad;

    public Medico() {}
    //Este constructor es para instanciar un medico para añadir o removerla por la matricula
    //en el arbol
    public Medico(int matricula) {
        this.matriculaProfesional = matricula;
        nombre = "";
        especialidad = "";
    }

    public Medico(int matricula,String especialidad) {
        this.matriculaProfesional = matricula;
        nombre = "";
        this.especialidad =especialidad;
    }

    public Medico(int matriculaProfesional, String nombre, String especialidad) {
        this.matriculaProfesional = matriculaProfesional;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public int getMatriculaProfesional() {
        return matriculaProfesional;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public int compareTo(Medico o) {
        return Integer.compare(this.matriculaProfesional, o.matriculaProfesional);
        /*
        precio1 == precio2 => 0
        precio1 > precio2 => 1
        precio1 < precio2 => -1
        * */
    }

    @Override
    public String toString() {
        return "Medico{" +
                "matriculaProfesional=" + matriculaProfesional +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }


    public int compareTo(String especialidad) {
        if(this.especialidad.equals(especialidad)) {
            return 0;
        }
        return 1;
    }
}
