# springboot-redis-streams
Aplicação em Springboot utilizando redis-streams

## Arquitetura

Para o cenário proposto foi escolhida a arquitetura utilizando a funcionalidade de streams do redis. As razões pela escolha dessa arquitetura são:
- Escalabilidade: A qualquer momento é possível escalar de forma individual tanto o Producer/API quanto o Consumer.

- Histórico: Todas as mensagens recebidas ficam armazenadas possibilitando ter um histórico de todas as operações feitas.

- Disponibilidade: Se o consumer estiver indisponível no momento em que a mensagem chega o consumer poderá consumi-la assim que ficar disponível.

### Redis Streams vs Publisher/Subscribe

| Pub/Sub                                                                              	| Stream                                                                                                                    	|
|--------------------------------------------------------------------------------------	|---------------------------------------------------------------------------------------------------------------------------	|
| Não persistente                                                                      	| Persistente                                                                                                               	|
| Modelo fire & forget. Para receber a mensagem o  recebedor precisa estar executando. 	| Os consumers não precisam estar rodando quando a mensagem chega. Podem consumir as mensagens assim que estiver operando.  	|
| Todos os subscribers recebem a mesma mensagem                                        	| Somente um consumer dentro de consumer group receberá a mensagem. Evitando assim o processamento de mensagens duplicadas. 	|

### Solicitar Pagamento

![](./doc/request_payment.png)

- Nessa arquitetura um `client` faz uma requisição `REST` para a `API` solicitando um pagamento, a `API` publica esse evento no `Redis`.
- Um `consumer` do grupo de `consumers` processará esse evento chamando a `API` da `ADIQ` e a resposta gerará um novo evento `payment_authorization` que será publicado no `Redis`. O `client` então poderá consumir esses eventos para obtrem a resposta da chamada (não implementado). O `consumer` do evento `solicitar pagamento` poderia armazenar essas informações em uma base de dados noSQL ou em uma base relacional (não implementado). 

### Cancelar Pagamento

![](./doc/cancel_payment.png)

- O `cancelar pagamento` segue a mesma arquitetura do `solicitar pagamento`

