package it.unibs.eliapitozzi.algoritmogenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Elia
 */
public class Popolazione {
    private static final int DIM_POPOLAZIONE = 500;
    private final List<ReteCombinatoria> listaDiReti = new ArrayList<>();

    private Popolazione() {
    }

    public static Popolazione creaPopolazioneCasuale(TabellaDiVerita tabellaDiVerita) {
        var popolazione = new Popolazione();
        var massimoNumeroDiRicorsioni = tabellaDiVerita.getTotaleRighe()*(tabellaDiVerita.getNumeroIngressi() - 1) + tabellaDiVerita.getTotaleRighe() - 1;

        for (int i = 0; i < DIM_POPOLAZIONE; i++) {
            popolazione.listaDiReti.add(new ReteCombinatoria(i % massimoNumeroDiRicorsioni, tabellaDiVerita.getNumeroIngressi()));
        }

        return popolazione;
    }

    public static Popolazione creaPopolazioneVuota() {
        return new Popolazione();
    }

    public CoppiaDiRetiCombinatorie selezionaCoppiaDiIndividui(TabellaDiVerita tabellaDiVerita) {
        var matingPool = new ArrayList<ReteCombinatoria>();

        for (ReteCombinatoria reteCombinatoria : listaDiReti) {
            var n = Math.round(reteCombinatoria.rawFitness(tabellaDiVerita) * 100);

            for (int i = 0; i < n; i++) {
                matingPool.add(reteCombinatoria);
            }
        }

        // se non è stato riempito si riempie con tutti e si sceglie a caso
        if (matingPool.isEmpty())
            matingPool.addAll(listaDiReti);

        Collections.shuffle(matingPool);

        // prendo due reti distinte
        for (int i = 1; i < matingPool.size(); i++) {
            if (!matingPool.get(0).equals(matingPool.get(i)))
                return new CoppiaDiRetiCombinatorie(matingPool.get(0), matingPool.get(i));
        }

        // se non ne ho trovate distinte ne prendo due uguali
        return new CoppiaDiRetiCombinatorie(matingPool.get(0), matingPool.get(0));
    }

    public boolean haStessaDimOPariSuccessivoDi(Popolazione generazioneDiConfronto) {
        if (listaDiReti.size() == generazioneDiConfronto.listaDiReti.size())
            return true;
        else if (listaDiReti.size() == generazioneDiConfronto.listaDiReti.size() + 1)
            return true;
        else return false;
    }

    public void inserisciCoppiaDiIndividui(CoppiaDiRetiCombinatorie coppiaDiIndividuiDaInserire) {
        this.listaDiReti.add(coppiaDiIndividuiDaInserire.reteCombinatoria1());
        this.listaDiReti.add(coppiaDiIndividuiDaInserire.reteCombinatoria2());
    }

    public void mostraInfo(int i, TabellaDiVerita tabellaDiVerita) {
        var migliorRete = this.listaDiReti.get(0);
        var totalRawFitness = 0.;

        for (ReteCombinatoria reteCombinatoria : listaDiReti) {
            var punteggioRete = reteCombinatoria.rawFitness(tabellaDiVerita);

            if (punteggioRete > migliorRete.rawFitness(tabellaDiVerita)) {
                migliorRete = reteCombinatoria;
            }

            totalRawFitness += punteggioRete;
        }

        var average = totalRawFitness / listaDiReti.size();

        StringBuilder builder = new StringBuilder(String.format("num gen: %3d", i))
                .append(String.format("  punteggio medio: %1.4f", average))
                .append(String.format("  miglior punteggio: %1.4f", migliorRete.rawFitness(tabellaDiVerita)))
                .append("  tot ingressi: " + migliorRete.getTotaleIngressiGiusti() + "/" + tabellaDiVerita.getTotaleRighe())
                .append("  stringa: ")
                .append(migliorRete.getDNAString());

        System.out.println(builder);
    }

    public ReteCombinatoria getMiglioreIndividuo(TabellaDiVerita tabellaDiVerita) {
        var migliorRete = listaDiReti.get(0);
        for (ReteCombinatoria reteCombinatoria : listaDiReti) {
            if (reteCombinatoria.rawFitness(tabellaDiVerita) > migliorRete.rawFitness(tabellaDiVerita))
                migliorRete = reteCombinatoria;
        }
        return migliorRete;
    }
}
