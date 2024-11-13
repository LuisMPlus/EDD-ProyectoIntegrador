package Hospital;

import Clases.Medico;
import Estructuras.BinarySearchTree;

public class GestorConsultorio {

    public GestorConsultorio(){

    }

    public void generarConsulta(String especialidad){


    }

    public Medico buscarmedico(){
        try{
             int ramdom =(int) Math.random()*(Datos.matriculas.size()-1);
            System.out.println("entro");
            int matricula = Datos.matriculas.get(ramdom);

             Medico buscar = new Medico(matricula);
            System.out.println("buscado: "+buscar.getMatriculaProfesional());
             buscarMedicoPorEspecialidadyMetricula(Datos.Medicosdisponibles.cloneTree(),buscar);
           // System.out.println("encontrado: "+encontrado.getEspecialidad());
            /* if(encontrado != null){
                 return null;
             }*/
        }catch (Exception e){

        }
        return null;
    }

    public void buscarMedicoPorEspecialidadyMetricula(BinarySearchTree<Medico> arbol,Medico medico){

     }
}
