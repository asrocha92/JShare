# Criação do projeto com nome: JShare141107

# Versão apresentada pelo professor

# Criando o pacote da interface do servidor. Data:08/04/2016
	+ br.menu.servidor
	+ criando a Jframe MenuServidor
	+ Primeiro método configura
	+ Segundo método listar endereços de IPs presente na máquina

# Criação da classe Menu UI2
	+ Criado o método configura os componentes
	+ Criado o método conectar que conecta no servidor
	+ Criado o método encerrar conexão com servidor
	+ Criado o método pesquisar, para poder pesquisar nome pelo nome do arquivo por alguma pessoa que conectou no servidor
	+ Criado o método que faz download do arquivo, na onde conecta no cliente que possui o arquivo e faz download do mesmo
	+ Criado o método escreverDownload na onde ele escreve o arquivo baixado, na pasta local no projeto
	+ Criado o método para instaciar cliente
	+ Criado o método addLisaDeArqAposPesq para poder listar arquivo após a pesquisa na tela para o usuário
	
# Implementação para iniciar um serviço no cliente na classe UI2
	+ Criado o método iniciarServ
	+ Criado o método encerrarServ
	+ Criado o método mostrar que fechou a conexão para todos usuários
	+ Criado o método mostrar para mostrar mesagem no console do que esta acontecendo
	+ Implementação dos métodos da interface UIService
	+ implementado o método registrar cliente
	+ implementado o método procurar arquivo
	+ implementado o método baixar arquivo
	+ implementado o método desconectar cliente registrado
	+ implementado o método publicar arquivo assim que se conecta no serviço de um cliente até mesmo do servidor
# Modificação e criação
	+ Criado um classe Util que possui os respectivos métodos:
	+ verificar nome do cliente se doi digitado
	+ verificar se o ip foi dígitado corretamente
	+ verificar se a porta par se conectar no serviço foi dígitada corretamente e faz a converção para um inteiro após a verificação da porta
	
	+ Na classe Leitura de arquivo separei em dois métodos:
	+ 1 - Para ler arquivo localizado na pasta indicada e retorna o arquivo em bytes[] para que possar ser copiado
	+ 2 - Para escrever arquivo, que passado por parametro o local na onde vai ser salvo e o tamanho do mesmo em bytes para possa ser escrito no HD.
	
	+ Na classe LerIP foi criado 2 métodos para retornar o endereço de ip fixo o outro retorna possível lista de IP que a sua máquina possa estar utilizando
	
	+ Na classe ListarDiretoriosArquios foi separado em método listar arquivos e listar diretorios.
	 
	
	