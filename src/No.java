public class No {
    private int conteudo;
    private No esquerdo;
    private No direito;

    public No(int conteudo) {
        this.conteudo = conteudo;
        this.esquerdo = null;
        this.direito = null;
    }

    public int getConteudo() {
        return conteudo;
    }

    public void setConteudo(int conteudo) {
        this.conteudo = conteudo;
    }

    public No getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(No esquerdo) {
        this.esquerdo = esquerdo;
    }

    public No getDireito() {
        return direito;
    }

    public void setDireito(No direito) {
        this.direito = direito;
    }
}
