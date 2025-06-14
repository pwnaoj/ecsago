package jml.data.transformation;

import jml.data.Data;

public abstract class DataTransformation {
  public abstract Data apply(Data paramData);
  
  public abstract Data inverse(Data paramData);
}


/* Location:              C:\Users\naojs\Downloads\ecsago.jar!\jml\data\transformation\DataTransformation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */