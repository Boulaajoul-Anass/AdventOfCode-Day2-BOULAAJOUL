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

            Report rep = new Report(line);

            if (dampener) {
                if (rep.isSafeWithDampener()) safe++;
            } else {
                if (rep.isSafe()) safe++;
            }
        }

        br.close();

        if (dampener) {
            System.out.println("SAFE reports avec Problem Dampener : " + safe);
        } else {
            System.out.println("SAFE reports (Partie 1, sans Dampener) : " + safe);
        }
    }
}
