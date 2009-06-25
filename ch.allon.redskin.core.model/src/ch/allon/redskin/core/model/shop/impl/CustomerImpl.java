/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ch.allon.redskin.core.model.shop.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import ch.allon.redskin.core.model.shop.Customer;
import ch.allon.redskin.core.model.shop.ShopPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Customer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.CustomerImpl#getSurname <em>Surname</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.CustomerImpl#getFamilyName <em>Family Name</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.CustomerImpl#getTelephoneNr <em>Telephone Nr</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.CustomerImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.CustomerImpl#getHotel <em>Hotel</em>}</li>
 *   <li>{@link ch.allon.redskin.core.model.shop.impl.CustomerImpl#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomerImpl extends EObjectImpl implements Customer {
	/**
	 * The default value of the '{@link #getSurname() <em>Surname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSurname()
	 * @generated
	 * @ordered
	 */
	protected static final String SURNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSurname() <em>Surname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSurname()
	 * @generated
	 * @ordered
	 */
	protected String surname = SURNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFamilyName() <em>Family Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFamilyName()
	 * @generated
	 * @ordered
	 */
	protected static final String FAMILY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFamilyName() <em>Family Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFamilyName()
	 * @generated
	 * @ordered
	 */
	protected String familyName = FAMILY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTelephoneNr() <em>Telephone Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTelephoneNr()
	 * @generated
	 * @ordered
	 */
	protected static final String TELEPHONE_NR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTelephoneNr() <em>Telephone Nr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTelephoneNr()
	 * @generated
	 * @ordered
	 */
	protected String telephoneNr = TELEPHONE_NR_EDEFAULT;

	/**
	 * The default value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected String address = ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getHotel() <em>Hotel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHotel()
	 * @generated
	 * @ordered
	 */
	protected static final String HOTEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHotel() <em>Hotel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHotel()
	 * @generated
	 * @ordered
	 */
	protected String hotel = HOTEL_EDEFAULT;

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
	protected CustomerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShopPackage.Literals.CUSTOMER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSurname(String newSurname) {
		String oldSurname = surname;
		surname = newSurname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.CUSTOMER__SURNAME, oldSurname, surname));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFamilyName(String newFamilyName) {
		String oldFamilyName = familyName;
		familyName = newFamilyName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.CUSTOMER__FAMILY_NAME, oldFamilyName, familyName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTelephoneNr() {
		return telephoneNr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTelephoneNr(String newTelephoneNr) {
		String oldTelephoneNr = telephoneNr;
		telephoneNr = newTelephoneNr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.CUSTOMER__TELEPHONE_NR, oldTelephoneNr, telephoneNr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddress(String newAddress) {
		String oldAddress = address;
		address = newAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.CUSTOMER__ADDRESS, oldAddress, address));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHotel() {
		return hotel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHotel(String newHotel) {
		String oldHotel = hotel;
		hotel = newHotel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShopPackage.CUSTOMER__HOTEL, oldHotel, hotel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getComments() {
		if (comments == null) {
			comments = new EDataTypeUniqueEList<String>(String.class, this, ShopPackage.CUSTOMER__COMMENTS);
		}
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ShopPackage.CUSTOMER__SURNAME:
				return getSurname();
			case ShopPackage.CUSTOMER__FAMILY_NAME:
				return getFamilyName();
			case ShopPackage.CUSTOMER__TELEPHONE_NR:
				return getTelephoneNr();
			case ShopPackage.CUSTOMER__ADDRESS:
				return getAddress();
			case ShopPackage.CUSTOMER__HOTEL:
				return getHotel();
			case ShopPackage.CUSTOMER__COMMENTS:
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
			case ShopPackage.CUSTOMER__SURNAME:
				setSurname((String)newValue);
				return;
			case ShopPackage.CUSTOMER__FAMILY_NAME:
				setFamilyName((String)newValue);
				return;
			case ShopPackage.CUSTOMER__TELEPHONE_NR:
				setTelephoneNr((String)newValue);
				return;
			case ShopPackage.CUSTOMER__ADDRESS:
				setAddress((String)newValue);
				return;
			case ShopPackage.CUSTOMER__HOTEL:
				setHotel((String)newValue);
				return;
			case ShopPackage.CUSTOMER__COMMENTS:
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
			case ShopPackage.CUSTOMER__SURNAME:
				setSurname(SURNAME_EDEFAULT);
				return;
			case ShopPackage.CUSTOMER__FAMILY_NAME:
				setFamilyName(FAMILY_NAME_EDEFAULT);
				return;
			case ShopPackage.CUSTOMER__TELEPHONE_NR:
				setTelephoneNr(TELEPHONE_NR_EDEFAULT);
				return;
			case ShopPackage.CUSTOMER__ADDRESS:
				setAddress(ADDRESS_EDEFAULT);
				return;
			case ShopPackage.CUSTOMER__HOTEL:
				setHotel(HOTEL_EDEFAULT);
				return;
			case ShopPackage.CUSTOMER__COMMENTS:
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
			case ShopPackage.CUSTOMER__SURNAME:
				return SURNAME_EDEFAULT == null ? surname != null : !SURNAME_EDEFAULT.equals(surname);
			case ShopPackage.CUSTOMER__FAMILY_NAME:
				return FAMILY_NAME_EDEFAULT == null ? familyName != null : !FAMILY_NAME_EDEFAULT.equals(familyName);
			case ShopPackage.CUSTOMER__TELEPHONE_NR:
				return TELEPHONE_NR_EDEFAULT == null ? telephoneNr != null : !TELEPHONE_NR_EDEFAULT.equals(telephoneNr);
			case ShopPackage.CUSTOMER__ADDRESS:
				return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
			case ShopPackage.CUSTOMER__HOTEL:
				return HOTEL_EDEFAULT == null ? hotel != null : !HOTEL_EDEFAULT.equals(hotel);
			case ShopPackage.CUSTOMER__COMMENTS:
				return comments != null && !comments.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer();
		result.append(familyName);
		result.append(" ");
		result.append(surname);
		return result.toString();
	}

} //CustomerImpl
