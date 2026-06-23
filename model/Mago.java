package model;

import Armas.Catalisador;

public class Mago extends Classe {
    public Mago(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.vidaMax = 80;
        this.vidaAtual = 80;
        this.forca = 5;
        this.destreza = 7;
        this.mana = 15;
        this.sorte = 10;
        this.inteligencia = 10;
        this.armaEquipada = new Catalisador("Livro Arcano velho", "Um livro contendo magias antigas.", 12, "Comum");
        this.inventario.add(this.armaEquipada);
    }
}
