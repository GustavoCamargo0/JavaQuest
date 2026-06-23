package model;
import Armas.Arma;
import Armas.Catalisador;
import Armas.Espada;
import Armas.Espadao;

import java.util.List;
import java.util.ArrayList;
import model.BancoDeDados;
import Armas.Arco;

public class Inimigo {
    
    int nivel;
    int vidaMax;
    int vidaAtual = getVidaMax();
    int forca;
    int destreza;
    int mana;
    int sorte;
    String area;
    Arma armaEquipada;
    int getNivel() {
        return nivel;
    }

    int getVidaMax() {
        return vidaMax + (nivel * 5);
    }

    int getForca() {
        return forca + (nivel * 3);
    }

    int getDestreza() {
        return destreza + (nivel * 4);
    }

    int getMana() {
        return mana + (nivel * 2);
    }
    
    int getSorte() {
        return sorte + (nivel * 2);
    }
    private List<Integer> toList(int... ids) {
    List<Integer> list = new ArrayList<>();

    for (int id : ids) {
        list.add(id);
    }

    return list;
}
    public Inimigo(int nivel, int vidaMax, int forca, int destreza, int mana, int sorte, String area, int... idsArmas) {
    this.nivel = nivel;
    this.vidaMax = vidaMax;
    this.forca = forca;
    this.destreza = destreza;
    this.mana = mana;
    this.sorte = sorte;
    this.area = area;
    this.armaEquipada = BancoDeDados.sortearArmaPorIds(toList(idsArmas));
    this.vidaAtual = getVidaMax();
}
}


