package it.unibs.eliapitozzi.algogen.caratteri;

import it.unibs.eliapitozzi.algogen.RigaTabella;

/**
 * @author Elia
 */
public class Not extends PortaUnaria {
    @Override
    public boolean computaOutput(RigaTabella rigaTabella) {
        return !(super.computaOutputDiIngresso(rigaTabella));
    }

    @Override
    public Carattere mutation() {
        return new Filo();
    }

    @Override
    public String getEncoding() {
        return "N";
    }
}
