package it.unibs.eliapitozzi.algogen;

import java.util.List;

/**
 * @author Elia
 */
public class TabellaDiVerita {
    private List<RigaTabella>  righeTabella;

    public TabellaDiVerita(List<RigaTabella> righeTabella) {
        this.righeTabella = righeTabella;
    }

    public List<RigaTabella> getRigheTabella() {
        return righeTabella;
    }

    public int getTotaleRighe() {
        return righeTabella.size();
    }
}
