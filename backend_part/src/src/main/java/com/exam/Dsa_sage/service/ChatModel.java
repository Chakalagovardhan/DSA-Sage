package com.exam.Dsa_sage.service;


import com.exam.Dsa_sage.entites.Response;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

@Service
public class ChatModel {



    private final ChatClient.Builder builder;

    public ChatModel(ChatClient.Builder builder) {
        this.builder = builder;
    }

    @Autowired
    private Questions questions;

    @Autowired
    private NotificationSenderservice notificationSenderservice;


    public String StringMaker(Response reponse)
    {
        StringBuilder chat= new StringBuilder("Give the dsa roadmap fro the following data where i have given my ocnfidence level according " +
                " to topic and the level using that parameters please genrate a road-map for 30 days daily task dont give study reources" +
                "the number side of the topic is that the confidence level of the student ratting himself from 1-5 range if the lower the number lower his" +
                "confidence level so lower confidence give some more time in roadmap dont give extra things direct roadmap ");
        String email=reponse.getEmail();
        Map<String,Integer> result=reponse.getResponse();
        for(Map.Entry<String,Integer> topic: result.entrySet())
        {
            chat.append(topic.getKey()).append(":").append(topic.getValue()).append(",");
        }
        return chat.toString();
    }

    public String chatModelRepsonse(String str)
    {


//        String questionsString = questions.getQuestionsbyId().getQuestions().toString();


        System.out.println(str);
        ChatClient client = builder.build();
        String response =client.prompt(str).call().content();
        System.out.println(response);
        return response;

    }





    public byte[] generatePdfInMemory(String content) throws Exception {
        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PDFont font = PDType1Font.HELVETICA;
            float fontSize = 12;
            float leading = 14.5f;
            float margin = 50;
            float yStart = 700;
            float bottomMargin = 50;
            float maxWidth = PDRectangle.LETTER.getWidth() - 2 * margin;

            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(font, fontSize);
            contentStream.setLeading(leading);

            float yPosition = yStart;
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);

            String[] lines = content.split("\n");

            for (String line : lines) {
                List<String> wrappedLines = wrapLine(line, maxWidth, font, fontSize);

                for (String wrap : wrappedLines) {
                    if (yPosition <= bottomMargin) {
                        contentStream.endText();
                        contentStream.close();

                        page = new PDPage();
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        contentStream.setFont(font, fontSize);
                        contentStream.setLeading(leading);

                        yPosition = yStart;
                        contentStream.beginText();
                        contentStream.newLineAtOffset(margin, yPosition);
                    }

                    contentStream.showText(wrap);
                    contentStream.newLine(); // leading handles the spacing
                }

            }

            contentStream.endText();
            contentStream.close();

            document.save(baos);
            return baos.toByteArray();
        }
    }

    // Helper method to wrap long lines
    private List<String> wrapLine(String text, float maxWidth, PDFont font, float fontSize) throws Exception {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();

        for (String word : text.split(" ")) {
            String testLine = line.length() == 0 ? word : line + " " + word;
            float size = font.getStringWidth(testLine) / 1000 * fontSize;

            if (size > maxWidth) {
                if (line.length() > 0) {
                    lines.add(line.toString());
                    line = new StringBuilder(word);
                } else {
                    // Word itself is too long, forcibly split
                    lines.add(word);  // Optional: break word mid-way if needed
                    line = new StringBuilder();
                }
            } else {
                line = new StringBuilder(testLine);
            }
        }

        if (line.length() > 0) {
            lines.add(line.toString());
        }

        return lines;
    }




}
