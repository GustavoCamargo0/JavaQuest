package model;

import Armas.Espada;

public class Guerreiro extends Classe {
    public Guerreiro(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.vidaMax = 120;
        this.vidaAtual = 120;
        this.forca = 15;
        this.destreza = 8;
        this.mana = 5;
        this.sorte = 10;
        this.inteligencia = 3;
        this.armaEquipada = new Espada("Espada De Ferro", "Uma espada feita de ferro.", 10, "Comum");
        this.inventario.add(this.armaEquipada);
    }
}
