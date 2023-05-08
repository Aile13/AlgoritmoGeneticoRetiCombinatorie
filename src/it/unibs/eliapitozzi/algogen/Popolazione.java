package it.unibs.eliapitozzi.algogen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Elia
 */
public class Popolazione {
    private static final int DIM_POPOLAZIONE = 50;
    private static final int MAX_NUM_ITERAZIONI = 30;
    private final List<ReteCombinatoria> listaDiReti = new ArrayList<>();

    private Popolazione() {
    }

    public static Popolazione creaPopolazioneCasuale() {
        var popolazione = new Popolazione();
        for (int i = 0; i < DIM_POPOLAZIONE; i++) {
            popolazione.listaDiReti.add(new ReteCombinatoria(i % MAX_NUM_ITERAZIONI));
        }
        return popolazione;
    }

    public static Popolazione creaPopolazioneVuota() {
        return new Popolazione();
    }

    public CoppiaDiRetiCombinatorie selezionaCoppiaDiIndividui(TabellaDiVerita tabellaDiVerita) {
        var matingPool = new ArrayList<ReteCombinatoria>();
        var totalRawFitness = 0.;
        for (ReteCombinatoria reteCombinatoria : listaDiReti) {
            totalRawFitness += reteCombinatoria.rawFitness(tabellaDiVerita);
        }

        for (ReteCombinatoria reteCombinatoria : listaDiReti) {
            var n = Math.round(reteCombinatoria.rawFitness(tabellaDiVerita) / totalRawFitness * 100);

            for (int i = 0; i < n; i++) {
                matingPool.add(reteCombinatoria);
            }
        }

        Collections.shuffle(matingPool);
        return new CoppiaDiRetiCombinatorie(matingPool.get(0), matingPool.get(1));
    }

    public boolean haStessaDimensioneDi(Popolazione generazioneDiConfronto) {
        return listaDiReti.size() == generazioneDiConfronto.listaDiReti.size();
    }

    public void inserisciCoppiaDiIndividui(CoppiaDiRetiCombinatorie coppiaDiIndividuiDaInserire) {

    }
}
