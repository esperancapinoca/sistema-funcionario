package model.funcionario;

public class Funcionario implements Comparable<Funcionario> {
    private int codigo;
    private String nome;
    private int contato;
    private String departamento;
    private char genero;
    private char estadoCivil;
    private int diasTrabalhados;
    private double salarioDiario;
    
    public Funcionario(int codigo, String nome, int contato, String departamento, 
                      char genero, char estadoCivil, int diasTrabalhados, double salarioDiario) {
        this.codigo = codigo;
        this.nome = nome;
        this.contato = contato;
        this.departamento = departamento;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.diasTrabalhados = diasTrabalhados;
        this.salarioDiario = salarioDiario;
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    
    public int getContato() { return contato; }
    public void setContato(int contato) { this.contato = contato; }
    
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    
    public int getDiasTrabalhados() { return diasTrabalhados; }
    public void setDiasTrabalhados(int diasTrabalhados) { this.diasTrabalhados = diasTrabalhados; }
    
    public double getSalarioDiario() { return salarioDiario; }
    public void setSalarioDiario(double salarioDiario) { this.salarioDiario = salarioDiario; }
    
    public char getGenero() { return genero; }
    public void setGenero(char genero) { this.genero = genero; }
    
    public char getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(char estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getGeneroDescricao() {
        if(genero == 'M' || genero == 'm') {
            return "Masculino";
        } 
        if (genero == 'F' || genero == 'f') {
            return "Feminino";
        }
        return "Inválido";
    }
    
    public String getEstadoCivilDescricao() {
        switch (estadoCivil) {
            case 'C', 'c': return "Casado";
            case 'S', 's': return "Solteiro";
            case 'D', 'd': return "Divorciado";
            case 'V', 'v': return "Viúvo";
            default: return "Estado civil inválido";
        }
    }
    
    public double calcularSalario() {
        return diasTrabalhados * salarioDiario;
    }
    
    public int compareTo(Funcionario outro) {
        return this.nome.compareTo(outro.getNome());
    }
    
    @Override
    public String toString() {
        return "\n--------------------------------------------------------------------------------------"+
                "\nNome: " + nome + " | Código: " + codigo + " | Salário Mensal: "+ calcularSalario() +
                "\nGênero: " + getGeneroDescricao() + " | Estado Civil: " + getEstadoCivilDescricao() +
                "\nContato: " + contato + " | Departamento: " + departamento +
                "\nDias Trabalhados: " + diasTrabalhados + " | Salário Diário: " + salarioDiario;
    }
}