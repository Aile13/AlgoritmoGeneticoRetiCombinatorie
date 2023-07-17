package it.unibs.eliapitozzi.algoritmogenetico.caratteri;

import it.unibs.eliapitozzi.algoritmogenetico.RigaTabella;
import it.unibs.eliapitozzi.mylib.NumeriCasuali;

/**
 * @author Elia
 */
public class Ingresso implements IngressoPortaUnaria {
    private final int numeroIngresso;
    private final int numeroIngressi;

    public Ingresso(int numeroIngresso, int numeroIngressi) {
        this.numeroIngresso = numeroIngresso;
        this.numeroIngressi = numeroIngressi;
    }

    @Override
    public boolean computaOutput(RigaTabella rigaTabella) {
        return rigaTabella.getValoreIngressoByNumero(numeroIngresso);
    }

    @Override
    public boolean isStessoTipo(Carattere carattere) {
        return carattere instanceof Ingresso;
    }

    @Override
    public Carattere mutation() {
        int numeroIngresso = NumeriCasuali.estraiIntero(0, numeroIngressi - 1);
        return new Ingresso(numeroIngresso, numeroIngressi);
    }

    @Override
    public String getEncoding() {
        return String.valueOf(numeroIngresso);
    }
}
