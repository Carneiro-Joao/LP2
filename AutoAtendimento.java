import java.util.Scanner;

public class AutoAtendimento {
  private Banco banco;

  public AutoAtendimento(Banco banco) {
    this.banco = banco;
  }

  public static void main(String[] args) {
    Banco banco = new Banco("UFMA Bank");
    AutoAtendimento autoAtendimento = new AutoAtendimento(banco);
    autoAtendimento.menuPrincipal();
  }

  public void menuPrincipal() {
    Scanner scanner = new Scanner(System.in);
    int opcao;
    do {
      System.out.println("------ Menu Principal ------ \n");
      System.out.println("Banco: " + banco.getNome());
      System.out.println("Agência: " + banco.getNumAgencia());
      System.out.println();
      System.out.println("1. Login Cliente");
      System.out.println("2. Login Gerente");
      System.out.println("3. Sair");
      System.out.print("Escolha uma opção: ");
      opcao = scanner.nextInt();
      switch (opcao) {
        case 1:
          loginCliente();
          break;
        case 2:
          loginGerente();
          break;
        case 3:
          System.out.println("\nSaindo... \n");
          break;
        default:
          System.out.println("\nOpção inválida. Tente novamente. \n");
      }
    } while (opcao != 3);
  }

  public void loginCliente() {
    Scanner scanner = new Scanner(System.in);
    System.out.println();
    System.out.print("Digite o seu CPF: ");
    long cpf = scanner.nextLong();

    Cliente cliente = buscarCliente(cpf);
    if (cliente != null) {
      Conta conta = cliente.getConta();
      if (conta != null) {
        // Verifica se a conta já possui uma senha cadastrada
        if (conta.getSenha() == 0) {
          System.out.println("\nVocê não possui uma senha cadastrada. Vamos criar uma para você.");
          System.out.print("Digite uma nova senha (6 dígitos): ");
          int novaSenha = scanner.nextInt();
          conta.setSenha(novaSenha); // Define a nova senha para a conta do cliente
          System.out.println("\nSenha cadastrada com sucesso. Realize o login novamente. \n");
        } else {
          // O cliente possui uma senha cadastrada, solicita a senha e realiza o login
          System.out.print("\nDigite a senha: ");
          int senha = scanner.nextInt();
          if (conta.getSenha() == senha) {
            menuCliente(cliente);
          } else {
            System.out.println("Senha incorreta. Tente novamente. \n");
          }
        }
      } else {
        System.out.println("\nConta do cliente não encontrada. Entre em contato com o suporte. \n");
      }
    } else {
      System.out.println("\nCliente não encontrado. Verifique o CPF e tente novamente. \n");
    }
  }

  public void loginGerente() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nDigite o número da agência: ");
    int numAgencia = scanner.nextInt();

    // Verifica se a senha do gerente já foi cadastrada
    if (banco.getSenhaGerente() == 0) {
      System.out.println("\nO gerente ainda não possui uma senha cadastrada.");
      System.out.print("Por favor, crie uma senha de 6 dígitos: ");
      int novaSenha = scanner.nextInt();

      // Cria a senha do gerente
      banco.setSenhaGerente(novaSenha);
      System.out.println("\nSenha do gerente cadastrada com sucesso. Por favor, faça o login novamente. \n");
    } else {
      System.out.print("Digite a senha do gerente: ");
      int senha = scanner.nextInt();
      System.out.println();

      // Implementar lógica de login para o gerente
      if (banco.getNumAgencia() == numAgencia && banco.getSenhaGerente() == senha) {
        menuGerente();
      } else {
        System.out.println("Login de gerente falhou. Verifique os dados e tente novamente.");
      }
    }
  }

  private Cliente buscarCliente(long cpf) {
    for (Cliente cliente : banco.getClientes()) {
      System.out.println("CPF do cliente: " + cliente.getCPF()); // Adicione esta linha para depurar
      if (cliente.getCPF() == cpf) {
        return cliente;
      }
    }
    return null; // Retorna null se o cliente não for encontrado
  }

  private void menuCliente(Cliente cliente) {
    Scanner scanner = new Scanner(System.in);
    int opcao;
    do {
      System.out.println("\n------ Menu Cliente ------ \n");
      System.out.println("Bem vindo " + cliente.getNome());
      System.out.println("Banco: " + banco.getNome());
      System.out.println("Agência: " + banco.getNumAgencia());
      System.out.println("Número da conta: " + cliente.getConta().getNumero());
      System.out.println();
      System.out.println("1. Sacar");
      System.out.println("2. Depositar");
      System.out.println("3. Verificar Saldo");
      System.out.println("4. Voltar ao Menu Principal");
      System.out.print("Escolha uma opção: ");
      opcao = scanner.nextInt();
      switch (opcao) {
        case 1:
          System.out.print("\nDigite o valor que deseja sacar: ");
          float valorSaque = scanner.nextFloat();
          System.out.print("Digite a senha da conta: ");
          int senhaSaque = scanner.nextInt();
          System.out.println();
          cliente.getConta().sacar(valorSaque, senhaSaque);
          // Implementar lógica para o cliente sacar
          break;
        case 2:
          System.out.print("\nDigite o valor que deseja depositar: ");
          float valorDeposito = scanner.nextFloat();
          System.out.println();
          cliente.getConta().depositar(valorDeposito);
          // Implementar lógica para o cliente depositar
          break;
        case 3:
          System.out.print("\nDigite a senha da conta: ");
          int senhaSaldo = scanner.nextInt();
          float saldo = cliente.getConta().verificarSaldo(senhaSaldo);
          if (saldo >= 0) {
            System.out.println("\nSaldo atual: " + saldo);
          } else {
            System.out.println("\nFalha ao verificar saldo. Senha incorreta.");
          }
          // Implementar lógica para o cliente verificar saldo
          break;
        case 4:
          System.out.println("\nVoltando ao Menu Principal... \n");
          break;
        default:
          System.out.println("\nOpção inválida. Tente novamente. \n");
      }
    } while (opcao != 4);
  }

  private void menuGerente() {
    Scanner scanner = new Scanner(System.in);
    int opcao;
    do {
      System.out.println("------ Menu Gerente ------\n");
      System.out.println("Banco: " + banco.getNome());
      System.out.println("Agência: " + banco.getNumAgencia());
      System.out.println();
      System.out.println("1. Cadastrar Cliente");
      System.out.println("2. Excluir Cliente");
      System.out.println("3. Gerar Relatório");
      System.out.println("4. Voltar ao Menu Principal");
      System.out.print("Escolha uma opção: ");
      opcao = scanner.nextInt();
      switch (opcao) {
        case 1:
          cadastrarNovoCliente();
          // Implementar lógica para o gerente cadastrar um novo cliente
          break;
        case 2:
          excluirCliente();
          // Implementar lógica para o gerente excluir um cliente
          break;
        case 3:
          gerarRelatorio();
          // Implementar lógica para o gerente gerar um relatório
          break;
        case 4:
          System.out.println("\nVoltando ao Menu Principal... \n");
          break;
        default:
          System.out.println("\nOpção inválida. Tente novamente. \n");
      }
    } while (opcao != 4);
  }

  private void cadastrarNovoCliente() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nDigite o nome do novo cliente: ");
    String nome = scanner.nextLine();
    System.out.print("Digite o CPF do novo cliente: ");
    long cpf = scanner.nextLong();
    System.out.println();
    Cliente novoCliente = new Cliente(nome, cpf);
    banco.cadastrarCliente(novoCliente);
  }

  private void excluirCliente() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nDigite o CPF do cliente a ser excluído: ");
    long cpf = scanner.nextLong();

    boolean clienteRemovido = banco.removerCliente(cpf);
    if (clienteRemovido) {
      System.out.println("Cliente removido com sucesso! \n");
    } else {
      System.out.println("Cliente não encontrado. Verifique o CPF e tente novamente. \n");
    }
  }

  private void gerarRelatorio() {
    banco.gerarRelatorio();
  }
}
