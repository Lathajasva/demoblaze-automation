# AI-Assisted Test Scenario Evidence

## Application
DemoBlaze Product Store

## AI-Generated Scenario Suggestions

| ID | Scenario | AI Suggestion | Human Validation |
|---|---|---|---|
| AI-01 | Login | Verify valid user can log in successfully | Validated against DemoBlaze |
| AI-02 | Product Search | Verify products can be filtered by category | Validated against actual product categories |
| AI-03 | Product Details | Verify product title, price and description are displayed | Validated manually |
| AI-04 | Add to Cart | Verify selected product can be added to cart | Validated through automation |
| AI-05 | Checkout | Verify checkout form accepts required customer details | Validated through automation |
| AI-06 | Logout | Verify logged-in user can log out successfully | Validated through automation |

## Human Correction / Validation

The AI suggestions were not accepted blindly. The tester compared each suggestion with the actual DemoBlaze application and the instructor's project requirements.

The final automation scenarios were selected and implemented only after manual review.

## Evidence of Implementation

The validated scenarios were mapped to the existing Java + Playwright + TestNG automation framework using Page Object Model.

Current automated areas include:
- Login
- Product Search
- Product Details
- Cart
- Checkout
- Logout

## Conclusion

AI was used as an engineering assistant for generating ideas and improving coverage. Human review and actual test execution were used to confirm the final implementation.
