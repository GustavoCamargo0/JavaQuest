package Armas;

public class Arco extends Arma {
    public Arco(int id, String nome, String descricao, int danoEspecifico, Raridade raridade) {
        super(id, nome, descricao, danoEspecifico, raridade);
        this.alcance = 30;
        this.peso = 2;
        this.atkVel = 7;
    }
}
