package Application;

//Unifacisa - Centro Universitário

//Sistemas de Informação
//Disciplina..: Programar em Linguagem Orientada a Objetos Básica
//Período.....: 3º Trimestre 2023
//Professor...: Ruan Pierre de Oliveira
//Alunos.......:
//				Gustavo Tomio Magalhaes Kubo
//				Sérgio Magno Castor Pinheiro
//				Thiago Limeira de Alencar
//				Tiago Marques Tito

import java.util.ArrayList;
import java.util.Scanner;

import Entities.Pessoa;
import Entities.Produto;

public class Estoque {
	private static ArrayList<Pessoa> vendedores = new ArrayList<>();
	private static ArrayList<Produto> produtos = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			System.out.print("\n╔══════════════════════════════════════╗\n");
			System.out.print("║ Sistema de Estoque                   ║\n");
			System.out.print("╠══════════════════════════════════════╣\n");
			System.out.print("║ 1 - Cadastro Geral                   ║\n");
			System.out.print("║ 2 - Movimentação do Produto          ║\n");
			System.out.print("║ 3 - Relatórios                       ║\n");
			System.out.print("║ 0 - Sair                             ║\n");
			System.out.print("╚══════════════════════════════════════╝\n\n");
			System.out.print("Digite a opção desejada : ");
			int escolha = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer

			switch (escolha) {
			case 1:
				menuCadastroGeral();
				break;
			case 2:
				menuMovimentacaoProduto();
				break;
			case 3:
				menuRelatorios();
				break;
			case 0:
				System.out.println("Saindo do sistema. Até logo!");
				System.exit(0);
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private static void menuCadastroGeral() {
		do {
			System.out.print("\n╔══════════════════════════════════════╗\n");
			System.out.print("║ Cadastro Geral                       ║\n");
			System.out.print("╠══════════════════════════════════════╣\n");
			System.out.print("║ 1 - Cadastrar Vendedor               ║\n");
			System.out.print("║ 2 - Cadastrar Produto                ║\n");
			System.out.print("╚══════════════════════════════════════╝\n\n");
			System.out.print("Digite a opção desejada: ");

			int escolha = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer

			if (escolha == 1) {
				// Cadastrar vendedor
				System.out.print("Digite o nome do vendedor: ");
				String nome = scanner.nextLine();
				System.out.print("Digite o CPF do vendedor: ");
				String cpf = scanner.nextLine();

				// Verificar se o CPF já está cadastrado
				boolean cpfDuplicado = false;
				for (Pessoa pessoa : vendedores) {
					if (pessoa.getCpf().equals(cpf)) {
						cpfDuplicado = true;
						break;
					}
				}

				if (cpfDuplicado) {
					System.out.println("CPF já cadastrado. Não é possível cadastrar o mesmo CPF novamente.");
				} else {
					Pessoa vendedor = new Pessoa(nome, cpf);
					vendedores.add(vendedor);
					System.out.println("Vendedor cadastrado com sucesso!");
				}
			} else if (escolha == 2) {
				// Cadastrar produto
				System.out.print("Digite o nome do produto: ");
				String nome = scanner.nextLine();
				System.out.print("Digite o código do produto: ");
				int codigo = scanner.nextInt();

				// Verificar se o código de produto já está cadastrado
				boolean codigoDuplicado = false;
				for (Produto produto : produtos) {
					if (produto.getCodigo() == codigo) {
						codigoDuplicado = true;
						break;
					}
				}

				if (codigoDuplicado) {
					System.out.println(
							"Código de produto já cadastrado. Não é possível cadastrar o mesmo código novamente.");
				} else {
					System.out.print("Digite o preço de venda do produto: ");
					double precoVenda = scanner.nextDouble();
					scanner.nextLine(); // Limpa o buffer
					System.out.print("Deseja incluir quantidade no estoque? (S/N) ");
					String incluirEstoque = scanner.nextLine();

					Pessoa vendedor = null;
					System.out.print("Digite o CPF do vendedor: ");
					String cpf = scanner.nextLine();
					for (Pessoa pessoa : vendedores) {
						if (pessoa.getCpf().equals(cpf)) {
							vendedor = pessoa;
							break;
						}
					}

					if (vendedor == null) {
						System.out.println("Vendedor não encontrado.");
					} else {
						Produto produto = new Produto(nome, codigo, precoVenda, vendedor);

						if (incluirEstoque.equalsIgnoreCase("S")) {
							System.out.print("Digite a quantidade em estoque: ");
							int quantidadeEstoque = scanner.nextInt();
							produto.setEstoque(quantidadeEstoque);
						}

						produtos.add(produto);
						System.out.println("Produto cadastrado com sucesso!");
						System.out.print("Nome do Produto: " + produto.getNome() + " | Código do Produto: "
								+ produto.getCodigo() + " | Estoque do Produto: " + produto.getEstoque()
								+ " | Preço do Produto: " + produto.getPrecoVenda() + " | Nome do Vendedor: "
								+ vendedor.getNome() + " | CPF do Vendedor: " + vendedor.getCpf());
					}
				}
			} else {
				System.out.println("Opção inválida. Tente novamente.");
			}

			// Pergunta se deseja adicionar mais algum item
			System.out.print("\nDeseja cadastrar mais algum vendedor ou produto? (S/N) ");
		} while (scanner.nextLine().equalsIgnoreCase("S"));
	}

	private static void menuMovimentacaoProduto() {
		System.out.print("\n╔══════════════════════════════════════╗\n");
		System.out.print("║ Movimentação de Produtos             ║\n");
		System.out.print("╠══════════════════════════════════════╣\n");
		System.out.print("║ 1 - Vender Produto                   ║\n");
		System.out.print("║ 2 - Adicionar Produto                ║\n");
		System.out.print("║ 3 - Excluir Produto                  ║\n"); 
		System.out.print("╚══════════════════════════════════════╝\n\n");
		System.out.print("Digite a opção desejada : ");
		int escolha = scanner.nextInt();
		scanner.nextLine(); // Limpa o buffer

		if (escolha == 1) {
			System.out.print("Digite o código do produto que deseja vender:");
			int codigo = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer

			Produto produtoVenda = buscarProdutoPorCodigo(codigo);

			if (produtoVenda != null) {
				System.out.println("Produto encontrado: " + produtoVenda.getNome());
				System.out.print("Digite a quantidade a ser vendida:");
				int quantidadeVendida = scanner.nextInt();
				scanner.nextLine(); // Limpa o buffer

				if (quantidadeVendida <= produtoVenda.getEstoque()) {
					int novoEstoque = produtoVenda.getEstoque() - quantidadeVendida;
					produtoVenda.setEstoque(novoEstoque);
					System.out.println("Produto Vendido com Sucesso!");
					System.out.println("Nome do Produto vendido: " + produtoVenda.getNome() + " | Código do Produto: "
							+ produtoVenda.getCodigo() + " | Novo estoque do Produto: " + novoEstoque
							+ " | Valor atualizado do estoque: " + (novoEstoque * produtoVenda.getPrecoVenda())
							+ " | Nome do Vendedor: " + produtoVenda.getVendedor().getNome() + " | CPF do Vendedor: "
							+ produtoVenda.getVendedor().getCpf());

				} else {
					System.out.println("Quantidade insuficiente em estoque.");
				}
			} else {
				System.out.println("Produto não encontrado.");
			}

		} else if (escolha == 2) {

			System.out.println("Digite o código do produto que deseja adicionar ao estoque:");
			int codigo = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer

			Produto produtoReposicao = buscarProdutoPorCodigo(codigo);

			if (produtoReposicao != null) {
				System.out.println("Produto encontrado: " + produtoReposicao.getNome());
				System.out.print("Digite a quantidade a ser adicionada ao estoque:");
				int quantidadeReposicao = scanner.nextInt();
				scanner.nextLine(); // Limpa o buffer

				int novoEstoque = produtoReposicao.getEstoque() + quantidadeReposicao;
				produtoReposicao.setEstoque(novoEstoque);
				System.out.println("Produto Adicionado ao Estoque com Sucesso!");
				System.out.println("Nome do Produto reposto: " + produtoReposicao.getNome() + " | Código do Produto: "
						+ produtoReposicao.getCodigo() + " | Novo estoque do Produto: " + novoEstoque
						+ " | Valor atualizado do estoque: " + (novoEstoque * produtoReposicao.getPrecoVenda())
						+ " | Nome do Vendedor: " + produtoReposicao.getVendedor().getNome() + " | CPF do Vendedor: "
						+ produtoReposicao.getVendedor().getCpf());

			} else {
				System.out.println("Produto não encontrado.");
			}

		} else if (escolha == 3) {
			excluirProduto();
		} else {
			System.out.println("Opção inválida. Tente novamente.");
		}
	}

	private static void excluirProduto() {
		System.out.print("Digite o código do produto que deseja excluir: ");
		int codigo = scanner.nextInt();
		scanner.nextLine(); // Limpa o buffer

		Produto produtoParaExcluir = buscarProdutoPorCodigo(codigo);

		if (produtoParaExcluir != null) {
			if (produtoParaExcluir.getEstoque() == 0) {
				System.out.print("O produto está sem estoque. Deseja realmente excluir? (S/N): ");
				String confirmacao = scanner.nextLine();
				if (confirmacao.equalsIgnoreCase("S")) {
					produtos.remove(produtoParaExcluir);
					System.out.println("Produto excluído com sucesso!");
				} else {
					System.out.println("Exclusão cancelada.");
				}
			} else {
				System.out.println("O produto não pode ser excluído porque ainda possui estoque.");
			}
		} else {
			System.out.println("Produto não encontrado.");
		}
	}

	private static Produto buscarProdutoPorCodigo(int codigo) {
		for (Produto produto : produtos) {
			if (produto.getCodigo() == codigo) {
				return produto;
			}
		}
		return null;
	}

	private static void menuRelatorios() {
		System.out.print("\n╔═════════════════════════════════════════════╗\n");
		System.out.print("║ Menu de Relatórios/Listas                    ║\n");
		System.out.print("╠══════════════════════════════════════════════╣\n");
		System.out.print("║ 1 - Todos os Vendedores                      ║\n");
		System.out.print("║ 2 - Todos os Produtos                        ║\n");
		System.out.print("║ 3 - Produtos por. Codigo                     ║\n");
		System.out.print("║ 4 - Produtos por Nome                        ║\n");
		System.out.print("║ 5 - Produtos Cadastrados p/ Vendedor         ║\n");
		System.out.print("║ 6 - Quantidade de Produtos p/Vendedor        ║\n");
		System.out.print("║ 7 - Soma dos Produtos Cadastrados p/Vendedor ║\n");
		System.out.print("╚══════════════════════════════════════════════╝\n\n");
		System.out.print("Digite a opção desejada : ");
		int escolha = scanner.nextInt();
		scanner.nextLine(); // Limpa o buffer

		switch (escolha) {
		case 1:
			listarVendedores();
			break;
		case 2:
			listarTodosProdutos();
			break;
		case 3:
			listarPorCodigo();
			break;
		case 4:
			listarPorNome();
			break;
		case 5:
			listarProdutosPorVendedor();
			break;
		case 6:
			listarQuantidadeProdutosPorVendedor();
			break;
		case 7:
			listarSomaProdutosPorVendedor();
			break;
		default:
			System.out.println("Opção inválida. Tente novamente.");
		}
	}

	private static void listarVendedores() {
		System.out.println("Nome e CPF de todos os vendedores:");
		for (Pessoa vendedor : vendedores) {
			System.out.println("Nome: " + vendedor.getNome() + " | CPF: " + vendedor.getCpf());
		}
	}

	private static void listarTodosProdutos() {
		if (produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado no sistema.");
		} else {
			int sequencial = 1;
			for (Produto produto : produtos) {
				System.out.println(sequencial + ") Nome do Produto: " + produto.getNome() + " (Cód.: "
						+ produto.getCodigo() + " | Estoque: " + produto.getEstoque() + " | Preço Venda: "
						+ produto.getPrecoVenda() + " | Nome do Vendedor: " + produto.getVendedor().getNome()
						+ " | CPF do Vendedor: " + produto.getVendedor().getCpf() + " | Valor do Estoque: "
						+ (produto.getEstoque() * produto.getPrecoVenda()) + ")");
				sequencial++;
			}
		}
	}

	private static void listarPorCodigo() {
		System.out.println("Digite o código do produto:");
		int codigo = scanner.nextInt();
		scanner.nextLine(); // Limpa o buffer

		Produto produto = buscarProdutoPorCodigo(codigo);

		if (produto != null) {
			System.out.println("Produto encontrado: " + "Nome do Produto: " + produto.getNome() + " | "
					+ "Código do Produto: " + produto.getCodigo() + " | " + "Estoque: " + produto.getEstoque() + " | "
					+ "Preço: " + produto.getPrecoVenda() + " | " + "Nome do Vendedor: "
					+ produto.getVendedor().getNome() + " | " + "CPF do Vendedor: " + produto.getVendedor().getCpf());

		} else {
			System.out.println("Produto não encontrado.");
		}
	}

	private static void listarPorNome() {
		System.out.println("Digite o nome do produto:");
		String nome = scanner.nextLine();

		boolean encontrado = false;
		int sequencial = 1;

		for (Produto produto : produtos) {
			if (produto.getNome().equalsIgnoreCase(nome)) {
				System.out.println(sequencial + ") " + "Nome do Produto: " + produto.getNome() + " (Cód.: "
						+ produto.getCodigo() + " | Estoque: " + produto.getEstoque() + " | Preço: "
						+ produto.getPrecoVenda() + " | Nome do Vendedor: " + produto.getVendedor().getNome()
						+ " | CPF do Vendedor: " + produto.getVendedor().getCpf() + ")");
				sequencial++;
				encontrado = true;
			}
		}

		if (!encontrado) {
			System.out.println("Nenhum produto com o nome especificado encontrado.");
		}
	}

	private static void listarProdutosPorVendedor() {
		System.out.print("Digite o CPF do vendedor:");
		String cpf = scanner.nextLine();

		boolean encontrado = false;
		int sequencial = 1;

		for (Produto produto : produtos) {
			if (produto.getVendedor().getCpf().equals(cpf)) {
				System.out.println(sequencial + ") " + "Nome do Produto: " + produto.getNome() + " (Cód.: "
						+ produto.getCodigo() + " | Estoque: " + produto.getEstoque() + " | Preço: "
						+ produto.getPrecoVenda() + " | Nome do Vendedor: " + produto.getVendedor().getNome()
						+ " | CPF do Vendedor: " + produto.getVendedor().getCpf() + ")");
				sequencial++;
				encontrado = true;
			}
		}

		if (!encontrado) {
			System.out.println("Nenhum produto cadastrado por esse vendedor encontrado.");
		}
	}

	private static void listarQuantidadeProdutosPorVendedor() {
		System.out.print("Digite o CPF do vendedor:");
		String cpf = scanner.nextLine();

		int quantidade = 0;

		for (Produto produto : produtos) {
			if (produto.getVendedor().getCpf().equals(cpf)) {
				quantidade++;
			}
		}

		System.out.println("Quantidade de produtos cadastrados por esse vendedor: " + quantidade);
	}

	private static void listarSomaProdutosPorVendedor() {
		System.out.print("Digite o CPF do vendedor:");
		String cpf = scanner.nextLine();

		double soma = 0;

		for (Produto produto : produtos) {
			if (produto.getVendedor().getCpf().equals(cpf)) {
				soma += produto.getPrecoVenda();
			}
		}

		System.out.println("Soma dos preços dos produtos cadastrados por esse vendedor: " + soma);
	}
}
