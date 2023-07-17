package it.unibs.eliapitozzi.espresso.minimizers.minimizerInterface;


import it.unibs.eliapitozzi.espresso.boolFunction.Cover;

public interface BooleanOnSetMinimizer {

  public Cover minimize(Cover onSet);
}
