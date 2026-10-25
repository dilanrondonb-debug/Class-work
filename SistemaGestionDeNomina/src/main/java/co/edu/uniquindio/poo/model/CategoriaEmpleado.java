package co.edu.uniquindio.poo.model;

public enum CategoriaEmpleado {
  JUNIOR(5),SEMI_SENIOR(10),SENIOR(15);
  private final float bonificacion;
  CategoriaEmpleado(float bonificacion) {
      this.bonificacion=bonificacion;
  }
  public float getPorcentajeBonificacion() {return bonificacion;}
}
