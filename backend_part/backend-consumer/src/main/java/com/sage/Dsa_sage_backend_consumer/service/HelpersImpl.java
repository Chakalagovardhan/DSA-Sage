package com.sage.Dsa_sage_backend_consumer.service;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HelpersImpl implements Helpers {

    private final ChatClient.Builder builder;

    public HelpersImpl(ChatClient.Builder builder) {
        this.builder = builder;
    }


    @Override
    public String chatResponse(String str) {
        ChatClient client = builder.build();
        String response =client.prompt(str).call().content();
        return response;
    }

    public String StringMaker(Map<String,Integer> reponse,int timeInWeeks,int dailyTime)
    {
        StringBuilder chat= new StringBuilder("Give the dsa roadmap for the following data where i have given my ocnfidence level according " +
                " to topic and the level using that parameters please genrate a road-map for"+ timeInWeeks+"  weeks task dont give study reources only give the roadmap to prepare and imp question related to that topic to practice also" +
                " where i can spend "+dailyTime+" daily each day the number side of the topic is that the confidence level of the student ratting myself from 0-4 range if the lower the number lower his" +
                "confidence level so lower confidence give some more time in roadmap dont give extra things direct roadmap and note that give in a such way that" +
                "topic wise dont club if one topic is completed move to another topic dont club them all  ");

        Map<String,Integer> result=reponse;
        for(Map.Entry<String,Integer> topic: result.entrySet())
        {
            chat.append(topic.getKey()).append(":").append(topic.getValue()).append(",");
        }

        return chat.toString();
    }

    public byte[] generatePdf(String content) {
        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PDFont font = PDType1Font.HELVETICA;
            PDFont boldFont = PDType1Font.HELVETICA_BOLD;
            float fontSize = 12;
            float boldFontSize = 14; // Slightly larger for day headings
            float leading = 16f;
            float margin = 50;
            float yStart = 700;
            float bottomMargin = 70;
            float maxWidth = PDRectangle.LETTER.getWidth() - 2 * margin;

            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(font, fontSize); // Set initial font

            float yPosition = yStart;
            boolean isNewPage = true;

            String[] lines = content.split("\n");

            for (String line : lines) {
                // Check if we need a new page
                if (yPosition <= bottomMargin) {
                    contentStream.close();
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(font, fontSize); // Reset font on new page
                    yPosition = yStart;
                    isNewPage = true;
                }

                // Skip empty lines
                if (line.trim().isEmpty()) {
                    yPosition -= leading;
                    continue;
                }

                // Highlight day lines
                if (line.trim().matches("^Day\\s+\\d+:.*")) {
                    if (!isNewPage) {
                        yPosition -= leading; // Add extra space before day heading
                    }

                    contentStream.setFont(boldFont, boldFontSize);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText(line.trim());
                    contentStream.endText();
                    yPosition -= leading * 1.5f;

                    // Reset to normal font
                    contentStream.setFont(font, fontSize);
                    continue;
                }

                // Handle regular text with word wrapping
                List<String> wrappedLines = wrapLine(line, maxWidth, font, fontSize);

                for (String wrap : wrappedLines) {
                    if (yPosition <= bottomMargin) {
                        contentStream.close();
                        page = new PDPage();
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        contentStream.setFont(font, fontSize);
                        yPosition = yStart;
                        isNewPage = true;
                    }

                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText(wrap);
                    contentStream.endText();

                    yPosition -= leading;
                    isNewPage = false;
                }

                // Add extra space between paragraphs
                yPosition -= leading * 0.5f;
            }

            contentStream.close();
            document.save(baos);
            return baos.toByteArray();
        } catch (Exception ex) {
            System.err.println("Error generating PDF: " + ex.getMessage());
            ex.printStackTrace(); // Print full stack trace for debugging
            return null;
        }
    }

    private List<String> wrapLine(String text, float maxWidth, PDFont font, float fontSize) throws IOException {
        List<String> lines = new ArrayList<>();
        if (text == null || text.trim().isEmpty()) {
            return lines;
        }

        StringBuilder line = new StringBuilder();
        String[] words = text.split(" ");

        for (String word : words) {
            if (word.isEmpty()) continue;

            String testLine = line.length() == 0 ? word : line + " " + word;
            float width = font.getStringWidth(testLine) / 1000 * fontSize;

            if (width <= maxWidth) {
                line.append(line.length() == 0 ? word : " " + word);
            } else {
                if (line.length() > 0) {
                    lines.add(line.toString());
                    line = new StringBuilder(word);
                } else {
                    // Handle very long words that exceed line width
                    int start = 0;
                    while (start < word.length()) {
                        int end = start + 1;
                        while (end <= word.length()) {
                            String subWord = word.substring(start, end);
                            float subWidth = font.getStringWidth(subWord) / 1000 * fontSize;
                            if (subWidth > maxWidth) break;
                            end++;
                        }
                        end--; // Back up to last valid position
                        if (start == end) end = start + 1; // Ensure progress for non-printable chars
                        lines.add(word.substring(start, end));
                        start = end;
                    }
                }
            }
        }

        if (line.length() > 0) {
            lines.add(line.toString());
        }

        return lines;
    }


}
