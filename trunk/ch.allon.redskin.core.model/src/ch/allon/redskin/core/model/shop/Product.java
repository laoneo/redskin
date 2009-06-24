/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ch.allon.redskin.core.model.shop.Product#getName <em>Name</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Product#getNumber <em>Number</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Product#getDescription <em>Description</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Product#getRentedDays <em>Rented Days</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Product#getProductCategory <em>Product Category</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.Product#getPriceCategory <em>Price Category</em>}</li>
 * </ul>
 * </p>
 *
 * @see ch.allon.redskin.core.model.shop.ShopPackage#getProduct()
 * @model
 * @generated
 */
public interface Product extends EObject {
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
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getProduct_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Product#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(Integer)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getProduct_Number()
	 * @model
	 * @generated
	 */
	Integer getNumber();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Product#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(Integer value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getProduct_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Product#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Rented Days</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rented Days</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rented Days</em>' attribute.
	 * @see #setRentedDays(Integer)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getProduct_RentedDays()
	 * @model
	 * @generated
	 */
	Integer getRentedDays();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Product#getRentedDays <em>Rented Days</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rented Days</em>' attribute.
	 * @see #getRentedDays()
	 * @generated
	 */
	void setRentedDays(Integer value);

	/**
	 * Returns the value of the '<em><b>Product Category</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ch.allon.redskin.core.model.shop.ProductCategory#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Category</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Category</em>' container reference.
	 * @see #setProductCategory(ProductCategory)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getProduct_ProductCategory()
	 * @see ch.allon.redskin.core.model.shop.ProductCategory#getProducts
	 * @model opposite="products" transient="false"
	 * @generated
	 */
	ProductCategory getProductCategory();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Product#getProductCategory <em>Product Category</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Category</em>' container reference.
	 * @see #getProductCategory()
	 * @generated
	 */
	void setProductCategory(ProductCategory value);

	/**
	 * Returns the value of the '<em><b>Price Category</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Price Category</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Price Category</em>' reference.
	 * @see #setPriceCategory(PriceCategory)
	 * @see ch.allon.redskin.core.model.shop.ShopPackage#getProduct_PriceCategory()
	 * @model
	 * @generated
	 */
	PriceCategory getPriceCategory();

	/**
	 * Sets the value of the '{@link ch.allon.redskin.core.model.shop.Product#getPriceCategory <em>Price Category</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Price Category</em>' reference.
	 * @see #getPriceCategory()
	 * @generated
	 */
	void setPriceCategory(PriceCategory value);

} // Product
