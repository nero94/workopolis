package com.example.endpoint;

import com.example.domain.GetPaymentRequest;
import com.example.domain.GetPaymentResponse;
import com.example.repository.SpendAndTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author yuriy.dizhak
 */
@Endpoint
public class SpandAndTrackEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/spandandtrack";

    @Autowired
    private SpendAndTrackRepository spandAndTrackRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPaymentRequest")
    @ResponsePayload
    public GetPaymentResponse getPayment(@RequestPayload GetPaymentRequest request) {
        GetPaymentResponse response = new GetPaymentResponse();

        response.setPayment(spandAndTrackRepository.findPayment(request.getId()));

        return response;
    }
}
