package it.unibs.eliapitozzi.algogen.caratteri;

import it.unibs.eliapitozzi.algogen.RigaTabella;

/**
 * @author Elia
 */
public class Ingresso implements IngressoPortaUnaria {
    private final int numeroIngresso;

    public Ingresso(int numeroIngresso) {
        this.numeroIngresso = numeroIngresso;
    }

    @Override
    public boolean computaOutput(RigaTabella rigaTabella) {
        return rigaTabella.getValoreIngressoByNumero(numeroIngresso);
    }

    @Override
    public boolean isStessoTipo(Carattere carattere) {
        return carattere instanceof Ingresso;
    }
}
