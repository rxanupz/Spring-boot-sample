package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sample.controller.CustomerController;
import com.sample.domain.Customer;
import com.sample.util.CustomerService;
import com.sample.util.JwtTokenProvider;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerUnitTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    private MockMvc mockMvc;

    @Test
    public void testCreateCustomerWithValidToken() throws Exception {
        // Mock authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "testpassword");
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);

        // Mock JWT token provider
        when(jwtTokenProvider.generateToken(any(Authentication.class))).thenReturn("mocked-jwt-token");

        // Set the authenticated user
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Create a customer request
        String customerJson = "{\"name\":\"John Doe\",\"email\":\"john@example.com\",\"phone\":\"1234567890\"}";

        // Perform the request with a valid JWT token
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/customers")
                .header("Authorization", "Bearer mocked-jwt-token") // Add a valid JWT token
                .content(customerJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        // Verify that the service method was called
        verify(customerService, times(1)).createCustomer(any(Customer.class));
    }
}
