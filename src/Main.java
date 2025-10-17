import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static int[] boletosDisponibles = new int[3];
    static int[] boletosPrecios = new int[3];
    static int[][] ventas = new int[3][2];
    public static void main(String[] args) {
        confirmacionFecha();
        System.out.println("Deseas capturar datos?? (precios y #boletos)\ny/n ?");
        String siNo = sc.nextLine();
        if (siNo.equals("y")){
            capturaDatos();
        } else {capturaDatosTest();}
        do {
            System.out.println("\n\n||||||||||MENU||||||||||\n1. Comprar Boletos\n\n" +
                    "2. Mostrar Resumen de ventas\n\n3. Salir\n||||||||||MENU||||||||||\n\n");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    ventaBoletos();
                    break;
                case 2:
                    resumenVentas();
                    break;
                case 3:
                    System.exit(0);
            }
        } while (boletosDisponibles[0] > 0 && boletosDisponibles[1] > 0 && boletosDisponibles[2] > 0);

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
            System.exit(0);
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
    // Sistema que registra las ventas
    static void ventaBoletos(){
        int zona;
        int bolCuantos;
        int bolCosto;
        System.out.println("En que zona deseas comprar boletos?\n1. GENERAL\n2. PREFERENTE\n3. VIP");
        zona = sc.nextInt();
        switch (zona){
            case 1:
                System.out.println("Cuantos boletos desea comprar?");
                bolCuantos = sc.nextInt();
                bolCosto = bolCuantos * boletosPrecios[0];
                boletosDisponibles[0] -= bolCuantos;
                ventas[0][0] += bolCuantos;
                ventas[0][1] += bolCosto;
                System.out.println("Recibo\nCantidad de boletos: " + bolCuantos +
                        "\nPrecio por los boletos: $" + bolCosto + "\nBoletos restantes: " + boletosDisponibles[0]);
                break;
            case 2:
                System.out.println("Cuantos boletos desea comprar?");
                bolCuantos = sc.nextInt();
                bolCosto = bolCuantos * boletosPrecios[1];
                boletosDisponibles[1] -= bolCuantos;
                ventas[1][0] += bolCuantos;
                ventas[1][1] += bolCosto;
                System.out.println("Recibo\nCantidad de boletos: " + bolCuantos +
                        "\nPrecio por los boletos: $" + bolCosto + "\nBoletos restantes: " + boletosDisponibles[1]);
                break;
            case 3:
                System.out.println("Cuantos boletos desea comprar?");
                bolCuantos = sc.nextInt();
                bolCosto = bolCuantos * boletosPrecios[2];
                boletosDisponibles[2] -= bolCuantos;
                ventas[2][0] += bolCuantos;
                ventas[2][1] += bolCosto;
                System.out.println("Recibo\nCantidad de boletos: " + bolCuantos +
                        "\nPrecio por los boletos: $" + bolCosto + "\nBoletos restantes: " + boletosDisponibles[2]);
                break;
        }
    }

    /// Metodo que resume las ventas
    static void resumenVentas(){
        System.out.println("Ventas  Total");
        for (int i = 0; i < ventas.length; i++) {
            for (int j = 0; j < ventas[i].length; j++) {
                System.out.print(ventas[i][j] + "\t\t"); // tab for spacing
            }
            System.out.println();
        }
        System.out.println("1.General\n2.Preferente\n3.Vip");
    }
    // Metodo que use para testear las ventas
    static void capturaDatosTest(){
        System.out.println("Inicio de Captura de Datos\nIngresa el limite de boletos por zonas\nZONA GENERAL:");
        boletosDisponibles[0] = 1000;
        System.out.println(boletosDisponibles[0]);
        System.out.println("ZONA PREFERENTE");
        boletosDisponibles[1] = 100;
        System.out.println(boletosDisponibles[1]);
        System.out.println("ZONA VIP");
        boletosDisponibles[2] = 50;
        System.out.println(boletosDisponibles[2]);
        System.out.println("Ahora introduce el costo de cada zona:\nGENERAL $:");
        boletosPrecios[0] = 50;
        System.out.println(boletosPrecios[0]);
        System.out.println("PREFERENTE $:");
        boletosPrecios[1] = 100;
        System.out.println(boletosPrecios[1]);
        System.out.println("VIP $:");
        boletosPrecios[2] = 1000;
        System.out.println(boletosPrecios[2]);
    }
}