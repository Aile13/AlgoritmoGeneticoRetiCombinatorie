package it.unibs.eliapitozzi.algogen.caratteri;

import it.unibs.eliapitozzi.algogen.RigaTabella;
import it.unibs.eliapitozzi.mylib.NumeriCasuali;

import static it.unibs.eliapitozzi.algogen.DNA.NUMERO_INGRESSI;

/**
 * @author Elia
 */
public class Ingresso implements IngressoPortaUnaria {
    private final int numeroIngresso;

    public Ingresso(int numeroIngresso) {
        this.numeroIngresso = numeroIngresso;
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
        int numeroIngresso = NumeriCasuali.estraiIntero(0, NUMERO_INGRESSI - 1);
        return new Ingresso(numeroIngresso);
    }
}
