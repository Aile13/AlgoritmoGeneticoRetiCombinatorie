package it.unibs.eliapitozzi.algogen;

/**
 * @author Elia
 */
public class AlgoritmoGenetico {

    private static final int NUMERO_ITERAZIONI = 60;

    public static void run() {
        Popolazione generazione = Popolazione.creaPopolazioneCasuale();

        for (int i = 0; i < NUMERO_ITERAZIONI; i++) {
            Popolazione nuovaGenerazione = Popolazione.creaPopolazioneVuota();

            while (!nuovaGenerazione.haStessaDimensioneDi(generazione)) {
                CoppiaDiRetiCombinatorie coppiaDiIndividui = generazione.selezionaCoppiaDiIndividui();

                coppiaDiIndividui = coppiaDiIndividui.crossover();
                coppiaDiIndividui = coppiaDiIndividui.mutation();

                nuovaGenerazione.inserisciCoppiaDiIndividui(coppiaDiIndividui);
            }

            generazione = nuovaGenerazione;
        }

    }
}
