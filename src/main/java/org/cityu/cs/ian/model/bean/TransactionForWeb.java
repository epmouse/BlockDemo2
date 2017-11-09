package org.cityu.cs.ian.model.bean;


import java.util.List;

public class TransactionForWeb {


    /**
     * transactionId : 714c8a04732d4a989b8a93e8c8d117b5
     * from : {"from":"2169c8245bd7449b9ffb6c9958694bbe","name":"蒙牛"}
     * to : {"to":"405821fbf3c541bba96a0f0d6e9c877e","name":"xx山牧场"}
     * fromPubkey : MIIBtzCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYQAAoGAUeQP9d2fdpAKdRTASYFGROk948O1Bxi1EeQ/YE7WLY9Pf4wRBwRuPqZhUsEyuX8Uf3mCHoNWHSVXES4zmwV9WtfjRX/0wBih287olpJ+iRa4DBM7Svc0Kj1JcM8VEQk2T4+r7G/peoWZIxg4hDkXb2+KZPNhE0nCwblQrSx7a5g=,MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEvwkJm19JiiSzz1BgkzqJDoAR/kSFnA3R6L2r733ai/lA/q9IlfHFmy5QcnkcKuW5JOeIRfKGkNL7TSy1xi+/rg==
     * toPubkey : MIIBtzCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYQAAoGAc120JkpjchtkgKqTCFzifDLEJeLVvvHzW0Pj7SLIoyHYNzEf85ZAH2lQwM1stE7+LNaUKLwZMp/GMvWToRMBkWA04nrfiG54XlEyONJblFCBYORoUkI/COCZfDinGDTbFUULUUOn97MQubnLuLxh4aNJeRLR5GxXwPcaCeWOZRU=,MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEh8m/VDiLJie26XNB5E2OmfijXfukcvom0iPscELb3vudg04AnlByMutG3wDiyrFJFk5wFQ4zrIn8Rnf/Wu/K5Q==
     * data : [{"item":"123","price":"1241"}]
     * include : 405821fbf3c541bba96a0f0d6e9c877e
     * total : 12
     * link : http://47.94.141.57/file/2169c8245bd7449b9ffb6c9958694bbe/blockchain.png
     * signatures : MCwCFHujObRtxzP8qEOV9toHzKpA9u9iAhRagzpzKg45PpACNvcY5Wtd5zGp0Q==
     * isSign : 0
     * type : 1
     * token :
     */

    private String transactionId;
    private FromBean from;
    private ToBean to;
    private String fromPubkey;
    private String toPubkey;
    private String include;
    private String total;
    private String link;
    private String signatures;
    private String isSign;
    private String type;
    private String token;
    private List<Transaction.Data> data;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public FromBean getFrom() {
        return from;
    }

    public void setFrom(FromBean from) {
        this.from = from;
    }

    public ToBean getTo() {
        return to;
    }

    public void setTo(ToBean to) {
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

    public String getSignatures() {
        return signatures;
    }

    public void setSignatures(String signatures) {
        this.signatures = signatures;
    }

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
        this.isSign = isSign;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Transaction.Data> getData() {
        return data;
    }

    public void setData(List<Transaction.Data> data) {
        this.data = data;
    }

    public static class FromBean {
        /**
         * from : 2169c8245bd7449b9ffb6c9958694bbe
         * name : 蒙牛
         */

        private String from;
        private String name;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ToBean {
        /**
         * to : 405821fbf3c541bba96a0f0d6e9c877e
         * name : xx山牧场
         */

        private String to;
        private String name;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DataBean {
        /**
         * item : 123
         * price : 1241
         */

        private String item;
        private String price;

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}