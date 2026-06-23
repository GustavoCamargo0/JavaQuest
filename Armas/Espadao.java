package Armas;

public class Espadao extends Arma {
    Espadao(String nome, String descricao, int danoEspecifico, String raridade) {
        super(nome, descricao, danoEspecifico, raridade);
        this.alcance = 15;
        this.peso = 10;
        this.atkVel = 2;
    }
}
