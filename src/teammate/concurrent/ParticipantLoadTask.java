package teammate.concurrent;

import teammate.exception.FileProcessingException;
import teammate.io.CsvParticipantReader;
import teammate.model.Participant;

import java.util.Collections;
import java.util.List;

public class ParticipantLoadTask implements Runnable {

    private final String filePath;
    private List<Participant> result;
    private Exception error;

    public ParticipantLoadTask(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        CsvParticipantReader reader = new CsvParticipantReader();
        try {
            result = reader.readParticipants(filePath);
        } catch (FileProcessingException e) {
            error = e;
        }
    }

    public List<Participant> getResult() {
        return result == null ? Collections.emptyList() : result;
    }

    public Exception getError() {
        return error;
    }
}
