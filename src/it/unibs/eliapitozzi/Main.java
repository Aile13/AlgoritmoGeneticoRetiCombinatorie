package it.unibs.eliapitozzi;

import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.rules.Rule;
import com.bpodgursky.jbool_expressions.rules.RuleSet;
import it.unibs.eliapitozzi.algoritmogenetico.AlgoritmoGenetico;
import it.unibs.eliapitozzi.algoritmogenetico.TabellaDiVerita;
import it.unibs.eliapitozzi.espresso.boolFunction.EspressoParseFunction;
import it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.SingleOutputEspressoMinimizer;
import it.unibs.eliapitozzi.quinemccluskey.QuineMcCluskey;
import it.unibs.eliapitozzi.quinemccluskey.QuineMcCluskeyParseFunction;

/**
 * @author Elia
 */
public class Main {
    public static void main(String[] args) {

        TabellaDiVerita tabellaDiVerita = TabellaDiVerita.getSumTable();
        // new TabellaDiVerita(2);

   /*     String function = AlgoritmoGenetico.run(tabellaDiVerita);
        System.out.println("Funzione dell'algoritmo genetico: " + function);
        esaminaFunzione(function, tabellaDiVerita);*/


        QuineMcCluskey quineMcCluskey = new QuineMcCluskey(tabellaDiVerita.getVariabili(), tabellaDiVerita.getPosizioni());
        String function2 = QuineMcCluskeyParseFunction.parseFunction(quineMcCluskey.getFunction());
        System.out.println("Funzione di Quine-McCluskey: " + function2);
        esaminaFunzione(function2, tabellaDiVerita);

        String function3 = EspressoParseFunction
                .parseFunction(
                        SingleOutputEspressoMinimizer.getInstance().minimize(tabellaDiVerita.getCover()),
                        tabellaDiVerita
                );
        System.out.println("Funzione di Espresso: " + function3);
        esaminaFunzione(function3, tabellaDiVerita);

    }

    private static void esaminaFunzione(String function, TabellaDiVerita tabellaDiVerita) {
        Expression<String> sopForm = RuleSet.toDNF(ExprParser.parse(function));
        System.out.println("Funzione in forma canonica: " + sopForm);
        var tabDiVeritaFunciton = ExprParser.parse(tabellaDiVerita.getSumOfProducts());
        System.out.println("Tabella di verità: " + tabDiVeritaFunciton);
        if (sopForm.equals(tabDiVeritaFunciton)) {
            System.out.println("La funzione è valida.");
        } else System.out.println("La funzione non è valida.");
    }

}