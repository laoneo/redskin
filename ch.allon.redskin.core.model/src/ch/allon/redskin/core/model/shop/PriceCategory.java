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
 * A representation of the model object '<em><b>Price Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ch.allon.redskin.core.model.shop.PriceCategory#getName <em>Name</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.PriceCategory#getProducts <em>Products</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.PriceCategory#getPrices <em>Prices</em>}</li>
 * </ul>
 * </p>
 *
 * @see ch.allon.redskin.core.model.shop.ShopPackage#getPriceCategory()
 * @model
 * @generated
 */
public interface PriceCategory extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getPriceCategory_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.PriceCategory#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Products</b></em>' containment reference list.
	 * The list contents are of type {@link ch.allon.redskin.core.model.shop.Product}.
	 * It is bidirectional and its opposite is '{@link ch.allon.redskin.core.model.shop.Product#getPriceCategory <em>Price Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Products</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Products</em>' containment reference list.
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getPriceCategory_Products()
	 * @see ch.allon.redskin.core.model.shop.Product#getPriceCategory
	 * @model opposite="priceCategory" containment="true"
	 * @generated
	 */
	EList<Product> getProducts();

	/**
	 * Returns the value of the '<em><b>Prices</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prices</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prices</em>' attribute list.
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getPriceCategory_Prices()
	 * @model
	 * @generated
	 */
	EList<Double> getPrices();

} // PriceCategory
