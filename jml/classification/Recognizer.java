package jml.classification;

public abstract class Recognizer {
  public abstract int classesNumber();
  
  public abstract double[] confidence(Object paramObject);
}


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\classification\Recognizer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */