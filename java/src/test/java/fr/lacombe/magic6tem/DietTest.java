package fr.lacombe.magic6tem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DietTest {

    @Nested
    @DisplayName("Get count of covers should")
    class GetCountCover {

        @Test
        public void return_0_when_given_no_participant(){
            assertThat(Restaurant.getCountOfCovers()).isEqualTo(0);
        }




    }


}
