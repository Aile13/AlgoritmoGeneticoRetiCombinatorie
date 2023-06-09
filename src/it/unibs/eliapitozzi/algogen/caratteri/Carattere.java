package it.unibs.eliapitozzi.algogen.caratteri;

import it.unibs.eliapitozzi.algogen.RigaTabella;

/**
 * @author Elia
 */
public interface Carattere {
    boolean computaOutput(RigaTabella rigaTabella);

    boolean isStessoTipo(Carattere carattere);

    Carattere mutation();

    String getEncoding();
}
