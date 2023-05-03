package it.unibs.eliapitozzi.algogen;

import it.unibs.eliapitozzi.algogen.caratteri.*;
import it.unibs.eliapitozzi.mylib.NumeriCasuali;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elia
 */
public class DNA {
    private static final int NUMERO_INGRESSI = 8;
    private final List<Carattere> geni = new ArrayList<>();


    public DNA(int numeroDiRicorsioni) {

        inizializzaDNA(numeroDiRicorsioni);
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
        } else if (numeroDiRicorsioni == 3) {
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
            nomePortaUnaria();
            portaBinariaTerminale();
            nomePortaUnaria();
            portaBinariaTerminale();
        } else if (numeroDiRicorsioni == 3) {
            nomePortaUnaria();
            portaBinariaSemiRicorsiva(1);
            nomePortaUnaria();
            portaUnariaTerminale();
        } else if (numeroDiRicorsioni == 4) {
            nomePortaUnaria();
            portaBinariaSemiRicorsiva(1);
            nomePortaUnaria();
            portaBinariaSemiRicorsiva(1);
        } else if (numeroDiRicorsioni == 5) {
            nomePortaUnaria();
            portaBinariaRicorsiva(2);
            nomePortaUnaria();
            portaBinariaSemiRicorsiva(1);
        } else if (numeroDiRicorsioni == 6) {
            nomePortaUnaria();
            portaBinariaRicorsiva(2);
            nomePortaUnaria();
            portaBinariaRicorsiva(2);
        } else {
            // TODO: 03/mag/2023 quindi qui ci vanno i casi con 7 o più porte binarie da combinare anche ricorsivamente
            //bisogna selezionare uno dei quattro metodi ricorsivi previsti.
            // bisogna adattare per i casi ricorsivi a valutare in maniera casuale equa
            // un caso che loro riescono a gestire es >= tot. si .
        }
    }

    private void portaBinariaSemiRicorsiva(int numeroDiRicorsioni) {
        nomePortaBinaria();
        numeroDiRicorsioni--;
        portaUnariaRicorsiva(numeroDiRicorsioni);
        portaUnariaTerminale();
    }

    private void nomePortaBinaria() {
        if (Math.random() < 0.5) {
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

    private void aggiungiDueIngressi() {
        ingresso();
        ingresso();
    }

    private void nomePortaUnaria() {
        if (Math.random() < 0.5) {
            geni.add(new Filo());
        } else {
            geni.add(new Not());
        }
    }

    private void ingresso() {
        int numeroIngresso = NumeriCasuali.estraiIntero(0, NUMERO_INGRESSI - 1);
        geni.add(new Ingresso(numeroIngresso));
    }

}
