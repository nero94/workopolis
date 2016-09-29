//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.14 at 07:42:56 PM EEST 
//


package com.example.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.domain package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _User_QNAME = new QName("http://example.com/spandandtrack", "user");
    private final static QName _Payment_QNAME = new QName("http://example.com/spandandtrack", "payment");
    private final static QName _Cash_QNAME = new QName("http://example.com/spandandtrack", "cash");
    private final static QName _Credit_QNAME = new QName("http://example.com/spandandtrack", "credit");
    private final static QName _Deposit_QNAME = new QName("http://example.com/spandandtrack", "deposit");
    private final static QName _Transfer_QNAME = new QName("http://example.com/spandandtrack", "transfer");
    private final static QName _Income_QNAME = new QName("http://example.com/spandandtrack", "income");
    private final static QName _Outgoings_QNAME = new QName("http://example.com/spandandtrack", "outgoings");
    private final static QName _BudgetPlan_QNAME = new QName("http://example.com/spandandtrack", "budgetPlan");
    private final static QName _SceduleItem_QNAME = new QName("http://example.com/spandandtrack", "sceduleItem");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.domain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPaymentRequest }
     * 
     */
    public GetPaymentRequest createGetPaymentRequest() {
        return new GetPaymentRequest();
    }

    /**
     * Create an instance of {@link GetPaymentResponse }
     * 
     */
    public GetPaymentResponse createGetPaymentResponse() {
        return new GetPaymentResponse();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link Cash }
     * 
     */
    public Cash createCash() {
        return new Cash();
    }

    /**
     * Create an instance of {@link Credit }
     * 
     */
    public Credit createCredit() {
        return new Credit();
    }

    /**
     * Create an instance of {@link Deposit }
     * 
     */
    public Deposit createDeposit() {
        return new Deposit();
    }

    /**
     * Create an instance of {@link Transfer }
     * 
     */
    public Transfer createTransfer() {
        return new Transfer();
    }

    /**
     * Create an instance of {@link Income }
     * 
     */
    public Income createIncome() {
        return new Income();
    }

    /**
     * Create an instance of {@link Outgoings }
     * 
     */
    public Outgoings createOutgoings() {
        return new Outgoings();
    }

    /**
     * Create an instance of {@link BudgetPlan }
     * 
     */
    public BudgetPlan createBudgetPlan() {
        return new BudgetPlan();
    }

    /**
     * Create an instance of {@link SceduleItem }
     * 
     */
    public SceduleItem createSceduleItem() {
        return new SceduleItem();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link AmountDistribution }
     * 
     */
    public AmountDistribution createAmountDistribution() {
        return new AmountDistribution();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/spandandtrack", name = "user")
    public JAXBElement<Object> createUser(Object value) {
        return new JAXBElement<Object>(_User_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Payment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/spandandtrack", name = "payment")
    public JAXBElement<Payment> createPayment(Payment value) {
        return new JAXBElement<Payment>(_Payment_QNAME, Payment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cash }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/spandandtrack", name = "cash")
    public JAXBElement<Cash> createCash(Cash value) {
        return new JAXBElement<Cash>(_Cash_QNAME, Cash.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Credit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/spandandtrack", name = "credit")
    public JAXBElement<Credit> createCredit(Credit value) {
        return new JAXBElement<Credit>(_Credit_QNAME, Credit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Deposit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/spandandtrack", name = "deposit")
    public JAXBElement<Deposit> createDeposit(Deposit value) {
        return new JAXBElement<Deposit>(_Deposit_QNAME, Deposit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transfer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/spandandtrack", name = "transfer")
    public JAXBElement<Transfer> createTransfer(Transfer value) {
        return new JAXBElement<Transfer>(_Transfer_QNAME, Transfer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Income }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/spandandtrack", name = "income")
    public JAXBElement<Income> createIncome(Income value) {
        return new JAXBElement<Income>(_Income_QNAME, Income.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Outgoings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/spandandtrack", name = "outgoings")
    public JAXBElement<Outgoings> createOutgoings(Outgoings value) {
        return new JAXBElement<Outgoings>(_Outgoings_QNAME, Outgoings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BudgetPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/spandandtrack", name = "budgetPlan")
    public JAXBElement<BudgetPlan> createBudgetPlan(BudgetPlan value) {
        return new JAXBElement<BudgetPlan>(_BudgetPlan_QNAME, BudgetPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SceduleItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/spandandtrack", name = "sceduleItem")
    public JAXBElement<SceduleItem> createSceduleItem(SceduleItem value) {
        return new JAXBElement<SceduleItem>(_SceduleItem_QNAME, SceduleItem.class, null, value);
    }

}
