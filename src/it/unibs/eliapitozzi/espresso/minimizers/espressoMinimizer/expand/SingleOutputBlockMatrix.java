package it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.expand;

import it.unibs.eliapitozzi.espresso.boolFunction.Cover;
import it.unibs.eliapitozzi.espresso.boolFunction.cube.Cube;
import it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.utils.BooleanMatrix;

public class SingleOutputBlockMatrix extends BooleanMatrix {

  public SingleOutputBlockMatrix(Cover cover, Cube cube) {
    super(new BlockMatrixElementGenerator(cover, cube));
  }
}
