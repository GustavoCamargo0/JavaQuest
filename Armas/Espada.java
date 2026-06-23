package Armas;

public class Espada extends Arma {
    public Espada(String nome, String descricao, int danoEspecifico, String raridade) {
        super(nome, descricao, danoEspecifico, raridade);
        this.alcance = 10;
        this.peso = 5;
        this.atkVel = 5;
    }
}
