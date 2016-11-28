Código não oficial em JAVA para o pagar-me
====


Para realizar uma nova transição:
----

```
PagarMeProvider pagarme = new PagarMeProvider("API_KEY","ENC_KEY");
TransactionSetup setup = new TransactionSetup();
setup.setAmount("10000");
setup.setPaymentMethod(PaymentMethod.credit_card);
setup.setCardHolderName("Bruce Lee");
setup.setCardExpirationDate("1016");
setup.setCardCvv("018");
setup.setCardNumber("5433229077370451");
  try{
        Transaction transaction = pagarme.postTransaction(setup);
        System.out.println(transaction.getStatus()); 
    }catch(PagarMeException pme){
      	System.out.println(pagarme.getErrorList().showErrors());
    }
```

Cancelar a transação:

```
transaction.refund();
```


Pesquisar as Transações:

```
 //usando paginação
Collection<Transaction> allTransactions = new Transaction(pagarme).listAllWithPagination(10,1); // 10 transacoes por pagina, primeira página.

    // ou listar tudo
    allTransactions = new Transaction(pagarme).listAll(); 
```



Obsersavações
=========

Jars necessários:
----
* gson-2.2.4
* guava-10.0.1
* jersey-client-2.4.1
* jersey-core-1.18
* jersey-server-1.18
* jsr311-api-0.11


Falta implementar:
----
* CardHash
* Salvar objetos
* Pesquisa por query
* Não permitir modificar alguns atributos