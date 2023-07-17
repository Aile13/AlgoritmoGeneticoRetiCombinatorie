package it.unibs.eliapitozzi.algoritmogenetico;

import java.util.List;

/**
 * @author Elia
 */
public record TabellaDiVerita(List<RigaTabella> righeTabella) {

    public int getTotaleRighe() {
        return righeTabella.size();
    }

    public int getNumeroIngressi() {
        return this.righeTabella.get(0).getNumeroIngressi();
    }
}
