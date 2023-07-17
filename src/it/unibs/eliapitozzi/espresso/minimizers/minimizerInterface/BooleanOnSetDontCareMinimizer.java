package it.unibs.eliapitozzi.espresso.minimizers.minimizerInterface;


import it.unibs.eliapitozzi.espresso.boolFunction.Cover;

public interface BooleanOnSetDontCareMinimizer {

  public Cover minimize(Cover onSet, Cover dontcare);
}
