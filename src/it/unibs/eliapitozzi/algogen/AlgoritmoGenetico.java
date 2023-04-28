package it.unibs.eliapitozzi.algogen;

/**
 * @author Elia
 */
public class AlgoritmoGenetico {

    private static final int NUMERO_ITERAZIONI = 60;

    public static void run() {
        Popolazione generazione = Popolazione.getPopolazioneRandom();

        for (int i = 0; i < NUMERO_ITERAZIONI; i++) {
            Popolazione nuovaGenerazione = Popolazione.getPopolazioneVuota();

            while (!nuovaGenerazione.haStessaDimensioneDi(generazione)) {
                ReteCombinatoria individuo1 = generazione.selezionaIndividuo();
                ReteCombinatoria individuo2 = generazione.selezionaIndividuo();

                individuo1.crossover(individuo2);

                individuo1.mutation();
                individuo2.mutation();

                nuovaGenerazione.inserisci(individuo1);
                nuovaGenerazione.inserisci(individuo2);
            }

            generazione = nuovaGenerazione;
        }

    }
}
