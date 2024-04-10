public class Cliente {
    private String nome;
    private long CPF;
    private Conta conta;

    public Cliente(String nome, long CPF) {
        this.nome = nome;
        this.CPF = CPF;
        this.conta = new Conta(nome, CPF);
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public long getCPF() {
        return CPF;
    }

    public Conta getConta() {
        return conta;
    }
}
