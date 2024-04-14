public class Conta {
    private int numero;
    private String nome;
    private long CPF;
    private float saldo;
    private int senha;

    public Conta(String nome, long CPF) {
        this.numero = gerarNumeroConta();
        this.nome = nome;
        this.CPF = CPF;
        this.saldo = 0;
    }

    public void depositar(float valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public boolean sacar(float valor, int senha) {
        if (this.senha == senha) {
            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                System.out.println("Saque realizado com sucesso!");
                return true;
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Senha incorreta.");
        }
        return false;
    }

    public float verificarSaldo(int senha) {
        if (this.senha == senha) {
            return saldo;
        } else {
            System.out.println("Senha incorreta.");
            return -1;
        }
    }

    public String getNome() {
        return nome;
    }

    public long getCPF() {
        return CPF;
    }

    public int getNumero() {
        return numero;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        if (String.valueOf(senha).length() == 6) {
            this.senha = senha;
        } else {
            System.out.println("A senha deve conter 6 digitos.");
        }
    }

    private int gerarNumeroConta() {
        return 100000 + (int) (Math.random() * 9000);
    }
}
