
### baseUrl 待定
### 交互接口：
#### 1、根据区块高度获取当前区块，json形式
  * url  baseUrl+block/syncBlock/blockDetail
  * params   blockHeight 
  * 请求方式  Get请求
  * 返回：
```
  {
     "transactionCount": 3,
     "blockHeight": 10,
     "blockHeader": {
         "previousHash": "previoushash",
         "merkleRoot": "merkleroot",
         "timeStamp": 33333,
         "randomTime": 12313,
         "nonce": "nonce",
         "blockHash": "blockhash",
         "nextHash": ""
     },
     "transactions": [
         {
             "transactionId": "0transactionId",
             "from": "0from",
             "to": "0to",
             "fromPubkey": "0from公钥",
             "toPubkey": "0to公钥",
             "data": [
                 {
                     "item": "",
                     "price": ""
                 },
                 {
                     "item": "",
                     "price": ""
                 }
             ],
             "include": "0include",
             "total": "0total",
             "link": "0link",
             "signatures": "0signatures",
             "isSign": "0true"
         },
         {
             "transactionId": "1transactionId",
             "from": "1from",
             "to": "1to",
             "fromPubkey": "1from公钥",
             "toPubkey": "1to公钥",
             "data": [
                 {
                     "item": "",
                     "price": ""
                 },
                 {
                     "item": "",
                     "price": ""
                 }
             ],
             "include": "1include",
             "total": "1total",
             "link": "1link",
             "signatures": "1signatures",
             "isSign": "1true"
         },
         {
             "transactionId": "2transactionId",
             "from": "2from",
             "to": "2to",
             "fromPubkey": "2from公钥",
             "toPubkey": "2to公钥",
             "data": [
                 {
                     "item": "",
                     "price": ""
                 },
                 {
                     "item": "",
                     "price": ""
                 }
             ],
             "include": "2include",
             "total": "2total",
             "link": "2link",
             "signatures": "2signatures",
             "isSign": "2true"
         },
         {
             "transactionId": "3transactionId",
             "from": "3from",
             "to": "3to",
             "fromPubkey": "3from公钥",
             "toPubkey": "3to公钥",
             "data": [
                 {
                     "item": "",
                     "price": ""
                 },
                 {
                     "item": "",
                     "price": ""
                 }
             ],
             "include": "3include",
             "total": "3total",
             "link": "3link",
             "signatures": "3signatures",
             "isSign": "3true"
         },
         {
             "transactionId": "4transactionId",
             "from": "4from",
             "to": "4to",
             "fromPubkey": "4from公钥",
             "toPubkey": "4to公钥",
             "data": [
                 {
                     "item": "",
                     "price": ""
                 },
                 {
                     "item": "",
                     "price": ""
                 }
             ],
             "include": "4include",
             "total": "4total",
             "link": "4link",
             "signatures": "4signatures",
             "isSign": "4true"
         }
     ]
 }
```   
   
#### 2、分页获取所有区块
      * url  baseUrl+block/syncBlock/all
      * params   page  值从0开始，返回区块从末尾开始，每页10条 
      * 请求方式  Get请求
      * 返回：jsonarray  里面jsonobject为  1 中 json串。
      
      ```
      {
          "blockTotalCount": 16,
          "blockBeans": [
              {
                  "transactionCount": 0,
                  "blockHeight": 14,
                  "blockHeader": {
                      "previousHash": "000000d2d7b9c49676de26f174f687b10ab58a27a97298deac8d2e64a3287e65",
                      "merkleRoot": "",
                      "timeStamp": 1508998730780,
                      "randomTime": 1508998674329,
                      "nonce": "11169791",
                      "blockHash": "00000014ccf11616149c6421189218a3a81ded23042e4cca091c3907e32900dc"
                  },
                  "transactions": []
              },
              {
                  "transactionCount": 0,
                  "blockHeight": 13,
                  "blockHeader": {
                      "previousHash": "000000d2d7b9c49676de26f174f687b10ab58a27a97298deac8d2e64a3287e65",
                      "merkleRoot": "",
                      "timeStamp": 1508998687478,
                      "randomTime": 1508998641820,
                      "nonce": "8974953",
                      "blockHash": "000000e3ed9df0def3ed540558304d9f6e63eff0a2b19aa835c30e86b7166942"
                  },
                  "transactions": []
              },
              {
                  "transactionCount": 0,
                  "blockHeight": 12,
                  "blockHeader": {
                      "previousHash": "000000d2d7b9c49676de26f174f687b10ab58a27a97298deac8d2e64a3287e65",
                      "merkleRoot": "",
                      "timeStamp": 1508998674317,
                      "randomTime": 1508998665650,
                      "nonce": "1811328",
                      "blockHash": "000000bbd298868cd952a2dc08e9077221b24bd649aef63aa3644b0a41233a07"
                  },
                  "transactions": []
              },
              {
                  "transactionCount": 0,
                  "blockHeight": 11,
                  "blockHeader": {
                      "previousHash": "000000d2d7b9c49676de26f174f687b10ab58a27a97298deac8d2e64a3287e65",
                      "merkleRoot": "",
                      "timeStamp": 1508998665635,
                      "randomTime": 1508998634753,
                      "nonce": "5813862",
                      "blockHash": "00000049ad48246611c7d90c61bd1d8f07d2cdd5b433981b1a7a1fa7c17dcdf6"
                  },
                  "transactions": []
              },
              {
                  "transactionCount": 0,
                  "blockHeight": 10,
                  "blockHeader": {
                      "previousHash": "000000d2d7b9c49676de26f174f687b10ab58a27a97298deac8d2e64a3287e65",
                      "merkleRoot": "",
                      "timeStamp": 1508998641788,
                      "randomTime": 1508998484014,
                      "nonce": "31681889",
                      "blockHash": "00000006048299ef7f8c87437d9eef5b98b6e7585479ffcf7a3ec9c29e14d8dd"
                  },
                  "transactions": []
              },
              {
                  "transactionCount": 0,
                  "blockHeight": 1,
                  "blockHeader": {
                      "previousHash": "",
                      "merkleRoot": "",
                      "timeStamp": 1508933670380,
                      "randomTime": 1508933605642,
                      "nonce": "14496959",
                      "blockHash": "000000b1d80612182fccc11ebe3d702effde7a780b9b3289873a59a09c4eec81"
                  },
                  "transactions": []
              }
          ]
      }
      ```
      
      
      
      
      
      
      
    
#### 3、通过transactionId查找transaction
      * url  baseUrl+block/syncBlock/getTransaction
      * params   transactionid 
      * 请求方式  Get请求
      * 返回
```
   {
       "transactionId": "aaaaaaaaaaa",
       "from": "4from",
       "to": "4to",
       "fromPubkey": "4from??",
       "toPubkey": "4to??",
       "data": [
           {
               "item": "",
               "price": ""
           },
           {
               "item": "",
               "price": ""
           }
       ],
       "include": "4include",
       "total": "4total",
       "link": "4link",
       "signatures": "4signatures",
       "isSign": "4true"
   }
```
   
   