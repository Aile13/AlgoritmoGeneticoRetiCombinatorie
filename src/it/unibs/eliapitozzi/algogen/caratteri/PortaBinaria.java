package it.unibs.eliapitozzi.algogen.caratteri;

import it.unibs.eliapitozzi.algogen.RigaTabella;

/**
 * @author Elia
 */
public abstract class PortaBinaria implements IngressoPortaUnaria {
    private PortaUnaria primoIngresso;
    private PortaUnaria secondoIngresso;

    public void aggiungiPrimoIngresso(PortaUnaria primaPortaUnaria) {
        primoIngresso = primaPortaUnaria;
    }

    public void aggiungiSecondoIngresso(PortaUnaria secondaPortaUnaria) {
        secondoIngresso = secondaPortaUnaria;
    }

    protected boolean computaOutputDiPrimoIngresso(RigaTabella rigaTabella) {
        return primoIngresso.computaOutput(rigaTabella);
    }

    protected boolean computaOutputDiSecondoIngresso(RigaTabella rigaTabella) {
        return secondoIngresso.computaOutput(rigaTabella);
    }

    @Override
    public boolean isStessoTipo(Carattere carattere) {
        return carattere instanceof PortaBinaria;
    }
}
