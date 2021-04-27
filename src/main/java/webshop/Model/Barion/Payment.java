package webshop.Model.Barion;

public class Payment {

    private String POSKey;
    private String PaymentType="Immediate";
    private boolean GuestCheckout=true;
    private String[] FoundingSources={"All"};
    private String PaymentRequestId; //generated by the shop
    private String Currency="HUF";

    private Transaction[] Transactions;

    public Payment() {
    }

    public String getPOSKey() {
        return POSKey;
    }

    public void setPOSKey(String POSKey) {
        this.POSKey = POSKey;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getPaymentRequestId() {
        return PaymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        PaymentRequestId = paymentRequestId;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public Transaction[] getTransactions() {
        return Transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        Transactions = transactions;
    }

    public boolean isGuestCheckout() {
        return GuestCheckout;
    }

    public void setGuestCheckout(boolean guestCheckout) {
        GuestCheckout = guestCheckout;
    }

    public String[] getFoundingSources() {
        return FoundingSources;
    }

    public void setFoundingSources(String[] foundingSources) {
        FoundingSources = foundingSources;
    }
}
