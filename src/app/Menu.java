package app;

import javax.swing.*;

public class Menu {
    public static void main() {


        String nomeAcad, ruaAcad, bairroAcad, telefoneAcad, cpfCliente, senhaCliente, nomeCliente;
        int numAcad, num_personais, idadeCliente;
        double avaliacaoAcad;

        JOptionPane.showMessageDialog(null, "Olá, caro cliente!\n\nSeja bem-vindo(a) a Agencia de Academias MaisSaude.");
        int opcaoCadastro = JOptionPane.showConfirmDialog(null, "Ja possui cadastro?\nSe sim, clique em Yes se não em No");

        if (opcaoCadastro == 0) {
           cpfCliente = JOptionPane.showInputDialog(null, "CPF:", "Login", JOptionPane.QUESTION_MESSAGE);
           senhaCliente = JOptionPane.showInputDialog(null, "Senha:", "Senha", JOptionPane.QUESTION_MESSAGE);
        } else if (opcaoCadastro == 1) {
            nomeCliente = JOptionPane.showInputDialog(null, "informe seu nome:", JOptionPane.QUESTION_MESSAGE);
            idadeCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "informe seu idade:", JOptionPane.QUESTION_MESSAGE));

        }
    }
}
