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
