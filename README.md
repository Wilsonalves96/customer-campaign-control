# Customer Campaign Control
Controle de clientes e campanhas

# Arquitetura

Para desenvolvimento, foi utilizado o Spring tool suite, e um tomcat server local, dentro do próprio spring tool 

Tal projeto está construido em cima do Spring Framework, utilizando o Maven como gerenciador de dependencias, utilizando recursos como (Spring Framework Web Features, JPA e MySQL)

A ideia principal de utilizar esse framework parte da ideia do controle que ele oferece. Todo esse controle, ele faz via notations, tais como @EnableAutoConfigure, @AutoWired e afins. Quando o projeto é iniciado, o Spring olha todos os recursos que você desenvolvou e publica estaticamente esses recursos para serem utilizados, dentro do seu escopo é claro.

Com isso, com nossas classes @RestController, após definir o mapeamento, o Spring cuida do trabalho de disponibilizar os serviços criados, dispondo também de features de segurança (Não foi utilizado pois a ideia era o teste e deixar todos os serviços públicos)

As web features do framework também ajudam a gerenciar a injeção de dependencia dos recursos criados. Juntamente com o JPA, dentro do Spring contamos com as interfaces de repositório, que fazem conexão com o banco de dados via hibernate (Para o projeto, está sendo utilizado um banco de dados MySQL dentro do serviço Cloud SQL da nuvem da google).

Também juntamente com o Maven, realizar o deploy de um arquivo .war para que seja possível executa-lo dentro de um tomcat.



# O que é Deadlock? Detalhe um pouco sobre o caso e como você poderia resolver isso. (5 pontos)

Inicialmente, deadlock se trata de processos paralelos, com dependencia entre si.
Processo 2 está aguardando o processo 1 terminar, e o processo 1 precisa do processo 2 para iniciar.
Claro que isso é um conceito muito superficila, mas os problemas já partem dai.

Deadlock existe em várias partes da programação, tanto para banco de dados, quanto para programação server-side, quanto para programação client-side, e até mesmo a nível de sistema operacional.

Para cada uma delas existem meios diferentes de evitar o deadlock, pois uma vez iniciado, nenhuma solução é tão simples quando corrigir o problema e reiniciar as operações. Apenas quando isso não é uma alternativa, deve ser estudado um meio de resolver tal problema, realizando análises de cada processo parado ou forçar a execução de algum processo, sabendo da perda que poderá causar.

Lembrando que a ideia é conhecer esses cenários, e fazer o possível para que não aconteça.

# 5)	Uma das grandes inclusões no Java 8 foi a API Stream. Com ela podemos fazer diversas operações de loop, filtros, maps, etc. Porém, existe uma variação bem interessante do Stream que é ParallelStreams. Descreva com suas palavras quando qual é a diferença entre os dois e quando devemos utilizar cada um deles

Basicamente, o Stream vai trabalhar cada operação de uma vez, seja uma filtro com um .Filter, ou uma busca específica com um .Find, ou até mesmo se algum item respeita seu critério com o .AnyMatch.

O ParallelStream trata-se da paralelização desses processos, seja um filtro, uma busca ou algum respeito de critério.

O ponto interessante nisso, é que o Java tem todo o controle necessário para paralilazação desses processos.

Internamente, o trabalho do parallelstream, vai ser distribuir seus processos pela quantidade de processadores que você tiver. Isso pode ser uma coisa boa e ruim ao mesmo tempo. Quando você paraleliza o processo, você pode ter problemas com GarbageCollector dentro do seu programa, pois as coisas serão escritas em memómria muito mais rápido, e se seu código não estiver preparado, você terá problemas.

Quanto mais processadores, memória e CPU a disposição, somado a um código bem escrito, o ParallelStream é a melhor escolha para certo tipo de operações. Para saber o que utilizar e em qual situação, vai sempre da analise do desenvolvedor.
