package Armas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.Item;

public class Arma extends Item {
    public enum Raridade {
        LIXO,
        COMUM,
        INCOMUM,
        RARA,
        EPICA,
        LENDARIA,
        MITICA
    }

    public static Map<Raridade, Integer> chances = new LinkedHashMap<>();

    static {
        chances.put(Raridade.COMUM, 40);
        chances.put(Raridade.INCOMUM, 30);
        chances.put(Raridade.RARA, 15);
        chances.put(Raridade.EPICA, 9);
        chances.put(Raridade.LENDARIA, 5);
        chances.put(Raridade.MITICA, 1);
    }
public static Raridade sortearRaridade() {
    int roll = new Random().nextInt(100);
    int acumulado = 0;

    for (Map.Entry<Raridade, Integer> entry : chances.entrySet()) {
        acumulado += entry.getValue();
        if (roll < acumulado) {
            return entry.getKey();
        }
    }

    return Raridade.COMUM;
}
public static Arma buscarArmaAleatoria(List<Arma> armas) {
    Raridade r = sortearRaridade();

    List<Arma> filtradas = new ArrayList<>();

    for (Arma e : armas) {
        if (e.raridade == r) {
            filtradas.add(e);
        }
    }

    if (filtradas.isEmpty()) {
        return armas.get(new Random().nextInt(armas.size()));
    }

    return filtradas.get(new Random().nextInt(filtradas.size()));
}
    public int id;
    public int dano;
    public int alcance;
    public int peso;
    public int atkVel;
    public Raridade  raridade;

    public Arma(int id, String nome, String descricao, int dano, Raridade raridade) {
        super(nome, descricao, 1);

        this.id = id;
        this.dano = dano;
        this.raridade = raridade;
    }

    public int getId() {
        return id;
    }
    
}
