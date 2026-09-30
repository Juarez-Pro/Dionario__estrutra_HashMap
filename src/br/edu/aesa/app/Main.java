import br.edu.aesa.model.Palavra;
import br.edu.aesa.service.DicionarioService;
 import java.util.Scanner;
 import java.util.List;

void main() {
    Scanner teclado = new Scanner(System.in);
    DicionarioService dicionario = new DicionarioService();

    int opcao;
    do {
        exibirMenu();
        System.out.print("Escolha uma opção: ");
        opcao = teclado.nextInt();
        System.out.println();

        switch (opcao) {
            case 1 -> telaCadastrar(teclado, dicionario);
            case 2 -> telaListarTodos(dicionario);
            case 3 -> telaBuscar(teclado, dicionario);
            case 4 -> telaAtualizar(teclado, dicionario);
            case 5 -> telaRemover(teclado, dicionario);
            case 0 -> System.out.println("Programa encerrado. Até logo!");
            default -> System.out.println("Opção inválida. Tente novamente.");
        }

    } while (opcao != 0);
}

private static void exibirMenu() {
    System.out.println("\n========================================");
    System.out.println("       DICIONÁRIO INGLÊS -> PORTUGUÊS");
    System.out.println("========================================");
    System.out.println("[1] Cadastrar nova palavra");
    System.out.println("[2] Listar todos os registros");
    System.out.println("[3] Buscar tradução");
    System.out.println("[4] Atualizar registro");
    System.out.println("[5] Remover registro");
    System.out.println("[0] Sair");
    System.out.println("========================================");
}

private static void telaCadastrar (Scanner teclado, DicionarioService dicionario) {
    System.out.println("---- CADASTRO ----");
    String chaveIngles = lerTexto(teclado, "Palavra em inglês -> ");
    String valorPortugues = lerTexto(teclado, "Tradução em português -> ");

    if (dicionario.cadastrar(chaveIngles, valorPortugues)){
        System.out.println("Cadastro realizado com sucesso!!!");
    } else {
        System.out.println("Não foi possível realizar o cadastro. ");
    }
}

private static void telaListarTodos (DicionarioService dicionario) {
    System.out.println("---- LISTAGEM ----");
    List<Palavra> palavras = dicionario.listarTodos();

    System.out.println("Números de palavras: " + palavras.size());
    if (dicionario.estaVazio()) {
        System.out.println("O dicionário está vazio.");
        return;
    }

    System.out.printf("0 - %15s | PORTUGUÊS \n", "INGLÊS");
    for (int i = 0; i < palavras.size(); i++) {
        System.out.printf("%d - %15s | %-15s \n", i+1, palavras.get(i).getTermoIngles(), palavras.get(i).getTraducaoPortugues());
    }

}

private static void telaBuscar (Scanner teclado, DicionarioService dicionario) {
    System.out.println("---- BUSCA ----");
    String chaveIngles = lerTexto(teclado, "Digite a palavra em inglês: ");
    String traducao = dicionario.BuscarPorChave(chaveIngles);

    if (traducao != null) {
        System.out.println("Tradução em português: " + traducao);
    } else {
        System.out.println("Nenhum registro encontrado para a palavra informada. ");
    }

}

private static void telaAtualizar (Scanner teclado, DicionarioService dicionario) {
    System.out.println("---- ATUALIZAÇÃO ----");
    if (dicionario.estaVazio()) {
        System.out.println("O dicionário está vazio. ");
        return;
    }

    String chaveInglesAtual = lerTexto(teclado, "Digite a palavra em inglês que deseja atualizar: ");
    String traducaoAtual = dicionario.BuscarPorChave(chaveInglesAtual);
    if (traducaoAtual == null) {
        System.out.println("Nenhum item de registro encontrado com a palavra informada... ");
        return;
    }

    System.out.printf("Registro atual: %s -> %s \n", chaveInglesAtual, traducaoAtual);
    String novaChaveIngles = lerTexto(teclado, "Digite a nova palavra em inglês: ");
    String novaTraducaoPortugues = lerTexto(teclado, "Digite a nova tradução em português: ");
    if (dicionario.atualizar(chaveInglesAtual, novaChaveIngles, novaTraducaoPortugues)){
        System.out.println("!!Registro atualizado com sucesso!! ");
    } else {
        System.out.println("Não foi possível realizar a atualização. ");
    }

}

private static void telaRemover (Scanner teclado, DicionarioService dicionario) {
    System.out.println( "---- REMOÇÃO ----");
    if (dicionario.estaVazio()) {
        System.out.println("O dicionário está vazio. ");
        return;
    }

    String chaveingles = lerTexto(teclado, "Informe a palavra em inglês que deseja remover:");
    if (dicionario.remover(chaveingles)) {
        System.out.println("Palavra removida com sucesso! ");
    } else {
        System.out.println("Nenhum item encontrado para a palavra informada... ");
    }
}

private static String lerTexto (Scanner teclado, String mensagem) {
    while (true) {
        System.out.print(mensagem);
        String texto = teclado.nextLine().trim().toLowerCase();

        if (!texto.isEmpty())  {
            return texto;
        }
        System.out.println("palavra inválida. Tente novamente... ");
    }
}