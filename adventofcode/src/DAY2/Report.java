package DAY2;

public class Report {

    private String report;
    private int[] levels;

    public Report(String report) {
        this.report = report.trim();
        parseLevels();
    }

    // Convertir les valeurs du report en entiers
    private void parseLevels() {
        String[] parts = report.split("\\s+");
        levels = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            levels[i] = Integer.parseInt(parts[i]);
        }
    }

    // --------------------------
    //  PARTIE 1 : sans Dampener
    // --------------------------
    public boolean isSafe() {

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

    // --------------------------
    //  PARTIE 2 : avec Dampener
    // --------------------------
    public boolean isSafeWithDampener() {

        // Si déjà safe
        if (isSafe()) return true;

        // Sinon on tente de retirer 1 valeur
        String[] parts = report.split("\\s+");

        for (int i = 0; i < parts.length; i++) {

            // Construire un rapport sans la valeur parts[i]
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < parts.length; j++) {
                if (j != i) sb.append(parts[j]).append(" ");
            }

            String newReport = sb.toString().trim();
            Report newRep = new Report(newReport);

            if (newRep.isSafe()) {
                return true; // devient safe en retirant un élément
            }
        }

        return false;
    }
}
