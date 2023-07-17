package it.unibs.eliapitozzi;

import com.bpodgursky.jbool_expressions.rules.RuleSet;
import it.unibs.eliapitozzi.algoritmogenetico.RigaTabella;
import it.unibs.eliapitozzi.algoritmogenetico.TabellaDiVerita;
import it.unibs.eliapitozzi.espresso.boolFunction.Cover;
import it.unibs.eliapitozzi.espresso.boolFunction.InputState;
import it.unibs.eliapitozzi.espresso.boolFunction.OutputState;
import it.unibs.eliapitozzi.espresso.boolFunction.cube.Cube;
import it.unibs.eliapitozzi.espresso.boolFunction.cube.CubeArray;
import it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.SingleOutputEspressoMinimizer;
import it.unibs.eliapitozzi.quinemccluskey.QuineMcCluskey;

import java.util.List;

import static it.unibs.eliapitozzi.espresso.boolFunction.InputState.*;
import static it.unibs.eliapitozzi.espresso.boolFunction.OutputState.OUTPUT;

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
        //AlgoritmoGenetico.run(tabellaDiVerita);

       /* QuineMcCluskey quineMcCluskey = new QuineMcCluskey(new String[]{"x2", "x1", "x0"}, new int[]{1, 2, 4, 7});
        String function = quineMcCluskey.getFunction();
        System.out.println(function);*/

        Cube cube1 = new Cube(new InputState[]{ ZERO, ZERO, ONE}, new OutputState[]{OUTPUT});
        Cube cube2 = new Cube(new InputState[]{ ZERO, ONE, ZERO}, new OutputState[]{OUTPUT});
        Cube cube3 = new Cube(new InputState[]{ ONE, ZERO, ZERO}, new OutputState[]{OUTPUT});
        Cube cube4 = new Cube(new InputState[]{ONE, ONE, ONE}, new OutputState[]{OUTPUT});

        Cover cover = new Cover(cube1, cube2, cube3, cube4);
        Cover minimized = SingleOutputEspressoMinimizer.getInstance().minimize(cover);
        System.out.println(minimized);


    }
}