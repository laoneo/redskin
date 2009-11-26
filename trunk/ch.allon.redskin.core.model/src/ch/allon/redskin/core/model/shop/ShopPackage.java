/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see ch.allon.redskin.core.model.shop.ShopFactory
 * @model kind="package"
 * @generated
 */
public interface ShopPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "shop";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://shop";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "shop";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ShopPackage eINSTANCE = ch.allon.redskin.core.model.shop.impl.ShopPackageImpl.init();

	/**
	 * The meta object id for the '{@link ch.allon.redskin.core.model.shop.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ch.allon.redskin.core.model.shop.impl.ProductImpl
	 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NUMBER = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Product Category</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__PRODUCT_CATEGORY = 3;

	/**
	 * The feature id for the '<em><b>Price Category</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__PRICE_CATEGORY = 4;

	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link ch.allon.redskin.core.model.shop.impl.CustomerImpl <em>Customer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ch.allon.redskin.core.model.shop.impl.CustomerImpl
	 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getCustomer()
	 * @generated
	 */
	int CUSTOMER = 1;

	/**
	 * The feature id for the '<em><b>Surname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__SURNAME = 0;

	/**
	 * The feature id for the '<em><b>Family Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__FAMILY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Telephone Nr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__TELEPHONE_NR = 2;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__ADDRESS = 3;

	/**
	 * The feature id for the '<em><b>Hotel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__HOTEL = 4;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER__COMMENTS = 5;

	/**
	 * The number of structural features of the '<em>Customer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMER_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link ch.allon.redskin.core.model.shop.impl.ProductCategoryImpl <em>Product Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ch.allon.redskin.core.model.shop.impl.ProductCategoryImpl
	 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getProductCategory()
	 * @generated
	 */
	int PRODUCT_CATEGORY = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_CATEGORY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Products</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_CATEGORY__PRODUCTS = 1;

	/**
	 * The feature id for the '<em><b>Sub Categorys</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_CATEGORY__SUB_CATEGORYS = 2;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_CATEGORY__PARENT = 3;

	/**
	 * The number of structural features of the '<em>Product Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_CATEGORY_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link ch.allon.redskin.core.model.shop.impl.PriceCategoryImpl <em>Price Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ch.allon.redskin.core.model.shop.impl.PriceCategoryImpl
	 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getPriceCategory()
	 * @generated
	 */
	int PRICE_CATEGORY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE_CATEGORY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Prices</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE_CATEGORY__PRICES = 1;

	/**
	 * The number of structural features of the '<em>Price Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRICE_CATEGORY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link ch.allon.redskin.core.model.shop.impl.OrderImpl <em>Order</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ch.allon.redskin.core.model.shop.impl.OrderImpl
	 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getOrder()
	 * @generated
	 */
	int ORDER = 4;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDER__NUMBER = 0;

	/**
	 * The feature id for the '<em><b>Transactions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDER__TRANSACTIONS = 1;

	/**
	 * The feature id for the '<em><b>Customer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDER__CUSTOMER = 2;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDER__COMMENTS = 3;

	/**
	 * The number of structural features of the '<em>Order</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link ch.allon.redskin.core.model.shop.impl.TransactionImpl <em>Transaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ch.allon.redskin.core.model.shop.impl.TransactionImpl
	 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getTransaction()
	 * @generated
	 */
	int TRANSACTION = 5;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSACTION__NUMBER = 0;

	/**
	 * The feature id for the '<em><b>Order</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSACTION__ORDER = 1;

	/**
	 * The feature id for the '<em><b>Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSACTION__PRODUCT = 2;

	/**
	 * The feature id for the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSACTION__START_DATE = 3;

	/**
	 * The feature id for the '<em><b>End Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSACTION__END_DATE = 4;

	/**
	 * The feature id for the '<em><b>Price</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSACTION__PRICE = 5;

	/**
	 * The feature id for the '<em><b>Paid Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSACTION__PAID_DATE = 6;

	/**
	 * The number of structural features of the '<em>Transaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSACTION_FEATURE_COUNT = 7;


	/**
	 * Returns the meta object for class '{@link ch.allon.redskin.core.model.shop.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see ch.allon.redskin.core.model.shop.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Product#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see ch.allon.redskin.core.model.shop.Product#getName()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Name();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Product#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see ch.allon.redskin.core.model.shop.Product#getNumber()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Number();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Product#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see ch.allon.redskin.core.model.shop.Product#getDescription()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Description();

	/**
	 * Returns the meta object for the container reference '{@link ch.allon.redskin.core.model.shop.Product#getProductCategory <em>Product Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Product Category</em>'.
	 * @see ch.allon.redskin.core.model.shop.Product#getProductCategory()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_ProductCategory();

	/**
	 * Returns the meta object for the reference '{@link ch.allon.redskin.core.model.shop.Product#getPriceCategory <em>Price Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Price Category</em>'.
	 * @see ch.allon.redskin.core.model.shop.Product#getPriceCategory()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_PriceCategory();

	/**
	 * Returns the meta object for class '{@link ch.allon.redskin.core.model.shop.Customer <em>Customer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customer</em>'.
	 * @see ch.allon.redskin.core.model.shop.Customer
	 * @generated
	 */
	EClass getCustomer();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Customer#getSurname <em>Surname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Surname</em>'.
	 * @see ch.allon.redskin.core.model.shop.Customer#getSurname()
	 * @see #getCustomer()
	 * @generated
	 */
	EAttribute getCustomer_Surname();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Customer#getFamilyName <em>Family Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Family Name</em>'.
	 * @see ch.allon.redskin.core.model.shop.Customer#getFamilyName()
	 * @see #getCustomer()
	 * @generated
	 */
	EAttribute getCustomer_FamilyName();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Customer#getTelephoneNr <em>Telephone Nr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Telephone Nr</em>'.
	 * @see ch.allon.redskin.core.model.shop.Customer#getTelephoneNr()
	 * @see #getCustomer()
	 * @generated
	 */
	EAttribute getCustomer_TelephoneNr();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Customer#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see ch.allon.redskin.core.model.shop.Customer#getAddress()
	 * @see #getCustomer()
	 * @generated
	 */
	EAttribute getCustomer_Address();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Customer#getHotel <em>Hotel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hotel</em>'.
	 * @see ch.allon.redskin.core.model.shop.Customer#getHotel()
	 * @see #getCustomer()
	 * @generated
	 */
	EAttribute getCustomer_Hotel();

	/**
	 * Returns the meta object for the attribute list '{@link ch.allon.redskin.core.model.shop.Customer#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Comments</em>'.
	 * @see ch.allon.redskin.core.model.shop.Customer#getComments()
	 * @see #getCustomer()
	 * @generated
	 */
	EAttribute getCustomer_Comments();

	/**
	 * Returns the meta object for class '{@link ch.allon.redskin.core.model.shop.ProductCategory <em>Product Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Category</em>'.
	 * @see ch.allon.redskin.core.model.shop.ProductCategory
	 * @generated
	 */
	EClass getProductCategory();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.ProductCategory#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see ch.allon.redskin.core.model.shop.ProductCategory#getName()
	 * @see #getProductCategory()
	 * @generated
	 */
	EAttribute getProductCategory_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link ch.allon.redskin.core.model.shop.ProductCategory#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Products</em>'.
	 * @see ch.allon.redskin.core.model.shop.ProductCategory#getProducts()
	 * @see #getProductCategory()
	 * @generated
	 */
	EReference getProductCategory_Products();

	/**
	 * Returns the meta object for the containment reference list '{@link ch.allon.redskin.core.model.shop.ProductCategory#getSubCategorys <em>Sub Categorys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Categorys</em>'.
	 * @see ch.allon.redskin.core.model.shop.ProductCategory#getSubCategorys()
	 * @see #getProductCategory()
	 * @generated
	 */
	EReference getProductCategory_SubCategorys();

	/**
	 * Returns the meta object for the container reference '{@link ch.allon.redskin.core.model.shop.ProductCategory#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see ch.allon.redskin.core.model.shop.ProductCategory#getParent()
	 * @see #getProductCategory()
	 * @generated
	 */
	EReference getProductCategory_Parent();

	/**
	 * Returns the meta object for class '{@link ch.allon.redskin.core.model.shop.PriceCategory <em>Price Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Price Category</em>'.
	 * @see ch.allon.redskin.core.model.shop.PriceCategory
	 * @generated
	 */
	EClass getPriceCategory();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.PriceCategory#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see ch.allon.redskin.core.model.shop.PriceCategory#getName()
	 * @see #getPriceCategory()
	 * @generated
	 */
	EAttribute getPriceCategory_Name();

	/**
	 * Returns the meta object for the attribute list '{@link ch.allon.redskin.core.model.shop.PriceCategory#getPrices <em>Prices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Prices</em>'.
	 * @see ch.allon.redskin.core.model.shop.PriceCategory#getPrices()
	 * @see #getPriceCategory()
	 * @generated
	 */
	EAttribute getPriceCategory_Prices();

	/**
	 * Returns the meta object for class '{@link ch.allon.redskin.core.model.shop.Order <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Order</em>'.
	 * @see ch.allon.redskin.core.model.shop.Order
	 * @generated
	 */
	EClass getOrder();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Order#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see ch.allon.redskin.core.model.shop.Order#getNumber()
	 * @see #getOrder()
	 * @generated
	 */
	EAttribute getOrder_Number();

	/**
	 * Returns the meta object for the containment reference list '{@link ch.allon.redskin.core.model.shop.Order#getTransactions <em>Transactions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transactions</em>'.
	 * @see ch.allon.redskin.core.model.shop.Order#getTransactions()
	 * @see #getOrder()
	 * @generated
	 */
	EReference getOrder_Transactions();

	/**
	 * Returns the meta object for the reference '{@link ch.allon.redskin.core.model.shop.Order#getCustomer <em>Customer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Customer</em>'.
	 * @see ch.allon.redskin.core.model.shop.Order#getCustomer()
	 * @see #getOrder()
	 * @generated
	 */
	EReference getOrder_Customer();

	/**
	 * Returns the meta object for the attribute list '{@link ch.allon.redskin.core.model.shop.Order#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Comments</em>'.
	 * @see ch.allon.redskin.core.model.shop.Order#getComments()
	 * @see #getOrder()
	 * @generated
	 */
	EAttribute getOrder_Comments();

	/**
	 * Returns the meta object for class '{@link ch.allon.redskin.core.model.shop.Transaction <em>Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transaction</em>'.
	 * @see ch.allon.redskin.core.model.shop.Transaction
	 * @generated
	 */
	EClass getTransaction();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Transaction#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see ch.allon.redskin.core.model.shop.Transaction#getNumber()
	 * @see #getTransaction()
	 * @generated
	 */
	EAttribute getTransaction_Number();

	/**
	 * Returns the meta object for the container reference '{@link ch.allon.redskin.core.model.shop.Transaction#getOrder <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Order</em>'.
	 * @see ch.allon.redskin.core.model.shop.Transaction#getOrder()
	 * @see #getTransaction()
	 * @generated
	 */
	EReference getTransaction_Order();

	/**
	 * Returns the meta object for the reference '{@link ch.allon.redskin.core.model.shop.Transaction#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Product</em>'.
	 * @see ch.allon.redskin.core.model.shop.Transaction#getProduct()
	 * @see #getTransaction()
	 * @generated
	 */
	EReference getTransaction_Product();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Transaction#getStartDate <em>Start Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Date</em>'.
	 * @see ch.allon.redskin.core.model.shop.Transaction#getStartDate()
	 * @see #getTransaction()
	 * @generated
	 */
	EAttribute getTransaction_StartDate();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Transaction#getEndDate <em>End Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Date</em>'.
	 * @see ch.allon.redskin.core.model.shop.Transaction#getEndDate()
	 * @see #getTransaction()
	 * @generated
	 */
	EAttribute getTransaction_EndDate();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Transaction#getPrice <em>Price</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Price</em>'.
	 * @see ch.allon.redskin.core.model.shop.Transaction#getPrice()
	 * @see #getTransaction()
	 * @generated
	 */
	EAttribute getTransaction_Price();

	/**
	 * Returns the meta object for the attribute '{@link ch.allon.redskin.core.model.shop.Transaction#getPaidDate <em>Paid Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Paid Date</em>'.
	 * @see ch.allon.redskin.core.model.shop.Transaction#getPaidDate()
	 * @see #getTransaction()
	 * @generated
	 */
	EAttribute getTransaction_PaidDate();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ShopFactory getShopFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link ch.allon.redskin.core.model.shop.impl.ProductImpl <em>Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ch.allon.redskin.core.model.shop.impl.ProductImpl
		 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT__NAME = eINSTANCE.getProduct_Name();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT__NUMBER = eINSTANCE.getProduct_Number();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT__DESCRIPTION = eINSTANCE.getProduct_Description();

		/**
		 * The meta object literal for the '<em><b>Product Category</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__PRODUCT_CATEGORY = eINSTANCE.getProduct_ProductCategory();

		/**
		 * The meta object literal for the '<em><b>Price Category</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__PRICE_CATEGORY = eINSTANCE.getProduct_PriceCategory();

		/**
		 * The meta object literal for the '{@link ch.allon.redskin.core.model.shop.impl.CustomerImpl <em>Customer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ch.allon.redskin.core.model.shop.impl.CustomerImpl
		 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getCustomer()
		 * @generated
		 */
		EClass CUSTOMER = eINSTANCE.getCustomer();

		/**
		 * The meta object literal for the '<em><b>Surname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER__SURNAME = eINSTANCE.getCustomer_Surname();

		/**
		 * The meta object literal for the '<em><b>Family Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER__FAMILY_NAME = eINSTANCE.getCustomer_FamilyName();

		/**
		 * The meta object literal for the '<em><b>Telephone Nr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER__TELEPHONE_NR = eINSTANCE.getCustomer_TelephoneNr();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER__ADDRESS = eINSTANCE.getCustomer_Address();

		/**
		 * The meta object literal for the '<em><b>Hotel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER__HOTEL = eINSTANCE.getCustomer_Hotel();

		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMER__COMMENTS = eINSTANCE.getCustomer_Comments();

		/**
		 * The meta object literal for the '{@link ch.allon.redskin.core.model.shop.impl.ProductCategoryImpl <em>Product Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ch.allon.redskin.core.model.shop.impl.ProductCategoryImpl
		 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getProductCategory()
		 * @generated
		 */
		EClass PRODUCT_CATEGORY = eINSTANCE.getProductCategory();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_CATEGORY__NAME = eINSTANCE.getProductCategory_Name();

		/**
		 * The meta object literal for the '<em><b>Products</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_CATEGORY__PRODUCTS = eINSTANCE.getProductCategory_Products();

		/**
		 * The meta object literal for the '<em><b>Sub Categorys</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_CATEGORY__SUB_CATEGORYS = eINSTANCE.getProductCategory_SubCategorys();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_CATEGORY__PARENT = eINSTANCE.getProductCategory_Parent();

		/**
		 * The meta object literal for the '{@link ch.allon.redskin.core.model.shop.impl.PriceCategoryImpl <em>Price Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ch.allon.redskin.core.model.shop.impl.PriceCategoryImpl
		 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getPriceCategory()
		 * @generated
		 */
		EClass PRICE_CATEGORY = eINSTANCE.getPriceCategory();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRICE_CATEGORY__NAME = eINSTANCE.getPriceCategory_Name();

		/**
		 * The meta object literal for the '<em><b>Prices</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRICE_CATEGORY__PRICES = eINSTANCE.getPriceCategory_Prices();

		/**
		 * The meta object literal for the '{@link ch.allon.redskin.core.model.shop.impl.OrderImpl <em>Order</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ch.allon.redskin.core.model.shop.impl.OrderImpl
		 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getOrder()
		 * @generated
		 */
		EClass ORDER = eINSTANCE.getOrder();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORDER__NUMBER = eINSTANCE.getOrder_Number();

		/**
		 * The meta object literal for the '<em><b>Transactions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORDER__TRANSACTIONS = eINSTANCE.getOrder_Transactions();

		/**
		 * The meta object literal for the '<em><b>Customer</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORDER__CUSTOMER = eINSTANCE.getOrder_Customer();

		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORDER__COMMENTS = eINSTANCE.getOrder_Comments();

		/**
		 * The meta object literal for the '{@link ch.allon.redskin.core.model.shop.impl.TransactionImpl <em>Transaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ch.allon.redskin.core.model.shop.impl.TransactionImpl
		 * @see ch.allon.redskin.core.model.shop.impl.ShopPackageImpl#getTransaction()
		 * @generated
		 */
		EClass TRANSACTION = eINSTANCE.getTransaction();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSACTION__NUMBER = eINSTANCE.getTransaction_Number();

		/**
		 * The meta object literal for the '<em><b>Order</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSACTION__ORDER = eINSTANCE.getTransaction_Order();

		/**
		 * The meta object literal for the '<em><b>Product</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSACTION__PRODUCT = eINSTANCE.getTransaction_Product();

		/**
		 * The meta object literal for the '<em><b>Start Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSACTION__START_DATE = eINSTANCE.getTransaction_StartDate();

		/**
		 * The meta object literal for the '<em><b>End Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSACTION__END_DATE = eINSTANCE.getTransaction_EndDate();

		/**
		 * The meta object literal for the '<em><b>Price</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSACTION__PRICE = eINSTANCE.getTransaction_Price();

		/**
		 * The meta object literal for the '<em><b>Paid Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSACTION__PAID_DATE = eINSTANCE.getTransaction_PaidDate();

	}

} //ShopPackage
