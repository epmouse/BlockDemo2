package org.cityu.cs.ian.model.bean;

public class SignTransaction {
    private String transactionId;
    private String from;
    private String to;
    private String fromPubkey;
    private String toPubkey;
    private String include;
    private String total;
    private String link;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromPubkey() {
        return fromPubkey;
    }

    public void setFromPubkey(String fromPubkey) {
        this.fromPubkey = fromPubkey;
    }

    public String getToPubkey() {
        return toPubkey;
    }

    public void setToPubkey(String toPubkey) {
        this.toPubkey = toPubkey;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
