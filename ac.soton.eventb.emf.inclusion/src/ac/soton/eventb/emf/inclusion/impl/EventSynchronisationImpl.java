/*******************************************************************************
 * Copyright (c) 2017, 2021 University of Southampton.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     University of Southampton - initial API and implementation
 *
 * $Id$
 *******************************************************************************/
package ac.soton.eventb.emf.inclusion.impl;

import ac.soton.eventb.emf.inclusion.EventSynchronisation;
import ac.soton.eventb.emf.inclusion.InclusionPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eventb.emf.core.impl.AbstractExtensionImpl;

import org.eventb.emf.core.machine.Event;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Synchronisation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ac.soton.eventb.emf.inclusion.impl.EventSynchronisationImpl#getSynchronisedEvent <em>Synchronised Event</em>}</li>
 *   <li>{@link ac.soton.eventb.emf.inclusion.impl.EventSynchronisationImpl#getPrefix <em>Prefix</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventSynchronisationImpl extends AbstractExtensionImpl implements EventSynchronisation {
	/**
	 * The cached value of the '{@link #getSynchronisedEvent() <em>Synchronised Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynchronisedEvent()
	 * @generated
	 * @ordered
	 */
	protected Event synchronisedEvent;

	/**
	 * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFIX_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected String prefix = PREFIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getExtensionId() <em>Extension Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensionId()
	 * @generated CUSTOM
	 * @ordered
	 */
	protected static final String EXTENSION_ID_EDEFAULT = InclusionPackage.INCLUSION_EXTENSION_ID+".EventSynchronisation";

	/**
	 * The cached value of the '{@link #getExtensionId() <em>Extension Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensionId()
	 * @generated CUSTOM
	 * @ordered
	 */
	protected String extensionId = EXTENSION_ID_EDEFAULT+"."+EcoreUtil.generateUUID();
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventSynchronisationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InclusionPackage.Literals.EVENT_SYNCHRONISATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event getSynchronisedEvent() {
		if (synchronisedEvent != null && synchronisedEvent.eIsProxy()) {
			InternalEObject oldSynchronisedEvent = (InternalEObject)synchronisedEvent;
			synchronisedEvent = (Event)eResolveProxy(oldSynchronisedEvent);
			if (synchronisedEvent != oldSynchronisedEvent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InclusionPackage.EVENT_SYNCHRONISATION__SYNCHRONISED_EVENT, oldSynchronisedEvent, synchronisedEvent));
			}
		}
		return synchronisedEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event basicGetSynchronisedEvent() {
		return synchronisedEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynchronisedEvent(Event newSynchronisedEvent) {
		Event oldSynchronisedEvent = synchronisedEvent;
		synchronisedEvent = newSynchronisedEvent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InclusionPackage.EVENT_SYNCHRONISATION__SYNCHRONISED_EVENT, oldSynchronisedEvent, synchronisedEvent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrefix(String newPrefix) {
		String oldPrefix = prefix;
		prefix = newPrefix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InclusionPackage.EVENT_SYNCHRONISATION__PREFIX, oldPrefix, prefix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case InclusionPackage.EVENT_SYNCHRONISATION__SYNCHRONISED_EVENT:
				if (resolve) return getSynchronisedEvent();
				return basicGetSynchronisedEvent();
			case InclusionPackage.EVENT_SYNCHRONISATION__PREFIX:
				return getPrefix();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case InclusionPackage.EVENT_SYNCHRONISATION__SYNCHRONISED_EVENT:
				setSynchronisedEvent((Event)newValue);
				return;
			case InclusionPackage.EVENT_SYNCHRONISATION__PREFIX:
				setPrefix((String)newValue);
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
			case InclusionPackage.EVENT_SYNCHRONISATION__SYNCHRONISED_EVENT:
				setSynchronisedEvent((Event)null);
				return;
			case InclusionPackage.EVENT_SYNCHRONISATION__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
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
			case InclusionPackage.EVENT_SYNCHRONISATION__SYNCHRONISED_EVENT:
				return synchronisedEvent != null;
			case InclusionPackage.EVENT_SYNCHRONISATION__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
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
		result.append(" (prefix: ");
		result.append(prefix);
		result.append(')');
		return result.toString();
	}

} //EventSynchronisationImpl
