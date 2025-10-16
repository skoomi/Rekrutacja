<img width="758" height="57" alt="image" src="https://github.com/user-attachments/assets/220601ac-9b23-44d4-a075-e461ed251761" />

Default values are saved in properties file: \
posts endpoint: https://jsonplaceholder.typicode.com/posts \
directory for saving files: output/posts 

Building from cmd: mvnw.cmd package \
Running from cmd: 
1) With maven: mvnw.cmd spring-boot:run 
2) From jar: java -jar Rekrutacja-0.0.1-SNAPSHOT.jar

To change output directory run with flag: --app.outputDir.posts=newPath \
(e.g. java -jar Rekrutacja-0.0.1-SNAPSHOT.jar --app.outputDir.posts=postyZneta) 
