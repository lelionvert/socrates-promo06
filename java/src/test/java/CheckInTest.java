import fr.lacombe.magic6tem.CheckIn;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CheckInTest {

    @Test
    public void given_checkindate_with_no_participant_should_return_zero_meal(){
        CheckIn checkInWithoutParticipant = new CheckIn(LocalDateTime.now());

        assertThat(checkInWithoutParticipant.getCountOfColdMealFor()).isEqualTo(0);

    }
}
