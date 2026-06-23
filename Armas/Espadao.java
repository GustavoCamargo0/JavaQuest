package Armas;

public class Espadao extends Arma {
    Espadao(int id, String nome, String descricao, int danoEspecifico, Raridade raridade) {
        super(id, nome, descricao, danoEspecifico, raridade);
        this.alcance = 15;
        this.peso = 10;
        this.atkVel = 2;
    }
}
