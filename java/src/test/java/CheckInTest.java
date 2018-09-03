import fr.lacombe.magic6tem.CheckIn;
import fr.lacombe.magic6tem.Participant;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CheckInTest {

    @Test
    public void given_checkindate_with_no_participant_should_return_zero_meal(){
        List<Participant> participants = new ArrayList<>();
        CheckIn checkInWithoutParticipant = new CheckIn(participants);

        assertThat(checkInWithoutParticipant.getCountOfColdMealFor()).isEqualTo(0);

    }

}
