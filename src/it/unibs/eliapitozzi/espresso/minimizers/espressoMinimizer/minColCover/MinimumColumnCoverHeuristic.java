package it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.minColCover;


import it.unibs.eliapitozzi.espresso.minimizers.espressoMinimizer.utils.BooleanMatrix;

import java.util.Set;

public interface MinimumColumnCoverHeuristic {

  Set<Integer> calculateMinimumColumnCover(BooleanMatrix matrix);
}
