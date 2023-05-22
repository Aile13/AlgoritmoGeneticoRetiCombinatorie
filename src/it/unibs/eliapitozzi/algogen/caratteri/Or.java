package it.unibs.eliapitozzi.algogen.caratteri;

import it.unibs.eliapitozzi.algogen.RigaTabella;

/**
 * @author Elia
 */
public class Or extends PortaBinaria {
    @Override
    public boolean computaOutput(RigaTabella rigaTabella) {
        return super.computaOutputDiPrimoIngresso(rigaTabella) || super.computaOutputDiSecondoIngresso(rigaTabella);
    }

    @Override
    public Carattere mutation() {
        return new And();
    }

    @Override
    public String getEncoding() {
        return "O";
    }
}
