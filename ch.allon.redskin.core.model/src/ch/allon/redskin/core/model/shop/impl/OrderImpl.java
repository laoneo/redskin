/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop.impl;

import ch.allon.redskin.core.model.shop.Customer;
import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.core.model.shop.Transaction;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Order</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.OrderImpl#getOrderNr <em>Order Nr</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.OrderImpl#getTransactions <em>Transactions</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.OrderImpl#getCustomer <em>Customer</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.OrderImpl#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderImpl extends EObjectImpl implements Order {
	/**
	 * The default value of the '{@link #getOrderNr() <em>Order Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderNr()
	 * @generated
	 * @ordered
	 */
	protected static final String ORDER_NR_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getOrderNr() <em>Order Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderNr()
	 * @generated
	 * @ordered
	 */
	protected String orderNr = ORDER_NR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTransactions() <em>Transactions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactions()
	 * @generated
	 * @ordered
	 */
	protected EList<Transaction> transactions;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShopPackage.Literals.ORDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOrderNr() {
		return orderNr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderNr(String newOrderNr) {
		String oldOrderNr = orderNr;
		orderNr = newOrderNr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.ORDER__ORDER_NR, oldOrderNr, orderNr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transaction> getTransactions() {
		if (transactions == null) {
			transactions = new EObjectContainmentWithInverseEList<Transaction>(Transaction.class, this, ShopPackage.ORDER__TRANSACTIONS, ShopPackage.TRANSACTION__ORDER);
		}
		return transactions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Customer getCustomer() {
		if (eContainerFeatureID() != ShopPackage.ORDER__CUSTOMER) return null;
		return (Customer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomer(Customer newCustomer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCustomer, ShopPackage.ORDER__CUSTOMER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCustomer(Customer newCustomer) {
		if (newCustomer != eInternalContainer() || (eContainerFeatureID() != ShopPackage.ORDER__CUSTOMER && newCustomer != null)) {
			if (EcoreUtil.isAncestor(this, newCustomer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCustomer != null)
				msgs = ((InternalEObject)newCustomer).eInverseAdd(this, ShopPackage.CUSTOMER__ORDERS, Customer.class, msgs);
			msgs = basicSetCustomer(newCustomer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.ORDER__CUSTOMER, newCustomer, newCustomer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getComments() {
		if (comments == null) {
			comments = new EDataTypeUniqueEList<String>(String.class, this, ShopPackage.ORDER__COMMENTS);
		}
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ShopPackage.ORDER__TRANSACTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTransactions()).basicAdd(otherEnd, msgs);
			case ShopPackage.ORDER__CUSTOMER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCustomer((Customer)otherEnd, msgs);
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
			case ShopPackage.ORDER__TRANSACTIONS:
				return ((InternalEList<?>)getTransactions()).basicRemove(otherEnd, msgs);
			case ShopPackage.ORDER__CUSTOMER:
				return basicSetCustomer(null, msgs);
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
			case ShopPackage.ORDER__CUSTOMER:
				return eInternalContainer().eInverseRemove(this, ShopPackage.CUSTOMER__ORDERS, Customer.class, msgs);
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
			case ShopPackage.ORDER__ORDER_NR:
				return getOrderNr();
			case ShopPackage.ORDER__TRANSACTIONS:
				return getTransactions();
			case ShopPackage.ORDER__CUSTOMER:
				return getCustomer();
			case ShopPackage.ORDER__COMMENTS:
				return getComments();
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
			case ShopPackage.ORDER__ORDER_NR:
				setOrderNr((String)newValue);
				return;
			case ShopPackage.ORDER__TRANSACTIONS:
				getTransactions().clear();
				getTransactions().addAll((Collection<? extends Transaction>)newValue);
				return;
			case ShopPackage.ORDER__CUSTOMER:
				setCustomer((Customer)newValue);
				return;
			case ShopPackage.ORDER__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends String>)newValue);
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
			case ShopPackage.ORDER__ORDER_NR:
				setOrderNr(ORDER_NR_EDEFAULT);
				return;
			case ShopPackage.ORDER__TRANSACTIONS:
				getTransactions().clear();
				return;
			case ShopPackage.ORDER__CUSTOMER:
				setCustomer((Customer)null);
				return;
			case ShopPackage.ORDER__COMMENTS:
				getComments().clear();
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
			case ShopPackage.ORDER__ORDER_NR:
				return ORDER_NR_EDEFAULT == null ? orderNr != null : !ORDER_NR_EDEFAULT.equals(orderNr);
			case ShopPackage.ORDER__TRANSACTIONS:
				return transactions != null && !transactions.isEmpty();
			case ShopPackage.ORDER__CUSTOMER:
				return getCustomer() != null;
			case ShopPackage.ORDER__COMMENTS:
				return comments != null && !comments.isEmpty();
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
		result.append(" (orderNr: ");
		result.append(orderNr);
		result.append(", comments: ");
		result.append(comments);
		result.append(')');
		return result.toString();
	}

} //OrderImpl
