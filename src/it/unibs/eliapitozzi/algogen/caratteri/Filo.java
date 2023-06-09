package it.unibs.eliapitozzi.algogen.caratteri;

import it.unibs.eliapitozzi.algogen.RigaTabella;

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
}
