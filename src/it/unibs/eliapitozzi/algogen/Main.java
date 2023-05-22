package it.unibs.eliapitozzi.algogen;

import java.util.List;

/**
 * @author Elia
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TabellaDiVerita tabellaDiVerita = new TabellaDiVerita(
                List.of(new RigaTabella(List.of(false, false, false, false, false, false, false, true), true),
                        new RigaTabella(List.of(false, false, false, true, true, true, true, true), true),
                        new RigaTabella(List.of(false, true, true, true, true, true, true, true), false),
                        new RigaTabella(List.of(false, false, false, false, false, false, false, true), true),
                        new RigaTabella(List.of(false, false, false, false, false, false, false, true), true),
                        new RigaTabella(List.of(false, false, false, false, false, false, false, true), true),
                        new RigaTabella(List.of(false, false, false, false, false, false, false, true), true),
                        new RigaTabella(List.of(true, true, true, true, true, true, true, true), true)
                        )
        );
        AlgoritmoGenetico.run(tabellaDiVerita);
    }
}