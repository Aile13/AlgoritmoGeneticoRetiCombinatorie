package it.unibs.eliapitozzi;

import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.rules.RuleSet;
import it.unibs.eliapitozzi.algoritmogenetico.AlgoritmoGenetico;
import it.unibs.eliapitozzi.algoritmogenetico.RigaTabella;
import it.unibs.eliapitozzi.algoritmogenetico.TabellaDiVerita;
import it.unibs.eliapitozzi.espresso.boolFunction.Cover;
import it.unibs.eliapitozzi.espresso.boolFunction.EspressoParseFunction;
import it.unibs.eliapitozzi.espresso.boolFunction.InputState;
import it.unibs.eliapitozzi.espresso.boolFunction.OutputState;
import it.unibs.eliapitozzi.espresso.boolFunction.cube.Cube;
import it.unibs.eliapitozzi.espresso.boolFunction.cube.CubeArray;
import it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.SingleOutputEspressoMinimizer;
import it.unibs.eliapitozzi.quinemccluskey.QuineMcCluskey;
import it.unibs.eliapitozzi.quinemccluskey.QuineMcCluskeyParseFunction;

import java.util.List;

import static it.unibs.eliapitozzi.espresso.boolFunction.InputState.*;
import static it.unibs.eliapitozzi.espresso.boolFunction.OutputState.OUTPUT;

/**
 * @author Elia
 */
public class Main {
    public static void main(String[] args) {

        TabellaDiVerita tabellaDiVerita = new TabellaDiVerita(3);

        String function = AlgoritmoGenetico.run(tabellaDiVerita);
        System.out.println(function);

        Expression<String> sopForm1 = RuleSet.toDNF(ExprParser.parse(function));
        System.out.println(sopForm1);

       /* QuineMcCluskey quineMcCluskey = new QuineMcCluskey(tabellaDiVerita.getVariabili(), tabellaDiVerita.getPosizioni());
        String function2 = QuineMcCluskeyParseFunction.parseFunction(quineMcCluskey.getFunction());
        System.out.println(function2);
        Expression<String> sopForm2 = RuleSet.toDNF(ExprParser.parse(function2));
        System.out.println(sopForm2);*/


        /*Cube cube1 = new Cube(new InputState[]{ ZERO, ZERO, ONE}, new OutputState[]{OUTPUT});
        Cube cube2 = new Cube(new InputState[]{ ZERO, ONE, ZERO}, new OutputState[]{OUTPUT});
        Cube cube3 = new Cube(new InputState[]{ ONE, ZERO, ZERO}, new OutputState[]{OUTPUT});
        Cube cube4 = new Cube(new InputState[]{ONE, ONE, ONE}, new OutputState[]{OUTPUT});

        Cover cover = new Cover(cube1, cube2, cube3, cube4);*/

/*        String function3 = EspressoParseFunction
                .parseFunction(
                        SingleOutputEspressoMinimizer.getInstance().minimize(tabellaDiVerita.getCover()),
                        tabellaDiVerita
                );
        System.out.println(function3);
        Expression<String> sopForm3 = RuleSet.toDNF(ExprParser.parse(function3));
        System.out.println(sopForm3);*/

    }

}