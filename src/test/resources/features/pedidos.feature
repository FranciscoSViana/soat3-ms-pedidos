# language: pt
Funcionalidade: Salvar um pedido

  Cenário: Salvar um novo pedido
    Dado um novo pedido a ser salvo
    Quando o pedido é enviado para ser salvo
    Então o sistema deve retornar o pedido salvo com sucesso
