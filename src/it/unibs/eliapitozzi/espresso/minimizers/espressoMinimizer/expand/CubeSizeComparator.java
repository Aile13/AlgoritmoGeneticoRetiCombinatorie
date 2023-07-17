package it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.expand;


import it.unibs.eliapitozzi.espresso.boolFunction.cube.Cube;

import java.util.Comparator;

public final class CubeSizeComparator implements Comparator<Cube> {

  @Override
  public int compare(Cube o1, Cube o2) {
    return Integer.valueOf(o1.dontcareCount()).compareTo(o2.dontcareCount());
  }

}
