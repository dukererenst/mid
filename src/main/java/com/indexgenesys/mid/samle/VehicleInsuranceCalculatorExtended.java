/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.samle;

/**
 *
 * @author ernest
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

public class VehicleInsuranceCalculatorExtended {

    // Base rate percentages
    private static final BigDecimal TP_RATE = new BigDecimal("0.02");            // 2% for Third-Party
    private static final BigDecimal COMPREHENSIVE_RATE = new BigDecimal("0.05");  // 5% for Comprehensive

    // Levies & fees
    private static final BigDecimal GHANA_LEVY_RATE = new BigDecimal("0.025");   // 2.5% National Insurance Levy
    private static final BigDecimal STAMP_DUTY = new BigDecimal("10.00");         // GH₵10 flat

    // Engine capacity loading tiers
    private static final int EC1_MAX = 1500;  // ≤1.5L
    private static final int EC2_MAX = 2500;  // 1.5–2.5L

    // Engine capacity surcharges
    private static final BigDecimal EC1_RATE = BigDecimal.ZERO;                   // no surcharge
    private static final BigDecimal EC2_RATE = new BigDecimal("0.05");           // 5% surcharge
    private static final BigDecimal EC3_RATE = new BigDecimal("0.10");           // 10% surcharge (>2.5L)

    // Add-on rates
    private static final BigDecimal PASS_ACC_RATE_PER_SEAT = new BigDecimal("0.005"); // 0.5% of base premium per seat
    private static final BigDecimal ROAD_ASSISTANCE_FEE = new BigDecimal("50.00");    // GH₵50 flat

    // Risk category multipliers
    public enum RiskCategory {
        LOW(new BigDecimal("0.95")),    // 5% discount
        MEDIUM(BigDecimal.ONE),         // no change
        HIGH(new BigDecimal("1.10"));   // 10% loading

        private final BigDecimal factor;
        RiskCategory(BigDecimal factor) { this.factor = factor; }
        public BigDecimal getFactor() { return factor; }
    }

    /**
     * Calculates the total premium.
     *
     * @param vehicleValue           declared value of the vehicle (GH₵)
     * @param comprehensive          true=comprehensive, false=third-party
     * @param noClaimDiscount        0–100 (e.g. 20 for 20%)
     * @param numberOfSeats          number of seats (for passenger accident cover)
     * @param engineCapacityCc       engine capacity in cc
     * @param riskCategory           LOW, MEDIUM, or HIGH
     * @param policyTermMonths       policy term in months (e.g. 12)
     * @param includePassengerAcc    whether to include passenger accident add-on
     * @param includeRoadsideAssist  whether to include roadside assistance add-on
     * @return total premium rounded to 2 dp
     */
    public static BigDecimal calculatePremium(
            BigDecimal vehicleValue,
            boolean comprehensive,
            BigDecimal noClaimDiscount,
            int numberOfSeats,
            int engineCapacityCc,
            RiskCategory riskCategory,
            int policyTermMonths,
            boolean includePassengerAcc,
            boolean includeRoadsideAssist
    ) {
        // 1. Base premium
        BigDecimal rate = comprehensive ? COMPREHENSIVE_RATE : TP_RATE;
        BigDecimal basePremium = vehicleValue.multiply(rate);

        // 2. No-claim discount
        BigDecimal discountFactor = BigDecimal.ONE
                .subtract(noClaimDiscount.divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP));
        BigDecimal discountedPremium = basePremium.multiply(discountFactor);

        // 3. Engine capacity surcharge
        BigDecimal ecRate = engineCapacitySurchargeRate(engineCapacityCc);
        BigDecimal ecSurcharge = discountedPremium.multiply(ecRate);

        // 4. Apply risk category
        BigDecimal riskAdjusted = discountedPremium.add(ecSurcharge)
                .multiply(riskCategory.getFactor());

        // 5. Add Ghana levy & stamp duty
        BigDecimal levy = riskAdjusted.multiply(GHANA_LEVY_RATE);
        BigDecimal subtotal = riskAdjusted.add(levy).add(STAMP_DUTY);

        // 6. Add-ons
        BigDecimal addons = BigDecimal.ZERO;
        if (includePassengerAcc && numberOfSeats > 0) {
            BigDecimal passAcc = basePremium
                    .multiply(PASS_ACC_RATE_PER_SEAT.multiply(new BigDecimal(numberOfSeats)));
            addons = addons.add(passAcc);
        }
        if (includeRoadsideAssist) {
            addons = addons.add(ROAD_ASSISTANCE_FEE);
        }

        BigDecimal totalBeforeTerm = subtotal.add(addons);

        // 7. Prorate by policy term (months/12)
        BigDecimal termFactor = new BigDecimal(policyTermMonths)
                .divide(new BigDecimal("12"), 4, RoundingMode.HALF_UP);
        BigDecimal finalPremium = totalBeforeTerm.multiply(termFactor);

        // 8. Round to 2 dp
        return finalPremium.setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal engineCapacitySurchargeRate(int cc) {
        if (cc <= EC1_MAX) {
            return EC1_RATE;
        } else if (cc <= EC2_MAX) {
            return EC2_RATE;
        } else {
            return EC3_RATE;
        }
    }

    // Demo
    public static void main(String[] args) {
        BigDecimal vehicleValue = new BigDecimal("60000");    // GH₵60,000
        boolean comprehensive = true;
        BigDecimal noClaimDiscount = new BigDecimal("15");    // 15%
        int seats = 5;
        int engineCc = 1800;
        RiskCategory risk = RiskCategory.MEDIUM;
        int termMonths = 12;
        boolean passengerAcc = true;
        boolean roadside = true;

        BigDecimal premium = calculatePremium(
                vehicleValue, comprehensive, noClaimDiscount,
                seats, engineCc, risk, termMonths,
                passengerAcc, roadside
        );

        System.out.println("Annual Premium: GH₵" + premium);
    }
}

