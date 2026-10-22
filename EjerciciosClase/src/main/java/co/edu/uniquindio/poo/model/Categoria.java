package co.edu.uniquindio.poo.model;

public enum Categoria {
    COMPUTADORES(0), CELULARES(1), ACCESORIOS(2), VIDEO_JUEGOS(3), COMPONENTES(4);
    private final int id;
    Categoria(int id){this.id=id;}
    public int getId(){return id;}

}
