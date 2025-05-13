package exmaple.student.client;

import example.client.request.ClientRequest;
import example.restclient.CustomClient;
import example.school.School;
import example.student.port.SchoolCommandRestClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolCommandRestClientAdapter implements SchoolCommandRestClientPort {

    private final CustomClient customClient;

    @Override
    public School updateStudentCnt(Long id) {
        return customClient.patch(ClientRequest.<School>builder()
                .domain("school")
                .path("/{id}")
                .responseType(School.class)
                .uriVariables(new Object[] { id })
                .build());
    }
}
