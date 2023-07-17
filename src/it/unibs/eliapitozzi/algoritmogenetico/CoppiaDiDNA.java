package it.unibs.eliapitozzi.algoritmogenetico;

/**
 * @author Elia
 */
public class CoppiaDiDNA {
    private final DNA dna1;
    private final DNA dna2;

    public DNA getDna1() {
        return dna1;
    }

    public DNA getDna2() {
        return dna2;
    }

    public CoppiaDiDNA(DNA dna1, DNA dna2) {
        this.dna1 = dna1;
        this.dna2 = dna2;
    }
}
