package com.callcenter.callcenterimplementation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class RequestControllerTest {

    private final String REQUEST = "    {\n" +
            "  \"number_of_calls\": \"30\",\n" +
            "  \"je\": [\n" +
            "    \"5,7,6,4,6\",\n" +
            "    \"5,8,7,5,10\",\n" +
            "    \"7,5,6,14,6\",\n" +
            "    \"10,4,9,5,12\",\n" +
            "    \"6,10,11,4,6\"\n" +
            "  ],\n" +
            "  \"se\": [\n" +
            "    \"6,14,12,10,5\",\n" +
            "    \"18,8,6,4,12\",\n" +
            "    \"8,6,13,7,1\"\n" +
            "  ],\n" +
            "  \"mgr\": \"20,12,25,13,20,3,3,3,9,2,7,1,7,11,10\"\n" +
            "       }";

    private final String BAD_REQUEST = "{\n" +
            "  \"number_of_calls\": \"20\",\n" +
            "  \"je\": [\n" +
            "    \"9,12,15,13,6,3,3,3\",\n" +
            "  \t\"10,9,11,1,4,1,1,4\",\n" +
            "\t\"1,4,12,8,4,5,9,2\",\n" +
            "  \t\"9,6,6,3,1,12,15,15\",\n" +
            "\t\"6,14,6,13,3,14,13,2\"\n" +
            "],\n" +
            "  \"se\": [\n" +
            "\t\"6,5,4,8,10,9,2,10\",\n" +
            "  \t\"2,13,2,8,6,15,10,14\",\n" +
            "\t\"11,14,2,13,13,15,4,13\"\n" +
            "]\n" +
            "}";

    private final String RESPONSE = "{\n" +
            "    \"number_of_calls\": \"30\",\n" +
            "    \"resolved\": \"23\",\n" +
            "    \"unresolved\": \"7\",\n" +
            "    \"totalTimeTakenInMinutes\": \"302\",\n" +
            "    \"performance\": [\n" +
            "        {\n" +
            "            \"manager\": {\n" +
            "                \"id\": \"mgr\",\n" +
            "                \"timeTakenInMinutes\": \"57\",\n" +
            "                \"callsAttended\": \"3\",\n" +
            "                \"resolved\": \"1\",\n" +
            "                \"unresolved\": \"2\"\n" +
            "            },\n" +
            "            \"junior_Executives\": [\n" +
            "                {\n" +
            "                    \"id\": \"je1\",\n" +
            "                    \"timeTakenInMinutes\": \"28\",\n" +
            "                    \"callsAttended\": \"5\",\n" +
            "                    \"resolved\": \"5\",\n" +
            "                    \"escalated\": \"0\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"je2\",\n" +
            "                    \"timeTakenInMinutes\": \"35\",\n" +
            "                    \"callsAttended\": \"5\",\n" +
            "                    \"resolved\": \"3\",\n" +
            "                    \"escalated\": \"2\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"je3\",\n" +
            "                    \"timeTakenInMinutes\": \"38\",\n" +
            "                    \"callsAttended\": \"5\",\n" +
            "                    \"resolved\": \"4\",\n" +
            "                    \"escalated\": \"1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"je4\",\n" +
            "                    \"timeTakenInMinutes\": \"40\",\n" +
            "                    \"callsAttended\": \"5\",\n" +
            "                    \"resolved\": \"2\",\n" +
            "                    \"escalated\": \"3\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"je5\",\n" +
            "                    \"timeTakenInMinutes\": \"37\",\n" +
            "                    \"callsAttended\": \"5\",\n" +
            "                    \"resolved\": \"3\",\n" +
            "                    \"escalated\": \"2\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"senior_Executives\": [\n" +
            "                {\n" +
            "                    \"id\": \"se1\",\n" +
            "                    \"timeTakenInMinutes\": \"32\",\n" +
            "                    \"callsAttended\": \"3\",\n" +
            "                    \"resolved\": \"1\",\n" +
            "                    \"escalated\": \"2\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"se2\",\n" +
            "                    \"timeTakenInMinutes\": \"0\",\n" +
            "                    \"callsAttended\": \"0\",\n" +
            "                    \"resolved\": \"0\",\n" +
            "                    \"escalated\": \"0\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"se3\",\n" +
            "                    \"timeTakenInMinutes\": \"35\",\n" +
            "                    \"callsAttended\": \"5\",\n" +
            "                    \"resolved\": \"4\",\n" +
            "                    \"escalated\": \"1\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private final String REQUEST_1 = "{\n" +
            "  \"number_of_calls\": \"30\",\n" +
            "  \"je\": [\n" +
            "    \"10\",\n" +
            "    \"10\",\n" +
            "    \"10\",\n" +
            "    \"10\",\n" +
            "    \"10\"\n" +
            "  ],\n" +
            "  \"se\": [\n" +
            "    \"16\",\n" +
            "    \"16\",\n" +
            "    \"16\"\n" +
            "  ],\n" +
            "  \"mgr\": \"25\"\n" +
            "}";

    private final String RESPONSE_1 = "{\n" +
            "    \"number_of_calls\": \"30\",\n" +
            "    \"resolved\": \"0\",\n" +
            "    \"unresolved\": \"30\",\n" +
            "    \"totalTimeTakenInMinutes\": \"123\",\n" +
            "    \"performance\": [\n" +
            "        {\n" +
            "            \"manager\": {\n" +
            "                \"id\": \"mgr\",\n" +
            "                \"timeTakenInMinutes\": \"25\",\n" +
            "                \"callsAttended\": \"1\",\n" +
            "                \"resolved\": \"0\",\n" +
            "                \"unresolved\": \"1\"\n" +
            "            },\n" +
            "            \"junior_Executives\": [\n" +
            "                {\n" +
            "                    \"id\": \"je1\",\n" +
            "                    \"timeTakenInMinutes\": \"10\",\n" +
            "                    \"callsAttended\": \"1\",\n" +
            "                    \"resolved\": \"0\",\n" +
            "                    \"escalated\": \"1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"je2\",\n" +
            "                    \"timeTakenInMinutes\": \"10\",\n" +
            "                    \"callsAttended\": \"1\",\n" +
            "                    \"resolved\": \"0\",\n" +
            "                    \"escalated\": \"1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"je3\",\n" +
            "                    \"timeTakenInMinutes\": \"10\",\n" +
            "                    \"callsAttended\": \"1\",\n" +
            "                    \"resolved\": \"0\",\n" +
            "                    \"escalated\": \"1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"je4\",\n" +
            "                    \"timeTakenInMinutes\": \"10\",\n" +
            "                    \"callsAttended\": \"1\",\n" +
            "                    \"resolved\": \"0\",\n" +
            "                    \"escalated\": \"1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"je5\",\n" +
            "                    \"timeTakenInMinutes\": \"10\",\n" +
            "                    \"callsAttended\": \"1\",\n" +
            "                    \"resolved\": \"0\",\n" +
            "                    \"escalated\": \"1\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"senior_Executives\": [\n" +
            "                {\n" +
            "                    \"id\": \"se1\",\n" +
            "                    \"timeTakenInMinutes\": \"16\",\n" +
            "                    \"callsAttended\": \"1\",\n" +
            "                    \"resolved\": \"0\",\n" +
            "                    \"escalated\": \"1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"se2\",\n" +
            "                    \"timeTakenInMinutes\": \"16\",\n" +
            "                    \"callsAttended\": \"1\",\n" +
            "                    \"resolved\": \"0\",\n" +
            "                    \"escalated\": \"1\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"se3\",\n" +
            "                    \"timeTakenInMinutes\": \"16\",\n" +
            "                    \"callsAttended\": \"1\",\n" +
            "                    \"resolved\": \"0\",\n" +
            "                    \"escalated\": \"1\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private MockMvc mockMvc;

    private RequestController requestController;

    @Before
    public void setup() {
        requestController = new RequestController();
        this.mockMvc = MockMvcBuilders.standaloneSetup(requestController)
                .build();
    }

    @Test
    public void shouldReturnOutputOnCorrectRequest() throws Exception {
        this.mockMvc
                .perform(
                        post("/").content(REQUEST)
                                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(RESPONSE.replaceAll("\n","").replaceAll(" ","")));
    }
    @Test
    public void shouldReturnOutputOnCorrectRequest_1() throws Exception {
        this.mockMvc
                .perform(
                        post("/").content(REQUEST_1)
                                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(RESPONSE_1.replaceAll("\n","").replaceAll(" ","")));
    }
    @Test
    public void shouldNotFindTheResource() throws Exception{
        this.mockMvc
                .perform(
                        post("/").content(BAD_REQUEST)
                                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().is(400));
    }
}