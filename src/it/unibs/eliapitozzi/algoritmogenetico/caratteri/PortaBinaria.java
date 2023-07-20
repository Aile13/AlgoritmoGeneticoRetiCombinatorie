package it.unibs.eliapitozzi.algoritmogenetico.caratteri;

import it.unibs.eliapitozzi.algoritmogenetico.RigaTabella;

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

    protected PortaUnaria getPrimoIngresso() {
        return primoIngresso;
    }

    protected PortaUnaria getSecondoIngresso() {
        return secondoIngresso;
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
