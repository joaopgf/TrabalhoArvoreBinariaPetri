public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria(int conteudo) {// cria árvore binária
        raiz = new No(conteudo);// raiz é criada a partir de um novo nó a partir de um conteúdo
    }

    public boolean estaVazia(){// ver se a árvore está vazia
        if(raiz == null){// se a raiz é nula, logo a árvore é vazia
            return true;
        } else {
            return false;
        }
    }

    public void inserir(int conteudo){// insere um novo nó
        No novoNo = new No(conteudo);// nó criado a partir do conteúdo
        if(estaVazia()){// se a árvore está vazia, o novo nó vira a raiz
            raiz = novoNo;
        } else{
            No aux = this.raiz;// cria auxiliar para ajudar no método
            while(true){
                if(novoNo.getConteudo() > aux.getConteudo()){// se o novo nó é maior que o aux
                    if(aux.getDireito() == null){// se aux não tem filho a direita, novo nó vira o seu novo filho a direita
                        aux.setDireito(novoNo);// define filho do aux
                        return;
                    } else{
                        aux = aux.getDireito();// aux "pula" para direita para continuar a busca do lugar certo do nó
                    }
                }else{// faz o oposto do código acima
                    if(aux.getEsquerdo() == null){// se o novo nó for menor que o aux, ele vira o seu novo filho a esquerda
                        aux.setEsquerdo(novoNo);// define filho do aux
                        return;
                    } else{
                        aux = aux.getEsquerdo();// aux "pula" para a esquerda
                    }
                }
            }
        }
    }

    public void visualizar(){// método para visualizar a raiz
        preOrdem(this.raiz);
    }

    public void preOrdem(No no){// visualização de pré-ordem
        if(no == null){
            return;
        }
        System.out.println(no.getConteudo());// printa o nó atual, depois vai para o esquerdo e depois direito de forma recursiva
        preOrdem(no.getEsquerdo());
        preOrdem(no.getDireito());
    }

    public void emOrdem(No no){// visualização de em-ordem
        if(no == null){
            return;
        }
        emOrdem(no.getEsquerdo());// vai para o nó esquerdo de forma recursiva, depois printa o nó atual e depois vai para o direito
        System.out.println(no.getConteudo());
        emOrdem(no.getDireito());
    }

    public void posOrdem(No no){// visualização de pós-ordem
        if(no == null){
            return;
        }
        posOrdem(no.getEsquerdo());// vai para o esquerdo, depois nó direito e depois printa, mostra primeiro as folhas
        posOrdem(no.getDireito());
        System.out.println(no.getConteudo());
    }

    public No buscarNoPai(int conteudoFilho, No noPai){// método auxiliar para remoção de nó, procura o pai do nó a ser removido
        noPai = this.raiz;// nó pai vira um nó auxiliar começando pela raiz e percorre a árvore
        while(noPai != null){
            if(noPai.getDireito() != null && noPai.getDireito().getConteudo() == conteudoFilho){// verifica se nó pai tem um apontamento não nulo e verifica se o filho direito é o nó a ser removido
                return noPai;// retorna o pai do nó a ser removido
            }else if(noPai.getEsquerdo()!= null && noPai.getEsquerdo().getConteudo() == conteudoFilho){// verifica se nó pai tem um apontamento não nulo e verifica se o filho esquerdo é o nó a ser removido
                return noPai;// retorna o pai do nó a ser removido
            }

            if(conteudoFilho > noPai.getConteudo()){// se o nó filho for maior que o atual pai
                noPai = noPai.getDireito();// nó pai vai para direita
            } else{
                noPai = noPai.getEsquerdo();// se não, nó pai vai para esquerda
            }

            if(noPai == null){// se pai é nulo = nó a ser removido não existe na árvore
                return null;
            }
        }
        return null;
    }

    public No defineNoFilho(int conteudoFlho, No noPai){// método auxiliar para remoção de nó, agora busca o filho, que é o nó a ser removido
        if (noPai.getDireito().getConteudo()  == conteudoFlho){// se get direito do pai for o filho, retorna pai.getdireito
            return noPai.getDireito();
        }else{// se get esquerdo do pai for o filho, retorna pai.getesquerdo
            return noPai.getEsquerdo();
        }
    }

    public int verFilhos(No noFilho){// método auxiliar para remoção de nós, ele contabiliza os filhos do nó a ser removido

        if (noFilho.getDireito() == null && noFilho.getEsquerdo() == null){// se nó filho não tem filhos, retorna 0
            return 0;
        }
        if(noFilho.getEsquerdo() != null && noFilho.getDireito() != null){// se filho tem dois filhos, retorna 3
            return 3;
        }
        if(noFilho.getEsquerdo() != null && noFilho.getDireito() == null){// se filho só tem filho a esquerda, volta 1
            return 1;
        }
        return 2;// se filho só tem filho a direita, volta 2

    }

    public void removerNo1(int conteudoFilho){// método para remover nó, o usuário só precisa inserir o conteúdo do nó a ser removido
        No noFilho = null;// no filho é o nó a ser removido
        No noPai = null;// nó pai é o pai do nó a ser removido
        int qtdFilho = -1; // variável para quardar qtd de filho do nó filho
        if(estaVazia()){// verifica se a árvore está vazia
            System.out.println("A árvore está vazia");// sout caso árvore esteja vazia
            return;
        } else{// se árvore não estiver vazia
            if(this.raiz.getConteudo() == conteudoFilho){// se o filho for a raiz
                noFilho = this.raiz;// filho vira a raiz
                qtdFilho = verFilhos(this.raiz);// olha qtd de filhos do filho
                removerNo2(noFilho, noPai, qtdFilho);// chama o método de remoção de nó com base nos filhos do filho
            } else {// caso o filho não seja a raiz
                noPai = buscarNoPai(conteudoFilho, noPai);// busca o nópai
                if(noPai == null){// se nó pai é nulo, nó filho não existe
                    System.out.println("O nó não existe na árvore!");
                    return;
                }// se o código chegou aqui, significa que o nó pai e filho existem
                noFilho = defineNoFilho(conteudoFilho, noPai);// define o nó filho
                qtdFilho = verFilhos(noFilho);// olha a qtd de filhos do filho
                removerNo2(noFilho, noPai, qtdFilho);// chama o método de remoção de nó com base nos filhos do filho
            }
        }
    }

    public void removerNo2(No noFilho, No noPai, int qtdFilho){// método auxiliar para remoção de nós, remove nó com base nos filhos
        switch(qtdFilho){// switch case com base nos filhos
            case 0:// se filho não tem filhos, ou seja, é um nó folha
                if (noFilho == this.raiz){// se o filho for a raiz e não tem filhos
                    this.raiz = null;// raiz = mull
                    return;
                }else {
                    if (noPai.getDireito() == noFilho){// se no pai.getdireito for o filho
                        noPai.setDireito(null);// apontamento a direita fica nulo
                    }else{// se no pai.getesquerdo for o filho
                        noPai.setEsquerdo(null);// apontamento a esquerda fica nulo
                    }
                }

            break;

            case 1:// se o filho tiver apenas um filho a esquerda
                if (noFilho == this.raiz){// se o filho for a raiz
                        this.raiz = noFilho.getEsquerdo();// raiz vira o filho a esquerda
                } else{// se filho não for a raiz
                    if(noPai.getDireito() == noFilho){// se nó pai.getdireito for o filho
                        noPai.setDireito(noFilho.getEsquerdo());// apontamento do nó pai a direita aponta para filho a esquerda do filho
                    }else{// apontamento do nó pai a esquerda aponta para filho a esquerda do filho
                        noPai.setEsquerdo(noFilho.getEsquerdo());
                    }
                }
                break;
                case 2:// se o filho tiver apenas um filho a direita
                    if (noFilho == this.raiz){// se o filho for a raiz
                        this.raiz = noFilho.getDireito();// raiz vira o filho a direita
                    } else{
                        if(noPai.getDireito() == noFilho){// se nó pai.getdireito for o filho
                            noPai.setDireito(noFilho.getDireito());// apontamento do nó pai a direita aponta para filho a direita do filho
                        }else{// se nó pai.getesquerdo for o filho
                            noPai.setEsquerdo(noFilho.getDireito());// apontamento do nó pai a esquerda aponta para filho a direita do filho
                        }
                    }
                    break;
            case 3:// se o filho tiver dois filhos
                No noPai2 = noFilho.getEsquerdo();// noPai2 vira o filho a esquerda, já que o código procura o maior nó na sub-árvore esquerda
                No noFilho2 = null;//noFilho2 será o nó que substituirá o nó a ser removido
                if(noPai2.getDireito() == null){// caso o primeiro filho a esquerda seja o maior
                    noFilho2 = noPai2;// noFilho2 vira o filho a esquerda
                    noPai2 = noFilho;// noPai2 vira o filho a ser removido
                }else{// caso o primeiro filho a esquerda não seja o maior
                    while(true){// loop para percorrer a sub-árvore esquerda
                        if(noPai2.getDireito().getDireito() == null){// se noPai2.getdireito.getdireito for nulo
                            noFilho2 = noPai2.getDireito();// o filo a direita do pai2 é o maior dos menores, ou seja, o maior da sub-árvore esquerda
                            break;
                        } else{// noPai2 "anda" para a direita em busca do maior nó do lado esquerdo
                            noPai2 = noPai2.getDireito();
                        }
                    }
                }
                if (noFilho == this.raiz){// se o filho for a raiz
                    this.raiz = noFilho2;// a raiz vira o filho2, que substituirá o nó a ser removido
                } else{// se nóFilho não for a raiz
                    if(noPai.getDireito() == noFilho){// se o nó a ser removido esteja a direita do seu pai
                        noPai.setDireito(noFilho2);// apontamento da direita do nó pai aponta para o filho2
                    } else{// se o nó a ser removido esteja a esquerda do seu pai
                        noPai.setEsquerdo(noFilho2);// apontamento da esquerda do nó pai aponta para o filho2
                    }
                }

                if(noPai2 == noFilho){// se nopai2 for igual ao filho, ou seja, caso o filho2 seja o filho a esquerda do nó a ser removido
                    noFilho2.setDireito(noFilho.getDireito());// o apontamento do filho2 a direita aponta para o filho a direita do filho
                }else{
                    if(noFilho2.getEsquerdo() != null){// se o filho2 tenha filho a esquerda
                        noPai2.setDireito(noFilho2.getEsquerdo());// o apontamento da direita do pai2 aponta para o filho a esquerda do filho2
                    } else{// caso filho2 não tenha filho a esquerda
                        noPai2.setDireito(null);// o apontamento da direita do pai do filho2 aponta para nulo
                    }
                    noFilho2.setDireito(noFilho.getDireito());// nó filho2 copia apontamento da direita do nó a ser removido
                    noFilho2.setEsquerdo(noFilho.getEsquerdo());// nó filho2 copia apontamento da esquerda do nó a ser removido
                }
                break;
        }
    }
}
