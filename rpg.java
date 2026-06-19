import java.util.Random;

import javax.swing.UIManager;

import java.awt.Color;
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
    ArrayList<Item> inventario = new ArrayList<>();

    void mostrarStatus() {
        UIManager.put("OptionPane.background", Color.BLACK); // customizacoes
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Button.background", Color.DARK_GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        while (true) { // loop pra nao fechar a janela dps de upar algum
            String[] opcoes = { "Upar vida max", "Upar Força", "Upar Destreza", "Upar Mana", "Upar Sorte",
                    "Upar inteligência", "Sair" }; // botoes pra upar os status
            String status = "---- Status ----\n" +
                    "Nome: " + nome + "\n" +
                    "Nivel: " + nivel + "\n" +
                    "Vida: " + vidaAtual + "/" + vidaMax + "\n" +
                    "Força: " + forca + "\n" +
                    "Destreza: " + destreza + "\n" +
                    "Mana: " + mana + "\n" +
                    "Sorte: " + sorte + "\n" +
                    "Inteligência: " + inteligencia + "\n" +
                    "----------------\n" +
                    "Arma Equipada: " + armaEquipada.nome + "\n" +
                    "Pontos Disponíveis: " + pontos + "\n" +
                    "----------------";

            int escolha = javax.swing.JOptionPane.showOptionDialog(null, status, "Status do personagem",
                    javax.swing.JOptionPane.DEFAULT_OPTION, javax.swing.JOptionPane.INFORMATION_MESSAGE, null, opcoes, // botoes
                                                                                                                       // pra
                                                                                                                       // upar
                                                                                                                       // os
                                                                                                                       // status
                    opcoes[6]);

            if (escolha == 0 && pontos > 0) {
                if (vidaAtual == vidaMax) {
                    vidaAtual += 10;
                    vidaMax += 10;
                } else {
                    vidaMax += 10;
                }
                pontos--;
            } else if (escolha == 1 && pontos > 0) {
                forca += 2;
                pontos--;
            } else if (escolha == 2 && pontos > 0) {
                destreza += 2;
                pontos--;
            } else if (escolha == 3 && pontos > 0) {
                mana += 2;
                pontos--;
            } else if (escolha == 4 && pontos > 0) {
                sorte += 2;
                pontos--;
            } else if (escolha == 5 && pontos > 0) {
                inteligencia += 2;
                pontos--;
            } else if (escolha == 6 || escolha == -1) {
                return;
            } else {
                if (pontos <= 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Você não tem pontos para gastar", "Erro",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
            // Pode apagar o código abaixo se for usar a interface gráfica ou se não gostar da interface, é só apagar a de cima, mas ainda faltaria conseguir upar os status pelo terminal
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

    };

    void mostrarInventario() {
        System.out.println("---- Inventário ----");
        for (Item item : inventario) {
            int posicao = inventario.indexOf(item) + 1;
            if (item instanceof Arma) {
                Arma arma = (Arma) item;
                if (arma == armaEquipada) {
                    System.out.println(
                            "[" + posicao + "] " + "(Equipado) " + arma.nome + " - " + "(Dano: " + arma.dano + " peso: "
                                    + arma.peso + "kg" + " Raridade: " + arma.raridade + ") ");
                    continue;
                }
                System.out.println("[" + posicao + "] " + arma.nome + " - " + "(Dano: " + arma.dano + " peso: "
                        + arma.peso + "kg" + " Raridade: " + arma.raridade + ")");
                continue;
            }
            System.out.println("[" + posicao + "] " + item.nome + " - " + " (Qntd: " + item.quantidade + ")");
        }
        System.out.println("-------------------");
        System.out.println("Inspecionar Itens - Digite o número do item ou 0 para sair");
        int escolha = Integer.parseInt(System.console().readLine());

        if (escolha > 0 && escolha <= inventario.size()) {
            Item itemEscolhido = inventario.get(escolha - 1);
            String ItemInfo = "---- " + itemEscolhido.nome + " ----\n" +
                    "Nome: " + itemEscolhido.nome + "\n" +
                    "Descrição: " + itemEscolhido.descricao + "\n";
            if (itemEscolhido instanceof Arma) {
                Arma arma = (Arma) itemEscolhido;
                ItemInfo += "Dano: " + arma.dano + "\n" +
                        "Peso: " + arma.peso + "kg\n" +
                        "Alcance: " + arma.alcance + "\n" +
                        "Velocidade de Ataque: " + arma.atkVel + "\n" +
                        "Raridade: " + arma.raridade + "\n";
            } else {
                ItemInfo += "Quantidade: " + itemEscolhido.quantidade + "\n";
            }

            System.out.println(ItemInfo);
            if (itemEscolhido != armaEquipada && itemEscolhido instanceof Arma) {
                System.out.println("[1] Equipar Arma \n[2] Descartar Item\n[3] Voltar");
                int escolha2 = Integer.parseInt(System.console().readLine());

                switch (escolha2) {
                    case 1:
                        armaEquipada = (Arma) itemEscolhido;
                        System.out.println("Arma equipada: " + armaEquipada.nome);
                        break;
                    case 2:
                        inventario.remove(itemEscolhido);
                        System.out.println("Item descartado");
                    case 3: 
                    return;
                }
            }
            System.out.println("Pressione Enter para continuar...");
            System.console().readLine();
        }
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
        this.armaEquipada = new Espada("Espada De Ferro", "Uma espada feita de ferro.", 10, "Comum");
        this.inventario.add(this.armaEquipada);
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
        this.armaEquipada = new Arco("Arco de Madeira", "Um arco feito de madeira durável.", 8, "Comum");
        this.inventario.add(this.armaEquipada);
        this.inventario.add(new Item("Flechas", "Flechas para um arco", 20));
        this.inventario.add(new Adaga("Adaga Curta", "Uma adaga pequena e rápida.", 5, "Comum"));
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
        this.armaEquipada = new Catalisador("Livro Arcano velho", "Um livro contendo magias antigas.", 12, "Comum");
        this.inventario.add(this.armaEquipada);
    }
}

class Item {
    String nome;
    String descricao;
    int quantidade;

    Item(String nome, String descricao, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }
}

class Arma extends Item {
    int dano;
    int alcance;
    int peso;
    int atkVel;
    String raridade;

    Arma(String nome, String descricao, int dano, String raridade) {
        super(nome, descricao, 1);
        this.dano = dano;
        this.raridade = raridade;
    }
}
class Adaga extends Arma {
    Adaga(String nome, String descricao, int danoEspecifico, String raridade) {
        super(nome, descricao, danoEspecifico, raridade);
        this.alcance = 2;
        this.peso = 1;
        this.atkVel = 15;
    }
}
class Espada extends Arma {
    Espada(String nome, String descricao, int danoEspecifico, String raridade) {
        super(nome, descricao, danoEspecifico, raridade);
        this.alcance = 10;
        this.peso = 5;
        this.atkVel = 5;

    }
}

class Espadao extends Arma {
    Espadao(String nome, String descricao, int danoEspecifico, String raridade) {
        super(nome, descricao, danoEspecifico, raridade);
        this.alcance = 15;
        this.peso = 10;
        this.atkVel = 2;

    }
}

class Arco extends Arma {
    Arco(String nome, String descricao, int danoEspecifico, String raridade) {
        super(nome, descricao, danoEspecifico, raridade);
        this.alcance = 30;
        this.peso = 2;
        this.atkVel = 7;
    }
}

class Catalisador extends Arma {
    Catalisador(String nome, String descricao, int danoEspecifico, String raridade) {
        super(nome, descricao, danoEspecifico, raridade);
        this.alcance = 20;
        this.peso = 3;
        this.atkVel = 5;
    }
}

class BancoDeDados {
    public static List<Arma> armas = new ArrayList<>();

}

class inventario extends Classe {
    List<Arma> armas = new ArrayList<>();

    void adicionarArma(Arma arma) {
        armas.add(arma);
    }

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
            System.out.println(
                    "\n[1] Sair\n [2] Descansar\n [3] Treinar\n [4] Eventos\n [5] Mostrar Status\n [6] Inventário");
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
                case 6:
                    personagem.mostrarInventario();
                    break;
            }

        }

    }
}
