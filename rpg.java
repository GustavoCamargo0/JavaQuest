import java.util.Random;
import java.util.ArrayList;
import java.util.List;
abstract class Classe {
    String nome;
    int nivel;
    int vidaMax;
    int vidaAtual;
    int forca;
    int destreza;
    int mana;
    int sorte;
    int inteligencia;
    int pontos;
    Arma armaEquipada;

    void mostrarStatus() {
        System.out.println("---- Status ----");
        System.out.println("Nome: " + nome);
        System.out.println("Nivel: " + nivel); // -100
        System.out.println("Vida: " + vidaAtual + "/" + vidaMax);
        System.out.println("Força: " + forca);
        System.out.println("Destreza: " + destreza);
        System.out.println("Mana: " + mana);
        System.out.println("Sorte: " + sorte); // no caso do davi e Negativo
        System.out.println("Inteligência: " + inteligencia);
        System.out.println("----------------");
        System.out.println("Arma Equipada: " + armaEquipada.nome);
        System.out.println("Pontos Disponíveis: " + pontos);
        System.out.println("----------------");
    }
}


class Guerreiro extends Classe {
    Guerreiro(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.vidaMax = 120;
        this.vidaAtual = 120;
        this.forca = 15;
        this.destreza = 8;
        this.mana = 5;
        this.sorte = 10;
        this.inteligencia = 3;
        this.armaEquipada = new Espada("Espada De Ferro", 10, "Comum");
    }
}

class Arqueiro extends Classe {
    Arqueiro(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.vidaMax = 100;
        this.vidaAtual = 100;
        this.forca = 8;
        this.destreza = 15;
        this.mana = 7;
        this.sorte = 13;
        this.inteligencia = 4;
        this.armaEquipada = new Arco("Arco de Madeira", 8, "Comum");
    }
}

class Mago extends Classe {
    Mago(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.vidaMax = 80;
        this.vidaAtual = 80;
        this.forca = 5;
        this.destreza = 7;
        this.mana = 15;
        this.sorte = 10;
        this.inteligencia = 10;
        this.armaEquipada = new Catalisador("Livro Arcano velho", 12, "Comum");
    }
}

class Arma {
    String nome;
    int dano;
    int alcance;
    int peso;
    int atkVel;
    String raridade;

    Arma(String nome, int dano, String raridade) {
        this.nome = nome;
        this.dano = dano;
        this.raridade = raridade;
    }
}

class Espada extends Arma {
    Espada(String nome, int danoEspecifico, String raridade) {
        super(nome, danoEspecifico, raridade);
        this.alcance = 10;
        this.peso = 5;
        this.atkVel = 5;

    }
}

class Espadao extends Arma {
    Espadao(String nome, int danoEspecifico, String raridade) {
        super(nome, danoEspecifico, raridade);
        this.alcance = 15;
        this.peso = 10;
        this.atkVel = 2;

    }
}

class Arco extends Arma {
    Arco(String nome, int danoEspecifico, String raridade) {
        super(nome, danoEspecifico, raridade);
        this.alcance = 30;
        this.peso = 2;
        this.atkVel = 7;
    }
}

class Catalisador extends Arma {
    Catalisador(String nome, int danoEspecifico, String raridade) {
        super(nome, danoEspecifico, raridade);
        this.alcance = 20;
        this.peso = 3;
        this.atkVel = 5;
    }
}
class BancoDeDados {
    public static List<Arma> armas = new ArrayList<>();

}
public class rpg {
    public static void main(String[] args) {
        String[] encontros = { "Conversar", "Comprar", "Caçar", "Explorar" };
        Random random = new Random();

        System.out.println("Seja Bem-vindo ao nosso RPG");
        System.out.println("---------------------------");

        System.out.println("Escolha Sua Classe");
        System.out.println("Escolha Sua Classe:\n[1] Guerreiro\n[2] Arqueiro\n[3] Mago\n");
        int escolha = Integer.parseInt(System.console().readLine());

        System.out.println("Digite o nome do personagem");
        String nome = System.console().readLine();

        Classe personagem = null;

        switch (escolha) {
            case 1:
                personagem = new Guerreiro(nome);
                break;
            case 2:
                personagem = new Arqueiro(nome);
                break;
            case 3:
                personagem = new Mago(nome);
                break;
        }

        System.out.printf(" \n Olá %s\n", nome);
        int controlador;
        while (true) {
            System.out.println("----- Controles -----");
            System.out.println("\n[1] Sair\n [2] Descansar\n [3] Treinar\n [4] Eventos\n [5] Mostrar Status");
            System.out.println("---------------------");
            controlador = Integer.parseInt(System.console().readLine());

            switch (controlador) {
                case 1:
                    return;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    int aleatorio = random.nextInt(encontros.length);
                    String encontroPego = encontros[aleatorio];
                    System.out.println(encontroPego);
                    break;
                case 5:
                    personagem.mostrarStatus();
                    break;
            }

        }

    }
}
