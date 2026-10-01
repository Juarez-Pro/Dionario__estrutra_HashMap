# 📘 Dicionário Inglês → Português com HashMap

Projeto desenvolvido em **Java** para demonstrar, de forma prática, a utilização da estrutura de dados **HashMap** por meio de um dicionário de palavras em inglês e suas respectivas traduções para português.

A aplicação funciona no terminal e permite realizar operações de **cadastro, busca, listagem, atualização e remoção** de palavras.

---

## 🎥 Apresentação em vídeo

A apresentação do projeto pode ser acessada pelo link abaixo:

> video -> https://drive.google.com/file/d/18aPChCJIrwIvlwpCxxTF51Btj-jsiXhg/view?usp=sharing.

---

## 🎯 Objetivo do projeto

O objetivo principal é aplicar os conceitos da estrutura de dados **HashMap** utilizando uma única estrutura para armazenar as palavras do dicionário.

A estrutura utilizada na classe `DicionarioService` é:

```java
private final Map<String, String> dicionario = new HashMap<>();
```

Nesse mapa:

- **Chave (`String`)**: palavra em inglês;
- **Valor (`String`)**: tradução da palavra para português.

Exemplo conceitual:

```text
house → casa
book  → livro
car   → carro
```

A palavra em inglês funciona como a chave do registro. Dessa forma, uma mesma chave não pode ser cadastrada duas vezes.

---

## 🧠 Por que utilizar HashMap?

O `HashMap` armazena informações no formato **chave → valor** e permite acessar um valor diretamente por meio de sua chave.

Neste projeto, a associação ocorre da seguinte maneira:

```text
PALAVRA EM INGLÊS → TRADUÇÃO EM PORTUGUÊS
```

Exemplo em Java:

```java
dicionario.put("house", "casa");
```

Para recuperar a tradução:

```java
dicionario.get("house");
```

O uso do `HashMap` é adequado ao projeto porque operações como inserção, consulta e remoção possuem, em condições médias, complexidade próxima de **O(1)**.

---

## ⚙️ Funcionalidades

O sistema possui as seguintes funcionalidades:

- cadastrar uma nova palavra e sua tradução;
- listar todas as palavras cadastradas;
- buscar uma tradução pela palavra em inglês;
- atualizar uma palavra e sua tradução;
- remover um registro;
- verificar se o dicionário está vazio;
- encerrar a aplicação pelo menu.

---

## 🔄 Operações CRUD

As funcionalidades do projeto podem ser relacionadas às operações de um CRUD:

| Operação | Função no projeto | Método principal |
|---|---|---|
| **Create** | Cadastrar uma nova palavra | `cadastrar()` |
| **Read** | Buscar uma tradução | `BuscarPorChave()` |
| **Read** | Listar todos os registros | `listarTodos()` |
| **Update** | Atualizar palavra e tradução | `atualizar()` |
| **Delete** | Remover uma palavra | `remover()` |

---

## 🗂️ Estrutura do projeto

```text
Dionario - estrutra HashMap/
│
├── README.md
├── Dionario - estrutra HashMap.iml
│
└── src/
    └── br/
        └── edu/
            └── aesa/
                ├── app/
                │   └── Main.java
                │
                ├── model/
                │   └── Palavra.java
                │
                └── service/
                    └── DicionarioService.java
```

---

## 🧩 Organização do código

### `Main.java`

É responsável pela interação com o usuário através do terminal.

Entre suas responsabilidades estão:

- criar o objeto `Scanner`;
- criar a instância de `DicionarioService`;
- exibir o menu principal;
- receber a opção escolhida;
- solicitar os dados ao usuário;
- chamar os métodos do serviço;
- exibir mensagens e resultados.

O fluxo principal utiliza um laço `do/while` e um `switch` para controlar as opções do menu.

---

### `DicionarioService.java`

É responsável pelo armazenamento e pelas operações realizadas sobre o dicionário.

A estrutura principal é:

```java
private final Map<String, String> dicionario = new HashMap<>();
```

Os métodos implementados são:

```java
cadastrar(String chaveIngles, String valorPortugues)
BuscarPorChave(String chaveIngles)
listarTodos()
atualizar(String chaveInglesAtual, String novaChaveIngles, String novaTraducaoPortugues)
remover(String chaveIngles)
estaVazio()
```

---

### `Palavra.java`

É a classe de modelo utilizada para representar uma palavra e sua tradução durante a listagem.

Seus atributos são:

```java
private String termoIngles;
private String traducaoPortugues;
```

A classe possui:

- construtor;
- `getTermoIngles()`;
- `setTermoIngles()`;
- `getTraducaoPortugues()`;
- `setTraducaoPortugues()`.

Os objetos `Palavra` são criados na preparação da listagem. O armazenamento principal dos registros continua sendo feito pelo único `HashMap<String, String>` existente em `DicionarioService`.

---

## 🔑 Palavra em inglês como chave

A palavra em inglês é utilizada como chave do `HashMap`.

Exemplo:

```text
Chave: house
Valor: casa
```

Internamente, o cadastro é realizado com:

```java
dicionario.put(chaveIngles, valorPortugues);
```

A busca utiliza:

```java
dicionario.get(chaveIngles);
```

Antes de cadastrar uma nova palavra, o sistema verifica se a chave já existe:

```java
if (dicionario.containsKey(chaveIngles)) {
    System.out.println("Palavra já cadastrada...");
    return false;
}
```

---

## 🔤 Tratamento das entradas

Os textos informados pelo usuário passam pelo método auxiliar `lerTexto()` presente em `Main.java`.

```java
String texto = teclado.nextLine().trim().toLowerCase();
```

Isso faz com que:

- espaços no início e no final sejam removidos;
- letras maiúsculas sejam convertidas para minúsculas;
- entradas vazias não sejam aceitas pela interface.

Por exemplo:

```text
HOUSE
 House 
house
```

passam a ser tratados como:

```text
house
```

Como o mesmo método é utilizado para receber a tradução em português, ela também é convertida para letras minúsculas antes de ser enviada ao serviço.

---

## ➕ Cadastro

O cadastro é realizado pelo método:

```java
public boolean cadastrar(String chaveIngles, String valorPortugues)
```

Antes da inserção, o serviço verifica se a palavra em inglês já foi cadastrada.

Caso a chave seja nova, o registro é armazenado com:

```java
dicionario.put(chaveIngles, valorPortugues);
```

Exemplo:

```text
Palavra em inglês -> house
Tradução em português -> casa
Cadastro realizado com sucesso!!!
```

---

## 🔎 Busca

A busca é realizada pela palavra em inglês através do método:

```java
public String BuscarPorChave(String chaveIngles) {
    return dicionario.get(chaveIngles);
}
```

Exemplo:

```text
Digite a palavra em inglês: house
Tradução em português: casa
```

Se a chave não existir, o método retorna `null` e a interface informa que nenhum registro foi encontrado.

---

## 📋 Listagem

O método:

```java
public List<Palavra> listarTodos()
```

percorre as entradas existentes no `HashMap` e cria objetos `Palavra` para gerar uma lista de apresentação.

Trecho utilizado:

```java
for (Map.Entry<String, String> entrada : dicionario.entrySet()) {
    palavras.add(new Palavra(entrada.getKey(), entrada.getValue()));
}
```

Depois, a lista é ordenada alfabeticamente pela palavra em inglês:

```java
palavras.sort(Comparator.comparing(Palavra::getTermoIngles));
```

Assim, mesmo que o `HashMap` não mantenha uma ordem de inserção, os registros são apresentados em ordem alfabética.

Exemplo:

```text
Números de palavras: 3
0 -          INGLÊS | PORTUGUÊS
1 -            book | livro
2 -             car | carro
3 -           house | casa
```

---

## ✏️ Atualização

A atualização utiliza o método:

```java
public boolean atualizar(
    String chaveInglesAtual,
    String novaChaveIngles,
    String novaTraducaoPortugues
)
```

Antes de chamar esse método, a interface verifica se a palavra atual existe no dicionário.

No serviço, também é verificado se a nova chave já está cadastrada:

```java
if (dicionario.containsKey(novaChaveIngles)) {
    System.out.println("Nova palavra já existe no dicionário... ");
    return false;
}
```

Se a nova chave estiver disponível, o registro anterior é removido e o novo é adicionado:

```java
dicionario.remove(chaveInglesAtual);
dicionario.put(novaChaveIngles, novaTraducaoPortugues);
```

Exemplo:

```text
Registro atual: house -> casa
Digite a nova palavra em inglês: home
Digite a nova tradução em português: lar
!!Registro atualizado com sucesso!!
```

> Na implementação atual, a nova palavra em inglês precisa ser uma chave ainda não cadastrada. Portanto, informar exatamente a mesma chave atual faz a verificação de duplicidade impedir a atualização.

---

## 🗑️ Remoção

A remoção utiliza a palavra em inglês como chave.

Método:

```java
public boolean remover(String chaveIngles)
```

Primeiro, o sistema verifica se a chave existe:

```java
if (!dicionario.containsKey(chaveIngles)) {
    return false;
}
```

Caso exista, ela é removida:

```java
dicionario.remove(chaveIngles);
```

---

## ✅ Validações presentes

A versão atual possui as seguintes verificações:

- impede texto vazio nas entradas realizadas por `lerTexto()`;
- remove espaços no início e no final dos textos digitados;
- converte os textos para letras minúsculas;
- impede o cadastro de uma chave já existente;
- informa quando uma busca não encontra uma palavra;
- impede atualização e remoção quando o dicionário está vazio;
- verifica se a palavra a ser atualizada existe antes da atualização;
- impede que a atualização utilize uma nova chave já cadastrada;
- verifica se a palavra existe antes de removê-la;
- informa quando uma opção numérica inexistente é escolhida no menu.

---

## 🖥️ Menu da aplicação

Ao executar o projeto, o menu apresentado é:

```text
========================================
       DICIONÁRIO INGLÊS -> PORTUGUÊS
========================================
[1] Cadastrar nova palavra
[2] Listar todos os registros
[3] Buscar tradução
[4] Atualizar registro
[5] Remover registro
[0] Sair
========================================
```

---

## 💡 Exemplo de utilização

### 1. Cadastro

```text
---- CADASTRO ----
Palavra em inglês -> house
Tradução em português -> casa
Cadastro realizado com sucesso!!!
```

### 2. Busca

```text
---- BUSCA ----
Digite a palavra em inglês: house
Tradução em português: casa
```

### 3. Atualização

```text
---- ATUALIZAÇÃO ----
Digite a palavra em inglês que deseja atualizar: house
Registro atual: house -> casa
Digite a nova palavra em inglês: home
Digite a nova tradução em português: lar
!!Registro atualizado com sucesso!!
```

### 4. Remoção

```text
---- REMOÇÃO ----
Informe a palavra em inglês que deseja remover: home
Palavra removida com sucesso!
```

---

## ⏱️ Complexidade das operações

Considerando o comportamento médio de um `HashMap`:

| Operação | Complexidade média |
|---|---:|
| Cadastro | **O(1)** |
| Busca pela chave | **O(1)** |
| Atualização | **O(1)** |
| Remoção | **O(1)** |
| Percorrer os registros | **O(n)** |
| Listagem com ordenação | **O(n log n)** |

A listagem possui custo maior porque primeiro percorre as entradas do mapa e depois ordena a lista resultante alfabeticamente.

---

## 🚀 Como executar

### IntelliJ IDEA

O arquivo de configuração do projeto está definido com nível de linguagem **JDK 25**.

1. Abra a pasta do projeto no **IntelliJ IDEA**;
2. configure um **JDK 25** para o projeto;
3. abra o arquivo:

```text
src/br/edu/aesa/app/Main.java
```

4. execute o método `main` pelo IntelliJ.

### Terminal com JDK 25

Na pasta raiz do projeto, crie uma pasta para a compilação:

```bash
mkdir out
```

Compile os arquivos:

```bash
javac -d out src/br/edu/aesa/model/Palavra.java src/br/edu/aesa/service/DicionarioService.java src/br/edu/aesa/app/Main.java
```

Execute a aplicação:

```bash
java -cp out Main
```

### Observação sobre Java 21

O arquivo `Main.java` utiliza a sintaxe de classe implícita/arquivo fonte simplificado. No Java 21, esse recurso é experimental e precisa ser habilitado como *preview*.

Exemplo de compilação com JDK 21:

```bash
javac --enable-preview --release 21 -d out src/br/edu/aesa/model/Palavra.java src/br/edu/aesa/service/DicionarioService.java src/br/edu/aesa/app/Main.java
```

Execução:

```bash
java --enable-preview -cp out Main
```

---

## 🛠️ Tecnologias e recursos utilizados

- Java;
- `HashMap`;
- `Map`;
- `ArrayList`;
- `List`;
- `Comparator`;
- `Scanner`;
- IntelliJ IDEA.

---

## ⚠️ Características da versão atual

O projeto foi desenvolvido utilizando **apenas um `HashMap<String, String>` como estrutura principal de armazenamento**.

Por isso:

- a consulta é feita pela palavra em inglês;
- não existe busca direta de português para inglês;
- os dados ficam somente em memória durante a execução;
- não existe persistência em arquivo ou banco de dados;
- ao encerrar o programa, os registros cadastrados são perdidos;
- a ordenação ocorre apenas na lista criada para exibição;
- todas as entradas recebidas por `lerTexto()` são convertidas para minúsculas.

---

## 📌 Conclusão

O projeto demonstra a aplicação prática da estrutura `HashMap<String, String>` em Java por meio de um dicionário inglês → português.

A palavra em inglês é utilizada como chave e a tradução em português como valor. A partir dessa estrutura foram implementadas operações de cadastro, busca, listagem, atualização e remoção, permitindo praticar conceitos de **estruturas de dados, CRUD, organização em classes e interação pelo terminal**.

A implementação mantém o foco em uma única estrutura `HashMap`, de acordo com a proposta atual do projeto.
