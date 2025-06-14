package jml.algebra;

import java.util.Vector;

public abstract class Components {
  public abstract double get(int paramInt);
  
  public abstract void set(int paramInt, double paramDouble);
  
  public abstract double[] full_values();
  
  public abstract Vector sparce_values();
  
  public abstract int dimension();
  
  public abstract Components substract(double paramDouble);
  
  public abstract Components sum(double paramDouble);
  
  public abstract Components multiply(double paramDouble);
  
  public abstract Components divide(double paramDouble);
  
  public abstract Components pow(double paramDouble);
  
  public abstract Components sum(Components paramComponents);
  
  public abstract Components substract(Components paramComponents);
  
  public abstract Components direct_product(Components paramComponents);
  
  public abstract double max();
  
  public abstract double min();
  
  public abstract double summation();
  
  public abstract String toString();
}


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\algebra\Components.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */