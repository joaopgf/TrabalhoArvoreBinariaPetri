public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria(int conteudo) {
        raiz = new No(conteudo);
    }

    public boolean estaVazia(){
        if(raiz == null){
            return true;
        } else {
            return false;
        }
    }

    public void inserir(int conteudo){
        No novoNo = new No(conteudo);
        if(estaVazia()){
            raiz = novoNo;
        } else{
            No aux = this.raiz;
            while(true){
                if(novoNo.getConteudo() > aux.getConteudo()){
                    if(aux.getDireito() == null){
                        aux.setDireito(novoNo);
                        return;
                    } else{
                        aux = aux.getDireito();
                    }
                }else{
                    if(aux.getEsquerdo() == null){
                        aux.setEsquerdo(novoNo);
                        return;
                    } else{
                        aux = aux.getEsquerdo();
                    }
                }
            }
        }
    }

    public void visualizar(){
        preOrdem(this.raiz);
    }

    public void preOrdem(No no){
        if(no == null){
            return;
        }
        System.out.println(no.getConteudo());
        preOrdem(no.getEsquerdo());
        preOrdem(no.getDireito());
    }

    public void emOrdem(No no){
        if(no == null){
            return;
        }
        emOrdem(no.getEsquerdo());
        System.out.println(no.getConteudo());
        emOrdem(no.getDireito());
    }

    public void posOrdem(No no){
        if(no == null){
            return;
        }
        posOrdem(no.getEsquerdo());
        posOrdem(no.getDireito());
        System.out.println(no.getConteudo());
    }

    public No buscarNoPai(int conteudoFilho, No noPai){
        noPai = this.raiz;
        while(noPai != null){
            if(noPai.getDireito() != null && noPai.getDireito().getConteudo() == conteudoFilho){
                return noPai;
            }else if(noPai.getEsquerdo()!= null && noPai.getEsquerdo().getConteudo() == conteudoFilho){
                return noPai;
            }


            if(conteudoFilho > noPai.getConteudo()){
                noPai = noPai.getDireito();
            } else{
                noPai = noPai.getEsquerdo();
            }

            if(noPai == null){
                return null;
            }
        }
        return null;
    }

    public No defineNoFilho(int conteudoFlho, No noPai){
        if (noPai.getDireito().getConteudo()  == conteudoFlho){
            return noPai.getDireito();
        }else{
            return noPai.getEsquerdo();
        }
    }

    public int verFilhos(No noFilho){

        if (noFilho.getDireito() == null && noFilho.getEsquerdo() == null){
            return 0;
        }
        if(noFilho.getEsquerdo() != null && noFilho.getDireito() != null){
            return 3;
        }
        if(noFilho.getEsquerdo() != null && noFilho.getDireito() == null){
            return 1;
        }
        return 2;

    }

    public void removerNo1(int conteudoFilho){
        No noFilho = null;
        No noPai = null;
        int qtdFilho = -1; //0 = 0 filho | 1 = filho a esquerda | 2 = filho a direita | 3 = filho a esquerda e direita
        if(estaVazia()){
            System.out.println("A árvore está vazia");
            return;
        } else{
            if(this.raiz.getConteudo() == conteudoFilho){
                noFilho = this.raiz;
                qtdFilho = verFilhos(this.raiz);
                if (qtdFilho == 0){
                    this.raiz = null;
                }
                removerNo2(noFilho, noPai, qtdFilho);
            } else {
                noPai = buscarNoPai(conteudoFilho, noPai);
                if(noPai == null){
                    System.out.println("O nó não existe na árvore!");
                    return;
                }
                noFilho = defineNoFilho(conteudoFilho, noPai);
                qtdFilho = verFilhos(noFilho);
                removerNo2(noFilho, noPai, qtdFilho);
            }
        }
    }

    public void removerNo2(No noFilho, No noPai, int qtdFilho){
        switch(qtdFilho){
            case 0:
            if (noPai.getDireito() == noFilho){
                noPai.setDireito(null);
            }else{
                noPai.setEsquerdo(null);
            }
            break;

            case 1:
                if (noFilho == this.raiz){
                        this.raiz = noFilho.getEsquerdo();
                } else{
                    if(noPai.getDireito() == noFilho){
                        noPai.setDireito(noFilho.getEsquerdo());
                    }else{
                        noPai.setEsquerdo(noFilho.getEsquerdo());
                    }
                }
                break;
                case 2:
                    if (noFilho == this.raiz){
                        this.raiz = noFilho.getDireito();
                    } else{
                        if(noPai.getDireito() == noFilho){
                            noPai.setDireito(noFilho.getDireito());
                        }else{
                            noPai.setEsquerdo(noFilho.getDireito());
                        }
                    }
                    break;
            case 3:
                No noPai2 = noFilho.getEsquerdo();
                No noFilho2 = null;
                if(noPai2.getDireito() == null){
                    noFilho2 = noPai2;
                    noPai2 = noFilho;
                }else{
                    while(true){
                        if(noPai2.getDireito().getDireito() == null){
                            noFilho2 = noPai2.getDireito();
                            break;
                        } else{
                            noPai2 = noPai2.getDireito();
                        }
                    }
                }
                if (noFilho == this.raiz){
                    this.raiz = noFilho2;
                } else{
                    if(noPai.getDireito() == noFilho){
                        noPai.setDireito(noFilho2);
                    } else{
                        noPai.setEsquerdo(noFilho2);
                    }
                }

                if(noPai2 == noFilho){
                    noFilho2.setDireito(noFilho.getDireito());
                }else{
                    if(noFilho2.getEsquerdo() != null){
                        noPai2.setDireito(noFilho2.getEsquerdo());
                    } else{
                        noPai2.setDireito(null);
                    }
                    noFilho2.setDireito(noFilho.getDireito());
                    noFilho2.setEsquerdo(noFilho.getEsquerdo());
                }
                break;
        }
    }
}
