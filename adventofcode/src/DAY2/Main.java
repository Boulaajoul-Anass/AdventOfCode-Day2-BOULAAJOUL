package DAY2;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        
        Scanner sc = new Scanner(System.in);
        char choix;

        do {
            System.out.println("Choisissez votre mode :");
            System.out.println("1 - Mode normal (Partie 1)");
            System.out.println("2 - Mode Dampener (Partie 2)");
            System.out.print("Votre choix : ");
            choix = sc.next().charAt(0);

            if (choix != '1' && choix != '2') {
                System.out.println("Choix invalide ! Veuillez entrer 1 ou 2.\n");
            }

        } while (choix != '1' && choix != '2');
        sc.close();

        boolean dampener = (choix == '2');
        

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line;
        int safe = 0;

        while ((line = br.readLine()) != null) {

            if (dampener) {
                if (isSafeWithDampener(line)) safe++;
            } else {
                if (isSafe(line)) safe++;
            }
        }

        br.close();

        if (dampener) {
            System.out.println("SAFE reports avec Problem Dampener : " + safe);
        } else {
            System.out.println("SAFE reports (Partie 1, sans Dampener) : " + safe);
        }
    }

    // --------------------------
    //  PARTIE 2 : avec Dampener
    // --------------------------
    public static boolean isSafeWithDampener(String report) {

        // Si déjà safe
        if (isSafe(report)) return true;

        // Sinon on tente de retirer 1 valeur
        String[] parts = report.trim().split("\\s+");

        for (int i = 0; i < parts.length; i++) {

            // Construire un rapport sans la valeur parts[i]
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < parts.length; j++) {
                if (j != i) sb.append(parts[j]).append(" ");
            }

            String newReport = sb.toString().trim();

            if (isSafe(newReport)) {
                return true; // devient safe en retirant un élément
            }
        }

        return false;
    }


    // --------------------------
    //  PARTIE 1 : sans Dampener
    // --------------------------
    public static boolean isSafe(String report) {

        String[] parts = report.trim().split("\\s+");
        int[] levels = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            levels[i] = Integer.parseInt(parts[i]);
        }

        // différences successives
        int[] dif = new int[levels.length - 1];
        for (int i = 0; i < levels.length - 1; i++) {
            dif[i] = levels[i + 1] - levels[i];
        }

        boolean croissant = true;
        boolean decroissant = true;

        for (int d : dif) {
            if (d <= 0) croissant = false;
            if (d >= 0) decroissant = false;
        }

        if (!croissant && !decroissant) {
            return false;
        }

        // Vérifier amplitude
        for (int d : dif) {
            if (Math.abs(d) < 1 || Math.abs(d) > 3) {
                return false;
            }
        }

        return true;
    }
}
