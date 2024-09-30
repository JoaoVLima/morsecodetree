package structures;

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




}
