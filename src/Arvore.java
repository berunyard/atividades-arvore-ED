import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.tree.TreeNode;

public class Arvore {
    Node raiz;
    int qtd, qtdFolhas;

    // ADICIONAR NA ÁRVORE
    public void add(Integer value) {
        Node newNode = new Node(value);

        if (this.isEmpty()) {
            this.raiz = newNode;
        } else {
            Node aux = this.raiz;
            while (true) {
                if (newNode.getValue() < aux.getValue()) { // newNode < aux(que tem o valor da raíz) = vai p esquerda, e
                                                           // vice-versa
                    if (aux.getLeft() != null) { // se a esquerda da raiz tiver um filho
                        aux = aux.getLeft(); // percorre mais pra esquerda
                    } else { // senão, adiciona a esquerda
                        aux.setLeft(newNode);
                        break;
                    }
                } else {
                    if (aux.getRight() != null) {
                        aux = aux.getRight();
                    } else {
                        aux.setRight(newNode);
                        break;
                    }
                }
            }
        }
        this.qtd++;
    }

    // PROCURAR NA ÁRVORE
    public void search(Node aux, int value) {
        if (this.isEmpty()) {
            System.out.println("Lista vazia.");
        }
        if (aux != null) {
            if (value == aux.getValue()) {
                System.out.println("Valor encontrado: " + aux.getValue());
            } else if (value < aux.getValue()) { // valor menor que o atual? procura pra esquerda
                search(aux.getLeft(), value);
            } else { // joga pra direita
                search(aux.getRight(), value);
            }
        } else {
            System.out.println("Valor não encontrado.");
        }
    }

    // REMOVER NA ÁRVORE
    public boolean remover(Integer valor) {
        Node atual = this.raiz;
        Node paiAtual = null;

        while (atual != null && !atual.getValue().equals(valor)) {
            if (valor.compareTo(atual.getValue()) == -1) {
                paiAtual = atual;
                atual = atual.getLeft();
            } else {
                paiAtual = atual;
                atual = atual.getRight();
            }
        }

        if (atual == null) {
            return false;
        } else {
            Node substituto;
            Node paiSubstituto;
            if (atual.getRight() != null) {
                substituto = atual.getRight();

                for (paiSubstituto = atual; substituto.getLeft() != null; substituto = substituto.getLeft()) {
                    paiSubstituto = substituto;
                }

                substituto.setLeft(atual.getLeft());
                if (paiAtual != null) {
                    if (atual.getValue().compareTo(paiAtual.getValue()) == -1) {
                        paiAtual.setLeft(substituto);
                    } else {
                        paiAtual.setRight(substituto);
                    }
                } else {
                    this.raiz = substituto;
                }

                if (substituto.getValue().compareTo(paiSubstituto.getValue()) == -1) {
                    paiSubstituto.setLeft(null);
                } else {
                    paiSubstituto.setRight(null);
                }
            } else if (atual.getLeft() != null) {
                substituto = atual.getLeft();

                for (paiSubstituto = atual; substituto.getRight() != null; substituto = substituto.getRight()) {
                    paiSubstituto = substituto;
                }

                if (paiAtual != null) {
                    if (atual.getValue().compareTo(paiAtual.getValue()) == -1) {
                        paiAtual.setLeft(substituto);
                    } else {
                        paiAtual.setRight(substituto);
                    }
                } else {
                    this.raiz = substituto;
                }

                if (substituto.getValue().compareTo(paiSubstituto.getValue()) == -1) {
                    paiSubstituto.setLeft(null);
                } else {
                    paiSubstituto.setRight(null);
                }
            } else if (paiAtual != null) {
                if (atual.getValue().compareTo(paiAtual.getValue()) == -1) {
                    paiAtual.setLeft(null);
                } else {
                    paiAtual.setRight(null);
                }
            } else {
                this.raiz = null;
            }

            return true;
        }
    }

    // ACHAR ENDEREÇO MENOR VALOR
    public Node findMin(Node aux) {
        if (this.isEmpty()) {
            System.out.println("Árvore vazia.");
        } else { // percorre pra esquerda, pois é onde está o menor valor
            while (aux.getLeft() != null) {
                aux = aux.getLeft();
            }
        }
        return aux;
    }

    // ACHAR ENDEREÇO MAIOR VALOR
    public Node findMax(Node aux) {
        if (this.isEmpty()) {
            System.out.println("Árvore vazia.");
        } else {
            while (aux.getRight() != null) {
                aux = aux.getRight();
            }
        }
        return aux;
    }

    // MÉDIA GERAL ÁRVORE
    public double mediaGeral(Node aux) {
        if (this.raiz == null) {
            return 0.0;
        }

        int soma = 0;
        int i = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(this.raiz);

        while (!queue.isEmpty()) {
            aux = queue.poll();
            soma += aux.getValue();
            i++;

            if (aux.getLeft() != null) {
                queue.add(aux.getLeft());
            }

            if (aux.getRight() != null) {
                queue.add(aux.getRight());
            }
        }

        if (i == 0) {
            return 0.0; // Evitar divisão por zero
        }

        return (double) soma / i;
    }

    // EXIBIR EM ORDEM
    public void emOrdem(Node aux) {
        if (aux != null) { // percorre
            emOrdem(aux.getLeft());
            System.out.println(aux.getValue());
            emOrdem(aux.getRight());
        }

    }

    // EXIBIR EM ORDEM (SEM RECURSÃO)
    public void emOrdemSem(Node aux) {
        if (this.raiz == null) {
            return;
        }
        Stack<Node> stack = new Stack<>(); // pilha de nodes
        // aux = this.raiz;

        while (aux != null || !stack.isEmpty()) { // enquanto o atual não for nulo OU o stack não estiver vazio
            while (aux != null) {
                stack.push(aux);
                aux = aux.getLeft(); // percorre e empilha todos os nós a esquerda
            }
            aux = stack.pop(); // o nó mais a esquerda é retirado da pilha e atribuido a aux
            System.out.println(aux.getValue() + " ");
            aux = aux.getRight();
        }
    }

    // EXIBIR PRE ORDEM
    public void preOrdem(Node aux) {
        if (aux != null) {
            System.out.println(aux.getValue()); // começa exibindo a raíz
            preOrdem(aux.getLeft());
            preOrdem(aux.getRight());
        }
    }

    // EXIBIR PRE ORDEM (SEM RECURSÃO)
    public void preOrdemSem(Node aux) {
        if (this.raiz == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(this.raiz);

        while (!stack.isEmpty()) {
            aux = stack.pop();
            System.out.print(aux.getValue() + " ");

            // Empilhe o nó direito primeiro, para que seja processado após o nó esquerdo
            if (aux.getRight() != null) {
                stack.push(aux.getRight());
            }
            // Empilhe o nó esquerdo, para que seja processado primeiro
            if (aux.getLeft() != null) {
                stack.push(aux.getLeft());
            }
        }
    }

    // EXIBIR POS ORDEM
    public void posOrdem(Node aux) {
        if (aux != null) {
            posOrdem(aux.getLeft());
            posOrdem(aux.getRight());
            System.out.println(aux.getValue()); // termina exibindo a raíz
        }
    }

    // EXIBIR POS ORDEM (SEM RECURSÃO)
    public void posOrdemSem(Node aux) {
        if (this.raiz == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Stack<Node> resultadoStack = new Stack<>(); // Para armazenar os resultados reversos

        stack.push(this.raiz);

        while (!stack.isEmpty()) {
            aux = stack.pop();
            resultadoStack.push(aux);

            // Empilhe o nó esquerdo primeiro, para que seja processado após o nó direito
            if (aux.getLeft() != null) {
                stack.push(aux.getLeft());
            }

            // Empilhe o nó direito, para que seja processado primeiro
            if (aux.getRight() != null) {
                stack.push(aux.getRight());
            }
        }

        // Imprime os resultados reversos
        while (!resultadoStack.isEmpty()) {
            Node resultNode = resultadoStack.pop();
            System.out.print(resultNode.getValue() + " ");
        }
    }

    // QTAS VEZES ITEM APARECE NA ÁRVORE
    public int qtdVezes(Node aux, int valorProcurado) {
        int i = 0;

        if (this.raiz == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(this.raiz);

        while (!queue.isEmpty()) {
            aux = queue.poll();

            if (aux.getValue() == valorProcurado) {
                i++;
            }

            if (aux.getLeft() != null) {
                queue.add(aux.getLeft());
            }

            if (aux.getRight() != null) {
                queue.add(aux.getRight());
            }
        }

        return i;
    }

    // QTD FOLHAS
    public int qtdFolhas(Node aux) {
        if (this.raiz == null) {
            return 0;
        }

        if (this.raiz.getLeft() == null && this.raiz.getRight() == null) {
            return 1; // Nó folha
        }

        int folhasEsq = qtdFolhas(this.raiz.getLeft());
        int folhasDir = qtdFolhas(this.raiz.getRight());

        return folhasEsq + folhasDir;
    }

    // QTD NÓS NÃO TERMINAIS
    public int qtdNosNaoTerminais(Node aux) {
        if (this.raiz == null) {
            return 0;
        }

        if (this.raiz.getLeft() == null && this.raiz.getRight() == null) {
            return 0; // Nó terminal (folha)
        }

        int naoTerminaisEsq = qtdNosNaoTerminais(this.raiz.getLeft());
        int naoTerminaisDir = qtdNosNaoTerminais(this.raiz.getRight());

        return naoTerminaisEsq + naoTerminaisDir + 1; // Conta o próprio nó como não-terminal
    }

    // QTD NODES
    public int qtdNodes() {
        return this.qtd;
    }

    public boolean isEmpty() {
        return this.raiz == null;
    }

    public Node getRaiz() {
        return raiz;
    }

    public void setRaiz(Node raiz) {
        this.raiz = raiz;
    }
}
