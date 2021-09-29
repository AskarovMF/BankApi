package ru.askarov.bankapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.askarov.bankapi.controller.BalanceController;
import ru.askarov.bankapi.model.TransferBalance;
import ru.askarov.bankapi.repository.BalanceRepository;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class BankApiApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetBalanceOnAccount() throws Exception{
        mvc.perform(get("/getBalanceOnAccount/1000"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("111.00"));
    }

    @Test
    public void testGetBalanceWithWrongAccount() throws Exception{
        mvc.perform(get("/getBalanceOnAccount/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Номер счета не найден"));
    }

    @Test
    public void testCreateNewCard() throws Exception{
        mvc.perform(post("/createNewCard/1000"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"cardNumber\":1008,\"accountNumber\":1000}"));
    }

    @Test
    public void testCreateNewCardWithWrongAccount() throws Exception{
        mvc.perform(post("/createNewCard/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Номер счета не найден"));
    }

    @Test
    public void testGetAllCards() throws Exception{
        mvc.perform(get("/getAllCards/1001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"accountNumber\":1001,\"cardNumber\":1000}," +
                        "{\"accountNumber\":1001,\"cardNumber\":1001}]"));
    }

    @Test
    public void testGetAllCardsWithWrongAccount() throws Exception{
        mvc.perform(get("/getAllCards/-1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Номер счета не найден"));
    }

    @Test
    public void testTransfer() throws Exception{
        TransferBalance balance = new TransferBalance();
        balance.setTo(1000L);
        balance.setAmount(new BigDecimal(500));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(balance );

        mvc.perform(patch("/addBalanceOnAccount").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"to\":1000,\"amount\":611}]"));
    }

    @Test
    public void testTransferWithWrongAccount() throws Exception{
        TransferBalance balance = new TransferBalance();
        balance.setTo(-1L);
        balance.setAmount(new BigDecimal(500));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(balance );

        mvc.perform(patch("/addBalanceOnAccount").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Номер счета не найден"));
    }

    @Test
    public void testTransferWithWrongAmount() throws Exception{
        TransferBalance balance = new TransferBalance();
        balance.setTo(1000L);
        balance.setAmount(new BigDecimal(-100));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(balance );

        mvc.perform(patch("/addBalanceOnAccount").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Неверная сумма"));
    }

    @Test
    public void testTransferWithNullAmountt() throws Exception{
        TransferBalance balance = new TransferBalance();
        balance.setTo(1000L);
        balance.setAmount(null);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(balance );

        mvc.perform(patch("/addBalanceOnAccount").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Неверная сумма"));
    }
}
