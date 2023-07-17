package it.unibs.eliapitozzi.algoritmogenetico.caratteri;

import it.unibs.eliapitozzi.algoritmogenetico.RigaTabella;

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
