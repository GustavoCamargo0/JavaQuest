package model;

import java.util.ArrayList;
import java.util.List;

import Armas.Arma;

public class inventario extends Classe {
    List<Arma> armas = new ArrayList<>();

    public void adicionarArma(Arma arma) {
        armas.add(arma);
    }
}
