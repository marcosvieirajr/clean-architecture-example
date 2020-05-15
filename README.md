
[![Test Coverage](https://img.shields.io/codecov/c/github/marcosvieirajr/clean-architecture-example?flag=CODECOV_TOKEN&token=113bff33-baf9-4eed-8c40-8a3c493430ce)]

# PREPAG - Clean Architecture

Exwmplo de implementação de Clean Architecture em uma silples API de cartão pré pago que pode ser adquirido sem muita burocracia, o PREPAG.

## User Stories

- [ ] Usuário poderá solicitar emissão do cartão
  - só será exigido como parâmetro de entrada o nome e saldo a ser creditado
  - a api deverá retornar: nome, número, cvv, validade, senha e saldo
  - o número do cartão de crédito deve possuir 16 números e os 6 primeiros dígitos, que representa o BIN do cartão, devem ser sempre o mesmo para esta API
  - o cvv é um código de 3 dígitos que, por segurança, não pode ser armazenado na base de dados. Ele sempre deve ser calculado utilizando um algoritmo que combina o ​número do cartão e a ​validade que deve resultar em um número de 3 dígitos
  - a senha inicial deve ser um número randômico de 4 dígitos salvo criptografado na base de dados
  - a validade do cartão deve ser de 2 anos contando da data de solicitação
- [ ] Sistema poderá autorizar a venda
  - parâmetro de entrada: número, validade, cvv, estabelecimento, valor e senha
  - para a compra ser aprovado a requisição deverá passar pelas seguintes validações: se o cartão existe, se o cartão não expirou, se o cvv é válido, se a senha é válida, se o cartão possui saldo suficiente para efetivar a compra
  - retornará código de erro para cada validação com sua respectiva mensagem
  - retornará codigo 00 e saldo novo_saldo_do_cartão_após_compra
