import java.util.Random;
import javax.swing.UIManager;

import Armas.*;
import model.*;

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
        combate.add(new JLabel("Você entrou em combate"));
        JButton atacarBtn = new JButton("Atacar");
        JButton defenderBtn = new JButton("Defender");
        JButton habilidadesBtn = new JButton("Habilidades");
        JButton correrBtn = new JButton("Correr");
        combate.add(atacarBtn);
        combate.add(defenderBtn);
        combate.add(habilidadesBtn);
        combate.add(correrBtn);
        
        atacarBtn.addActionListener(e -> {
            personagem.ataque(personagem);
        });

        defenderBtn.addActionListener(e -> {
        
        });

        habilidadesBtn.addActionListener(e -> {
        
        });
        correrBtn.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "JOGO");
        });

        // ---------------------------------------------------------------------------

        // TELA PRINCIPAL
        JPanel telaJogo = new JPanel(new FlowLayout());
        String[] acoes = { "Descansar", "Treinar", "Eventos", "Mostrar Status", "Inventario" };
        JComboBox<String> acao = new JComboBox<>(acoes);
        JButton jogarButton = new JButton("Confirmar");
        telaJogo.add(acao);
        telaJogo.add(jogarButton);
        String[] encontros = { "Conversar", "Comprar", "Caçar", "Explorar", "Combate" };
        Random random = new Random();

        // LÓGICA DOS BOTÕES
        jogarButton.addActionListener(e -> {
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
