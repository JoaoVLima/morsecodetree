package structures;

import java.util.Scanner;

public class ArvoreBinaria {
    Node inicio;

    public ArvoreBinaria() {
        this.inicio = null;
    }

    boolean is_vazio() {
        return (this.inicio == null);
    }

    void insere(String info){
        this.inicio = insere_recursao(this.inicio, info);
    }

    Node insere_recursao(Node no, String info){
        if(no == null){
            no = new Node(info);
            return no;
        }
        if (info.compareTo(no.info) < 0) {
            no.esquerda = insere_recursao(no.esquerda, info);
        } else {
            no.direita = insere_recursao(no.direita, info);
        }
        return no;
    }

    public void remove(String info) {
        remove_recursao(this.inicio, info);
    }

    private Node remove_recursao(Node no, String info) {
        if (no == null)
            return no;

        if (info.compareTo(no.info) < 0)
            no.esquerda = remove_recursao(no.esquerda, info);
        else if (info.compareTo(no.info) > 0)
            no.direita = remove_recursao(no.direita, info);
        else {
            // Caso 1: Nó sem filhos (folha)
            if (no.esquerda == null && no.direita == null)
                return null;

            // Caso 2: Nó com um filho
            if (no.esquerda == null)
                return no.direita;
            else if (no.direita == null)
                return no.esquerda;

            // Caso 3: Nó com dois filhos
            no.info = min_info(no.direita);
            no.direita = remove_recursao(no.direita, no.info);
        }
        return no;
    }

    private String min_info(Node no) {
        String min = no.info;
        while (no.esquerda != null) {
            min = no.esquerda.info;
            no = no.esquerda;
        }
        return min;
    }

    int tamanho(){
        return tamanho_recursao(this.inicio);
    }

    public void imprime() {
        this.print_horizontal(this.inicio, 0);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1 - Inserir");
            System.out.println("2 - Excluir Valor");
            System.out.println("0 - Parar");

            int opcao = scanner.nextInt();
            if (opcao == 0) {
                this.imprime();
                break;
            } else if (opcao == 1) {
                System.out.print("Digite o valor para inserir: ");
                String info = scanner.next();
                this.insere(info);
                this.imprime();
            } else if (opcao == 2) {
                System.out.print("Digite o valor do nó para excluir: ");
                String info = scanner.next();
                this.remove(info);
                this.imprime();
            } else {
                System.out.println("Opção inválida");
            }
        }
    }

    private int tamanho_recursao(Node no) {
        if (no == null)
            return 0;
        else {
            int tamanho_esquerda = tamanho_recursao(no.esquerda);
            int tamanho_direita = tamanho_recursao(no.direita);

            if (tamanho_esquerda > tamanho_direita)
                return (tamanho_esquerda + 1);
            else
                return (tamanho_direita + 1);
        }
    }

    private void nivel(Node no, int n) {
        if (no == null) {
            System.out.print("\t");
            return;
        }
        if (n == 1) {
            System.out.print(no.info+"\t\t");
        }
        else if (n > 1) {
            nivel(no.esquerda, n-1);
            nivel(no.direita, n-1);
        }
    }

    private void print_vertical() {
        for (int i=1; i<=tamanho(); i++) {
            for(int j=i+1; j<=tamanho(); j++) {
                System.out.print("\t");
            }
            nivel(this.inicio, i);
            System.out.println();
        }
    }

    private void print_horizontal(Node no, int space) {
        if (no == null)
            return;

        space += 10;

        print_horizontal(no.direita, space);

        System.out.println();
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        System.out.println(no.info);

        print_horizontal(no.esquerda, space);
    }

}