package com.example.krkapartments.module.checkout;

import com.stripe.param.ChargeCaptureParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}") //stripe test public key -> will need to be changed later for production
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 100 * 100); // 100 * 100 gr = 100zł
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", StripeChargeRequestDto.Currency.PLN);
        return "checkout";
    }
}
