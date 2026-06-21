package com.kobra.news_ai.client;


import com.kobra.news_ai.dto.Article;
import com.kobra.news_ai.dto.OllamaRequest;
import com.kobra.news_ai.dto.OllamaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OllamaClient {

    @Value("${ollama.base.url}")
    private String ollamaUrl;

    @Value("${ollama.mistral.model}")
    private String aiModel;

    public OllamaResponse generateSummary(final List<Article> articles) {
        final RestTemplate restTemplate = new RestTemplate();

        final String prompt = getPrompt(articles);

        final OllamaRequest requestPayLoad = OllamaRequest.builder()
                .model(aiModel)
                .prompt(prompt)
                .stream(false)
                .build();

        final HttpEntity<OllamaRequest> entity = getHttpEntity(requestPayLoad);

        final ResponseEntity<OllamaResponse> response =
                restTemplate.postForEntity(ollamaUrl, entity, OllamaResponse.class);

        log.info("Ollama response: {}", response.getBody());
        return response.getBody();
    }

    private static HttpEntity<OllamaRequest> getHttpEntity(OllamaRequest requestPayLoad) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OllamaRequest> entity = new HttpEntity<>(requestPayLoad, headers);
        return entity;
    }

    private String getPrompt(List<Article> articles) {
        final StringBuilder promptBuilder = new StringBuilder();

        promptBuilder.append("You are an experienced international news editor. " +
                "Search for and analyze the top global news stories published today from reliable sources worldwide. " +
                "Prioritize major geopolitical events, government decisions, economic developments, financial markets, technology announcements, scientific discoveries, environmental events, and significant social issues. " +
                "Return the results entirely in Brazilian Portuguese. " +
                "For each story include: " +
                "- Headline (translated to Portuguese); " +
                "- Category (Politics, Economy, Technology, Science, Business, World, etc.); " +
                "- Country/Region; " +
                "- Summary (3-5 sentences in Portuguese); " +
                "- Impact or relevance (1 sentence). " +
                "Maintain a neutral and factual tone. " +
                "Avoid opinions, speculation, sensationalism, or unverified information. " +
                "Format the response as a professional daily news report in Portuguese (Brazil).");

        for (Article article : articles) {
            promptBuilder
                    .append("Title: ").append(article.getTitle()).append("\n")
                    .append("Description").append(article.getDescription()).append("\n")
                    .append("End of article\n\n");
        }

        final String prompt = promptBuilder.toString();
        return prompt;

    }
}
