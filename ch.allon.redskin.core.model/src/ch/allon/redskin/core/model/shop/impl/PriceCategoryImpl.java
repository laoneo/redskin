/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop.impl;

import ch.allon.redskin.core.model.shop.PriceCategory;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ShopPackage;

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
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Price Category</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ch.allon.redskin.core.model.shop.impl.PriceCategoryImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link ch.allon.redskin.core.model.shop.impl.PriceCategoryImpl#getProducts
 * <em>Products</em>}</li>
 * <li>{@link ch.allon.redskin.core.model.shop.impl.PriceCategoryImpl#getPrices
 * <em>Prices</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PriceCategoryImpl extends EObjectImpl implements PriceCategory {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProducts() <em>Products</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProducts()
	 * @generated
	 * @ordered
	 */
	protected EList<Product> products;

	/**
	 * The cached value of the '{@link #getPrices() <em>Prices</em>}' attribute
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPrices()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> prices;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PriceCategoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShopPackage.Literals.PRICE_CATEGORY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ShopPackage.PRICE_CATEGORY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Product> getProducts() {
		if (products == null) {
			products = new EObjectContainmentWithInverseEList<Product>(
					Product.class, this, ShopPackage.PRICE_CATEGORY__PRODUCTS,
					ShopPackage.PRODUCT__PRICE_CATEGORY);
		}
		return products;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Double> getPrices() {
		if (prices == null) {
			prices = new EDataTypeUniqueEList<Double>(Double.class, this,
					ShopPackage.PRICE_CATEGORY__PRICES);
		}
		return prices;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ShopPackage.PRICE_CATEGORY__PRODUCTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getProducts())
					.basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ShopPackage.PRICE_CATEGORY__PRODUCTS:
			return ((InternalEList<?>) getProducts()).basicRemove(otherEnd,
					msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ShopPackage.PRICE_CATEGORY__NAME:
			return getName();
		case ShopPackage.PRICE_CATEGORY__PRODUCTS:
			return getProducts();
		case ShopPackage.PRICE_CATEGORY__PRICES:
			return getPrices();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ShopPackage.PRICE_CATEGORY__NAME:
			setName((String) newValue);
			return;
		case ShopPackage.PRICE_CATEGORY__PRODUCTS:
			getProducts().clear();
			getProducts().addAll((Collection<? extends Product>) newValue);
			return;
		case ShopPackage.PRICE_CATEGORY__PRICES:
			getPrices().clear();
			getPrices().addAll((Collection<? extends Double>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ShopPackage.PRICE_CATEGORY__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ShopPackage.PRICE_CATEGORY__PRODUCTS:
			getProducts().clear();
			return;
		case ShopPackage.PRICE_CATEGORY__PRICES:
			getPrices().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ShopPackage.PRICE_CATEGORY__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case ShopPackage.PRICE_CATEGORY__PRODUCTS:
			return products != null && !products.isEmpty();
		case ShopPackage.PRICE_CATEGORY__PRICES:
			return prices != null && !prices.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(name);
//		result.append(" [");
//		for (int i = 0; i < prices.size(); i++) {
//			Double d = prices.get(i);
//			result.append(d);
//			if (i < prices.size() - 1)
//				result.append(',');
//		}
//		result.append(']');
		return result.toString();
	}

} // PriceCategoryImpl
