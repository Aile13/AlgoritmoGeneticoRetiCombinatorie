package it.unibs.eliapitozzi.algoritmogenetico;

import it.unibs.eliapitozzi.espresso.boolFunction.Cover;
import it.unibs.eliapitozzi.espresso.boolFunction.InputState;
import it.unibs.eliapitozzi.espresso.boolFunction.OutputState;
import it.unibs.eliapitozzi.espresso.boolFunction.cube.Cube;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            variabili[i] = String.valueOf((char)(indiceVariabile + 1 + 64));;
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
            }
        }

        return posizioni;
    }

    public Cover getCover() {
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
    }
}
