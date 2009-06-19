/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop.impl;

import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.core.model.shop.Transaction;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transaction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.TransactionImpl#getTransactionNr <em>Transaction Nr</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.TransactionImpl#getOrder <em>Order</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.TransactionImpl#getProduct <em>Product</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.TransactionImpl#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.TransactionImpl#getEndDate <em>End Date</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.TransactionImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.TransactionImpl#getPrice <em>Price</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransactionImpl extends EObjectImpl implements Transaction {
	/**
	 * The default value of the '{@link #getTransactionNr() <em>Transaction Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionNr()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSACTION_NR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransactionNr() <em>Transaction Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionNr()
	 * @generated
	 * @ordered
	 */
	protected String transactionNr = TRANSACTION_NR_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date START_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected Date startDate = START_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndDate() <em>End Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date END_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndDate() <em>End Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndDate()
	 * @generated
	 * @ordered
	 */
	protected Date endDate = END_DATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected EList<String> comments;

	/**
	 * The default value of the '{@link #getPrice() <em>Price</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrice()
	 * @generated
	 * @ordered
	 */
	protected static final double PRICE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPrice() <em>Price</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrice()
	 * @generated
	 * @ordered
	 */
	protected double price = PRICE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransactionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShopPackage.Literals.TRANSACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransactionNr() {
		return transactionNr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransactionNr(String newTransactionNr) {
		String oldTransactionNr = transactionNr;
		transactionNr = newTransactionNr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.TRANSACTION__TRANSACTION_NR, oldTransactionNr, transactionNr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Order getOrder() {
		if (eContainerFeatureID() != ShopPackage.TRANSACTION__ORDER) return null;
		return (Order)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrder(Order newOrder, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOrder, ShopPackage.TRANSACTION__ORDER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrder(Order newOrder) {
		if (newOrder != eInternalContainer() || (eContainerFeatureID() != ShopPackage.TRANSACTION__ORDER && newOrder != null)) {
			if (EcoreUtil.isAncestor(this, newOrder))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOrder != null)
				msgs = ((InternalEObject)newOrder).eInverseAdd(this, ShopPackage.ORDER__TRANSACTIONS, Order.class, msgs);
			msgs = basicSetOrder(newOrder, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.TRANSACTION__ORDER, newOrder, newOrder));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product getProduct() {
		if (eContainerFeatureID() != ShopPackage.TRANSACTION__PRODUCT) return null;
		return (Product)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProduct(Product newProduct, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newProduct, ShopPackage.TRANSACTION__PRODUCT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProduct(Product newProduct) {
		if (newProduct != eInternalContainer() || (eContainerFeatureID() != ShopPackage.TRANSACTION__PRODUCT && newProduct != null)) {
			if (EcoreUtil.isAncestor(this, newProduct))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newProduct != null)
				msgs = ((InternalEObject)newProduct).eInverseAdd(this, ShopPackage.PRODUCT__TRANSACTIONS, Product.class, msgs);
			msgs = basicSetProduct(newProduct, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.TRANSACTION__PRODUCT, newProduct, newProduct));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartDate(Date newStartDate) {
		Date oldStartDate = startDate;
		startDate = newStartDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.TRANSACTION__START_DATE, oldStartDate, startDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndDate(Date newEndDate) {
		Date oldEndDate = endDate;
		endDate = newEndDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.TRANSACTION__END_DATE, oldEndDate, endDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getComments() {
		if (comments == null) {
			comments = new EDataTypeUniqueEList<String>(String.class, this, ShopPackage.TRANSACTION__COMMENTS);
		}
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrice(double newPrice) {
		double oldPrice = price;
		price = newPrice;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.TRANSACTION__PRICE, oldPrice, price));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ShopPackage.TRANSACTION__ORDER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOrder((Order)otherEnd, msgs);
			case ShopPackage.TRANSACTION__PRODUCT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetProduct((Product)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ShopPackage.TRANSACTION__ORDER:
				return basicSetOrder(null, msgs);
			case ShopPackage.TRANSACTION__PRODUCT:
				return basicSetProduct(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ShopPackage.TRANSACTION__ORDER:
				return eInternalContainer().eInverseRemove(this, ShopPackage.ORDER__TRANSACTIONS, Order.class, msgs);
			case ShopPackage.TRANSACTION__PRODUCT:
				return eInternalContainer().eInverseRemove(this, ShopPackage.PRODUCT__TRANSACTIONS, Product.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ShopPackage.TRANSACTION__TRANSACTION_NR:
				return getTransactionNr();
			case ShopPackage.TRANSACTION__ORDER:
				return getOrder();
			case ShopPackage.TRANSACTION__PRODUCT:
				return getProduct();
			case ShopPackage.TRANSACTION__START_DATE:
				return getStartDate();
			case ShopPackage.TRANSACTION__END_DATE:
				return getEndDate();
			case ShopPackage.TRANSACTION__COMMENTS:
				return getComments();
			case ShopPackage.TRANSACTION__PRICE:
				return getPrice();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ShopPackage.TRANSACTION__TRANSACTION_NR:
				setTransactionNr((String)newValue);
				return;
			case ShopPackage.TRANSACTION__ORDER:
				setOrder((Order)newValue);
				return;
			case ShopPackage.TRANSACTION__PRODUCT:
				setProduct((Product)newValue);
				return;
			case ShopPackage.TRANSACTION__START_DATE:
				setStartDate((Date)newValue);
				return;
			case ShopPackage.TRANSACTION__END_DATE:
				setEndDate((Date)newValue);
				return;
			case ShopPackage.TRANSACTION__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends String>)newValue);
				return;
			case ShopPackage.TRANSACTION__PRICE:
				setPrice((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ShopPackage.TRANSACTION__TRANSACTION_NR:
				setTransactionNr(TRANSACTION_NR_EDEFAULT);
				return;
			case ShopPackage.TRANSACTION__ORDER:
				setOrder((Order)null);
				return;
			case ShopPackage.TRANSACTION__PRODUCT:
				setProduct((Product)null);
				return;
			case ShopPackage.TRANSACTION__START_DATE:
				setStartDate(START_DATE_EDEFAULT);
				return;
			case ShopPackage.TRANSACTION__END_DATE:
				setEndDate(END_DATE_EDEFAULT);
				return;
			case ShopPackage.TRANSACTION__COMMENTS:
				getComments().clear();
				return;
			case ShopPackage.TRANSACTION__PRICE:
				setPrice(PRICE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ShopPackage.TRANSACTION__TRANSACTION_NR:
				return TRANSACTION_NR_EDEFAULT == null ? transactionNr != null : !TRANSACTION_NR_EDEFAULT.equals(transactionNr);
			case ShopPackage.TRANSACTION__ORDER:
				return getOrder() != null;
			case ShopPackage.TRANSACTION__PRODUCT:
				return getProduct() != null;
			case ShopPackage.TRANSACTION__START_DATE:
				return START_DATE_EDEFAULT == null ? startDate != null : !START_DATE_EDEFAULT.equals(startDate);
			case ShopPackage.TRANSACTION__END_DATE:
				return END_DATE_EDEFAULT == null ? endDate != null : !END_DATE_EDEFAULT.equals(endDate);
			case ShopPackage.TRANSACTION__COMMENTS:
				return comments != null && !comments.isEmpty();
			case ShopPackage.TRANSACTION__PRICE:
				return price != PRICE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (transactionNr: ");
		result.append(transactionNr);
		result.append(", startDate: ");
		result.append(startDate);
		result.append(", endDate: ");
		result.append(endDate);
		result.append(", comments: ");
		result.append(comments);
		result.append(", price: ");
		result.append(price);
		result.append(')');
		return result.toString();
	}

} //TransactionImpl