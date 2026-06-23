package model;

import java.util.Random;
import javax.swing.UIManager;

import Armas.Arma;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Classe {
    public String nome;
    public int nivel;
    public int vidaMax;
    public int vidaAtual;
    public int forca;
    public int destreza;
    public int mana;
    public int sorte;
    public int inteligencia;
    public int pontos;
    public Arma armaEquipada;
    public String atributoPrincipal;
    public boolean defendendo = false;
    public ArrayList<Item> inventario = new ArrayList<>();

    public void ataque(Classe Inimigo) {
        int atributo = 0;
        Random random = new Random();

        switch (atributoPrincipal.toLowerCase()) {
            case "forca":
                atributo = forca;
                break;
            case "destreza":
                atributo = destreza;
                break;
            case "inteligencia":
                atributo = inteligencia;
                break;
        }

        int dano = armaEquipada.dano + (atributo * 2);

        dano -= Inimigo.destreza / 2;


        if (dano < 1) {
            dano = 1;
        }
        
        int critico = random.nextInt(100);

        if (critico < sorte) {
            dano *= 2;
        }
    
        Inimigo.receberDano(dano);
    }

    public void receberDano(int dano) {

        if (defendendo) {
            dano /= 2;
            defendendo = false;
        }

        vidaAtual -= dano;

        if (vidaAtual < 0) {
            vidaAtual = 0;
        }
    }

    public void defender() {
        defendendo = true;
    }

    public void mostrarStatus() {
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
            // Pode apagar o código abaixo se for usar a interface gráfica ou se não gostar
            // da interface, é só apagar a de cima, mas ainda faltaria conseguir upar os
            // status pelo terminal
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

    public void mostrarInventario() {
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
