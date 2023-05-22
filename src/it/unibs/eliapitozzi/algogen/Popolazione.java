package it.unibs.eliapitozzi.algogen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Elia
 */
public class Popolazione {
    private static final int DIM_POPOLAZIONE = 4;
    private static final int MAX_NUM_DI_RICORSIONI = 12;
    private final List<ReteCombinatoria> listaDiReti = new ArrayList<>();

    private Popolazione() {
    }

    public static Popolazione creaPopolazioneCasuale(TabellaDiVerita tabellaDiVerita) {
        var popolazione = new Popolazione();

        // TODO: 18/mag/2023 parametrizza con num di ingressi e di righe in tab.

        for (int i = 0; i < DIM_POPOLAZIONE; i++) {
            popolazione.listaDiReti.add(new ReteCombinatoria(i % MAX_NUM_DI_RICORSIONI));
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

    public boolean haStessaDimensioneDi(Popolazione generazioneDiConfronto) {
        return listaDiReti.size() == generazioneDiConfronto.listaDiReti.size();
    }

    public void inserisciCoppiaDiIndividui(CoppiaDiRetiCombinatorie coppiaDiIndividuiDaInserire) {
        this.listaDiReti.add(coppiaDiIndividuiDaInserire.getReteCombinatoria1());
        this.listaDiReti.add(coppiaDiIndividuiDaInserire.getReteCombinatoria2());
    }

    // TODO: 15/mag/2023 questo serve ora, dobbiamo mostrare info circa popolazione corrente,
    // punteggio medio, puntenggio migliore etc...
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
