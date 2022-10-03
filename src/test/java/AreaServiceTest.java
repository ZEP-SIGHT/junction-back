import com.junction.tonight.spark.dto.NumberVisitor;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AreaServiceTest {

    @Test
    void testSumFromMap() {
        Map<String, Integer> sample = new HashMap<>();
        sample.put("a", 1);
        sample.put("b", 2);
        sample.put("v", 3);
        sample.put("d", 4);

        int totalCount = sample.values().stream().mapToInt(data -> data).sum();
        assertEquals(totalCount, 10);
    }

    @Test
    void testNumberOfVisitors() {
        HashMap<String, Integer> areaMap = new HashMap<>();
        areaMap.put("a", 1);
        areaMap.put("b", 2);
        areaMap.put("v", 3);
        areaMap.put("d", 4);


        int totalCount = areaMap.values()
                .stream()
                .mapToInt(data -> data)
                .sum();

        NumberVisitor build = NumberVisitor.builder()
                .totalNumber(totalCount)
                .areaData(areaMap)
                .build();

        Integer numberOfVisitorA = build.getAreaData().get("a");
        Integer valueA = areaMap.get("a");
        Integer totalNumber = build.getTotalNumber();

        assertEquals(numberOfVisitorA, valueA);
        assertEquals(totalNumber, 10);
    }

}