package com.example.JavaProject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.JavaProject.enums.BRRegion;
import com.example.JavaProject.enums.BRState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BRStateTest {
   private Map<BRRegion, List<BRState>> brStatesByRegion =
           BRState.stream().collect(Collectors.groupingBy(BRState::getRegion));

   @Test
   void brazil_should_have_27_states() {
      assertEquals(27, BRState.values().length);
   }

   @Test
   void brazil_should_have_5_regions() {
      assertEquals(5, brStatesByRegion.size());
   }

   @Test
   void brazil_should_contains_the_correct_number_of_states_by_region() {
      assertEquals(7, brStatesByRegion.get(BRRegion.N).size());
      assertEquals(9, brStatesByRegion.get(BRRegion.NE).size());
      assertEquals(4, brStatesByRegion.get(BRRegion.SE).size());
      assertEquals(3, brStatesByRegion.get(BRRegion.S).size());
      assertEquals(4, brStatesByRegion.get(BRRegion.CO).size());
   }
}
