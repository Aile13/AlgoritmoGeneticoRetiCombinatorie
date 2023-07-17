package it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.expand;


import it.unibs.eliapitozzi.espresso.boolFunction.Cover;
import it.unibs.eliapitozzi.espresso.boolFunction.InputState;
import it.unibs.eliapitozzi.espresso.boolFunction.cube.Cube;
import it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.utils.MatrixElementGenerator;

import static it.unibs.eliapitozzi.espresso.boolFunction.InputState.ONE;
import static it.unibs.eliapitozzi.espresso.boolFunction.InputState.ZERO;

public class CoverMatrixElementGenerator implements MatrixElementGenerator {

  private Cover cover;
  private Cube cube;

  public CoverMatrixElementGenerator(Cover cover, Cube cube) {
    //region Exceptions
    if (cover.size() == 0) {
      throw new IllegalArgumentException(
          "Cover can't be empty."
      );
    }
    if (cover.inputCount() != cube.inputLength()) {
      throw new IllegalArgumentException(
          "Input counts for cover and cube need to be equal."
      );
    }
    //endregion

    this.cover = cover;
    this.cube = cube;
  }

  @Override
  public boolean generateElement(int rowIndex, int columnIndex) {
    InputState coverInputState = cover.get(rowIndex).getInputState(columnIndex);
    InputState cubeInputState = cube.getInputState(columnIndex);

    return cubeInputState == ONE && coverInputState != ONE ||
        cubeInputState == ZERO && coverInputState != ZERO;
  }

  @Override
  public int getRowCount() {
    return cover.size();
  }

  @Override
  public int getColumnCount() {
    return cube.inputLength();
  }

}
