package it.unibs.eliapitozzi.algogen;

import java.util.List;

/**
 * @author Elia
 */
public class RigaTabella {
    private List<Boolean> combinazioneDiInput;
    private Boolean output;

    public RigaTabella(List<Boolean> combinazioneDiInput, Boolean output) {
        this.combinazioneDiInput = combinazioneDiInput;
        this.output = output;
    }

    public boolean getOutputAtteso() {
        return output;
    }

    public boolean getValoreIngressoByNumero(int numeroIngresso) {
        return combinazioneDiInput.get(numeroIngresso);
    }
}
