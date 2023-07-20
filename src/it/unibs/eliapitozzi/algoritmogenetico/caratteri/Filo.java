package it.unibs.eliapitozzi.algoritmogenetico.caratteri;

import it.unibs.eliapitozzi.algoritmogenetico.RigaTabella;

/**
 * @author Elia
 */
public class Filo extends PortaUnaria {

    @Override
    public boolean computaOutput(RigaTabella rigaTabella) {
        return super.computaOutputDiIngresso(rigaTabella);
    }

    @Override
    public Carattere mutation() {
        return new Not();
    }

    @Override
    public String getEncoding() {
        return "F";
    }

    @Override
    public String getFunction() {
        return "( " + super.getIngresso().getFunction() + " )";
    }
}
