package it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.irredundant;


import it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.utils.BooleanMatrix;

import java.util.List;

public class NoCoverMatrix extends BooleanMatrix {

  public NoCoverMatrix(List<List<Integer>> minSets, int columnCount) {
    super(new NoCoverMatrixElementGenerator(minSets, columnCount));
  }

}
