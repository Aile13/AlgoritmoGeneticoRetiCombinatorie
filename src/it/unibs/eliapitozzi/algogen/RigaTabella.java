package it.unibs.eliapitozzi.algogen;

import java.util.List;

/**
 * @author Elia
 */
public class RigaTabella {
    private final List<Boolean> combinazioneDiInput;
    private final Boolean output;

    public RigaTabella(List<Boolean> combinazioneDiInput, Boolean output) {
        this.combinazioneDiInput = combinazioneDiInput;
        this.output = output;
    }

    public boolean getOutputAtteso() {
        return output;
    }

    public boolean getValoreIngressoByNumero(int numeroIngresso) {
        return combinazioneDiInput.get((combinazioneDiInput.size() - 1) - numeroIngresso);
    }

    public int getNumeroIngressi() {

        return this.combinazioneDiInput.size();
    }
}
