package it.unibs.eliapitozzi.algoritmogenetico.caratteri;

import it.unibs.eliapitozzi.algoritmogenetico.RigaTabella;

/**
 * @author Elia
 */
public interface Carattere {
    boolean computaOutput(RigaTabella rigaTabella);

    boolean isStessoTipo(Carattere carattere);

    Carattere mutation();

    String getEncoding();
}
