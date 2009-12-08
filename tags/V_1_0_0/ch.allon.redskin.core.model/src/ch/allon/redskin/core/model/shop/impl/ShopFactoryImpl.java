/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop.impl;

import ch.allon.redskin.core.model.shop.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ShopFactoryImpl extends EFactoryImpl implements ShopFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ShopFactory init() {
		try {
			ShopFactory theShopFactory = (ShopFactory)EPackage.Registry.INSTANCE.getEFactory("http://shop"); 
			if (theShopFactory != null) {
				return theShopFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ShopFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShopFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ShopPackage.PRODUCT: return createProduct();
			case ShopPackage.CUSTOMER: return createCustomer();
			case ShopPackage.PRODUCT_CATEGORY: return createProductCategory();
			case ShopPackage.PRICE_CATEGORY: return createPriceCategory();
			case ShopPackage.ORDER: return createOrder();
			case ShopPackage.TRANSACTION: return createTransaction();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product createProduct() {
		ProductImpl product = new ProductImpl();
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Customer createCustomer() {
		CustomerImpl customer = new CustomerImpl();
		return customer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductCategory createProductCategory() {
		ProductCategoryImpl productCategory = new ProductCategoryImpl();
		return productCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PriceCategory createPriceCategory() {
		PriceCategoryImpl priceCategory = new PriceCategoryImpl();
		return priceCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Order createOrder() {
		OrderImpl order = new OrderImpl();
		return order;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transaction createTransaction() {
		TransactionImpl transaction = new TransactionImpl();
		return transaction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShopPackage getShopPackage() {
		return (ShopPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ShopPackage getPackage() {
		return ShopPackage.eINSTANCE;
	}

} //ShopFactoryImpl
