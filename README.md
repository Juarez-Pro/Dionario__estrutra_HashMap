# 📘 Dicionário Inglês → Português com HashMap

Projeto desenvolvido em **Java** com o objetivo de demonstrar, de forma prática, a utilização da estrutura de dados **HashMap** através de um dicionário de palavras em inglês e suas respectivas traduções para português.

A aplicação funciona pelo terminal e permite realizar as principais operações de um **CRUD**: cadastrar, consultar, atualizar e remover palavras.

---

## 🎯 Objetivo do projeto

O objetivo principal é utilizar uma única estrutura `HashMap` para armazenar os registros do dicionário.

A estrutura utilizada é:

```java
Map<String, String> dicionario = new HashMap<>();
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

A palavra em inglês funciona como o **identificador único do registro**. Por isso, não é possível cadastrar duas vezes a mesma palavra em inglês.

---

## 🧠 Por que utilizar HashMap?

O `HashMap` é uma estrutura baseada em associação entre **chave e valor**.

Neste projeto, ele é adequado porque permite localizar uma tradução diretamente através da palavra em inglês, sem precisar percorrer todos os registros do dicionário.

Na média, operações como inserção, busca e remoção possuem complexidade próxima de **O(1)**.

Exemplo:

```java
dicionario.put("house", "casa");

String traducao = dicionario.get("house");
```

Resultado:

```text
casa
```

---

## ⚙️ Funcionalidades

O sistema disponibiliza as seguintes operações:

- Cadastrar uma nova palavra e sua tradução;
- Listar todos os registros cadastrados;
- Buscar uma tradução através da palavra em inglês;
- Atualizar uma palavra e sua tradução;
- Remover um registro;
- Encerrar a aplicação.

---

## 🔄 Operações CRUD

O projeto implementa as quatro operações fundamentais de um CRUD.

| Operação | Descrição | Método principal |
|---|---|---|
| **Create** | Cadastra uma palavra e sua tradução | `cadastrar()` |
| **Read** | Busca um registro pela palavra em inglês | `buscarPorId()` |
| **Read** | Lista todos os registros | `listarTodos()` |
| **Update** | Atualiza uma palavra e sua tradução | `atualizar()` |
| **Delete** | Remove um registro | `remover()` |

---

## 🗂️ Estrutura do projeto

```text
dicionario-hashmap/
│
├── README.md
├── teste_saida.txt
├── dicionario-hashmap.iml
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

## 🧩 Organização das classes

### `Main.java`

É a classe responsável pela interação com o usuário.

Ela apresenta o menu no terminal, recebe os dados digitados e chama os métodos da classe `DicionarioService`.

Entre suas responsabilidades estão:

- exibir o menu;
- receber os dados pelo `Scanner`;
- cadastrar palavras;
- solicitar buscas;
- solicitar atualizações;
- solicitar remoções;
- exibir mensagens e resultados.

---

### `DicionarioService.java`

É a classe responsável pelas regras de funcionamento do dicionário.

Ela contém a única estrutura principal de armazenamento utilizada no projeto:

```java
private final Map<String, String> dicionario = new HashMap<>();
```

Os principais métodos são:

```java
cadastrar(String ingles, String portugues)
buscarPorId(String ingles)
listarTodos()
atualizar(String inglesAtual, String novoIngles, String novoPortugues)
remover(String ingles)
estaVazio()
```

Também possui métodos auxiliares responsáveis pela limpeza e normalização das entradas do usuário.

---

### `Palavra.java`

Representa uma palavra e sua tradução durante a apresentação dos registros.

Possui os atributos:

```java
private String ingles;
private String portugues;
```

A classe possui construtor, métodos `get`, métodos `set` e sobrescrita do método `toString()`.

É importante destacar que a classe `Palavra` **não representa uma segunda estrutura de armazenamento**. Os dados permanecem armazenados somente no `HashMap`. Objetos `Palavra` são criados temporariamente quando a listagem é preparada para exibição.

---

## 🔑 Palavra em inglês como chave

A palavra em inglês é utilizada como chave do `HashMap` e também funciona como identificador do registro.

Por exemplo:

```text
Chave: house
Valor: casa
```

Internamente:

```java
dicionario.put("house", "casa");
```

Para buscar:

```java
dicionario.get("house");
```

---

## 🔤 Normalização das palavras

Antes de utilizar uma palavra em inglês como chave, o sistema aplica uma normalização através do método:

```java
private String normalizar(String texto) {
    if (texto == null) {
        return "";
    }
    return texto.trim().toLowerCase();
}
```

Isso significa que entradas como:

```text
House
HOUSE
 house 
house
```

são interpretadas como a mesma chave:

```text
house
```

Isso evita registros duplicados causados apenas por diferenças entre letras maiúsculas, minúsculas ou espaços extras.

A tradução em português também tem os espaços extras das extremidades removidos, porém mantém a forma como foi digitada pelo usuário.

---

## ➕ Cadastro

O cadastro é realizado pelo método:

```java
public boolean cadastrar(String ingles, String portugues)
```

Antes de inserir o registro, o sistema verifica:

- se a palavra em inglês está vazia;
- se a tradução está vazia;
- se a palavra em inglês já foi cadastrada.

Quando o registro é válido, é armazenado com:

```java
dicionario.put(chaveIngles, traducaoPortugues);
```

---

## 🔎 Busca

A busca utiliza a palavra em inglês como chave do registro.

Método:

```java
public String buscarPorId(String ingles) {
    return dicionario.get(normalizar(ingles));
}
```

Exemplo:

```text
Palavra pesquisada: house
Resultado: casa
```

Como a busca é feita diretamente pela chave do `HashMap`, sua complexidade média é **O(1)**.

---

## 📋 Listagem

O método:

```java
public List<Palavra> listarTodos()
```

percorre os registros do `HashMap` e cria uma lista temporária de objetos `Palavra` para apresentação.

Depois, essa lista é ordenada alfabeticamente pela palavra em inglês:

```java
palavras.sort((p1, p2) ->
    p1.getIngles().compareToIgnoreCase(p2.getIngles())
);
```

Portanto, embora o `HashMap` não mantenha ordem própria, os registros são exibidos ao usuário em **ordem alfabética**.

---

## ✏️ Atualização

A atualização recebe:

- a palavra em inglês atual;
- a nova palavra em inglês;
- a nova tradução em português.

Método:

```java
public boolean atualizar(
    String inglesAtual,
    String novoIngles,
    String novoPortugues
)
```

O sistema verifica se:

- o registro atual existe;
- a nova palavra não está vazia;
- a nova tradução não está vazia;
- a nova chave não pertence a outro registro já cadastrado.

Como a chave pode ser alterada, o registro anterior é removido e o novo é inserido:

```java
dicionario.remove(chaveAtual);
dicionario.put(novaChave, novaTraducao);
```

---

## 🗑️ Remoção

A remoção também utiliza a palavra em inglês como chave.

Método:

```java
public boolean remover(String ingles)
```

Se a chave existir, o registro é removido:

```java
dicionario.remove(chave);
```

---

## ✅ Validações implementadas

O projeto trata diferentes situações para evitar operações inválidas:

- Palavra em inglês vazia;
- Tradução em português vazia;
- Tentativa de cadastrar uma chave já existente;
- Busca por uma palavra inexistente;
- Atualização de registro inexistente;
- Tentativa de atualizar para uma chave já utilizada;
- Remoção de registro inexistente;
- Tentativa de atualizar ou remover quando o dicionário está vazio;
- Opção inexistente no menu;
- Entrada não numérica no menu.

---

## 🖥️ Menu da aplicação

Ao executar o projeto, o seguinte menu é apresentado:

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

### Cadastro

```text
Palavra em inglês: house
Tradução em português: casa

Registro cadastrado com sucesso!
```

Outro cadastro:

```text
Palavra em inglês: book
Tradução em português: livro

Registro cadastrado com sucesso!
```

### Listagem

```text
Inglês: book | Português: livro
Inglês: house | Português: casa
```

### Busca

```text
Digite a palavra em inglês (chave/ID): house
Tradução em português: casa
```

### Atualização

```text
Informe a palavra em inglês que deseja atualizar (chave/ID): house
Registro atual: house -> casa

Nova palavra em inglês: home
Nova tradução em português: lar

Registro atualizado com sucesso!
```

### Remoção

```text
Informe a palavra em inglês que deseja remover (chave/ID): home
Registro removido com sucesso!
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
| Percorrer todos os registros | **O(n)** |
| Listagem com ordenação | **O(n log n)** |

A listagem possui custo maior porque, além de percorrer todos os registros, o sistema ordena a lista alfabeticamente antes de exibi-la.

---

## 🚀 Como executar

### Opção 1 — IntelliJ IDEA

1. Abra a pasta do projeto no **IntelliJ IDEA**;
2. Certifique-se de que um JDK compatível está configurado;
3. O projeto atual está configurado no IntelliJ com **JDK 25**;
4. Abra a classe:

```text
src/br/edu/aesa/app/Main.java
```

5. Execute o método `main`.

---

### Opção 2 — Terminal

A partir da pasta raiz do projeto, crie uma pasta para os arquivos compilados:

```bash
mkdir out
```

Compile as classes:

```bash
javac -d out src/br/edu/aesa/model/Palavra.java src/br/edu/aesa/service/DicionarioService.java src/br/edu/aesa/app/Main.java
```

Depois execute:

```bash
java -cp out br.edu.aesa.app.Main
```

> Em sistemas Windows, os mesmos comandos podem ser executados no terminal do IntelliJ, PowerShell ou Prompt de Comando, desde que o Java esteja corretamente configurado.

---

## ⚠️ Limitações da versão atual

Esta versão foi desenvolvida propositalmente com **apenas um `HashMap`**, seguindo a estrutura:

```text
Inglês → Português
```

Por esse motivo:

- a busca direta ocorre somente através da palavra em inglês;
- não existe busca direta de português para inglês;
- os dados não são salvos em banco de dados;
- os dados não são gravados em arquivo;
- ao encerrar o programa, os registros armazenados em memória são perdidos.

Essas características fazem parte da implementação atual e mantêm o foco da atividade no estudo da estrutura `HashMap`.

---

## 🛠️ Tecnologias utilizadas

- **Java**;
- **HashMap**;
- **ArrayList** para apresentação temporária dos registros;
- **Scanner** para entrada de dados pelo terminal;
- **IntelliJ IDEA** como ambiente de desenvolvimento.

---

## 🎥 Apresentação em vídeo

Caso seja necessário adicionar uma apresentação do projeto, substitua o endereço abaixo pelo link do vídeo:

```text
LINK_DO_VIDEO
```

Exemplo em Markdown:

```md
[Assistir à apresentação do projeto](LINK_DO_VIDEO)
```

---

## 📌 Conclusão

O projeto demonstra a aplicação prática de uma estrutura `HashMap<String, String>` em Java através da implementação de um dicionário inglês → português.

A palavra em inglês é utilizada como chave única e a tradução em português como valor. A partir dessa estrutura, foram implementadas operações de cadastro, busca, atualização, remoção e listagem, aplicando conceitos de **CRUD**, estruturas de dados, organização em classes e validação de entradas.

A implementação utiliza **somente um HashMap como estrutura principal de armazenamento**, mantendo o projeto simples e alinhado ao objetivo da atividade.
