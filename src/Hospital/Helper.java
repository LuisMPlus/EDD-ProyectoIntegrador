//
// Created by Julio Tentor <jtentor@fi.unju.edu.ar>
//
package Hospital;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Helper<ELEMENT> {


    //region Static Objects
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    //endregion


    public static Integer validarPositivo(String mensaje){
        String mensajeError = "El numero ingresado no es positivo, ingrese otro: ";
        int valor = Helper.getInteger(mensaje);
        while (valor<=0){
            valor = Helper.getInteger(mensajeError,  mensajeError);
        }
        return valor;
    }
    public static Integer validarEnteroEnUnRango(int inicio, int fin, String mensaje) {
        int valor = Helper.getInteger(mensaje);
        String mensajeError = "El valor ingresado no corresponde al rango establecido, ingrese otro: ";
        while (valor < inicio || valor > fin) {
            valor = Helper.getInteger(mensajeError, mensajeError);
        }
        return valor;
    }


    //region Character Helpers
    public static Character getCharacter(Scanner scanner, String inputMessage, String errorMessage) {
        Character characterValue;
        while (true) {
            System.out.print(inputMessage);
            try {
                characterValue = scanner.nextLine().charAt(0);
                return characterValue;
            } catch (Exception exception) {
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
    }
    public static Character getCharacter(Scanner scanner, String inputMessage) {
        return getCharacter(scanner, inputMessage, "Ingrese un caracter válido");
    }
    public static Character getCharacter(String inputMessage, String errorMessage) {
        return getCharacter(Helper.scanner, inputMessage, errorMessage);
    }
    public static Character getCharacter(String inputMessage) {
        return getCharacter(Helper.scanner, inputMessage, "Ingrese un caracter válido");
    }
    //endregion


    //region Integer Helpers
    public static Integer getInteger(Scanner scanner, String inputMessage, String errorMessage) {
        Integer integerValue = 0;
        while (true) {
            try {
                System.out.print(inputMessage);
                integerValue = Integer.parseInt(scanner.nextLine());
                return integerValue;
            }
            catch (Exception exception) {
                System.out.println(errorMessage);
            }
        }
    }
    public static Integer getInteger(Scanner scanner, String inputMessage) {
        return getInteger(scanner, inputMessage, "Ingrese un número válido");
    }
    public static Integer getInteger(String inputMessage, String errorMessage) {
        return getInteger(Helper.scanner, inputMessage, errorMessage);
    }
    public static Integer getInteger(String inputMessage) {
        return getInteger(Helper.scanner, inputMessage, "Ingrese un número válido");
    }
    //endregion


    //region Double Helpers
    public static Double getDouble(Scanner scanner, String inputMessage, String errorMessage) {
        Double doubleValue = 0.0;
        while (true) {
            try {
                System.out.print(inputMessage);
                doubleValue = Double.parseDouble(scanner.nextLine());
                return doubleValue;
            }
            catch (Exception exception) {
                System.out.println(errorMessage);
            }
        }
    }
    public static Double getDouble(Scanner scanner, String inputMessage) {
        return getDouble(scanner, inputMessage, "Ingrese un número válido");
    }
    public static Double getDouble(String inputMessage, String errorMessage) {
        return getDouble(Helper.scanner, inputMessage, errorMessage);
    }
    public static Double getDouble(String inputMessage) {
        return getDouble(Helper.scanner, inputMessage, "Ingrese un número válido");
    }
    //endregion


    //region Float Helpers
    public static Float getFloat(Scanner scanner, String inputMessage, String errorMessage) {
        Float floatValue = 0f;
        while (true) {
            try {
                System.out.print(inputMessage);
                floatValue = Float.parseFloat(scanner.nextLine());
                return floatValue;
            }
            catch (Exception exception) {
                System.out.println(errorMessage);
            }
        }
    }
    public static Float getFloat(Scanner scanner, String inputMessage) {
        return getFloat(scanner, inputMessage, "Ingrese un número válido");
    }
    public static Float getFloat(String inputMessage, String errorMessage) {
        return getFloat(Helper.scanner, inputMessage, errorMessage);
    }
    public static Float getFloat(String inputMessage) {
        return getFloat(Helper.scanner, inputMessage, "Ingrese un número válido");
    }
    //endregion



    //region Array Helpers

    static void printOneDimensionArray(String textBefore, Object[] array, String textAfter) {
        System.out.print(textBefore);
        System.out.print("[" + array[0]);
        for (int i = 1; i < array.length; ++i) {
            System.out.print("," + array[i]);
        }
        System.out.print("]");
        System.out.print(textAfter);
    }


    static void printTwoDimensionArray(String textBefore, Object[][] array, String textAfter) {
        System.out.print(textBefore);

        System.out.print("[[" + array[0][0]);
        for (int j = 1; j < array[0].length; ++j) {
            System.out.print("," + array[0][j]);
        }
        System.out.print("]");

        for (int i = 1; i < array.length; ++i) {
            System.out.print(",[" + array[i][0]);
            for (int j = 1; j < array[i].length; ++j) {
                System.out.print("," + array[i][j]);
            }
            System.out.print("]");
        }
        System.out.print("]");
        System.out.print(textAfter);
    }
    //endregion



    //region Enum Helpers

    // from http://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as-a-string
    public static String[] getEnumNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }

    //endregion

    public static void limpiarPantalla(){
        try {
            System.out.println("Presione enter para continuar...");
            new java.util.Scanner(System.in).nextLine();
            String sistemaOperativo = System.getProperty("os.name");
            ArrayList<String> comando= new ArrayList<>();
            if(sistemaOperativo.contains("Windows")){
                comando.add("cmd");
                comando.add("/C");
                comando.add("cls");

            } else {
                comando.add("clear");
            }

            ProcessBuilder pb = new ProcessBuilder(comando);
            Process iniciarProceso = pb.inheritIO().start();
            iniciarProceso.waitFor();

        } catch (Exception e) {
            System.out.println("Error al limpiar la pantalla"+e.getMessage());
        }
    }

    // Método para validar una fecha ingresada por consola en un formato específico
    public static LocalDate validarFecha(Scanner entrada, String mensaje, String formato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        LocalDate fecha;

        while (true) {
            System.out.println(mensaje + " (formato " + formato + "):");
            String valorIngresado = entrada.nextLine().trim();
            try {
                fecha = LocalDate.parse(valorIngresado, formatter);
                return fecha;
            } catch (DateTimeParseException e) {
                System.out.println("Error!!! Debe ingresar una fecha válida en el formato " + formato + ".");
            }
        }
    }

    // Método para validar la entrada de un tiempo en formato HH:mm:ss
    public static LocalTime validarHora(Scanner entrada, String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime hora;

        while (true) {
            System.out.println(mensaje + " (formato HH:mm:ss):");
            String valorIngresado = entrada.nextLine().trim();
            try {
                hora = LocalTime.parse(valorIngresado, formatter);
                return hora;
            } catch (DateTimeParseException e) {
                System.out.println("Error!!! Debe ingresar un valor válido en formato HH:mm:ss.");
            }
        }
    }

    //Metodo para validar que el numero ingresado sea entero

    public static Integer validarEnteroPositivo(String mensaje) {
        int valor = Helper.getInteger(mensaje);
        String mensajeError = "El valor ingresado no es positivo, ingrese otro: ";

        while (valor <= 0) {
            valor = Helper.getInteger(mensajeError, mensajeError);
        }
        return valor;
    }

    public static Double validarDoublePositivo(String mensaje) {
        double valor = Helper.getDouble(mensaje);
        String mensajeError = "El valor ingresado no es positivo, ingrese otro: ";

        while (valor <= 0) {
            valor = Helper.getDouble(mensajeError, mensajeError);
        }
        return valor;
    }


    public static Integer validarEnIntervalo(String mensaje, int infimo, int supremo) {
        int valor;
        String mensajeError = "El numero no esta en el intervalo de [" + infimo + ", " + supremo + "]";

        while(true){
            System.out.print(mensaje);
            valor = Helper.getInteger(mensaje);

            if(infimo <= valor && valor <= supremo){
                return valor;
            }
            System.out.println(mensajeError);
        }
    }

    public static String validarSustPropio(String mensaje){
        String valor;
        String mensajeError = "El nombre no es valido";

        while(true){
            System.out.print(mensaje);
            valor = scanner.nextLine();

            if(!valor.isBlank() && Character.isUpperCase(valor.charAt(0)) && valor.length() > 2 && valor.length() < 50 ){
                return valor;
            }

            System.out.println(mensajeError);
        }
    }

    public static String validarString(String mensaje){
        String valor;
        String mensajeError = "El texto no puede estar vacio";

        while(true){
            System.out.print(mensaje);
            valor = scanner.nextLine();

            if(!valor.isBlank()){
                return valor;
            }

            System.out.println(mensajeError);
        }
    }

}

