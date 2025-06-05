Introduction   Dsa-Sage: AI-Powered DSA Interview Preparation Platform
Dsa-Sage is a powerful, AI-driven platform designed to help students and professionals master Data Structures and Algorithms (DSA) through interactive chat-based learning. It combines a modern architecture with real-time communication and intelligent suggestions to deliver an engaging and personalized DSA preparation experience.

At its core, the system integrates Spring Boot, Kafka, Redis, and React to facilitate seamless user interaction, dynamic content generation, and intelligent response handling via AI models like ChatGPT.


 Tech Stack
Layer         Technology Used
Frontend      React.js, Tailwind CSS
Backend       Java 17, Spring Boot, Spring Web, Spring Kafka
AI Engine     Spring AI (OpenAI Client)
Messaging     Apache Kafka
Database      No persistent DB used for this phase (can be extended)
Postman       For testing API endpoints

                             
 Additional Tools & Libraries
. Lombok – Simplifies boilerplate code (getters/setters)

. Spring AI – For chat-based AI communication with OpenAI

. Kafka Producer/Consumer – For real-time message passing

. Postman – For testing API endpoints

. Spring Dev Tools – Live reload during development

. Git & GitHub – Version control and source hosting

. VS Code / IntelliJ IDEA – Development environment

Dsa-sage-backend-producer(Dependencies)
• Spring Web
• Spring MongoDB
• Lombok
• Spring Boot Dev Tools   (Optional)
• Spring for Apache Kafka
Dsa-sage-backend-consumer(Dependencies)	
• Spring Web
• Lombok
• Spring Boot Dev Tools   (Optional)
• Spring for Apache Kafka
• Java Mail Sender
• Open Ai
• Pdf box (copied form maven)
***********************************************************************************************************************************************************************************************
Note:- You need to have kafka and Zookeper installed and turn on them before you run them and nedd to change the java .properties file like location of the db and the other stuff in your local
You need to have the nodejs installed to run the react app
************************************************************************************************************************************************************************************************
To run Kafka and zookeeper
1. kafka path:-cd C:\kafka
2. Zookerper starter:- zookeeper-server-start.bat .\config\zookeeper.properties
3. kafka starter:- kafka-server-start.bat .\config\server.properties
4. kafka-console-consumer.bat --topic user-response --from-beginning --bootstrap-server localhost:9092
For frontend running you need only 
1. just pull from the git
2. Normally npm build
3. To run the file npm run dev

Home page of the portal where you need submit the mail Id to get reponse of DSA sheet
![image](https://github.com/user-attachments/assets/8ba3912e-6668-4156-969d-040129ca6b70)
The second image represent exam portal where the user reponse is being recorded
![image](https://github.com/user-attachments/assets/debf26a1-3032-4393-9e0a-725088da189c)


Note:This is like a microservice of the Easy Guide website the navigation to this website done from the Easy Guide website itself it will be clubed into that later


