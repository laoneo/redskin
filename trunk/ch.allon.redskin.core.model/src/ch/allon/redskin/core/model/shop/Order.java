/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop;

import java.text.SimpleDateFormat;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ch.allon.redskin.core.model.shop.Order#getNumber <em>Number</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Order#getTransactions <em>Transactions</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Order#getCustomer <em>Customer</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Order#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @see ch.allon.redskin.core.model.shop.ShopPackage#getOrder()
 * @model
 * @generated
 */
public interface Order extends EObject {
	
	public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(String)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getOrder_Number()
	 * @model default=""
	 * @generated
	 */
	String getNumber();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Order#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(String value);

	/**
	 * Returns the value of the '<em><b>Transactions</b></em>' containment reference list.
	 * The list contents are of type {@link ch.allon.redskin.core.model.shop.Transaction}.
	 * It is bidirectional and its opposite is '{@link ch.allon.redskin.core.model.shop.Transaction#getOrder <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transactions</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transactions</em>' containment reference list.
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getOrder_Transactions()
	 * @see ch.allon.redskin.core.model.shop.Transaction#getOrder
	 * @model opposite="order" containment="true"
	 * @generated
	 */
	EList<Transaction> getTransactions();

	/**
	 * Returns the value of the '<em><b>Customer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Customer</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customer</em>' reference.
	 * @see #setCustomer(Customer)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getOrder_Customer()
	 * @model resolveProxies="false"
	 * @generated
	 */
	Customer getCustomer();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Order#getCustomer <em>Customer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customer</em>' reference.
	 * @see #getCustomer()
	 * @generated
	 */
	void setCustomer(Customer value);

	/**
	 * Returns the value of the '<em><b>Comments</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' attribute list.
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getOrder_Comments()
	 * @model
	 * @generated
	 */
	EList<String> getComments();

} // Order
