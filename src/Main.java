import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArvoreBinaria arvore = new ArvoreBinaria(50);
    arvore.inserir(30);
    arvore.inserir(70);
    arvore.inserir(40);
    arvore.inserir(60);
    arvore.inserir(80);
    arvore.inserir(15);
    arvore.inserir(25);
    arvore.inserir(35);
    arvore.inserir(45);
    arvore.inserir(26);
    arvore.inserir(24);
    arvore.inserir(23);
//    arvore.inserir(sc.nextInt());
    arvore.visualizar();
    System.out.println(" ");
    arvore.removerNo1(50);
    arvore.visualizar();

    }
}