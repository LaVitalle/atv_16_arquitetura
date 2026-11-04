# Atividade 016 - Padrão Template Method

Refatoração do sistema de geração de relatórios utilizando o padrão **Template Method**, incluindo o desafio adicional do relatório em texto simples.

## Estrutura

| Classe | Papel |
|---|---|
| `ReportGenerator` | Classe abstrata com o Template Method `generate()` (público e `final`) |
| `CsvReport` | Formata em CSV e salva em `report.csv` |
| `HtmlReport` | Formata em lista HTML e salva em `report.html` |
| `TextReport` | Formata um produto por linha, salva em `report.txt` e sobrescreve o hook (desafio adicional) |
| `Main` | Usa referências do tipo `ReportGenerator` para gerar os relatórios |

Ordem das etapas em `generate()`: `readData()` → `formatData(data)` → `saveReport(content)` → `afterGeneration()`.

## Como executar

```bash
javac -d out src/*.java
java -cp out Main
```

## Questões para reflexão

**a) Qual problema existente no código inicial foi resolvido com o Template Method?**

Cada relatório repetia o fluxo inteiro: ler os dados, formatar, salvar e avisar que terminou. Uma mudança na leitura ou na ordem exigiria alterar todas as classes, e um formato novo poderia esquecer uma etapa ou fazer em outra ordem. Agora o fluxo fica num lugar só, na classe base, e as subclasses implementam apenas o que muda: o formato e o arquivo.

**b) Qual método representa o Template Method e quais etapas são comuns, abstratas ou hooks na sua implementação?**

- Template Method: `generate()`
- Etapa comum: `readData()`, implementada na classe base e marcada como `final`
- Etapas abstratas: `formatData(String[] data)` e `saveReport(String content)`
- Hook: `afterGeneration()`, que tem um comportamento padrão e pode ser sobrescrito (como no `TextReport`)

**c) Por que o método `generate()` foi declarado como `final`?**

Para que nenhuma subclasse possa alterar a ordem das etapas ou pular alguma delas. O objetivo do padrão é garantir que todos os relatórios sigam o mesmo esqueleto. As subclasses só podem personalizar as etapas que a classe base permite.

**d) Qual é a diferença entre uma etapa abstrata obrigatória e um hook com implementação padrão?**

A etapa abstrata não tem implementação na classe base, então toda subclasse é obrigada a implementá-la (o compilador exige). O hook já tem um comportamento padrão, e a subclasse só o sobrescreve se quiser mudar ou complementar esse comportamento. O `TextReport`, por exemplo, chama `super.afterGeneration()` e depois acrescenta a notificação.

**e) Como Template Method e Strategy diferem quanto ao uso de herança ou composição e à possibilidade de variar o comportamento em tempo de execução?**

O Template Method usa herança: o comportamento que varia está nas subclasses, e ele é definido quando o objeto é criado, sem poder ser trocado depois. O Strategy usa composição: o objeto guarda uma estratégia e pode trocá-la em tempo de execução. Além disso, o Template Method fixa um algoritmo com várias etapas e deixa algumas para as subclasses, enquanto o Strategy troca o algoritmo inteiro.
