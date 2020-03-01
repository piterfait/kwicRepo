FROM openjdk:latest

COPY /target/classes/ /

#con los ficheritos de texto debo hacer lo propio
COPY /src/frases.txt /src/frases.txt
COPY /src/noclaves.txt /src/noclaves.txt

CMD ["/usr/bin/java","Driver"]