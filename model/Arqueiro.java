package model;

import Armas.Adaga;
import Armas.Arco;
import Armas.Arma.Raridade;
import Armas.Espada;
public class Arqueiro extends Classe {
    public Arqueiro(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.vidaMax = 100;
        this.vidaAtual = 100;
        this.forca = 8;
        this.destreza = 15;
        this.mana = 7;
        this.sorte = 13;
        this.inteligencia = 4;
        this.atributoPrincipal = "destreza"; 
        this.armaEquipada = new Arco(4, "Arco de Madeira", "Um arco feito de madeira durável.", 8, Raridade.COMUM);
        this.inventario.add(this.armaEquipada);
        this.inventario.add(new Item("Flechas", "Flechas para um arco", 20));
       this.inventario.add(new Adaga(5,"Adaga Curta", "Uma adaga pequena e rápida.", 5, Raridade.COMUM));
    }
}
