package roundrobin;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RoundRobin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // El usuario digita la cantidad de procesos que requiere
//        System.out.print("Ingresa el número de procesos: ");
        JOptionPane.showInputDialog("Ingresa el número de procesos: ");
        int n = sc.nextInt(); 

        // Estos 3 arrays almacenan tiempos de ráfaga, espera y retorno
        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n]; 

        // El usuario ingresa el tiempo de ráfaga para cada proceso
        System.out.println("Ingresa el tiempo de ráfaga de cada proceso: ");
//        JOptionPane.showInputDialog("Ingresa el tiempo de ráfaga de cada proceso: ");
        for(int i = 0; i < n; i++) {
//            System.out.print("Proceso " + (i+1) + ": ");
            JOptionPane.showMessageDialog(null, "Proceso" + (i + 1) + ": " );
            burstTime[i] = sc.nextInt();
        }    

        // El usuario ingresa el tamaño del quantum
//        System.out.print("Ingresa el tamaño del quantum: ");
        JOptionPane.showInputDialog("Ingresa el tamaño del quantum: ");
        int quantum = sc.nextInt();

        // Se verifica que el número de procesos y el tamaño del quantum sean mayores que cero
        if (n <= 0 || quantum <= 0) {
//            System.out.println("El número de procesos y el tamaño del quantum deben ser mayores que cero.");
            JOptionPane.showInputDialog("El número de procesos y el tamaño del quantum deben ser mayores que cero.");
            sc.close();
            return;
        }

        // Este array almacena el tiempo restante de cada proceso
        int[] remainingTime = new int[n];
        for(int i = 0; i < n; i++) {
            remainingTime[i] = burstTime[i];
        }      

        int time = 0;
        while(true) {
            boolean done = true;
            for(int i = 0; i < n; i++) {
                if(remainingTime[i] > 0) {
                    done = false;
                    if(remainingTime[i] > quantum) {
                        time += quantum;
                        remainingTime[i] -= quantum;
                    }
                    else {
                        time += remainingTime[i];
                        waitingTime[i] = time - burstTime[i];
                        remainingTime[i] = 0;
                    }
                }
            }
            if(done == true) {
                break;
            }
        }

        // Para calcular el tiempo de retorno para cada proceso
        for(int i = 0; i < n; i++) {
            turnaroundTime[i] = burstTime[i] + waitingTime[i];
        } 

        // Para la mpresión de tiempos de ráfaga, espera y retorno de cada proceso
//        System.out.println("Proceso\t\tTiempo de ráfaga\t\tTiempo de espera\t\tTiempo de retorno");
        JOptionPane.showMessageDialog(null, "Proceso\t\tTiempo de ráfaga\t\tTiempo de espera\t\tTiempo de retorno");
        for(int i = 0; i < n; i++) {
//            System.out.println("P" + (i+1) + "\t\t" + burstTime[i] + "\t\t\t\t\t" + waitingTime[i] + "\t\t\t" + turnaroundTime[i]);
            JOptionPane.showMessageDialog(null, "P" + (i+1) + "\t\t" + burstTime[i] + "\t\t\t\t\t" + waitingTime[i] + "\t\t\t" + turnaroundTime[i]);
        }

        // Para calcular y mostrar el tiempo promedio de espera y retorno
        float totalWaitingTime = 0;
        float totalTurnaroundTime = 0;

        for(int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }       
//        System.out.println("Tiempo promedio de espera: " + (totalWaitingTime/n));
        JOptionPane.showMessageDialog(null, "Tiempo promedio de espera: " + (totalWaitingTime/n));
//        System.out.println("Tiempo promedio de retorno: " + (totalTurnaroundTime/n));
        JOptionPane.showMessageDialog(null, "Tiempo promedio de retorno: " + (totalTurnaroundTime/n));
        sc.close();
    }
    
}

