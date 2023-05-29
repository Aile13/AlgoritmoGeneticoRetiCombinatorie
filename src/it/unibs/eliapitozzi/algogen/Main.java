package it.unibs.eliapitozzi.algogen;

import java.util.List;

/**
 * @author Elia
 */
public class Main {
    public static void main(String[] args) {

        TabellaDiVerita tabellaDiVerita = new TabellaDiVerita(
                List.of(new RigaTabella(List.of(false, false, false), false),
                        new RigaTabella(List.of(false, false, true), true),
                        new RigaTabella(List.of(false, true, false), true),
                        new RigaTabella(List.of(false, true, true), false),
                        new RigaTabella(List.of(true, false, false), true),
                        new RigaTabella(List.of(true, false, true), false),
                        new RigaTabella(List.of(true, true, false), false),
                        new RigaTabella(List.of(true, true, true), true)
                        )
        );

        /*TabellaDiVerita tabellaDiVerita = new TabellaDiVerita(
                List.of(new RigaTabella(List.of(false, false), false),
                        new RigaTabella(List.of(false, true), true),
                        new RigaTabella(List.of(true, false), true),
                        new RigaTabella(List.of(true, true), false)
                        )
        );*/
        AlgoritmoGenetico.run(tabellaDiVerita);
    }
}