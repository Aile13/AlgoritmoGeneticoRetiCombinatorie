package it.unibs.eliapitozzi.espresso.boolFunction;

import it.unibs.eliapitozzi.algoritmogenetico.TabellaDiVerita;
import it.unibs.eliapitozzi.espresso.boolFunction.cube.Cube;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Elia
 */
public class EspressoParseFunction {
    public static String parseFunction(Cover minimized, TabellaDiVerita tabellaDiVerita) {
        List<String> stringList;
        List<String> products = new LinkedList<>();
        int i = tabellaDiVerita.getNumeroIngressi() - 1;

        for (Cube cube : minimized) {
             stringList = new LinkedList<>();
            for (InputState inputState : cube.getInputState()) {
                if (inputState.equals(InputState.ONE)) {
                    stringList.add("x" + i);
                } else stringList.add("!x" + i);
                i--;
            }
            String product = String.join(" & ", stringList);
            product = "(" + product + ")";
            products.add(product);

            i = tabellaDiVerita.getNumeroIngressi() - 1;
        }

        return String.join(" | ", products);
    }
}
