package it.unibs.eliapitozzi.quinemccluskey;

/**
 * @author Elia
 */
public class QuineMcCluskeyParseFunction {
    public static String parseFunction(String function) {
        function = function.replace("NOT ", "~")
                .replace("AND", "&")
                .replace("OR", "|");
        return "(" + function + ")";
    }
}
