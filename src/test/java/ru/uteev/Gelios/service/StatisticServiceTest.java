package ru.uteev.Gelios.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import ru.uteev.Gelios.model.Document;
import ru.uteev.Gelios.model.Statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StatisticServiceTest {

    @Autowired
    private StatisticService statisticService;

    @BeforeTestClass
    Collection<Document> initDocument() {
        Document doc1 = new Document(12, 23, new BigDecimal("100.4"),  new Date());
        Document doc2 = new Document(12, 23, new BigDecimal("100.1"),  new Date());
        Document doc3 = new Document(12, 23, new BigDecimal("100.6"),  new Date());

        List<Document> list = new ArrayList<>();
        list.add(doc1);
        list.add(doc2);
        list.add(doc3);

        return new ArrayList<>(list);
    }

    @Test
    void getStatistic() {
        statisticService.getData(initDocument());
        Statistics stat = statisticService.getStatistic(0,0);

        BigDecimal sumForEquals = new BigDecimal("301.1");
        int resultSum = stat.getSum().compareTo(sumForEquals);
        assertEquals(0, resultSum);

        BigDecimal minForEquals = new BigDecimal("100.1");
        int resultMin = stat.getMinAmount().compareTo(minForEquals);
        assertEquals(0, resultMin);

        BigDecimal maxForEquals = new BigDecimal("100.6");
        int resultMax = stat.getMaxAmount().compareTo(maxForEquals);
        assertEquals(0, resultMax);
    }

}