package it.unibs.eliapitozzi.algoritmogenetico;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Elia
 */
public final class TabellaDiVerita {
    private final List<RigaTabella> righeTabella = new LinkedList<>();
    private final int numeroDiIngressi;

    public TabellaDiVerita(int numeroDiIngressi) {
        this.numeroDiIngressi = numeroDiIngressi;
        int numeroRighe = (int) Math.pow(2, numeroDiIngressi);
        for (int i = 0; i < numeroRighe; i++) {
            boolean output = Math.random() < 0.5;
            righeTabella.add(new RigaTabella(getListBoolByNum(i, numeroDiIngressi), output));
        }
    }

    private TabellaDiVerita() {
        /*numeroDiIngressi = 2;
        righeTabella.add(new RigaTabella(List.of(false, false), false));
        righeTabella.add(new RigaTabella(List.of(false, true), false));
        righeTabella.add(new RigaTabella(List.of(true, false), true));
        righeTabella.add(new RigaTabella(List.of(true, true), true));*/
        numeroDiIngressi = 3;
        righeTabella.add(new RigaTabella( List.of(false, false, false), true));
        righeTabella.add(new RigaTabella( List.of(false, false, true), true));
        righeTabella.add(new RigaTabella( List.of(false, true, false), true));
        righeTabella.add(new RigaTabella( List.of(false, true, true), false));
        righeTabella.add(new RigaTabella( List.of(true, false, false), true));
        righeTabella.add(new RigaTabella( List.of(true, false, true), false));
        righeTabella.add(new RigaTabella( List.of(true, true, false), false));
        righeTabella.add(new RigaTabella( List.of(true, true, true), false));
    }

    public static TabellaDiVerita getSumTable() {
        return new TabellaDiVerita();
    }

    private List<Boolean> getListBoolByNum(int numero, int numeroDiIngressi) {
        return String.format("%" + numeroDiIngressi + "s",
                        Integer.toBinaryString(numero)).replace(' ', '0')
                .chars().mapToObj(value -> {
                            char carattere = (char) value;
                            return carattere == '1';
                        }
                ).collect(Collectors.toList());
    }

    public int getTotaleRighe() {
        return righeTabella.size();
    }

    public int getNumeroIngressi() {
        return numeroDiIngressi;
    }

    public List<RigaTabella> righeTabella() {
        return righeTabella;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TabellaDiVerita) obj;
        return Objects.equals(this.righeTabella, that.righeTabella);
    }

    @Override
    public int hashCode() {
        return Objects.hash(righeTabella);
    }

    @Override
    public String toString() {
        return "TabellaDiVerita[" +
                "righeTabella=" + righeTabella + ']';
    }

    public String[] getVariabili() {
        String[] variabili = new String[numeroDiIngressi];
        int indiceVariabile = numeroDiIngressi - 1;

        for (int i = 0; i < variabili.length; i++) {
            variabili[i] = String.valueOf((char) (indiceVariabile + 1 + 64));
            indiceVariabile--;
        }
        return variabili;
    }

    public int[] getPosizioni() {
        var totalePosizioni = 0;
        for (RigaTabella rigaTabella : righeTabella) {
            if (rigaTabella.getOutputAtteso()) {
                totalePosizioni++;
            }
        }
        var posizioni = new int[totalePosizioni];
        int i = 0;

        for (int j = 0; j < righeTabella.size(); j++) {
            if (righeTabella.get(j).getOutputAtteso()) {
                posizioni[i] = j;
                i++;
            }
        }

        return posizioni;
    }

    /*public Cover getCover() {
        Cover cover = new Cover(numeroDiIngressi, 1);
        for (RigaTabella rigaTabella : righeTabella) {
            var inputState = new InputState[numeroDiIngressi];
            if (rigaTabella.getOutputAtteso()) {
                for (int i = 0; i < numeroDiIngressi; i++) {
                    if (rigaTabella.getValoreIngressoByNumero(i)) {
                        inputState[i] = InputState.ONE;
                    } else inputState[i] = InputState.ZERO;
                }
                cover.add(new Cube(inputState, new OutputState[]{OutputState.OUTPUT}));
            }
        }
        return cover;
    }*/

    private int numeroRigheConUscitaAuno() {
        int numero = 0;
        for (RigaTabella rigaTabella : righeTabella) {
            if (rigaTabella.getOutputAtteso())
                numero++;
        }
        return numero;
    }

    public String getSumOfProducts() {
        List<String> products = new LinkedList<>();

        for (RigaTabella rigaTabella : righeTabella) {

            int indiceVariabile = numeroDiIngressi - 1;
            if (rigaTabella.getOutputAtteso()) {
                List<String> terms = new LinkedList<>();
                for (int i = 0; i < numeroDiIngressi; i++) {
                    String variabile = String.valueOf((char) (indiceVariabile + 1 + 64));
                    if (rigaTabella.getValoreIngressoByNumero(indiceVariabile)) {
                        terms.add(variabile);
                    } else terms.add("~" + variabile);

                    indiceVariabile--;
                }

                products.add("( " + String.join(" & ", terms) + " )");
            }
        }
        return String.join(" | ", products);
    }
}
