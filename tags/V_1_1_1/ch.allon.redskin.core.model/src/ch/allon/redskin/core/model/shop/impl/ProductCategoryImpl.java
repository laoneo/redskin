/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop.impl;

import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.ProductCategoryImpl#getName <em>Name</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.ProductCategoryImpl#getProducts <em>Products</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.ProductCategoryImpl#getSubCategorys <em>Sub Categorys</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.ProductCategoryImpl#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProductCategoryImpl extends EObjectImpl implements ProductCategory {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProducts() <em>Products</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProducts()
	 * @generated
	 * @ordered
	 */
	protected EList<Product> products;

	/**
	 * The cached value of the '{@link #getSubCategorys() <em>Sub Categorys</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubCategorys()
	 * @generated
	 * @ordered
	 */
	protected EList<ProductCategory> subCategorys;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductCategoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShopPackage.Literals.PRODUCT_CATEGORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.PRODUCT_CATEGORY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Product> getProducts() {
		if (products == null) {
			products = new EObjectContainmentWithInverseEList<Product>(Product.class, this, ShopPackage.PRODUCT_CATEGORY__PRODUCTS, ShopPackage.PRODUCT__PRODUCT_CATEGORY);
		}
		return products;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProductCategory> getSubCategorys() {
		if (subCategorys == null) {
			subCategorys = new EObjectContainmentWithInverseEList<ProductCategory>(ProductCategory.class, this, ShopPackage.PRODUCT_CATEGORY__SUB_CATEGORYS, ShopPackage.PRODUCT_CATEGORY__PARENT);
		}
		return subCategorys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductCategory getParent() {
		if (eContainerFeatureID() != ShopPackage.PRODUCT_CATEGORY__PARENT) return null;
		return (ProductCategory)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(ProductCategory newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, ShopPackage.PRODUCT_CATEGORY__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(ProductCategory newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != ShopPackage.PRODUCT_CATEGORY__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, ShopPackage.PRODUCT_CATEGORY__SUB_CATEGORYS, ProductCategory.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.PRODUCT_CATEGORY__PARENT, newParent, newParent));
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
			case ShopPackage.PRODUCT_CATEGORY__PRODUCTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProducts()).basicAdd(otherEnd, msgs);
			case ShopPackage.PRODUCT_CATEGORY__SUB_CATEGORYS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubCategorys()).basicAdd(otherEnd, msgs);
			case ShopPackage.PRODUCT_CATEGORY__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((ProductCategory)otherEnd, msgs);
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
			case ShopPackage.PRODUCT_CATEGORY__PRODUCTS:
				return ((InternalEList<?>)getProducts()).basicRemove(otherEnd, msgs);
			case ShopPackage.PRODUCT_CATEGORY__SUB_CATEGORYS:
				return ((InternalEList<?>)getSubCategorys()).basicRemove(otherEnd, msgs);
			case ShopPackage.PRODUCT_CATEGORY__PARENT:
				return basicSetParent(null, msgs);
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
			case ShopPackage.PRODUCT_CATEGORY__PARENT:
				return eInternalContainer().eInverseRemove(this, ShopPackage.PRODUCT_CATEGORY__SUB_CATEGORYS, ProductCategory.class, msgs);
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
			case ShopPackage.PRODUCT_CATEGORY__NAME:
				return getName();
			case ShopPackage.PRODUCT_CATEGORY__PRODUCTS:
				return getProducts();
			case ShopPackage.PRODUCT_CATEGORY__SUB_CATEGORYS:
				return getSubCategorys();
			case ShopPackage.PRODUCT_CATEGORY__PARENT:
				return getParent();
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
			case ShopPackage.PRODUCT_CATEGORY__NAME:
				setName((String)newValue);
				return;
			case ShopPackage.PRODUCT_CATEGORY__PRODUCTS:
				getProducts().clear();
				getProducts().addAll((Collection<? extends Product>)newValue);
				return;
			case ShopPackage.PRODUCT_CATEGORY__SUB_CATEGORYS:
				getSubCategorys().clear();
				getSubCategorys().addAll((Collection<? extends ProductCategory>)newValue);
				return;
			case ShopPackage.PRODUCT_CATEGORY__PARENT:
				setParent((ProductCategory)newValue);
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
			case ShopPackage.PRODUCT_CATEGORY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ShopPackage.PRODUCT_CATEGORY__PRODUCTS:
				getProducts().clear();
				return;
			case ShopPackage.PRODUCT_CATEGORY__SUB_CATEGORYS:
				getSubCategorys().clear();
				return;
			case ShopPackage.PRODUCT_CATEGORY__PARENT:
				setParent((ProductCategory)null);
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
			case ShopPackage.PRODUCT_CATEGORY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ShopPackage.PRODUCT_CATEGORY__PRODUCTS:
				return products != null && !products.isEmpty();
			case ShopPackage.PRODUCT_CATEGORY__SUB_CATEGORYS:
				return subCategorys != null && !subCategorys.isEmpty();
			case ShopPackage.PRODUCT_CATEGORY__PARENT:
				return getParent() != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ProductCategoryImpl
