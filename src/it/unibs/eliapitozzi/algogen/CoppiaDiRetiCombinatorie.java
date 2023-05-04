package it.unibs.eliapitozzi.algogen;

/**
 * @author Elia
 */
public class CoppiaDiRetiCombinatorie {
    private final ReteCombinatoria reteCombinatoria1;
    private final ReteCombinatoria reteCombinatoria2;

    public CoppiaDiRetiCombinatorie(ReteCombinatoria reteCombinatoria1, ReteCombinatoria reteCombinatoria2) {
        this.reteCombinatoria1 = reteCombinatoria1;
        this.reteCombinatoria2 = reteCombinatoria2;
    }

    public CoppiaDiRetiCombinatorie crossover() {
        return reteCombinatoria1.crossover(reteCombinatoria1);
    }

    public CoppiaDiRetiCombinatorie mutation() {
        reteCombinatoria1.mutation();
        reteCombinatoria2.mutation();
        return this;
    }
}
