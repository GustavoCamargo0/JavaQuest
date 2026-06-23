package Armas;

import model.Item;

public class Arma extends Item {
    public int dano;
    public int alcance;
    public int peso;
    public int atkVel;
    public String raridade;

    public Arma(String nome, String descricao, int dano, String raridade) {
        super(nome, descricao, 1);
        this.dano = dano;
        this.raridade = raridade;
    }
}
