import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static int[] boletosDisponibles = new int[3];
    static int[] boletosPrecios = new int[3];
    public static void main(String[] args) {

        confirmacionFecha();
        capturaDatos();
    }
    static void confirmacionFecha(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Introducir Fecha del Concierto:\n(!!!!FORMATO dd/MM/yyyy!!!!)");
        String entrada = sc.nextLine();

        try {
            // Texto a fecha
            LocalDate fechaConcierto = LocalDate.parse(entrada, formato);
            LocalDate fechaActual = LocalDate.now();

            // Comparar fechas
            if (fechaConcierto.isBefore(fechaActual)) {
                System.out.println("El concierto ya paso");
                System.exit(0);
            } else if (fechaConcierto.isEqual(fechaActual)){
                System.out.println("El concierto es hoy!!!, faltan pocas horas");
            } else {
                long diasFaltantes = ChronoUnit.DAYS.between(fechaActual, fechaConcierto);
                System.out.println("Faltan " + diasFaltantes + " dia(s) para el concierto.");
            }
        } catch (DateTimeException e){
            System.out.println("El formato de la fecha no es correcto");
        }
    }
    static void capturaDatos(){
        System.out.println("Inicio de Captura de Datos\nIngresa el limite de boletos por zonas\nZONA GENERAL:");
        boletosDisponibles[0] = sc.nextInt();
        System.out.println("ZONA PREFERENTE");
        boletosDisponibles[1] = sc.nextInt();
        System.out.println("ZONA VIP");
        boletosDisponibles[2] = sc.nextInt();
        System.out.println("Ahora introduce el costo de cada zona:\nGENERAL $:");
        boletosPrecios[0] = sc.nextInt();
        System.out.println("PREFERENTE $:");
        boletosPrecios[1] = sc.nextInt();
        System.out.println("VIP $:");
        boletosPrecios[2] = sc.nextInt();
    }
    static void ventaBoletos(){

    }
}