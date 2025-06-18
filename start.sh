bash
#!/bin/bash

#Carrega variáveis do .env
if [ -f .env ]; then
  export(grep -v '^#' .env | xargs)
  echo "Variáveis do .env carregadas com sucesso!"
else
  echo "Arquivo .env não encontrado. Usando valores padrão."
fi

#Limpa, compila e executa o projeto
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=$SPRING_PROFILES_ACTIVE
