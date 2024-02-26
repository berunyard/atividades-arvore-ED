import java.util.Scanner;

class Produto {
    String codigo;
    String descricao;
    String fornecedor;
    double preco;
    int quantidade;

    public Produto(String codigo, String descricao, String fornecedor, double preco, int quantidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Descrição: " + descricao + ", Fornecedor: " + fornecedor
                + ", Preço: " + preco + ", Quantidade em Estoque: " + quantidade;
    }
}

class TreeNode {
    Produto produto;
    TreeNode left, right;

    public TreeNode(Produto produto) {
        this.produto = produto;
        this.left = this.right = null;
    }
}

class SearchTree {
    private TreeNode root;

    public SearchTree() {
        root = null;
    }

    public void cadastrarProduto(Produto produto) {
        root = insert(root, produto);
    }

    private TreeNode insert(TreeNode root, Produto produto) {
        if (root == null) {
            return new TreeNode(produto);
        }

        int compareResult = produto.codigo.compareTo(root.produto.codigo);

        if (compareResult < 0) {
            root.left = insert(root.left, produto);
        } else if (compareResult > 0) {
            root.right = insert(root.right, produto);
        }

        return root;
    }

    public void exibirEmOrdem() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.produto);
            inOrderTraversal(root.right);
        }
    }

    public Produto procurarProduto(String codigo) {
        return search(root, codigo);
    }

    private Produto search(TreeNode root, String codigo) {
        while (root != null && !codigo.equals(root.produto.codigo)) {
            int compareResult = codigo.compareTo(root.produto.codigo);
            if (compareResult < 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return (root != null) ? root.produto : null;
    }

    public void alterarPreco(String codigo, double novoPreco) {
        TreeNode node = searchNode(root, codigo);
        if (node != null) {
            node.produto.preco = novoPreco;
        }
    }

    private TreeNode searchNode(TreeNode root, String codigo) {
        while (root != null && !codigo.equals(root.produto.codigo)) {
            int compareResult = codigo.compareTo(root.produto.codigo);
            if (compareResult < 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return root;
    }

    public void alterarQuantidade(String codigo, int novaQuantidade) {
        TreeNode node = searchNode(root, codigo);
        if (node != null) {
            node.produto.quantidade = novaQuantidade;
        }
    }
}

class ScannerUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, insira um número válido.");
            System.out.print(mensagem);
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public static int lerInt(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um número inteiro válido.");
            System.out.print(mensagem);
            scanner.next();
        }
        return scanner.nextInt();
    }
}

public class CadastroProdutosApp {
    public static void main(String[] args) {
        SearchTree tree = new SearchTree();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Exibir Produtos em Ordem Crescente de Código");
            System.out.println("3. Procurar Produto");
            System.out.println("4. Alterar Preço do Produto");
            System.out.println("5. Alterar Quantidade do Produto");
            System.out.println("6. Sair");

            int opcao = ScannerUtils.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarProduto(tree);
                    break;
                case 2:
                    tree.exibirEmOrdem();
                    break;
                case 3:
                    procurarProduto(tree);
                    break;
                case 4:
                    alterarPreco(tree);
                    break;
                case 5:
                    alterarQuantidade(tree);
                    break;
                case 6:
                    System.out.println("Saindo do programa. Até logo!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarProduto(SearchTree tree) {
        String codigo = ScannerUtils.lerString("Digite o código do produto: ");
        Produto existingProduct = tree.procurarProduto(codigo);

        if (existingProduct == null) {
            String descricao = ScannerUtils.lerString("Digite a descrição do produto: ");
            String fornecedor = ScannerUtils.lerString("Digite o fornecedor do produto: ");
            double preco = ScannerUtils.lerDouble("Digite o preço do produto: ");
            int quantidade = ScannerUtils.lerInt("Digite a quantidade em estoque do produto: ");

            Produto novoProduto = new Produto(codigo, descricao, fornecedor, preco, quantidade);
            tree.cadastrarProduto(novoProduto);

            System.out.println("Produto cadastrado com sucesso!");
        } else {
            System.out.println("Já existe um produto com o mesmo código.");
        }
    }

    private static void procurarProduto(SearchTree tree) {
        String codigo = ScannerUtils.lerString("Digite o código do produto a ser procurado: ");
        Produto produtoEncontrado = tree.procurarProduto(codigo);

        if (produtoEncontrado != null) {
            System.out.println("Produto encontrado:\n" + produtoEncontrado);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void alterarPreco(SearchTree tree) {
        String codigo = ScannerUtils.lerString("Digite o código do produto: ");
        Produto produto = tree.procurarProduto(codigo);

        if (produto != null) {
            double novoPreco = ScannerUtils.lerDouble("Digite o novo preço do produto: ");
            tree.alterarPreco(codigo, novoPreco);
            System.out.println("Preço do produto alterado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void alterarQuantidade(SearchTree tree) {
        String codigo = ScannerUtils.lerString("Digite o código do produto: ");
        Produto produto = tree.procurarProduto(codigo);

        if (produto != null) {
            int novaQuantidade = ScannerUtils.lerInt("Digite a nova quantidade em estoque do produto: ");
            tree.alterarQuantidade(codigo, novaQuantidade);
            System.out.println("Quantidade do produto alterada com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}
