package it.unibs.eliapitozzi.quinemccluskey;

/**
 * @author Elia
 */
public class QuineMcCluskeyParseFunction {
    public static String parseFunction(String function) {
        return function.replace("NOT", "!")
                .replace("AND", "&")
                .replace("OR", "|");
    }
}
