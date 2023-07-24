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

        System.out.println(minimized);
        for (Cube cube : minimized) {
            int indice = tabellaDiVerita.getNumeroIngressi() - 1;

            stringList = new LinkedList<>();
            for (InputState inputState : cube.getInputState()) {
                String variabile = String.valueOf((char) (indice + 1 + 64));

                if (inputState.equals(InputState.ONE)) {
                    stringList.add(variabile);
                } else if (inputState.equals(InputState.ZERO)) {
                    stringList.add("!" + variabile);
                }
                indice--;
            }
            String product = String.join(" & ", stringList);
            product = "(" + product + ")";
            products.add(product);
        }

        return String.join(" | ", products);
    }
}
