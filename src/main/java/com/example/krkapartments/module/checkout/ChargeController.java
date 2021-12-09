package com.example.krkapartments.module.checkout;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ChargeController {

    private StripeService paymentService;

    @PostMapping("/charge")
    public String charge(StripeChargeRequestDto chargeRequest, Model model) throws SecurityException, StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(StripeChargeRequestDto.Currency.PLN);

        Charge charge = paymentService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
