package structures;

import java.util.Scanner;

public class ArvoreMorse extends ArvoreBinaria{

    public ArvoreMorse() {
        super();
    }

    public void insere(String morse, String info){
        this.inicio = insere_recursao(this.inicio, info, morse);
    }

    public String buscar(String morse){
        String[] list_morse = morse.split(" ");
        String result = "";
        for(String m : list_morse){
            result += buscar_recursao(this.inicio, m);
        }
        return result;
    }

    private Node insere_recursao(Node no, String info, String morse){
        if(no == null || morse.isEmpty()){
            no = new Node(info);
            return no;
        }
        int length = morse.length();
        String head = morse.substring(0,1);
        String tail = morse.substring(1,length);

        if (head.equals(".")) {
            no.esquerda = insere_recursao(no.esquerda, info, tail);
        } else if (head.equals("-")) {
            no.direita = insere_recursao(no.direita, info, tail);
        }

        return no;
    }

    private String buscar_recursao(Node no, String morse){
        if(morse.isEmpty()){
            return no.info;
        }
        int length = morse.length();
        String head = morse.substring(0,1);
        String tail = morse.substring(1,length);

        if (head.equals(".")) {
            return buscar_recursao(no.esquerda, tail);
        } else if (head.equals("-")) {
            return buscar_recursao(no.direita, tail);
        } else {
            return "";
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1 - Inserir");
            System.out.println("2 - Excluir Valor");
            System.out.println("3 - Buscar Morse");
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
            } else if (opcao == 3) {
                System.out.print("Digite o codigo morse (Divida as letras com ' ') (Exemplo: '... --- ...'): ");
                String info = scanner.next();
                info+=scanner.nextLine();
                System.out.println(this.buscar(info));
            } else {
                System.out.println("Opção inválida");
            }
        }
    }
}
