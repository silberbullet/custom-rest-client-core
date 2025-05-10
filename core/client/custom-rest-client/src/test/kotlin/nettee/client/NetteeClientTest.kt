package nettee.client

import example.client.request.ClientRequest
import example.restclient.CustomClient
import example.restclient.config.RestClientConfig
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import nettee.student.entity.Student
import nettee.student.persistence.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(RestClientConfig::class)
class NetteeClientTest(
    @Autowired private val repository: StudentRepository,
    @Autowired private val customClient: CustomClient
) : FreeSpec({

    "[HTTP GET 요청] 학생 조회" - {
        "학생 목록 조회 요청 할 때" - {
            // given & when
            val result = customClient.getList(ClientRequest.builder<List<Student>>()
                .domain("student")
                .path("/api/v1/student")
                .build())

            "학생 10명 조회" {
                result.size shouldBe 10
            }
        }

        "학생 단건 조회 요청 할 때" - {
            // given & when
            val result = customClient.get(ClientRequest.builder<Student>()
                .domain("student")
                .path("/api/v1/student/{id}")
                .responseType(Student::class.java)
                .uriVariables(arrayOf(5L))
                .build())

            "ID 5번 학생 조회" {
                result.id shouldBe 5L
            }
        }
    }

    "[HTTP POST 요청] 학생 추가" - {
        "학생 생성 요철 할 때" - {
            // given & when
            val result = customClient.post(ClientRequest.builder<Student>()
                .domain("student")
                .path("/api/v1/student")
                .responseType(Student::class.java)
                .build())

            "ID 10번 이상의 학생 추가" {
                result.id shouldBeGreaterThan  10L
            }
        }
    }

    beforeSpec {
        repeat(10) {
            repository.save(Student())
        }
    }
})