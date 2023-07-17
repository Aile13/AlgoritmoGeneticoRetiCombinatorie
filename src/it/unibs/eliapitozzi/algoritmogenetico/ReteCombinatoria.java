package it.unibs.eliapitozzi.algoritmogenetico;

import it.unibs.eliapitozzi.algoritmogenetico.caratteri.Carattere;
import it.unibs.eliapitozzi.algoritmogenetico.caratteri.Ingresso;
import it.unibs.eliapitozzi.algoritmogenetico.caratteri.PortaBinaria;
import it.unibs.eliapitozzi.algoritmogenetico.caratteri.PortaUnaria;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Elia
 */
public class ReteCombinatoria {
    private static final int BASE = 3;
    private static final double CROSSOVER_RATE = 0.75;
    private DNA dna;
    private PortaUnaria program;
    private int righeCorrette;

    public ReteCombinatoria(int numeroDiRicorsioni, int numeroIngressi) {
        this.dna = new DNA(numeroDiRicorsioni, numeroIngressi);
        assemblaRete();
    }

    public double rawFitness(TabellaDiVerita tabellaDiVerita) {
        righeCorrette = 0;

        for (RigaTabella rigaTabella : tabellaDiVerita.righeTabella()) {
            var output = program.computaOutput(rigaTabella);

            if (output == rigaTabella.getOutputAtteso())
                righeCorrette++;
        }

        return (Math.pow(BASE, (double) righeCorrette / tabellaDiVerita.getTotaleRighe()) - 1 ) / (BASE - 1);
    }

    private void assemblaRete() {
        var caratteriDaConsumare = new LinkedList<>(this.dna.getGeni());
        program = portaUnaria(caratteriDaConsumare);
    }

    private PortaUnaria portaUnaria(List<Carattere> caratteri) {
        PortaUnaria portaUnaria = (PortaUnaria) next(caratteri);
        if (view(caratteri) instanceof PortaBinaria) {
            portaUnaria.aggiungiIngresso(portaBinaria(caratteri));
        } else {
            portaUnaria.aggiungiIngresso(ingresso(caratteri));
        }
        return portaUnaria;
    }

    private Ingresso ingresso(List<Carattere> caratteri) {
        return (Ingresso) next(caratteri);
    }

    private PortaBinaria portaBinaria(List<Carattere> caratteri) {
        PortaBinaria portaBinaria = (PortaBinaria) next(caratteri);
        portaBinaria.aggiungiPrimoIngresso(portaUnaria(caratteri));
        portaBinaria.aggiungiSecondoIngresso(portaUnaria(caratteri));
        return portaBinaria;
    }

    private Carattere next(List<Carattere> caratteri) {
        return caratteri.remove(0);
    }

    private Carattere view(List<Carattere> caratteri) {
        return caratteri.get(0);
    }

    public CoppiaDiRetiCombinatorie crossover(ReteCombinatoria reteCombinatoriaPartner) {
        if (Math.random() < CROSSOVER_RATE) {
            CoppiaDiDNA crossover = this.dna.crossover(reteCombinatoriaPartner.dna);
            this.dna = crossover.getDna1();
            reteCombinatoriaPartner.dna = crossover.getDna2();
        }

        return new CoppiaDiRetiCombinatorie(this, reteCombinatoriaPartner);
    }

    public void mutation() {
        this.dna.mutation();
    }

    public String getDNAString() {
        return this.dna.getString();
    }

    public int getTotaleIngressiGiusti() {
        return righeCorrette;
    }
}
