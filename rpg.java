import java.util.Random;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

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

    void presente_misterioso() {
        Random random = new Random();
        int num = random.nextInt();
        System.out.println(num);
        if (num >= 50) {
            System.out.println("parabens vc ganhou");
        } else {
            System.out.println("vc perdeu... quer os 2 reais");
        }
    }

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

class Inimigo {
    String nome;
    int dano;
    int vidaMax;
    int vidaAtual;
    int nivel;
  
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

    private static Classe personagem;
    private static int intervalod = 0;
    private static int intervalot = 0;
    private static CardLayout cardLayout;
    private static JPanel painelPrincipal;

    public static JPanel criarGui() {

        JFrame frame = new JFrame("Java Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        // Tela Criação Personagem
        JPanel persoCriacao = new JPanel(new FlowLayout());
        String[] classes = { "Guerreiro", "Arqueiro", "Mago" };
        JComboBox<String> cla = new JComboBox<>(classes);
        JLabel msgNome = new JLabel("Nome do personagem");
        JTextField nome = new JTextField(20);
        JButton confirmarBtn = new JButton("Criar personagem");
        persoCriacao.add(new JLabel("Seja bem-vindo ao nosso RPG"));
        persoCriacao.add(new JLabel("Nome do personagem"));
        persoCriacao.add(nome);
        persoCriacao.add(new JLabel("Escolha uma classe"));
        persoCriacao.add(cla);
        persoCriacao.add(confirmarBtn);
        // ---------------------------------------------------------------------------
        // TELA INVENTARIO

        // ---------------------------------------------------------------------------
        // TELA COMBATE 

        JPanel combate = new JPanel(new FlowLayout());
        JButton atacarBtn = new JButton("Atacar");
        JButton defenderBtn = new JButton("Defender");
        JButton correrBtn = new JButton("Correr");
        combate.add(atacarBtn);
        combate.add(defenderBtn);
        combate.add(correrBtn);
        


        // ---------------------------------------------------------------------------
        // TELA PRINCIPAL
        JPanel telaJogo = new JPanel(new FlowLayout());
        String[] acoes = { "Descansar", "Treinar", "Eventos", "Mostrar Status", "Inventario" };
        JComboBox<String> acao = new JComboBox<>(acoes);
        JButton confirmButton = new JButton("Confirmar");
        telaJogo.add(acao);
        telaJogo.add(confirmButton);
        String[] encontros = { "Conversar", "Comprar", "Caçar", "Explorar", "Combate" };
        Random random = new Random();

        // LÓGICA DOS BOTÕES
        confirmButton.addActionListener(e -> {
            String acaoEscolhida = (String) acao.getSelectedItem();
            switch (acaoEscolhida) {
                case "Descansar":
                    if (intervalod == 0) {
                        JOptionPane.showMessageDialog(frame, "Você descansa e se sente melhor\n você curou 30 de vida");
                        personagem.vidaAtual = personagem.vidaAtual + 30;
                        if (personagem.vidaAtual > personagem.vidaMax) {
                            personagem.vidaAtual = personagem.vidaMax;
                        }
                        intervalod = intervalod + 3;
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Você ainda não pode descansar\n turnos restantes para descansar:" + intervalod);
                    }
                    break;
                case "Mostrar Status":
                    personagem.mostrarStatus();
                    break;

                case "Treinar":
                    if (intervalot == 0) {
                        JOptionPane.showMessageDialog(frame,
                                "Você treina e se sente mais forte");
                        personagem.pontos = personagem.pontos + 3;
                        intervalot = intervalot + 3;
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Você ainda não pode treinar");
                    }
                    break;

                case "Inventario":
                    carregarInventario();
                    cardLayout.show(painelPrincipal, "INVENTARIO");
                    break;
                case "Eventos":
                    int aleatorio = random.nextInt(encontros.length);
                    String encontroPego = encontros[aleatorio];
                    JOptionPane.showMessageDialog(frame, encontroPego);
                    switch (encontroPego) {
                        case "Combate":
                            cardLayout.show(painelPrincipal, "COMBATE");
                            break;
                    }
                 
                    if (intervalod > 0) {
                        intervalod = intervalod - 1;
                    }
                    if (intervalot > 0) {
                        intervalot = intervalot - 1;
                    }
                    break;
            }
        });

        // ---------------------------------------------------------------------------
        JLabel mensagemApresentacao = new JLabel();
        telaJogo.add(mensagemApresentacao);

        // painel Inventário

        painelPrincipal.add(persoCriacao, "CRIACAO");
        painelPrincipal.add(telaJogo, "JOGO");
        painelPrincipal.add(telaInventario, "INVENTARIO");
        painelPrincipal.add(combate, "COMBATE");

        frame.add(painelPrincipal);
        frame.setVisible(true);

        confirmarBtn.addActionListener(e -> {
            String nomePersonagem = nome.getText();
            String classEscolhida = (String) cla.getSelectedItem();

            if (nomePersonagem.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Insira um nome para o personagem.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (classEscolhida) {
                case "Guerreiro":
                    personagem = new Guerreiro(nomePersonagem);
                    break;
                case "Arqueiro":
                    personagem = new Arqueiro(nomePersonagem);
                    break;
                case "Mago":
                    personagem = new Mago(nomePersonagem);
                    break;
            }
            mensagemApresentacao.setText(
                    "<html><center><h1>JavaQuest</h1" + nomePersonagem + " o " + classEscolhida + "!</center><html>");
            JOptionPane.showMessageDialog(frame,
                    "Personagem Criado!\nNome: " + personagem.nome + "\nClasse: " + classEscolhida);
            cardLayout.show(painelPrincipal, "JOGO");

        });
        return painelPrincipal;

    }

    static JPanel telaInventario = new JPanel(new GridLayout(8, 1));
    static ArrayList<JButton> items = new ArrayList<>();

    static void carregarInventario() {
        telaInventario.removeAll();
        items.clear();
        {
            for (Item item : personagem.inventario) {
                JButton botaoItem;
                if (item instanceof Arma) {
                    Arma arma = (Arma) item;
                    if (arma == personagem.armaEquipada) {
                        botaoItem = new JButton(
                                "[Equipado] " + arma.nome + " - " + "(Dano: " + arma.dano + ") " + " (peso: "
                                        + arma.peso + "kg ) " + "(Raridade: " + arma.raridade + ")");
                        items.add(botaoItem);
                    } else {
                        botaoItem = new JButton(arma.nome + " - " + "(Dano: " + arma.dano + ") " + " (peso: "
                                + arma.peso + "kg) " + "(Raridade: " + arma.raridade + ") ");
                        items.add(botaoItem);
                    }
                } else {
                    botaoItem = new JButton(item.nome + " - " + " (Qntd: " + item.quantidade + ") ");
                    items.add(botaoItem);
                }
                botaoItem.addActionListener(e -> {
                    abrirPainelEspItem(item);
                });
                telaInventario.add(botaoItem);
            }
            JButton voltarBtn = new JButton("Voltar");
            voltarBtn.addActionListener(e -> {
                cardLayout.show(painelPrincipal, "JOGO");
            });
            telaInventario.add(voltarBtn);
        }

        for (JButton item : items) {
            telaInventario.add(item);
        }
        telaInventario.revalidate();
        telaInventario.repaint();
    }

    private static void abrirPainelEspItem(Item itemEscolhido) {

        JPanel painelDetalhes = new JPanel();
        painelDetalhes.setLayout(new BoxLayout(painelDetalhes, BoxLayout.Y_AXIS));
        painelDetalhes.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("<html><h2>--- DETALHES DO ITEM ---</h2></html>");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelDetalhes.add(titulo);
        painelDetalhes.add(Box.createVerticalStrut(10));

        JLabel lblNome = new JLabel("<html><b>Nome:</b> " + itemEscolhido.nome + "</html>");
        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelDetalhes.add(lblNome);
        painelDetalhes.add(Box.createVerticalStrut(5));

        JLabel lblDesc = new JLabel("<html><body style='width: 350px; text-align: center;'><b>Descrição:</b> "
                + itemEscolhido.descricao + "</body></html>");
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelDetalhes.add(lblDesc);
        painelDetalhes.add(Box.createVerticalStrut(5));

        if (itemEscolhido instanceof Arma) {
            Arma arma = (Arma) itemEscolhido;
            JLabel lblDano = new JLabel("<html><b>Dano:</b> " + arma.dano + "</html>");
            lblDano.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelDetalhes.add(lblDano);
            painelDetalhes.add(Box.createVerticalStrut(5));

            JLabel lblRari = new JLabel("<html><b>Raridade:</b> " + arma.raridade + "</html>");
            lblRari.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelDetalhes.add(lblRari);
            painelDetalhes.add(Box.createVerticalStrut(10));

            if (arma != personagem.armaEquipada) {
                JButton equiparBtn = new JButton("Equipar");
                equiparBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                equiparBtn.addActionListener(ev -> {
                    personagem.armaEquipada = arma;
                    JOptionPane.showMessageDialog(painelPrincipal, arma.nome + " equipada!");
                    carregarInventario();
                    cardLayout.show(painelPrincipal, "INVENTARIO");
                });
                painelDetalhes.add(equiparBtn);
                painelDetalhes.add(Box.createVerticalStrut(5));
            }
        } else {
            JLabel lblQtd = new JLabel("<html><b>Quantidade:</b> " + itemEscolhido.quantidade + "</html>");
            lblQtd.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelDetalhes.add(lblQtd);
            painelDetalhes.add(Box.createVerticalStrut(10));
        }

        JButton descartarBtn = new JButton("Descartar");
        descartarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        descartarBtn.addActionListener(ev -> {
            personagem.inventario.remove(itemEscolhido);
            JOptionPane.showMessageDialog(painelPrincipal, itemEscolhido.nome + " descartado!");
            carregarInventario();
            cardLayout.show(painelPrincipal, "INVENTARIO");
        });
        painelDetalhes.add(descartarBtn);
        painelDetalhes.add(Box.createVerticalStrut(5));

        JButton voltarBtn = new JButton("Voltar");
        voltarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        voltarBtn.addActionListener(ev -> {
            cardLayout.show(painelPrincipal, "INVENTARIO");
        });
        painelDetalhes.add(voltarBtn);

        painelPrincipal.add(painelDetalhes, "DETALHES_ITEM");
        cardLayout.show(painelPrincipal, "DETALHES_ITEM");
    }

    // private static void inspecionarItemGui(Item item) {
    // String itemInfo = "---- " + item.nome + " ----\n" +
    // "Nome: " + item.nome + "\n" +
    // "Descrição: " + item.descricao + "\n";
    // if (item instanceof Arma) {
    // Arma arma = (Arma) item;
    // itemInfo += "Dano: " + arma.dano + "\n" +
    // "Peso: " + arma.peso + "kg\n" +
    // "Alcance: " + arma.alcance + "\n" +
    // "Velocidade Atk: " + arma.atkVel + "\n" +
    // "Raridade: " + arma.raridade + "\n";
    // }
    // JOptionPane.showMessageDialog(null, itemInfo, item.nome,
    // JOptionPane.INFORMATION_MESSAGE);
    // }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                criarGui();
            }
        });
    }
}

// String[] encontros = { "Conversar", "Comprar", "Caçar", "Explorar" };
// Random random = new Random();

// System.out.println("Seja Bem-vindo ao nosso RPG");
// System.out.println("---------------------------");

// System.out.println("Escolha Sua Classe");
// System.out.println("Escolha Sua Classe:\n[1] Guerreiro\n[2] Arqueiro\n[3]
// Mago\n");
// int escolha = Integer.parseInt(System.console().readLine());

// int controlador;
// int intervalot = 0;
// int intervalod = 0;
// while (true) {
// System.out.println("----- Controles -----");
// System.out.println(
// "\n [1] Sair\n [2] Descansar\n [3] Treinar\n [4] Eventos\n [5] Mostrar
// Status\n [6] Inventário \n [7] presente misterioso");
// System.out.println("---------------------");
// controlador = Integer.parseInt(System.console().readLine());

// switch (controlador) {
// case 1:
// return;
// case 2:
// if (intervalod == 0) {
// System.out.println("Você descansa e se sente melhor");
// personagem.vidaAtual = personagem.vidaAtual + 30;
// if (personagem.vidaAtual > personagem.vidaMax) {
// personagem.vidaAtual = personagem.vidaMax;
// }
// intervalod = intervalod + 3;
// } else
// System.out.println("Você ainda não pode descansar");
// break;
// case 3:
// if (intervalot == 0) {
// System.out.println("Você treina e se sente mais forte");
// personagem.pontos = personagem.pontos + 3;
// intervalot = intervalot + 3;
// } else
// System.out.println("Você ainda não pode treinar");
// break;
// case 4:
// int aleatorio = random.nextInt(encontros.length);
// String encontroPego = encontros[aleatorio];
// System.out.println(encontroPego);
// if (intervalod > 0) {
// intervalod = intervalod - 1;
// }
// if (intervalot > 0) {
// intervalot = intervalot - 1;
// }
// break;
// case 5:
// personagem.mostrarStatus();
// break;
// case 6:
// personagem.mostrarInventario();
// break;
// case 7:
// personagem.presente_misterioso();
// break;
// }
// }

// }
// }
