/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Customer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ch.allon.redskin.core.model.shop.Customer#getSurname <em>Surname</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Customer#getFamilyName <em>Family Name</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Customer#getTelephoneNr <em>Telephone Nr</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Customer#getAddress <em>Address</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Customer#getHotel <em>Hotel</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Customer#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @see ch.allon.redskin.core.model.shop.ShopPackage#getCustomer()
 * @model
 * @generated
 */
public interface Customer extends EObject {
	/**
	 * Returns the value of the '<em><b>Surname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Surname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Surname</em>' attribute.
	 * @see #setSurname(String)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getCustomer_Surname()
	 * @model
	 * @generated
	 */
	String getSurname();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Customer#getSurname <em>Surname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Surname</em>' attribute.
	 * @see #getSurname()
	 * @generated
	 */
	void setSurname(String value);

	/**
	 * Returns the value of the '<em><b>Family Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Family Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Family Name</em>' attribute.
	 * @see #setFamilyName(String)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getCustomer_FamilyName()
	 * @model
	 * @generated
	 */
	String getFamilyName();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Customer#getFamilyName <em>Family Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Family Name</em>' attribute.
	 * @see #getFamilyName()
	 * @generated
	 */
	void setFamilyName(String value);

	/**
	 * Returns the value of the '<em><b>Telephone Nr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Telephone Nr</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Telephone Nr</em>' attribute.
	 * @see #setTelephoneNr(String)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getCustomer_TelephoneNr()
	 * @model
	 * @generated
	 */
	String getTelephoneNr();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Customer#getTelephoneNr <em>Telephone Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Telephone Nr</em>' attribute.
	 * @see #getTelephoneNr()
	 * @generated
	 */
	void setTelephoneNr(String value);

	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #setAddress(String)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getCustomer_Address()
	 * @model
	 * @generated
	 */
	String getAddress();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Customer#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' attribute.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

	/**
	 * Returns the value of the '<em><b>Hotel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hotel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hotel</em>' attribute.
	 * @see #setHotel(String)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getCustomer_Hotel()
	 * @model
	 * @generated
	 */
	String getHotel();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Customer#getHotel <em>Hotel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hotel</em>' attribute.
	 * @see #getHotel()
	 * @generated
	 */
	void setHotel(String value);

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
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getCustomer_Comments()
	 * @model
	 * @generated
	 */
	EList<String> getComments();

} // Customer
