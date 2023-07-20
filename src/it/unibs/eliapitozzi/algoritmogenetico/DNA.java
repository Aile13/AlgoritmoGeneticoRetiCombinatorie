package it.unibs.eliapitozzi.algoritmogenetico;

import it.unibs.eliapitozzi.algoritmogenetico.caratteri.*;
import it.unibs.eliapitozzi.mylib.NumeriCasuali;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Elia
 */
public class DNA {
    private static final double MUTATION_RATE = 0.01;
    private final List<Carattere> geni = new LinkedList<>();
    private int numeroIngressi;

    public DNA() {
    }

    public DNA(int numeroDiRicorsioni, int numeroIngressi) {
        this.numeroIngressi = numeroIngressi;
        inizializzaDNA(numeroDiRicorsioni);
    }

    public List<Carattere> getGeni() {
        return geni;
    }

    private void inizializzaDNA(int numeroDiRicorsioni) {
        if (numeroDiRicorsioni == 0) {
            portaUnariaTerminale();
        } else {
            portaUnariaRicorsiva(numeroDiRicorsioni);
        }
    }

    private void portaUnariaRicorsiva(int numeroDiRicorsioni) {
        nomePortaUnaria();
        portaBinaria(numeroDiRicorsioni);
    }

    private void portaBinaria(int numeroDiRicorsioni) {
        if (numeroDiRicorsioni == 1) {
            portaBinariaTerminale();
        } else if (numeroDiRicorsioni == 2) {
            portaBinariaSemiRicorsiva(numeroDiRicorsioni);
        } else if (numeroDiRicorsioni >= 3) {
            if (Math.random() < 0.5) {
                portaBinariaSemiRicorsiva(numeroDiRicorsioni);
            } else {
                portaBinariaRicorsiva(numeroDiRicorsioni);
            }
        }
    }

    private void portaBinariaRicorsiva(int numeroDiRicorsioni) {
        nomePortaBinaria();
        numeroDiRicorsioni--;
        if (numeroDiRicorsioni == 2) {
            variante1();
        } else if (numeroDiRicorsioni >= 6) {
            switch (NumeriCasuali.estraiIntero(2, 5)) {
                case 2 -> variante2(numeroDiRicorsioni);
                case 3 -> variante3(numeroDiRicorsioni);
                case 4 -> variante4(numeroDiRicorsioni);
                case 5 -> variante5(numeroDiRicorsioni);
            }
        } else if (numeroDiRicorsioni >= 5) {
            switch (NumeriCasuali.estraiIntero(2, 4)) {
                case 2 -> variante2(numeroDiRicorsioni);
                case 3 -> variante3(numeroDiRicorsioni);
                case 4 -> variante4(numeroDiRicorsioni);
            }
        } else if (numeroDiRicorsioni >= 4) {
            switch (NumeriCasuali.estraiIntero(2, 3)) {
                case 2 -> variante2(numeroDiRicorsioni);
                case 3 -> variante3(numeroDiRicorsioni);
            }
        } else if (numeroDiRicorsioni >= 3) {
            variante2(numeroDiRicorsioni);
        }
    }

    private void variante5(int numeroDiRicorsioni) {
        nomePortaUnaria();
        int sottoNumDiRicorsioni = NumeriCasuali.estraiIntero(3, numeroDiRicorsioni - 3);
        portaBinariaRicorsiva(sottoNumDiRicorsioni);
        nomePortaUnaria();
        int sottoNumDiRicorsioniRestanti = numeroDiRicorsioni - sottoNumDiRicorsioni;
        portaBinariaRicorsiva(sottoNumDiRicorsioniRestanti);
    }

    private void variante4(int numeroDiRicorsioni) {
        nomePortaUnaria();
        int sottoNumDiRicorsioni = NumeriCasuali.estraiIntero(3, numeroDiRicorsioni - 2);
        portaBinariaRicorsiva(sottoNumDiRicorsioni);
        nomePortaUnaria();
        int sottoNumDiRicorsioniRestanti = numeroDiRicorsioni - sottoNumDiRicorsioni;
        portaBinariaSemiRicorsiva(sottoNumDiRicorsioniRestanti);
    }

    private void variante3(int numeroDiRicorsioni) {
        nomePortaUnaria();
        int sottoNumDiRicorsioni = NumeriCasuali.estraiIntero(2, numeroDiRicorsioni - 2);
        portaBinariaSemiRicorsiva(sottoNumDiRicorsioni);
        nomePortaUnaria();
        int sottoNumDiRicorsioniRestanti = numeroDiRicorsioni - sottoNumDiRicorsioni;
        portaBinariaSemiRicorsiva(sottoNumDiRicorsioniRestanti);
    }

    private void variante2(int numeroDiRicorsioni) {
        nomePortaUnaria();
        portaBinariaSemiRicorsiva(numeroDiRicorsioni);
        portaUnariaTerminale();
    }

    private void variante1() {
        nomePortaUnaria();
        portaBinariaTerminale();
        nomePortaUnaria();
        portaBinariaTerminale();
    }

    private void portaBinariaSemiRicorsiva(int numeroDiRicorsioni) {
        nomePortaBinaria();
        numeroDiRicorsioni--;
        portaUnariaRicorsiva(numeroDiRicorsioni);
        portaUnariaTerminale();
    }

    private void nomePortaBinaria() {
        if (Math.random() < 0.50) {
            geni.add(new And());
        } else {
            geni.add(new Or());
        }
    }

    private void portaBinariaTerminale() {
        nomePortaBinaria();
        portaUnariaTerminale();
        portaUnariaTerminale();
    }

    private void portaUnariaTerminale() {
        nomePortaUnaria();
        ingresso();
    }

    private void nomePortaUnaria() {
        if (Math.random() < 0.50) {
            geni.add(new Filo());
        } else {
            geni.add(new Not());
        }
    }

    private void ingresso() {
        int numeroIngresso = NumeriCasuali.estraiIntero(0, numeroIngressi - 1);
        geni.add(new Ingresso(numeroIngresso, numeroIngressi));
    }

    public void mutation() {
        for (int i = 0; i < this.getGeni().size(); i++) {
            if (Math.random() < MUTATION_RATE) {
                this.getGeni().set(i, this.getGeni().get(i).mutation());
            }
        }
    }

    public CoppiaDiDNA crossover(DNA dnaPartner) {
        int numeroCaratteriCompatibili = 0;
        int dimensioneMinimaDNA = Math.min(this.getGeni().size(), dnaPartner.getGeni().size());

        for (int i = 0; i < dimensioneMinimaDNA; i++) {
            if (this.getGeni().get(i).isStessoTipo(dnaPartner.getGeni().get(i))) {
                numeroCaratteriCompatibili++;
            } else break;
        }

        DNA primoFiglio = new DNA();
        primoFiglio.getGeni().addAll(this.getGeni().subList(0, numeroCaratteriCompatibili));
        primoFiglio.getGeni().addAll(dnaPartner.getGeni().subList(numeroCaratteriCompatibili, dnaPartner.getGeni().size()));

        DNA secondoFiglio = new DNA();
        secondoFiglio.getGeni().addAll(dnaPartner.getGeni().subList(0, numeroCaratteriCompatibili));
        secondoFiglio.getGeni().addAll(this.getGeni().subList(numeroCaratteriCompatibili, this.getGeni().size()));

        return new CoppiaDiDNA(primoFiglio, secondoFiglio);
    }

    public String getString() {
        StringBuilder builder = new StringBuilder();
        for (Carattere carattere : this.getGeni()) {
            builder.append(carattere.getEncoding());
        }
        return builder.toString();
    }

}
