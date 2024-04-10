import java.util.ArrayList;

class Banco {
    private String nome;
    private int numAgencia;
    private int senhaGerente;
    private ArrayList<Cliente> clientes;

    public Banco(String nome) {
        this.nome = nome;
        this.numAgencia = gerarNumeroAgencia();
        this.clientes = new ArrayList<>();
    }

    public boolean cadastrarCliente(Cliente cliente) {
        return clientes.add(cliente);
    }

    public boolean removerCliente(long CPF) {
        for (Cliente cliente : clientes) {
            if (cliente.getCPF() == CPF) {
                clientes.remove(cliente);
                return true;
            }
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public int getNumAgencia() {
        return numAgencia;
    }

    public int getSenhaGerente() {
        return senhaGerente;
    }

    public void setSenhaGerente(int senhaGerente) {
        if (String.valueOf(senhaGerente).length() == 6) {
            this.senhaGerente = senhaGerente;
        } else {
            System.out.println("A senha do gerente deve conter 6 digitos.");
        }
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void gerarRelatorio() {
        float totalSaldo = 0;
        System.out.println("\nRelatório do Banco " + nome + ":");
        for (Cliente cliente : clientes) {
            if (cliente.getConta() != null) { // Verifica se a conta do cliente não é nula
                System.out.println("Cliente: " + cliente.getNome());
                float saldo = cliente.getConta().verificarSaldo(cliente.getConta().getSenha());
                System.out.println("Saldo: " + saldo);
                totalSaldo += saldo;
            } else {
                System.out.println("Cliente " + cliente.getNome() + " não possui uma conta associada.");
            }
        }
        System.out.println("Total de clientes: " + clientes.size());
        System.out.println("Total de dinheiro no banco: " + totalSaldo);
        System.out.println();
    }

    private int gerarNumeroAgencia() {
        return 1000 + (int) (Math.random() * 9000);
    }
}
