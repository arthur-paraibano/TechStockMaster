# TechStockMaster
Projeto para um controle interno de materiais na área do TI.

## Olá pessoal 👋

- Em desenvolvimento 

Projeto que visa um controle interno de saídas de materiais do setor TI. 

----------------------------------------------------------------------------------------------------------------

1° Tela:
![01](https://github.com/arthur-paraibano/TechStockMaster/assets/110489981/a17e23a9-7f5d-459f-9ce3-5c4d8989e382)
Tela de acesso rápido para o usuário fornece Entrada de materiais, Saida de materiais e Estoque atual na tabela abaixo.
- Outras ações: Contem acesso para outras três interfaces.
- Cadastro de estoque: Entrada de materiais, um combobox padronizado com os materiais.
- Saída de estoque: Saída de materiais.
- Tabela: Fornece informações de maneira rápida a quantidade em estoque.
- Gerar Relatório de Estoque e Relatório de Saída: Puxam direto do Banco de dados e fornecer as informações de maneira atualizada. Através do JasperSoft.

  Exemple de relatório:
 ![08](https://github.com/arthur-paraibano/TechStockMaster/assets/110489981/22d502b8-d226-4f3e-8c7f-613382381d13)

----------------------------------------------------------------------------------------------------------------

2° Tela:
![02](https://github.com/arthur-paraibano/TechStockMaster/assets/110489981/9914e9fc-5c56-4e00-b92a-649757fc70d5)
- Cadastro de equipamento: Informar o nome do material e a observação iniciando com a unidade de medida.
- Editar registro: Selecione um item da tabela para pode mudar o nome ou a descrição do material.
- Excluir registro: Desativado, colocar uma tela de login e dar permissões aos usuários para que eles possam ter acesso a este item.

  Tela de "Editar registro":
![03](https://github.com/arthur-paraibano/TechStockMaster/assets/110489981/0e272062-82e2-420d-acc2-f08f916c5d85)

----------------------------------------------------------------------------------------------------------------

3° Tela:
![04](https://github.com/arthur-paraibano/TechStockMaster/assets/110489981/0bcd107d-bbcc-432e-a822-371ef12492c3)
Objetivo desta tela é ter uma comunicação rápida e eficiente com quem solicita compras de materiais.
- Solicitação de Compra: Campos aonde deve ser preenchido pelo técnico para solicitar algum material.
- Informações: Tabela aonde exibir as solicitações dos técnico(s) e um "Status" aonde mostrar de material foi comprado ou tem outra informação, acrescentei cores para cada status para melhor visualização.
- Relatório de Solicitações: Gera um relatório através do JasperSoft diretamente do Banco de dados.
- Status: Tela aonde o solicitante pode alterar status e quantidade que ira comprar, se for necessário.
  
  Exemplo da tela "Status":
![05](https://github.com/arthur-paraibano/TechStockMaster/assets/110489981/091b3aca-15f9-4bbe-bb10-7c272de23e9f)

----------------------------------------------------------------------------------------------------------------

4° Tela:
![06](https://github.com/arthur-paraibano/TechStockMaster/assets/110489981/c7700e36-c901-4c16-ba8f-c36499aafece)
Objetivo desta tela e manter todo setor TI com todos os técnicos e supervisores informados de materiais com problemas.
- Concertos de Equipamentos: Informa quais materiais precisam ir para concerto.
- Tabela: informa quais materiais foram ou retornaram do concerto, na coluna "Status" coloquei cores para melhor identificação dos materiais defeituosos.
- Editar Status: Alterar status dos equipamentos, porem ser alterados dando um clique em cima do nome "Aguardando envio", por exemplo.

  Exemplo da tela "Editar Status":
![07](https://github.com/arthur-paraibano/TechStockMaster/assets/110489981/a9d3b0a2-0b5a-4f7f-ae6d-9e933899bea0)

----------------------------------------------------------------------------------------------------------------

(Em desenvolvimento):

V1.2 = Telas separadas de entrada de materiais, saídas e controle de estoque.
Telas visivelmente mais agradável com redimensionamento de telas, telas de login, telas de interação com o usuário.
