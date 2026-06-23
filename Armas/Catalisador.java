package Armas;

public class Catalisador extends Arma {
    public Catalisador(int id, String nome, String descricao, int danoEspecifico, Raridade raridade) {
        super(id, nome, descricao, danoEspecifico, raridade);
        this.alcance = 20;
        this.peso = 3;
        this.atkVel = 5;
    }
}
