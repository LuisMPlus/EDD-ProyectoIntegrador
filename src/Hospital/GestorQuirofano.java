package Hospital;

import Clases.*;

import java.util.ArrayList;
import java.util.Random;

public class GestorQuirofano {

    private Medico medico;

    public GestorQuirofano() {

        medico = seleccionarMedico();

        System.out.println("El medico seleccionado es: ");
        System.out.println(medico.toString());


    }

    public Medico seleccionarMedico(){
        int cantMedicos = Datos.matriculas.size();
        Medico medico = new Medico();
        int matricula = 0;

        ArrayList<Integer> matriculasAux = new ArrayList();

        for (int i = 0; i < cantMedicos; i++) {
            matricula = seleccionarMatricula();

            medico = Datos.Medicosdisponibles.remove(new Medico(matricula));

            if(medico.getEspecialidad().equals("Cirujano")) break;

            matriculasAux.add(Datos.matriculas.remove(matricula));
            Datos.Medicosdisponibles.add(medico);
        }

        //Volver a llenar el arrayList de matriculas
        for(int i = matriculasAux.size(); i > 0; i--){
            int matricuAux = matriculasAux.remove(0);

            if(matricuAux != matricula){
                Datos.matriculas.add(matriculasAux.remove(0));
            }
        }

        return medico;
    }

    public int seleccionarMatricula(){
        Random rand = new Random();
        //Se selecciona un indice random del arrayList
        int indice = rand.nextInt(Datos.matriculas.size());
        int matricula = Datos.matriculas.get(indice);

        return matricula;
    }


}
