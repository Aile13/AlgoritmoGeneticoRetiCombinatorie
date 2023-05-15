package it.unibs.eliapitozzi.algogen;

import it.unibs.eliapitozzi.algogen.caratteri.Carattere;
import it.unibs.eliapitozzi.algogen.caratteri.Ingresso;
import it.unibs.eliapitozzi.algogen.caratteri.PortaBinaria;
import it.unibs.eliapitozzi.algogen.caratteri.PortaUnaria;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Elia
 */
public class ReteCombinatoria {
    private DNA dna;
    private PortaUnaria program;

    public ReteCombinatoria(int numeroDiRicorsioni) {
        this.dna = new DNA(numeroDiRicorsioni);
        assemblaRete();
    }

    // TODO: 04/mag/2023 questa è la prox cosa da fare. a meno di piccole cose è fatta.  questa dovrebbe essere fatta.
    public double rawFitness(TabellaDiVerita tabellaDiVerita) {

        int righeCorrette = 0;
        for (RigaTabella rigaTabella : tabellaDiVerita.getRigheTabella()) {
            var output = program.computaOutput(rigaTabella);

            if (output == rigaTabella.getOutputAtteso())
                righeCorrette++;
        }

        return Math.pow(2., (double) righeCorrette / tabellaDiVerita.getTotaleRighe());
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
        if (Math.random() < 0.75) {
            CoppiaDiDNA crossover = this.dna.crossover(reteCombinatoriaPartner.dna);
            this.dna = crossover.getDna1();
            reteCombinatoriaPartner.dna = crossover.getDna2();
        }

        return new CoppiaDiRetiCombinatorie(this, reteCombinatoriaPartner);
    }

    public void mutation() {
        this.dna.mutation();
    }

}
