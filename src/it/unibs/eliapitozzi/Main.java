package it.unibs.eliapitozzi;

import it.unibs.eliapitozzi.algoritmogenetico.AlgoritmoGenetico;
import it.unibs.eliapitozzi.algoritmogenetico.TabellaDiVerita;
import it.unibs.eliapitozzi.quinemccluskey.QuineMcCluskey;
import it.unibs.eliapitozzi.quinemccluskey.QuineMcCluskeyParseFunction;
import org.apache.commons.lang3.time.StopWatch;
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

        for (int j = 0; j < 10; j++) {// ciclo per fare 10 test.
            for (int i = 2; i < 9; i++) {  // ciclo sul numero di variabili.
                System.out.println("\tFunzione con " + i + " variabili di input:");

                TabellaDiVerita tabellaDiVerita = new TabellaDiVerita(i);
                StopWatch watch = StopWatch.create();

                watch.start();
                String function1 = AlgoritmoGenetico.run(tabellaDiVerita);
                watch.stop();
                System.out.println("Algoritmo genetico: " + watch);
                esaminaFunzione(function1, tabellaDiVerita);

                if (i <= 5) {
                    watch.reset();
                    watch.start();
                    QuineMcCluskey quineMcCluskey = new QuineMcCluskey(tabellaDiVerita.getVariabili(), tabellaDiVerita.getPosizioni());
                    String function2 = quineMcCluskey.getFunction();
                    watch.stop();
                    System.out.println("Algoritmo Quine-McCluskey: " + watch);
                    function2 = QuineMcCluskeyParseFunction.parseFunction(function2);
                    esaminaFunzione(function2, tabellaDiVerita);
                }


                if (i <= 8) {
                    AdvancedSimplifier advancedSimplifier = new AdvancedSimplifier();

                    FormulaFactory f = new FormulaFactory();
                    PropositionalParser p = new PropositionalParser(f);

                    watch.reset();
                    watch.start();
                    Formula formulaTabellaDiVerita = p.parse(tabellaDiVerita.getSumOfProducts());
                    Formula applied = advancedSimplifier.apply(formulaTabellaDiVerita, true);
                    watch.stop();
                    System.out.println("Advanced Simplifier by LogicNG: " + watch);
                    String function3 = applied.toString();
                    esaminaFunzione(function3, tabellaDiVerita);
                }
            }
            System.out.println("------------   Fine esecuzione di un test --------------");
        }

    }

    private static void esaminaFunzione(String function, TabellaDiVerita tabellaDiVerita) throws ParserException {
        FormulaFactory f = new FormulaFactory();
        PropositionalParser p = new PropositionalParser(f);
        Formula formulaAlgoritmo = p.parse(function);

        Formula formulaTabVerita = p.parse(tabellaDiVerita.getSumOfProducts());

        if (formulaAlgoritmo.isEquivalentTo(formulaTabVerita)) {
            System.out.println("La funzione è valida.");
        } else System.out.println("La funzione non è valida.");

    }

}