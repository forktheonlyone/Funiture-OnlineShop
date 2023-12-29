package com.example.funitureOnlineShop.payments;

import com.example.funitureOnlineShop.core.error.exception.Exception500;
import com.example.funitureOnlineShop.order.OrderRequest;
import com.example.funitureOnlineShop.order.OrderService;
import com.example.funitureOnlineShop.orderCheck.OrderCheckDto;
import com.example.funitureOnlineShop.orderCheck.OrderCheckRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class NicepayController {
    private final OrderService orderService;
    private final OrderCheckRepository orderCheckRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String CLIENT_ID = "S2_dfb77b9e65454834a591d9866b77a273";
    private final String SECRET_KEY = "cdcb051f99f1495fa46d1499ed294be6";

    @RequestMapping("/")
    public String indexDemo(Model model){
        UUID id = UUID.randomUUID();
        model.addAttribute("orderId", id);
        model.addAttribute("clientId", CLIENT_ID);
        return "/payindex";
    }

    @RequestMapping(value="/cancel")
    public String cancelDemo(){
        return "/paycancel";
    }

    @RequestMapping("/serverAuth")
    public String requestPayment(
            @RequestParam String tid,
            @RequestParam Long amount,
            Model model) throws Exception {


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((CLIENT_ID + ":" + SECRET_KEY).getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> AuthenticationMap = new HashMap<>();
        AuthenticationMap.put("amount", String.valueOf(amount));
        System.out.println("Order Amount: " + amount);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(AuthenticationMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://sandbox-api.nicepay.co.kr/v1/payments/" + tid, request, JsonNode.class);

        JsonNode responseNode = responseEntity.getBody();
        String resultCode = responseNode.get("resultCode").asText();
        model.addAttribute("resultMsg", responseNode.get("resultMsg").asText());

        System.out.println(responseNode.toPrettyString());

        if (resultCode.equalsIgnoreCase("0000")) {

            OrderCheckDto orderCheck = new OrderCheckDto();
            orderCheck.setTid(tid);
            orderCheck.setPrice(amount);
            orderCheckRepository.save(orderCheck.toEntity());
            // 기타 결제 성공 비즈니스 로직
            // (예시: 성공한 결제에 대한 로그 기록 등)
            // 결제 성공 시 오더 생성
        } else {
            throw new Exception500("잘못된 계산정보입니다");
        }
        return "/payresponse";
    }

    @RequestMapping("/cancelAuth")
    public String requestCancel(
            @RequestParam OrderRequest.OrderDTO orderDTO,
            @RequestParam String tid,
            @RequestParam Long amount,
            Model model) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((CLIENT_ID + ":" + SECRET_KEY).getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> AuthenticationMap = new HashMap<>();
        AuthenticationMap.put("amount", amount);
        AuthenticationMap.put("reason", "test");
        AuthenticationMap.put("orderId", UUID.randomUUID().toString());

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(AuthenticationMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://sandbox-api.nicepay.co.kr/v1/payments/"+ tid +"/cancel", request, JsonNode.class);

        JsonNode responseNode = responseEntity.getBody();
        String resultCode = responseNode.get("resultCode").asText();
        model.addAttribute("resultMsg", responseNode.get("resultMsg").asText());

        System.out.println(responseNode.toPrettyString());

        if (resultCode.equalsIgnoreCase("0000")) {
            OrderCheckDto orderCheck = new OrderCheckDto();
            orderCheck.setTid(tid);
            orderCheck.setPrice(amount);
            orderCheckRepository.save(orderCheck.toEntity());
        } else {
            // 취소 실패 비즈니스 로직 구현
            // 고객알림? - User에서 작업할 것인지
        }
        return "/payresponse";
    }

    @RequestMapping("/hook")
    public ResponseEntity<String> hook(@RequestBody HashMap<String, Object> hookMap) throws Exception {
        String resultCode = hookMap.get("resultCode").toString();

        System.out.println(hookMap);

        if(resultCode.equalsIgnoreCase("0000")){
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}