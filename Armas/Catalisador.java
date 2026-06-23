package Armas;

public class Catalisador extends Arma {
    public Catalisador(String nome, String descricao, int danoEspecifico, String raridade) {
        super(nome, descricao, danoEspecifico, raridade);
        this.alcance = 20;
        this.peso = 3;
        this.atkVel = 5;
    }
}
