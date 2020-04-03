package com.hibicode.demoawssqs.sqs.listener;

import com.hibicode.demoawssqs.account.AccountApi;
import com.hibicode.demoawssqs.account.request.AccountRequest;
import com.hibicode.demoawssqs.account.response.AccountResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class TextSqsListener {

    private static final Logger logger = LoggerFactory.getLogger(TextSqsListener.class);

    @Autowired
    private AccountApi accountApi;

    @SqsListener("hibicode-first-queue.fifo")
    @SendTo("hibicode-second-queue")
    public String handle(String accountId) {
        throwRandomException(accountId);

        try {
            logger.info("Get ammount for accountId: " + accountId);
            AccountResponse accountResponse = accountApi.getAmount(Integer.valueOf(accountId));
        } catch (FeignException.NotFound e) {
            logger.info(">>>> Amount not found, saving for accountId: " + accountId);
            accountApi.save(new AccountRequest(2, new BigDecimal("5")));
        }

        return "Done for account: " + accountId + " - " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

    }

    private void throwRandomException(String accountId) {
        int random = new Random().nextInt(4);

        logger.info("#### Random: " + random);
        if (random >= 2) {
            logger.error("Vamos retornar um erro pra account: " + accountId);
            throw new RuntimeException("Erro consumindo mensagem");
        }
    }

}
