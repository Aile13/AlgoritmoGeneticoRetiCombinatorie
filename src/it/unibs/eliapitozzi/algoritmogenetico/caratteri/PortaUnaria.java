package it.unibs.eliapitozzi.algoritmogenetico.caratteri;

import it.unibs.eliapitozzi.algoritmogenetico.RigaTabella;

/**
 * @author Elia
 */
public abstract class PortaUnaria implements IngressoPortaBinaria {
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
