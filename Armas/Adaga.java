package Armas;

public class Adaga extends Arma {
    public Adaga(int id, String nome, String descricao, int danoEspecifico, Raridade raridade) {
        super(id, nome, descricao, danoEspecifico, raridade);
        this.alcance = 2;
        this.peso = 1;
        this.atkVel = 15;
    }
}
