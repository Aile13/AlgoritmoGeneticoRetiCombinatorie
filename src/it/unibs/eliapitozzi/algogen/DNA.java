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
                case 2:
                    variante2();
                case 3:
                    variante3();
                case 4:
                    variante4();
                case 5:
                    variante5();
            }
        } else if (numeroDiRicorsioni >= 5) {
            switch (NumeriCasuali.estraiIntero(2, 4)) {
                case 2:
                    variante2();
                case 3:
                    variante3();
                case 4:
                    variante4();
            }
        } else if (numeroDiRicorsioni >= 4) {
            switch (NumeriCasuali.estraiIntero(2, 3)) {
                case 2:
                    variante2();
                case 3:
                    variante3();
            }
        } else if (numeroDiRicorsioni >= 3) {
            variante2();
        }
    }

    private void variante5() {
        nomePortaUnaria();
        portaBinariaRicorsiva(2);
        nomePortaUnaria();
        portaBinariaRicorsiva(2);
    }

    private void variante4() {
        nomePortaUnaria();
        portaBinariaRicorsiva(2);
        nomePortaUnaria();
        portaBinariaSemiRicorsiva(1);
    }

    private void variante3() {
        nomePortaUnaria();
        portaBinariaSemiRicorsiva(1);
        nomePortaUnaria();
        portaBinariaSemiRicorsiva(1);
    }

    private void variante2() {
        nomePortaUnaria();
        portaBinariaSemiRicorsiva(1);
        nomePortaUnaria();
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

    // TODO: 04/mag/2023 questa tecnicamente è l'ultima. 
    public void mutation() {
        
    }

    // TODO: 04/mag/2023 questa è l'operazione successiva. 
    public CoppiaDiDNA crossover(DNA dnaPartner) {
        return null;
    }
}
