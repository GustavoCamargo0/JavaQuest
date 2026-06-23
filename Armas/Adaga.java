package Armas;

public class Adaga extends Arma {
    public Adaga(String nome, String descricao, int danoEspecifico, String raridade) {
        super(nome, descricao, danoEspecifico, raridade);
        this.alcance = 2;
        this.peso = 1;
        this.atkVel = 15;
    }
}
