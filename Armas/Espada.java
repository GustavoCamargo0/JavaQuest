package Armas;

public class Espada extends Arma {
    public Espada(int id, String nome, String descricao, int danoEspecifico, Raridade raridade) {
        super(id, nome, descricao, danoEspecifico, raridade);
        this.alcance = 10;
        this.peso = 5;
        this.atkVel = 5;
    }
}
