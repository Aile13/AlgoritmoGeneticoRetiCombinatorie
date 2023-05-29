package it.unibs.eliapitozzi.algogen;

/**
 * @author Elia
 */
public record CoppiaDiRetiCombinatorie(ReteCombinatoria reteCombinatoria1, ReteCombinatoria reteCombinatoria2) {

    public CoppiaDiRetiCombinatorie crossover() {
        return reteCombinatoria1.crossover(reteCombinatoria1);
    }

    public void mutation() {
        reteCombinatoria1.mutation();
        reteCombinatoria2.mutation();
    }
}
