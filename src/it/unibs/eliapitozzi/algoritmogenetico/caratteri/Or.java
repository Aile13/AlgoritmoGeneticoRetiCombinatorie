package it.unibs.eliapitozzi.algoritmogenetico.caratteri;

import it.unibs.eliapitozzi.algoritmogenetico.RigaTabella;

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

    @Override
    public String getFunction() {
        return "( " + super.getPrimoIngresso().getFunction() +
                " | "
                + super.getSecondoIngresso().getFunction() + " )";
    }
}
