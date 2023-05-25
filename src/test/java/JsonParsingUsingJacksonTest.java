import additional.OrderInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.InputStreamReader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonParsingUsingJacksonTest {
    private final ClassLoader cl = JsonParsingUsingJacksonTest.class.getClassLoader();

    @Test
    void JsonFileParsingTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try (
                InputStream is = cl.getResourceAsStream("order.json");
        ) {
            assert is != null;
            try (InputStreamReader isr = new InputStreamReader(is);
            ) {
                OrderInfo orderInfo = objectMapper.readValue(isr, OrderInfo.class);

                assertEquals(6, orderInfo.getOrderID());
                assertEquals("Zhansaya Kalsatova", orderInfo.getCustomerName());
                assertEquals("zhansayakalsat@gmail.com", orderInfo.getCustomerEmail());
                assertEquals(52, orderInfo.getOrderContent().getProductID());
                assertEquals("RedBull", orderInfo.getOrderContent().getProductName());
                assertEquals(10, orderInfo.getOrderContent().getQuantity());
                assertTrue(orderInfo.isOrderCompleted());
            }
        }
    }
}
