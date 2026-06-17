
abstract class Classe {
    String nome;
    int nivel;
    int vida;
    int força;
    int destreza;
    int mana;
    int sorte;
    int inteligencia;

    void mostrarStatus() {
        System.out.println("---- Status ----");
        System.out.println("Nome: " + nome);
        System.out.println("Nivel: " + nivel);
        System.out.println("Vida: " + vida);
        System.out.println("Força: " + força);
        System.out.println("Destreza: " + destreza);
        System.out.println("Mana: " + mana);
        System.out.println("Sorte: " + sorte);
        System.out.println("Inteligência: " + inteligencia);
        System.out.println("----------------");
    }
}

class Guerreiro extends Classe {
    Guerreiro(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.vida = 120;
        this.força = 15;
        this.destreza = 8;
        this.mana = 5;
        this.sorte = 10;
        this.inteligencia = 3;
    }
}

class Arqueiro extends Classe {
    Arqueiro(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.vida = 100;
        this.força = 8;
        this.destreza = 15;
        this.mana = 7;
        this.sorte = 13;
        this.inteligencia = 4;
    }
}

class Mago extends Classe {
    Mago(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.vida = 80;
        this.força = 5;
        this.destreza = 7;
        this.mana = 15;
        this.sorte = 10;
        this.inteligencia = 10;
    }
}

public class rpg {
    public static void main(String[] args) {
        System.out.println("Seja Bem-vindo ao nosso RPG");
        System.out.println("---------------------------");

        System.out.println("Escolha Sua Classe");
        System.out.println("Escolha Sua Classe: Guerreiro - 1, Arqueiro - 2, Mago - 3");
        String escolha = System.console().readLine();

        System.out.println("Digite o nome do personagem");
        String nome = System.console().readLine();

        Classe personagem = null;

        switch (escolha) {
            case "1":
                personagem = new Guerreiro(nome);
                break;
            case "2":
                personagem = new Arqueiro(nome);
                break;
            case "3":
                personagem = new Mago(nome);
                break;
        }

    }
}
