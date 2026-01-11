package app;

import javax.swing.*;

public class RedeAcademias {

    public static void main(String[] args) {

        String testeNull = "";
        int opcao = 1;

        String nomeCliente, cpfCliente, restricaoMedCliente, nomeAcd, n_contatoAcd, atividadesOfertadas, enderecoAcd;
        int idadeCliente, num_personais, busca, resposta, idAcademia_matricula, opcaoFiltrar, opcaoFiltrarPreco, opcaoFiltrarAvaliacao;
        double mensalidade, avali;
        Academia academia;
        GerenciamentoSistema gerenciamento = new GerenciamentoSistema();

        JOptionPane.showMessageDialog(null, "Entrando no SISTEMA...");

        while (opcao != 0) {
            testeNull = JOptionPane.showInputDialog(null, "MENU\n1 - Cadastrar Cliente\n2 - Cadastrar Academia\n3 - Buscar Academia\n4 - Listar Academias\n5 - Excluir Academia\n6 - Avaliar Academia\n7 - Matricular\n8 - Filtrar");
            if (testeNull == null) {  // aqui serve para verificar quando a pessoa clica no botão "fechar" ou no botão "cancel"
                System.out.println("Programa encerrado");
                break;
            } else {
                opcao = Integer.parseInt(testeNull);
            }
            switch (opcao) {
                case 1: // cadastrar clientes
                    nomeCliente = JOptionPane.showInputDialog(null, "Informe o nome: ", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                    cpfCliente = JOptionPane.showInputDialog(null, "Informe o CPF: ", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                    idadeCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe sua idade: ", "Cadastro", JOptionPane.QUESTION_MESSAGE));
                    restricaoMedCliente = JOptionPane.showInputDialog(null, "Informe qual comorbidade você possui: ", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                    gerenciamento.CadastrarCliente(nomeCliente, cpfCliente, idadeCliente, restricaoMedCliente);
                    break;
                case 2: // cadastrar academias
                    nomeAcd = JOptionPane.showInputDialog(null, "Informe o nome da Academia: ", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                    n_contatoAcd = JOptionPane.showInputDialog(null, "Informe o numero para contato: ", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                    enderecoAcd = JOptionPane.showInputDialog(null, "Informe o endereço: ", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                    mensalidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Mensalidade da academia: ", "Cadastro", JOptionPane.QUESTION_MESSAGE));
                    num_personais = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe quantos personais trabalham na academia: ", "Cadastro", JOptionPane.QUESTION_MESSAGE));
                    atividadesOfertadas = JOptionPane.showInputDialog(null,"Quais atividades são ofertadas: ", "Cadastro", JOptionPane.QUESTION_MESSAGE);
                    gerenciamento.CadastrarAcademia(nomeAcd, n_contatoAcd, enderecoAcd, mensalidade, num_personais, atividadesOfertadas);
                    break;
                case 3: // buscar academia
                    busca = Integer.parseInt(JOptionPane.showInputDialog(null, "Buscar Academia: ", "Buscar", JOptionPane.QUESTION_MESSAGE));
                    academia = gerenciamento.buscarAcademia(busca);

                    if (academia != null) {
                        JOptionPane.showMessageDialog(null, "Academia encontrada!\n"+ academia.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Academia nao encontrada!");
                    }
                    break;
                case 4: // listar academias
                    academia = gerenciamento.buscarAcademia(100); // [ERRADO] tratamento de erro pra verificar se a lista de Academias está errada STONKSSS
                    if (academia != null) {
                        JOptionPane.showMessageDialog(null, "Lista com todas as Academias: \n" + gerenciamento.listarAcademias());
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhuma Academia Encontrada!");
                    }
                    break;
                case 5: // excluir academia
                    busca = Integer.parseInt(JOptionPane.showInputDialog(null, "Deseja excluir qual Academia? "));
                    academia = gerenciamento.buscarAcademia(busca);

                    if (academia != null) {
                        JOptionPane.showMessageDialog(null, "Informaçoes da Academia: \n" + academia.toString());
                        resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir essa Academia?");

                        if (resposta == 0) {
                            JOptionPane.showMessageDialog(null, "Academia excluida com sucesso!");
                            gerenciamento.excluirAcademia(busca);

                        } else {
                            JOptionPane.showMessageDialog(null, "Exclusao de Academia não concluida!");
                        }
                    }
                    break;
                case 6:  // avaliar academia
                    busca = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual academia deseja avaliar?"));
                    avali = Double.parseDouble(JOptionPane.showInputDialog(null, "Avaliacao: Vai de 0.0 ate 5.0"));
                    gerenciamento.avaliarAcademia(busca, avali);
                    JOptionPane.showMessageDialog(null, "Academia Avaliada com sucesso!");
                    break;
                case 7: // matricular cliente
                    idAcademia_matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual Academia voce pretende matricular? [informe o ID]", "Matricula", JOptionPane.QUESTION_MESSAGE));
                    academia = gerenciamento.buscarAcademia(idAcademia_matricula);

                    if (academia != null) {
                        // informações da academia
                        JOptionPane.showMessageDialog(null, "Informações da Academia:\n" + "Nome: " + academia.getNome() + "\nEndereco: " + academia.getEndereco() + "\nMensalidade: R$ " + academia.getMensalidade() + "\nAvaliacao: " + academia.getAvaliacao(), "Matricula", JOptionPane.QUESTION_MESSAGE);
                        // voce deseja prosseguir
                        resposta = JOptionPane.showConfirmDialog(null, "Voce deseja prosseguir?");

                        if (resposta == 0) {
                            JOptionPane.showMessageDialog(null, "Academia: " + academia.getNome() + "\nPreco: R$" + academia.getMensalidade(), "Matricula", JOptionPane.QUESTION_MESSAGE);
                            resposta = JOptionPane.showConfirmDialog(null, "Deseja se matricular?");

                            if (resposta == 0) {
                                JOptionPane.showMessageDialog(null, "Matricula realizada com sucesso!\n\nSeja bem-vindo(a) a Academia "+ academia.getNome(), "Matricula", JOptionPane.QUESTION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Matricula cancelada!", "Matricula", JOptionPane.QUESTION_MESSAGE);
                            }
                        }
                    }
                    break;
                case 8: // filtrar
                    opcaoFiltrar = Integer.parseInt(JOptionPane.showInputDialog(null, "Filtrar por:\n1 - Preco\n2 - Avaliacao", "Filtragem", JOptionPane.QUESTION_MESSAGE));
                    if (opcaoFiltrar == 1) {
                        // preço
                        opcaoFiltrarPreco = Integer.parseInt(JOptionPane.showInputDialog(null, "Filtragem por preco:\n1 - R$ 0.00 ate R$ 49.99\n2 - R$ 50.00 ate R$ 99.99\n3 - a partir de R$ 100.00", "Filtragem", JOptionPane.QUESTION_MESSAGE));
                        JOptionPane.showMessageDialog(null, "Academias na faixa de preco escolhido\n" + gerenciamento.listarAcademias_Preco(opcaoFiltrarPreco));
                    } else if (opcaoFiltrar == 2) {
                        opcaoFiltrarAvaliacao = Integer.parseInt(JOptionPane.showInputDialog(null, "Filtragem por avaliacao:\n1 Estrela\n2 Estrelas\n3 Estrelas\n4 Estrelas\n5 Estrelas", "Filtragem", JOptionPane.QUESTION_MESSAGE));
                        JOptionPane.showMessageDialog(null, "Academias na avaliacao escolhido\n" + gerenciamento.listarAcademias_Avaliacao(opcaoFiltrarAvaliacao));
                    }
                    break;
            }

        }

    }
}