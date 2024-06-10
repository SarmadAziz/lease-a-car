package com.example.customer;

import com.example.exceptions.CustomerNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    List<CustomerDto> customerDtos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        customerDtos = List.of(
                CustomerDto.builder().id(1L).name("John Doe").street("Main St").houseNumber(123).zipcode("1234AB")
                        .place("City").email("john.doe@example.com").phoneNumber("1234567890").build(),
                CustomerDto.builder().id(2L).name("Jane Doe").street("Main St").houseNumber(345).zipcode("9876AB")
                        .place("City").email("john.doe@example.com").phoneNumber("1234567890").build()
        );
    }

    @Test
    void getAllCustomers() throws Exception {
        String jsonResponse = """
                [
                    {
                      "id": 1,
                      "name": "John Doe",
                      "street": "Main St",
                      "houseNumber": 123,
                      "zipcode": "1234AB",
                      "place": "City",
                      "email": "john.doe@example.com",
                      "phoneNumber": "1234567890"
                    },
                    {
                      "id": 2,
                      "name": "Jane Doe",
                      "street": "Main St",
                      "houseNumber": 345,
                      "zipcode": "9876AB",
                      "place": "City",
                      "email": "john.doe@example.com",
                      "phoneNumber": "1234567890"
                    }
                ]""";

        when(customerService.getAllCustomers()).thenReturn(customerDtos);

        MvcResult result = mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse))
                .andReturn();

        JSONAssert.assertEquals(jsonResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    void SuccessfullyUpdateCustomer() throws Exception {
        doNothing().when(customerService).updateCustomer(anyLong(), any(CustomerDto.class));

        mockMvc.perform(
                        put("/api/v1/customers/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(customerDtos.getFirst()))
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void SuccessfullyCreateCustomer() throws Exception {
        when(customerService.saveCustomer(any(CustomerDto.class))).thenReturn(customerDtos.getFirst());

        mockMvc.perform(
                post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDtos.getFirst()))
            )
            .andExpect(status().isCreated());
    }

    @Test
    void ExceptionForInvalidCustomerInformation() throws Exception {
        CustomerDto invalidCustomerDto = CustomerDto.builder()
                .name("")
                .street("")
                .zipcode("123412AB")
                .place("")
                .email(" as asd asd .com")
                .phoneNumber("1234")
                .build();

        mockMvc.perform(
                        post("/api/v1/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidCustomerDto))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("House number is mandatory")))
                .andExpect(jsonPath("$.message", containsString("Phone number must be 10 digits")))
                .andExpect(jsonPath("$.message", containsString("Place is mandatory")))
                .andExpect(jsonPath("$.message", containsString("Invalid email")))
                .andExpect(jsonPath("$.message", containsString("Name is mandatory")))
                .andExpect(jsonPath("$.message", containsString("Street is mandatory")))
                .andExpect(jsonPath("$.message", containsString("Zipcode must be of format 1234AB")));
    }

    @Test
    void SuccessfullyDeleteACustomer() throws Exception {
        Long customerId = 1L;
        doNothing().when(customerService).deleteCustomer(customerId);

        mockMvc.perform(delete("/api/v1/customers/1"))
                .andExpect(status().isNoContent());

        verify(customerService, times(1)).deleteCustomer(1L);
    }

    @Test
    void throwExceptionWhenDeletingCustomerThatDoesNotExist() throws Exception {
        Long customerId = 1L;
        String jsonResponse = """
                    {
                        "message": "Customer with id 1 was not found"
                    }
                """;
        doThrow(new CustomerNotFoundException(customerId))
                .when(customerService).deleteCustomer(customerId);

        mockMvc.perform(delete("/api/v1/customers/{customerId}", customerId))
                .andExpect(status().isNotFound())
                .andExpect(content().json(jsonResponse));
    }
}