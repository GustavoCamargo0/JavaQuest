package Armas;

public class Arco extends Arma {
    public Arco(String nome, String descricao, int danoEspecifico, String raridade) {
        super(nome, descricao, danoEspecifico, raridade);
        this.alcance = 30;
        this.peso = 2;
        this.atkVel = 7;
    }
}
