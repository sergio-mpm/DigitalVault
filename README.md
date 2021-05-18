# DigitalVault
 INF1416 - CofreDigital
 
 Trabalho realizado por:
 Alex Nascimento Rodrigues - 16212588
 Sergio Gustavo Mendonça Pyrrho Moreira - 1621240
 
 
 AJUSTE DO MYSQL-CONNECTOR-JAVA:
 
 O ajuste deve ser feito conforme o local que estará o arquivo JAR na máquina do usuário, aqui está o passo a passo de como confirgurar:
 1. Clicar com botão direito na pasta do projeto (No Project Explorer no caso do IDE Eclipse) e clicar em Propriedades;
 2. Selecionar a opção Java Build Path dentro das propriedades do projeto.
 3. Selecione o path do JAR que está presente na versão baixada.
 4. Clique em Add External JAR;
 5. Busque o diretório em que se encontre o JAR que você deseja inserir no programa (mysql-connector-java-8.0.16.jar e clique em Abrir;
 6. Por fim Clique em Apply;
 
 Pronto, o JAR que conecta o banco de dados (MySQL) ao programa estará inserido no programa.
 
 AJUSTE DOS DADOS DO BANCO NO PROGRAMA:
 
 O ajuste deve ser feito conforme definiçÕes do banco local a ser utilizado, aqui está o passo a passo de como configurar:
 1. Abra a classe BDConnect.java que se encontra no pacote models do programa;
 2. Troque na variável url o caminho em que se encontra o banco (por exemplo: "jdbc:mysql://localhost:3306/sectest?useTimezone=true&serverTimezone=UTC");
 3. Em User será definido o nome do usuário utilizado para acessar o banco (Em geral o usuário master do MySQL é chamado de root).
 4. Em password deverá ser definida a senha utilizada para acessar o banco (por exemplo: "root").
 
 Pronto, a conexão com o banco de dados está definida junto ao programa.
