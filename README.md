完成：
1、计算区块 （缺merkletree计算）
2、接收区块  （缺merkletree计算）
3、接收transaction  （缺验证）

baseUrl 待定
交互接口：
1、根据区块高度获取当前区块，json形式
   url  baseUrl+block/syncBlock/blockDetail
   params   blockHeight 
   请求方式  Get请求
   返回：
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
     "transaction1s": [
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
   
   
   2、分页获取所有区块
       url  baseUrl+block/syncBlock/all
      params   page  值从0开始，返回区块从末尾开始，每页10条 
      请求方式  Get请求
      返回：jsonarray  里面jsonobject为  1 中 json串。
    
   3、通过transactionId查找transaction
    url  baseUrl+block/syncBlock/getTransaction
         params   transactionid 
         请求方式  Get请求
        返回：
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
   
   