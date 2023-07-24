package it.unibs.eliapitozzi;

import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.rules.RuleSet;
import it.unibs.eliapitozzi.algoritmogenetico.TabellaDiVerita;
import it.unibs.eliapitozzi.espresso.boolFunction.EspressoParseFunction;
import it.unibs.eliapitozzi.espresso.minimizers.Simplify;
import it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.SingleOutputEspressoMinimizer;
import it.unibs.eliapitozzi.quinemccluskey.QuineMcCluskey;
import it.unibs.eliapitozzi.quinemccluskey.QuineMcCluskeyParseFunction;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.io.parsers.ParserException;
import org.logicng.io.parsers.PropositionalParser;
import org.logicng.transformations.simplification.AdvancedSimplifier;

/**
 * @author Elia
 */
public class Main {
    public static void main(String[] args) throws ParserException {

        for (int i = 5; i < 6; i++) {
            System.out.println("\tFunzione con " + i + " variabili di input");

            TabellaDiVerita tabellaDiVerita = new TabellaDiVerita(i);
   /*     String function = AlgoritmoGenetico.run(tabellaDiVerita);
        System.out.println("Funzione dell'algoritmo genetico: " + function);
        esaminaFunzione(function, tabellaDiVerita);*/

/*
            System.out.println("Algoritmo Quine-McCluskey");
            QuineMcCluskey quineMcCluskey = new QuineMcCluskey(tabellaDiVerita.getVariabili(), tabellaDiVerita.getPosizioni());
            String function2 = QuineMcCluskeyParseFunction.parseFunction(quineMcCluskey.getFunction());
            //System.out.println("Funzione di Quine-McCluskey: " + function2);
            esaminaFunzione(function2, tabellaDiVerita);*/

            System.out.println("Algoritmo Simplify");
            String function3 = EspressoParseFunction
                    .parseFunction(
                            //SingleOutputEspressoMinimizer.getInstance().minimize(tabellaDiVerita.getCover()),
                            Simplify.getInstance().minimize(tabellaDiVerita.getCover()),
                            tabellaDiVerita
                    );
            //System.out.println("Funzione di Simplify: " + function3);
            AdvancedSimplifier advancedSimplifier = new AdvancedSimplifier();
            FormulaFactory f = new FormulaFactory();
            PropositionalParser p = new PropositionalParser(f);
            Formula formulaTabellaDiVerita = p.parse(tabellaDiVerita.getSumOfProducts());
            Formula applied = advancedSimplifier.apply(formulaTabellaDiVerita, true);
            esaminaFunzione(applied.toString(), tabellaDiVerita);
        }

    }


    private static void esaminaFunzione(String function, TabellaDiVerita tabellaDiVerita) throws ParserException {
        FormulaFactory f = new FormulaFactory();
        PropositionalParser p = new PropositionalParser(f);
        Formula formulaAlgoritmo = p.parse(function);

        //System.out.println("Funzione in forma canonica: " + formulaAlgoritmo.nnf());

        Formula formulaTabVerita = p.parse(tabellaDiVerita.getSumOfProducts());
        //System.out.println("Tabella di verità: " + formulaTabVerita.nnf());
        if (formulaAlgoritmo.isEquivalentTo(formulaTabVerita)) {
            System.out.println("La funzione è valida.");
        } else System.out.println("La funzione non è valida.");

    }

}