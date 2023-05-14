package it.unibs.eliapitozzi.algogen.caratteri;

import it.unibs.eliapitozzi.algogen.RigaTabella;

/**
 * @author Elia
 */
public abstract class PortaUnaria implements Carattere {
    private IngressoPortaUnaria ingresso;
    public void aggiungiIngresso(IngressoPortaUnaria ingresso) {
        this.ingresso = ingresso;
    }

    protected boolean computaOutputDiIngresso(RigaTabella rigaTabella) {
        return ingresso.computaOutput(rigaTabella);
    }

    @Override
    public boolean isStessoTipo(Carattere carattere) {
        return carattere instanceof PortaUnaria;
    }
}
