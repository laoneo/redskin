/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop.util;

import ch.allon.redskin.core.model.shop.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see ch.allon.redskin.core.model.shop.ShopPackage
 * @generated
 */
public class ShopAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ShopPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShopAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ShopPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShopSwitch<Adapter> modelSwitch =
		new ShopSwitch<Adapter>() {
			@Override
			public Adapter caseProduct(Product object) {
				return createProductAdapter();
			}
			@Override
			public Adapter caseCustomer(Customer object) {
				return createCustomerAdapter();
			}
			@Override
			public Adapter caseProductCategory(ProductCategory object) {
				return createProductCategoryAdapter();
			}
			@Override
			public Adapter casePriceCategory(PriceCategory object) {
				return createPriceCategoryAdapter();
			}
			@Override
			public Adapter caseOrder(Order object) {
				return createOrderAdapter();
			}
			@Override
			public Adapter caseTransaction(Transaction object) {
				return createTransactionAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link ch.allon.redskin.core.model.shop.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ch.allon.redskin.core.model.shop.Product
	 * @generated
	 */
	public Adapter createProductAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link ch.allon.redskin.core.model.shop.Customer <em>Customer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ch.allon.redskin.core.model.shop.Customer
	 * @generated
	 */
	public Adapter createCustomerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link ch.allon.redskin.core.model.shop.ProductCategory <em>Product Category</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ch.allon.redskin.core.model.shop.ProductCategory
	 * @generated
	 */
	public Adapter createProductCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link ch.allon.redskin.core.model.shop.PriceCategory <em>Price Category</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ch.allon.redskin.core.model.shop.PriceCategory
	 * @generated
	 */
	public Adapter createPriceCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link ch.allon.redskin.core.model.shop.Order <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ch.allon.redskin.core.model.shop.Order
	 * @generated
	 */
	public Adapter createOrderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link ch.allon.redskin.core.model.shop.Transaction <em>Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ch.allon.redskin.core.model.shop.Transaction
	 * @generated
	 */
	public Adapter createTransactionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ShopAdapterFactory
