package model;

import java.util.ArrayList;
import java.util.List;
import Armas.Arma;
import Armas.Catalisador;
import Armas.Espada;
import Armas.Espadao;
import Armas.Arco;
import Armas.Arma.Raridade;

public class BancoDeDados {
    public static List<Arma> armas = new ArrayList<>();
    public static List<Inimigo> inimigos = new ArrayList<>();


public static Arma buscarPorId(int id) {
    for (Arma a : armas) {
        if (a.id == id) {
            return a;
        }
    }
    return null;
}
public static Arma sortearArmaPorIds(List<Integer> ids) {
    List<Arma> possiveis = new ArrayList<>();

    for (int id : ids) {
        Arma a = buscarPorId(id);
        if (a != null) {
            possiveis.add(a);
        }
    }

    if (possiveis.isEmpty()) return null;

    return Arma.buscarArmaAleatoria(possiveis);
}
    static  {
        armas.add(new Espada(1, "Graveto", "Graveto comum e duro", 5, Raridade.COMUM));
        armas.add(new Espada(2, "Espada de madeira", "Espada feita de madeira", 12,  Raridade.COMUM));
    }
//  static {
//      inimigos.add(
//          new Inimigo( 1, 30, 6, 7,6, 8,"Floresta Verde", 1, 2)
//      );
//  }

}
