package it.unibs.eliapitozzi.algogen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Elia
 */
public class Popolazione {
    // TODO: 22/mag/2023 capire perché popolazione deve essere in numero pari
    private static final int DIM_POPOLAZIONE = 26;
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

    public CoppiaDiRetiCombinatorie
    selezionaCoppiaDiIndividui(TabellaDiVerita tabellaDiVerita) {
        var matingPool = new ArrayList<ReteCombinatoria>();

        for (ReteCombinatoria reteCombinatoria : listaDiReti) {
            var n = Math.round(reteCombinatoria.rawFitness(tabellaDiVerita) * 100);

            for (int i = 0; i < n; i++) {
                matingPool.add(reteCombinatoria);
            }
            matingPool.add(reteCombinatoria); // almeno una volta comunque viene inserita.
        }

        Collections.shuffle(matingPool);
        return new CoppiaDiRetiCombinatorie(matingPool.get(0), matingPool.get(1));
    }

    public boolean haStessaDimOPariSuccessivoDi(Popolazione generazioneDiConfronto) {
        if (listaDiReti.size() == generazioneDiConfronto.listaDiReti.size())
            return true;
        else if (listaDiReti.size() == generazioneDiConfronto.listaDiReti.size() + 1)
            return true;
        else return false;
    }

    public void inserisciCoppiaDiIndividui(CoppiaDiRetiCombinatorie coppiaDiIndividuiDaInserire) {
        this.listaDiReti.add(coppiaDiIndividuiDaInserire.getReteCombinatoria1());
        this.listaDiReti.add(coppiaDiIndividuiDaInserire.getReteCombinatoria2());
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
        var totalFitness = 0.;
        for (ReteCombinatoria reteCombinatoria : listaDiReti) {
            totalFitness += reteCombinatoria.rawFitness(tabellaDiVerita) / totalRawFitness;
        }
        var average = totalFitness / listaDiReti.size();

        StringBuilder builder = new StringBuilder(String.format("num generazione: %3d", i));
        builder.append(String.format("   punteggio medio: %1.4f", average));
        builder.append(String.format("   miglior punteggio: %1.4f", migliorRete.rawFitness(tabellaDiVerita)));
        builder.append("   stringa: " + migliorRete.getDNAString());

        System.out.println(builder);
    }
}
