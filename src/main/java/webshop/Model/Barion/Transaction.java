package webshop.Model.Barion;

public class Transaction {


        private String POSTransactionId= "1";     //EXMPLSHOP-PM-001TR001",
            private String Payee="poliphonalj@freemail.hu";
            private int Total=5000;
            private Item[] Items;

    public Transaction() {
    }

    public String getPOSTransactionId() {
        return POSTransactionId;
    }

    public void setPOSTransactionId(String POSTransactionId) {
        this.POSTransactionId = POSTransactionId;
    }

    public String getPayee() {
        return Payee;
    }

    public void setPayee(String payee) {
        Payee = payee;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public Item[] getItems() {
        return Items;
    }

    public void setItems(Item[] items) {
        Items = items;
    }
}
