package fr.lacombe.magic6tem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckIn {
    private List<Participant> participants;

    public CheckIn(List<Participant> participants) {
        this.participants = participants;
    }

    public CheckIn() {
        this.participants = new ArrayList<>();
    }

    public int  getCountOfColdMealFor() {
        return participants.size();
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }
}
