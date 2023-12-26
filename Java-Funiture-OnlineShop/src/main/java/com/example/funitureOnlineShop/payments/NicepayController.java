package com.example.funitureOnlineShop.payments;

import com.example.funitureOnlineShop.cart.Cart;
import com.example.funitureOnlineShop.cart.CartService;
import com.example.funitureOnlineShop.option.Option;
import com.example.funitureOnlineShop.option.OptionService;
import com.example.funitureOnlineShop.order.Order;
import com.example.funitureOnlineShop.order.OrderRepository;
import com.example.funitureOnlineShop.order.OrderService;
import com.example.funitureOnlineShop.order.item.Item;
import com.example.funitureOnlineShop.orderCheck.OrderCheck;
import com.example.funitureOnlineShop.user.User;
import com.example.funitureOnlineShop.user.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class NicepayController {
    private final OptionService optionService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String CLIENT_ID = "1234567890";
    private final String SECRET_KEY = "57e4b065ef904b56b9247eda037ef064";

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

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(AuthenticationMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://sandbox-api.nicepay.co.kr/v1/payments/" + tid, request, JsonNode.class);

        JsonNode responseNode = responseEntity.getBody();
        String resultCode = responseNode.get("resultCode").asText();
        model.addAttribute("resultMsg", responseNode.get("resultMsg").asText());

        System.out.println(responseNode.toPrettyString());

        if (resultCode.equalsIgnoreCase("0000")) {
            //Order order = orderService.findById(id);
            //User user = order.getUser();
            //orderService.deductStockOnOrder(order);
            // cartService.deleteCartList(,user);

            // 결제 성공시 결제 후 옵션서비스에서 재고 갱신 로직 - optionService - 완료
            // 결제 성공시 오더체크에서 주문 정보 저장 로직 -OrderCheck
            // 결제 성공시 장바구니 비우기 - CartService
            // 기타 결제 성공 비즈니스 로직
            // (예시: 성공한 결제에 대한 로그 기록 등)
            // 결제 성공 시 오더 생성
        } else {
            // 실패한 결제에 대한 로그 기록
            // 기타 결제 실패 비즈니스 로직
            // 고객알림? - User에서 작업할 것인지
        }
        return "/payresponse";
    }

    @RequestMapping("/cancelAuth")
    public String requestCancel(
            @RequestParam String tid,
            @RequestParam String amount,
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
            // 취소 성공 비즈니스 로직 구현
            // 취소된 주문 정보 갱신 - OrderCheck
            // 결제 성공 시 오더 취소
        } else {
            // 취소 실패 비즈니스 로직 구현
            // 고객알림? - User에서 작업할 것인지
            // 재고복구 - Option서비스
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