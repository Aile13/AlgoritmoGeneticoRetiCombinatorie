package it.unibs.eliapitozzi.algoritmogenetico;

/**
 * @author Elia
 */
public class AlgoritmoGenetico {

    private static final int NUMERO_ITERAZIONI = 50;

    public static String run(TabellaDiVerita tabellaDiVerita) {
        Popolazione generazione = Popolazione.creaPopolazioneCasuale(tabellaDiVerita);

        for (int i = 0; i < NUMERO_ITERAZIONI; i++) {
            Popolazione nuovaGenerazione = Popolazione.creaPopolazioneVuota();

            generazione.mostraInfo(i, tabellaDiVerita);

            while (!nuovaGenerazione.haStessaDimOPariSuccessivoDi(generazione)) {
                CoppiaDiRetiCombinatorie coppiaDiIndividui = generazione.selezionaCoppiaDiIndividui(tabellaDiVerita);

                coppiaDiIndividui = coppiaDiIndividui.crossover();
                coppiaDiIndividui.mutation();

                nuovaGenerazione.inserisciCoppiaDiIndividui(coppiaDiIndividui);
            }

            generazione = nuovaGenerazione;
        }

        ReteCombinatoria miglioreIndividuo = generazione.getMiglioreIndividuo(tabellaDiVerita);
        return miglioreIndividuo.getFunction();
    }
}
