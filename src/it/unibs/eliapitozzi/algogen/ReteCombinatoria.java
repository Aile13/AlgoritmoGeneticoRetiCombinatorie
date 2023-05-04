package it.unibs.eliapitozzi.algogen;

/**
 * @author Elia
 */
public class ReteCombinatoria {
    private DNA dna;
    public ReteCombinatoria(int numeroDiRicorsioni) {
        this.dna = new DNA(numeroDiRicorsioni);
    }

    // TODO: 04/mag/2023 questa è la prox cosa da fare.
    public double fitness() {


    }

    public CoppiaDiRetiCombinatorie crossover(ReteCombinatoria reteCombinatoriaPartner) {
        CoppiaDiDNA crossover = this.dna.crossover(reteCombinatoriaPartner.dna);

        this.dna = crossover.getDna1();
        reteCombinatoriaPartner.dna = crossover.getDna2();

        return new CoppiaDiRetiCombinatorie(this, reteCombinatoriaPartner);
    }

    public void mutation() {
        this.dna.mutation();
    }
}
