package com.example.moviemingle.services.payments;

import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentGatewayServiceImpl implements PaymentGatewayService {
    
    @Value("${server.base-url}")
    private String serverUrl;
    
    @Value("${payment.stripe.api-key}")
    private String apiKey;
    
    @PostConstruct
    protected void setupStripe(){
        Stripe.apiKey = apiKey;
    }
    
    @Override
    public String createPaymentSession(Order order) throws StripeException {
        
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for (var item : order.getOrderDetails()) {
            Price price = createPrice(item);
            lineItems.add(createLineItem(price));
        }
        
        SessionCreateParams params = SessionCreateParams.builder()
                .addAllLineItem(lineItems)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCustomerEmail(order.getUser().getEmail())
                .setCurrency("usd")
                .setSuccessUrl(serverUrl+"/payments/stripe/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(serverUrl+"/payments/stripe/cancel")
                .setClientReferenceId(order.getId().toString())
                .build();
        
        return Session.create(params).getUrl();
    }
    
    private Price createPrice(OrderDetail orderDetail) throws StripeException {
        PriceCreateParams params = PriceCreateParams.builder()
                .setProductData(createProductData(orderDetail.getMovie()))
                .setCurrency("usd")
                .setUnitAmountDecimal(BigDecimal.valueOf(orderDetail.getPurchasePrice() * 100))
                .build();
        return Price.create(params);
    }
    
    private SessionCreateParams.LineItem createLineItem(Price price){
        return SessionCreateParams.LineItem.builder()
                .setPrice(price.getId())
                .setQuantity(1L)
                .build();
    }
    
    private PriceCreateParams.ProductData createProductData(Movie movie){
        return PriceCreateParams.ProductData.builder()
                .setName(movie.getMovieTitle())
                .build();
    }
    
}
