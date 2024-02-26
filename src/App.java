public class App {
    public static void main(String[] args) throws Exception {
        Arvore a1 = new Arvore();
        a1.add(10);
        a1.add(8);
        a1.add(5);
        a1.add(9);
        a1.add(7);
        a1.add(18);
        a1.add(13);
        a1.add(20);

        System.out.println("\nEm-ordem:");
        a1.emOrdem(a1.getRaiz());

        System.out.println("\nEm-ordem sem recursão:");
        a1.emOrdemSem(a1.getRaiz());

        System.out.println("\nPré-ordem:");
        a1.preOrdem(a1.getRaiz());

        System.out.println("\nPré-ordem:");
        a1.preOrdemSem(a1.getRaiz());

        System.out.println("\nPós-ordem:");
        a1.posOrdem(a1.getRaiz());

        System.out.println("\nPós-ordem:");
        a1.posOrdemSem(a1.getRaiz());


        // a1.search(a1.getRaiz(), 18);

        /*System.out.println("Endereço do menor valor dentro da árvore: " + a1.findMin(a1.getRaiz()));
        System.out.println("Endereço do maior valor dentro da árvore: " + a1.findMax(a1.getRaiz()));

        System.out.println("Quantidade de nós na árvore: " + a1.qtdNodes());
        System.out.println("Quantidade de folhas na árvore: " + a1.qtdFolhas(a1.getRaiz()));*/
    }
}
