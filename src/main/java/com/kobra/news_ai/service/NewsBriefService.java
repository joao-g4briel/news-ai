package com.kobra.news_ai.service;

import com.kobra.news_ai.client.NewsApiClient;
import com.kobra.news_ai.dto.NewsApiResponse;
import com.kobra.news_ai.dto.NewsSummaryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewsBriefService {

    private final NewsApiClient newsApiClient;

    @Autowired
    public NewsBriefService(NewsApiClient newsApiClient) {
        this.newsApiClient = newsApiClient;
    }

    public NewsSummaryResponse generateGeneralNewsBrief() {
        final NewsApiResponse newsApiResponse = newsApiClient.getTopHeadlines();

        log.info("Requesting summary for {} articles", newsApiResponse.getArticles().size());

        return NewsSummaryResponse.builder()
                .createdAt(java.time.LocalDateTime.now())
                .summary("This is a placeholder summary.")
                .build();
    }
}
